package io.javabrains.sbs.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Lazy
@Scope("prototype")
public class TopicServiceImpl implements TopicService {

	private static final Logger logger = LogManager.getLogger(TopicServiceImpl.class);
	
	@Autowired
	private final TopicRepository topicRespository;

	public TopicServiceImpl(TopicRepository topicRespository) {
		logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
        logger.fatal("Damn! Fatal error. Please fix me.");
		this.topicRespository = topicRespository;
		System.out.println("Service Init");
	}

	@Override
	@Async
	public CompletableFuture<Optional<List<Topic>>> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRespository.findAll().forEach(topics::add);
		return CompletableFuture.completedFuture(Optional.of(topics));
	}

	@Override
	@Async
	public CompletableFuture<Optional<Topic>> getTopic(Long id) throws InterruptedException, ExecutionException {
		return CompletableFuture.completedFuture(id == null ? Optional.empty() : topicRespository.findById(id));
	}

	@Override
	public void addTopic(Topic topic) {
		topicRespository.save(topic);
	}

	@Override
	public void update(Topic topic) throws Exception {
		Optional<Topic> toUpdate = topicRespository.findById(topic.getId());

		if (!toUpdate.isPresent())
			throw new Exception("No topic found");

		topicRespository.save(topic);
	}

	@Override
	public void deleteTopic(Long id) {
		topicRespository.deleteById(id);
	}

	@Override
	public Optional<Topic> getTopicByName(String name) {
		return Optional.of(topicRespository.findByName(name));
	}
}
