package io.javabrains.sbs.topic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRespository extends CrudRepository<Topic, Long> {

	public Topic findByName(String name);
}
