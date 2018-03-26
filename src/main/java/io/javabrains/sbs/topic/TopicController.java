package io.javabrains.sbs.topic;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/topics")
public class TopicController {

	private final TopicService topicService;

	@Autowired
	@Lazy
	public TopicController(TopicService topicService) {
		this.topicService = topicService;
	}

	@Async
	@RequestMapping()
	public CompletableFuture<ResponseEntity<?>> getAllTopics() throws InterruptedException, ExecutionException {
		Optional<List<Topic>> optTopics = topicService.getAllTopics().get();
		return optTopics.isPresent() ? CompletableFuture.completedFuture(ResponseEntity.ok(optTopics.get()))
				: CompletableFuture.completedFuture(ResponseEntity.status(500).build());
	}

	@RequestMapping("{id}")
	public ResponseEntity<?> getTopic(@PathVariable Long id) throws Exception {
		if (id == 1) {
			throw new Exception("thrown deliberately");
		}
		CompletableFuture<Optional<Topic>> topicOption = topicService.getTopic(id);

		return topicOption.get().isPresent() ? ResponseEntity.ok(topicOption.get().get())
				: ResponseEntity.badRequest().body(Arrays.asList("Some Error"));
	}

	@RequestMapping("/name/{name}")
	public ResponseEntity<?> getTopicByName(@PathVariable String name) {
		Optional<Topic> topicOption = topicService.getTopicByName(name);

		return topicOption.isPresent() ? ResponseEntity.ok(topicOption.get())
				: ResponseEntity.badRequest().body(Arrays.asList("Some Error"));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTopic(@RequestBody Topic topic) {
		System.out.println(topic);
		topicService.addTopic(topic);

		URI uri = UriComponentsBuilder.newInstance().scheme("http").host("localhost")
				.path("/api/topics/" + topic.getId()).build().toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
		try {

			CompletableFuture<Optional<Topic>> toUpdate = topicService.getTopic(id);

			if (!toUpdate.get().isPresent()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList("Some Error"));
			}

			topicService.update(topic);

			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(e.getMessage()));
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
		topicService.deleteTopic(id);
		return ResponseEntity.noContent().build();
	}
}
