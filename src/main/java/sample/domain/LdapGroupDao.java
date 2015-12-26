package sample.domain;

public interface LdapGroupDao {

	public void addMemberToGroup(String groupName, User user);
	
}
