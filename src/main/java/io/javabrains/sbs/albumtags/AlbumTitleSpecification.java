package io.javabrains.sbs.albumtags;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class AlbumTitleSpecification implements Specification<Album> {

	private final AlbumSearchCriteria criteria;

	public AlbumTitleSpecification(AlbumSearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Path<Object> value = root.get("title");
		return cb.equal(value, criteria.getTitle());
	}

}
