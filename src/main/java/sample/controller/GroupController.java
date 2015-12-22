package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.dao.GroupRepo;

@RestController
public class GroupController {

	@Autowired
	@Qualifier("groupRepoImpl")
    private GroupRepo groupRepo;
	
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ModelMap listGroups(ModelMap map) {
        map.put("groups", groupRepo.getAllGroupNames());
        return map;
	}
	
}
