package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.domain.LdapGroupDaoImpl;
import sample.domain.User;

@Component
public class LdapGroupService {

	@Autowired
	private LdapGroupDaoImpl ldapGroupDao;
	
	public void addMemberToGroup(String groupName, User user) {
		ldapGroupDao.addMemberToGroup(groupName, user);
	}
	
	public void removeMemberFromGroup(String groupName, User user) {
		ldapGroupDao.removeMemberFromGroup(groupName, user);
	}
	
}
