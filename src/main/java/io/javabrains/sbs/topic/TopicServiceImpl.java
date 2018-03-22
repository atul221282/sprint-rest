package io.javabrains.sbs.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Lazy
@Scope("prototype")
public class TopicServiceImpl implements TopicService {

	@Autowired
	private final TopicRepository topicRespository;

	public TopicServiceImpl(TopicRepository topicRespository) {
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
		return CompletableFuture
				.completedFuture(id == null ? Optional.empty() : Optional.of(topicRespository.findOne(id)));
	}

	@Override
	public void addTopic(Topic topic) {
		topicRespository.save(topic);
	}

	@Override
	public void update(Topic topic) throws Exception {
		Topic toUpdate = topicRespository.findOne(topic.getId());

		if (toUpdate == null)
			throw new Exception("No topic found");

		topicRespository.save(topic);
	}

	@Override
	public void deleteTopic(Long id) {
		topicRespository.delete(id);
	}

	@Override
	public Optional<Topic> getTopicByName(String name) {
		return Optional.of(topicRespository.findByName(name));
	}
}
