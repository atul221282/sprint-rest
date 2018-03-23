package io.javabrains.sbs.manytomany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	static final String getTutorialsWithStudent = "select new io.javabrains.sbs.manytomany.TutorialDto(t.id,t.name, s.id, s.name) from Tutorial t join t.students s";

	@Query(getTutorialsWithStudent)
	public List<TutorialDto> findAllWithStudent();
}
