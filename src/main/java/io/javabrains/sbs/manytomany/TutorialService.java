package io.javabrains.sbs.manytomany;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorialService {

	private final TutorialRepository tutorialRepository;

	@Autowired
	public TutorialService(TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}

	public void save(Tutorial tutorial) {
		tutorialRepository.save(tutorial);
	}

	public Optional<Tutorial> get(long id) {
		return Optional.of(tutorialRepository.findOne(id));
	}

	public Optional<List<TutorialDto>> get() {
		return Optional.of(tutorialRepository.findAllWithStudent());

	}
}
