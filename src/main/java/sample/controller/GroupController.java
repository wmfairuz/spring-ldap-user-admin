package sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sample.domain.GroupRepo;

@RestController
public class GroupController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private GroupRepo groupRepo;
	
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
    public @ResponseBody String listGroups(ModelMap map) {
		List<String> groups = groupRepo.getAllGroupNames();
		for(String group: groups) {
			log.info(">> " + group);
		}
        map.put("groups", groups);
        return "test";
	}
	
}
