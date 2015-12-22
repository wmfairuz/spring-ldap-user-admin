package sample.domain;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepoImpl implements GroupRepoExtension, BaseLdapNameAware {
    private final static LdapName ADMIN_USER = LdapUtils.newLdapName("cn=administrators,ou=System,ou=IT,ou=Departments");

    private LdapTemplate ldapTemplate;
    private LdapName baseLdapPath;

    @Autowired
    public GroupRepoImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }

    @Override
    public List<String> getAllGroupNames() {
        LdapQuery query = query().attributes("cn")
                .where("objectclass").is("groupOfNames");

        return ldapTemplate.search(query, new AttributesMapper<String>() {
            @Override
            public String mapFromAttributes(Attributes attributes) throws NamingException {
                return (String) attributes.get("cn").get();
            }
        });
    }

    @Override
    public void create(Group group) {
        // A groupOfNames cannot be empty - add a system entry to all new groups.
        group.addMember(LdapUtils.prepend(ADMIN_USER, baseLdapPath));
        ldapTemplate.create(group);
    }

}