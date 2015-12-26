package sample.domain;

import javax.naming.Name;
import javax.naming.ldap.LdapName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

@Component
public class LdapGroupDaoImpl implements LdapGroupDao, BaseLdapNameAware {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier(value = "ldapTemplate")
	private LdapTemplate ldapTemplate;
	
	private LdapName baseLdapPath;
	
	@Override
	public void setBaseLdapPath(LdapName baseLdapPath) {
		this.baseLdapPath = baseLdapPath;
	}

	private Name buildGroupDn(String groupName) {
		return LdapNameBuilder.newInstance()
				.add("ou=groups")
				.add("cn", groupName).build();
	}

	private Name buildUserDn(String uid) {
		return LdapNameBuilder.newInstance(baseLdapPath)
				.add("ou", "people")
				.add("uid", uid)
				.build();
	}

	public void addMemberToGroup(String groupName, User user) {
		Name groupDn = buildGroupDn(groupName);
		Name userDn = buildUserDn(user.getUid());

		DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
		ctx.addAttributeValue("uniqueMember", userDn);
		ldapTemplate.modifyAttributes(ctx);		
	}
	
	public void removeMemberFromGroup(String groupName, User user) {
		Name groupDn = buildGroupDn(groupName);
		Name userDn = buildUserDn(user.getUid());

		DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
		ctx.removeAttributeValue("uniqueMember", userDn);
		ldapTemplate.modifyAttributes(ctx);		
	}

	
}
