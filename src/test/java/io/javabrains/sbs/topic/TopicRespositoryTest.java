package io.javabrains.sbs.topic;

import static org.junit.Assert.assertNotNull;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicRespositoryTest {

	@Autowired
	private TopicRespository topicRespository;

	@Test
	public void test() {
		assertNotNull(topicRespository);

		topicRespository.save(new Topic("desc", "name"));

		Topic tp = topicRespository.findByName("name");

		System.out.println(tp.getDescription() + " " + tp.getId());

		assertNotNull(tp);
		
		Assertions.assertThat("name").isEqualTo(tp.getName());
		
		Assertions.assertThat("desc").isEqualTo(tp.getDescription());
	}

}
