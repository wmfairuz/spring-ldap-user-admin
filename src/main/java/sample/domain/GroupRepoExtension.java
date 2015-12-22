package sample.domain;

import java.util.List;

public interface GroupRepoExtension {
	List<String> getAllGroupNames();
    void create(Group group);
}
