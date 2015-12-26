package sample.domain;

import java.util.List;

import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.ldap.NameNotFoundException;

public interface LdapUserDao <T>{
	
	public List<String> getAllPersonNames();
	
	public List<User> getAllPersons();
	
	public User findUserByString(String dn);
	
	public User findUser(User user);
	
	public void create(User user) throws NameAlreadyBoundException;
	
	public void delete(User user) throws NameNotFoundException;
	
}