package io.javabrains.sbs.topic;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface TopicService {

	public CompletableFuture<Optional<List<Topic>>> getAllTopics();

	public CompletableFuture<Optional<Topic>> getTopic(Long id) throws InterruptedException, ExecutionException;

	public void addTopic(Topic topic);

	public void update(Topic topic) throws Exception;

	public void deleteTopic(Long id);

	public Optional<Topic> getTopicByName(String name);
}
