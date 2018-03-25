package io.javabrains.sbs.topic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.javabrains.sbs.course.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicServiceTest {

	@Mock
	private TopicRepository topicRepository;

	@Autowired
	private TopicService topicService;

	@Before
	public void setUp() throws Exception {
		System.out.println("=======Running TopicServiceTest==========");
		topicRepository = Mockito.mock(TopicRepository.class);
		topicService = new TopicServiceImpl(topicRepository);
	}

	@Test
	public void test() throws InterruptedException, ExecutionException {
		Mockito.when(topicRepository.findAll()).thenReturn(Arrays.asList(new Topic("Desc", "Name")));

		CompletableFuture<Optional<List<Topic>>> topics = topicService.getAllTopics();

		assertTrue(topics.get().get().stream().findFirst().get().getDescription() == "Desc");

		System.out.println("=======Running TopicServiceTest Ends==========");
	}

}
