package com.hjq.repository.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.data.jpa.domain.Specification;

import com.hjq.entity.activity.ActivityStatisticInfo;

public class ActivityStatisticSpecification {

	public static Specification<ActivityStatisticInfo> statisticByActId (final String id) {

		return new Specification<ActivityStatisticInfo>(){

			@Override
			public Predicate toPredicate(Root<ActivityStatisticInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				if (NumberUtils.isNumber(id)) {
					cb.equal(root.<Long>get("activityRecord.id"), Long.valueOf(id));
				}

				query.groupBy(root.<String>get("channel"));
				return null;
			}
		};
	}
}
