package io.javabrains.sbs.topic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TopicRepositoryIT {

	@Autowired
	private TopicRepository topicRespository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("=======Running Integration Test==========");
		topicRespository.save(new Topic("desc", "name"));
		System.out.println("=======Running Integration Test Ends==========");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		topicRespository.deleteAll();
	}

	@Test
	public void test() {
		Topic tp = topicRespository.findByName("name");

		Assertions.assertThat("name").isEqualTo(tp.getName());

		Assertions.assertThat("desc").isEqualTo(tp.getDescription());

		assertNotNull(tp.getId());

		assertTrue(tp.getId() > 0);
	}

}
