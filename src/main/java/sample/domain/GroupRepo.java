package sample.domain;

import java.util.Collection;

import javax.naming.Name;

import org.springframework.ldap.repository.LdapRepository;
import org.springframework.ldap.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends LdapRepository<Group>, GroupRepoExtension {
    public final static String USER_GROUP = "ROLE_USER";

    Group findByName(String groupName);

    @Query("(member={0})")
    Collection<Group> findByMember(Name member);
}
