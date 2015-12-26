package sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sample.domain.User;
import sample.service.LdapUserService;

@RestController
public class LdapUserController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public LdapUserService ldapUserService;

	@RequestMapping("/test/usernames")
	@ResponseBody
	public List<String> getUserNameList() {
		log.info("Testing person name list");
		return ldapUserService.getAllPersonNames();
	}
	
	@RequestMapping("/test/users")
	@ResponseBody
	public List<User> getUserList() {
		log.info("Testing person object list");
		return ldapUserService.getAllPersons();
	}
	
	@RequestMapping("/test/userlookup")
	@ResponseBody
	public User getUserByString() {
		log.info("Testing user lookup using String Dn");
		return ldapUserService.findUserByString("uid=fairuz.ismail,ou=people");
	}
	
	@RequestMapping("/test/userlookup2")
	@ResponseBody
	public User getUser() {
		log.info("Testing user lookup");
		User user = new User();
		user.setUid("fairuz.ismail");
		return ldapUserService.findUser(user);
	}
	
	@RequestMapping("/test/userbind")
	@ResponseBody
	public User createUser(@RequestParam(value="uid", defaultValue="test.001") String uid) {
		log.info("Testing user binding");
		
		User user = new User(uid, "test", uid, "password");
		try {
			ldapUserService.createUser(user);
		} catch (NameAlreadyBoundException e) {
			log.error("User already exist!!!");
		} catch (Exception e) {
			log.error("Something unknown happened >>> " + e.getClass().getName());
			log.error("ERROR", e);
		}
		return user;
	}
	
	@RequestMapping("/test/userunbind")
	@ResponseBody
	public User deleteUser(@RequestParam(value="uid", defaultValue="test.001") String uid) {
		log.info("Testing user binding");
		
		User user = new User(uid);
		try {
			ldapUserService.delete(user);
		} catch (NameNotFoundException e) {
			log.error("User not found!!!");
		} catch (Exception e) {
			log.error("Something unknown happened >>> " + e.getClass().getName());
			log.error("ERROR", e);
		}
		return user;
	}
	
}
