package io.javabrains.sbs.topic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.TinyBitSet;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.javabrains.sbs.course.Course;

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

	@Test
	public void testVersion_topicmasterdetail_save() {
		Topic topic = new Topic("desc", "name2");
		topic.setCourses(Arrays.asList(new Course("desc", "name")));
		topicRespository.save(topic);

		Topic tInserted = topicRespository.findByName("name2");
		assertNotNull(tInserted);

		tInserted.setName("name3");
		Course cs = tInserted.getCourses().get(0);
		cs.setDescription("desc2");
		assertNotNull(cs);

		topicRespository.save(tInserted);

		Topic tInserted3 = topicRespository.findByName("name3");
		assertTrue(tInserted3.getCourses().get(0).getDescription() == "desc2");

		assertNotNull(tInserted3);

		System.out.println(tInserted.getCourses().get(0).getDescription());
	}

}
