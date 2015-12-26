package sample.domain;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LdapUserDaoImpl implements LdapUserDao<LdapUser> {
	
	@Value("${amba.ldap.contextSource.base:dc=amba,dc=online}")
	private String BASE_DN;

	@Autowired
	@Qualifier(value = "ldapTemplate")
	private LdapTemplate ldapTemplate;
	
	@Autowired
	@Qualifier(value = "ldapShaPasswordEncoder")
	private LdapShaPasswordEncoder ldapShaPasswordEncoder;

	private Name buildDn(User p) {
		return LdapNameBuilder.newInstance()
				.add("ou", "people")
				.add("uid", p.getUid())
				.build();
	}
	
	private class PersonAttributesMapper implements AttributesMapper<User> {
		public User mapFromAttributes(Attributes attrs) throws NamingException {
			User person = new User();
			person.setFullName((String)attrs.get("cn").get());
			person.setLastName((String)attrs.get("sn").get());
			person.setUid((String)attrs.get("uid").get());
			return person;
		}
	}

	private Attributes buildAttributes(User user) {
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("person");
		ocattr.add("inetOrgPerson");
		ocattr.add("organizationalPerson");
		attrs.put(ocattr);
		attrs.put("cn", user.getFullName());
		attrs.put("sn", user.getLastName());
		attrs.put("userPassword", ldapShaPasswordEncoder.encodePassword(user.getPassword(), null));
		return attrs;
	}

	public List<String> getAllPersonNames() {
		return ldapTemplate.search(
				query().where("objectclass").is("person"),
				new AttributesMapper<String>() {
					public String mapFromAttributes(Attributes attrs)
							throws NamingException {
						return (String) attrs.get("cn").get();
					}
				});
	}

	public List<User> getAllPersons() {
		return ldapTemplate.search(query()
				.where("objectclass").is("person"), new PersonAttributesMapper());
	}

	public User findUserByString(String dn) {
		return ldapTemplate.lookup(dn, new PersonAttributesMapper());
	}
	
	public User findUser(User user) {
		return ldapTemplate.lookup(buildDn(user), new PersonAttributesMapper());
	}
	
	public void create(User user) throws NameAlreadyBoundException {
		Name dn = buildDn(user);
		
		ldapTemplate.bind(dn, null, buildAttributes(user));
	}
	
	public void delete(User user) throws NameNotFoundException {
		Name dn = buildDn(user);
		
		ldapTemplate.unbind(dn);
	}


}