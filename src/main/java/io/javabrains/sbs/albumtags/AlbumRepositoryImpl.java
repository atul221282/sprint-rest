package io.javabrains.sbs.albumtags;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepositoryImpl implements AlbumRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	// https://dzone.com/articles/java-using-specification
	@Override
	public List<AlbumSummaryDto> customSpecification(Specification<Album> specification) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AlbumSummaryDto> query = cb.createQuery(AlbumSummaryDto.class);
		Root<Album> root = query.from(Album.class);
		query.select(cb.construct(AlbumSummaryDto.class, root.get("id"), root.get("title"), root.get("description")));
		Predicate predicate = specification.toPredicate(root, query, cb);
		// set predicate and execute query
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}

}
