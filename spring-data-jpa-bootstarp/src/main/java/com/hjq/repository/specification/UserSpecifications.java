package com.hjq.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hjq.entity.User;

public class UserSpecifications {

	public static final String LIKE = "%";

	public static Specification<User> userNameIsLike(final String username) {

		return new Specification<User>() {

			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.like(root.<String>get("userName"), getLikeUsernamePartten(username));
				
			}

			private String getLikeUsernamePartten(String username) {

				return new StringBuilder().append(LIKE).append(username.toLowerCase()).append(LIKE).toString();
			}
		};
	}
}
