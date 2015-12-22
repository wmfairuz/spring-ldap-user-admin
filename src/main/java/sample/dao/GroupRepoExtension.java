package sample.dao;

import java.util.List;

import sample.model.Group;

public interface GroupRepoExtension {
	List<String> getAllGroupNames();
    void create(Group group);
}
