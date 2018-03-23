package io.javabrains.sbs.manytomany;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public void save(Student student) {
		studentRepository.save(student);
	}

	public Optional<Student> get(long id) {
		return Optional.of(studentRepository.findOne(id));
	}

	public Optional<List<Student>> get() {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return Optional.of(students);
	}
}
