package io.javabrains.sbs.albumtags;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class AndSpecification<T> implements Specification<T> {

	private Specification<T> left;
	private Specification<T> right;

	public AndSpecification(Specification<T> left, Specification<T> right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(left.toPredicate(root, query, cb), right.toPredicate(root, query, cb));
	}
}
