package io.javabrains.sbs.manytomany;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/tutorials")
public class TutorialController {
	private final TutorialService tutorialService;

	@Autowired
	@Lazy
	public TutorialController(TutorialService tutorialService) {
		this.tutorialService = tutorialService;
	}

	@Async
	@RequestMapping()
	public CompletableFuture<ResponseEntity<?>> get() throws InterruptedException, ExecutionException {
		Optional<List<TutorialDto>> optTopics = tutorialService.get();

		return optTopics.isPresent() ? CompletableFuture.completedFuture(ResponseEntity.ok(optTopics.get()))
				: CompletableFuture.completedFuture(ResponseEntity.status(500).build());
	}

	@RequestMapping("{id}")
	public ResponseEntity<?> getTopic(@PathVariable Long id) throws InterruptedException, ExecutionException {
		Optional<Tutorial> topicOption = tutorialService.get(id);

		return topicOption.isPresent() ? ResponseEntity.ok(topicOption.get())
				: ResponseEntity.badRequest().body(Arrays.asList("Some Error"));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTopic(@RequestBody Tutorial tutorial) {
		System.out.println(tutorial);
		tutorialService.save(tutorial);

		URI uri = UriComponentsBuilder.newInstance().scheme("http").host("localhost")
				.path("/api/tutorials/" + tutorial.getId()).build().toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody Tutorial tutorial) {
		try {

			Optional<Tutorial> toUpdate = tutorialService.get(id);

			if (!toUpdate.isPresent()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList("Some Error"));
			}

			tutorialService.save(tutorial);

			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(e.getMessage()));
		}
	}
}
