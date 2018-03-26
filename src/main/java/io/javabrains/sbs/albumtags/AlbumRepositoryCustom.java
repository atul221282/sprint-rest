package io.javabrains.sbs.albumtags;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public interface AlbumRepositoryCustom {
	public List<AlbumSummaryDto> customSpecification(Specification<Album> albumSpec);
}
