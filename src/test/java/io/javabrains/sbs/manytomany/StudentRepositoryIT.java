package io.javabrains.sbs.manytomany;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StudentRepositoryIT {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testOptimisticConcurrency_when_save() {
		Tutorial tut = new Tutorial();
		tut.setName("ABC Kids");
		Tutorial tut2 = new Tutorial();
		tut.setName("Pocyo");
		Student student = new Student("Iha", Arrays.asList(tut, tut2));
		studentRepository.save(student);

		Student insStudent = studentRepository.findOne((long) 1);
		insStudent.setName("Ishana Chaudhary");
		List<Tutorial> tuts = insStudent.getTutorials();
		tuts.get(0).setName("ABC TV");

		studentRepository.save(insStudent);
		Student insStudent2 = studentRepository.findOne((long) 1);

		Assert.assertEquals("Ishana Chaudhary", insStudent2.getName());
		Assert.assertEquals("ABC TV", insStudent2.getTutorials().get(0).getName());
	}

}
