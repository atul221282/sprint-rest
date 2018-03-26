package io.javabrains.sbs.albumtags;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

	private final AlbumRepository repository;

	@Autowired
	public AlbumService(AlbumRepository repository) {
		this.repository = repository;
	}

	public Album save(Album album) {
		return repository.save(album);
	}

	public List<Album> findBySearchCriteria(AlbumSearchCriteria criteria) {
		Specification<Album> albumSpec = null;

		if (!criteria.getTitle().isEmpty()) {
			albumSpec = new AlbumTitleSpecification(criteria);
		}

		if (!criteria.getDescription().isEmpty()) {
			albumSpec = albumSpec == null ? new AlbumDescriptionSpecification(criteria)
					: new AndSpecification<>(albumSpec, new AlbumDescriptionSpecification(criteria));
		}

		List<Album> list = repository.findAll(albumSpec);

		return list;
	}
}
