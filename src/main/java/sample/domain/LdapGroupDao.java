package sample.domain;

public interface LdapGroupDao {

	public void addMemberToGroup(String groupName, User user);
	
	public void removeMemberFromGroup(String groupName, User user);
	
}
