package com.hjq.repository.specification.activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.hjq.entity.activity.ActivityRecord;
import com.hjq.util.CommonUtils;

public class ActivitySpecification {

	public static Specification<ActivityRecord> activityListQuery(final String activityName, final String startDateBegin, final String startDateEnd, 
			final String endDateBegin, final String endDateEnd) {
        
        return new Specification<ActivityRecord>() {
            @Override
            public Predicate toPredicate(Root<ActivityRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            	List<Predicate> list = new ArrayList<Predicate>();
        		
                if (StringUtils.isNotBlank(startDateBegin) && StringUtils.isNotBlank(startDateEnd)) {
                	Predicate datePredicate;
        			try {
        				datePredicate = cb.between(root.<Date>get("startDate"), CommonUtils.sdf.parse(startDateBegin), CommonUtils.sdf.parse(startDateEnd));
        				list.add(datePredicate);
        			} catch (ParseException e) {
        				e.printStackTrace();
        			}// 时间条件
                }

                if (StringUtils.isNotBlank(endDateBegin) && StringUtils.isNotBlank(endDateEnd)) {
                	Predicate datePredicate;
        			try {
        				datePredicate = cb.between(root.<Date>get("endDate"), CommonUtils.sdf.parse(endDateBegin), CommonUtils.sdf.parse(endDateEnd));
        				list.add(datePredicate);
        			} catch (ParseException e) {
        				e.printStackTrace();
        			}// 时间条件
                }

                if (StringUtils.isNotBlank(activityName)) {
                	String likePattern = getLikePattern(activityName);
                	Predicate namePredicate = cb.like(cb.lower(root.<String>get("activityName")), likePattern);// 按姓名模糊查询
                	list.add(namePredicate);
                }
                
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
            
        };
	}

	public static Specification<ActivityRecord> activityListForTouch() {

		return new Specification<ActivityRecord>() {

			@Override
			public Predicate toPredicate(Root<ActivityRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.and(cb.equal(root.<Integer>get("status"), 0));
			}

		};
	}

	private static String getLikePattern(final String searchTerm) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("%");
        pattern.append(searchTerm.toLowerCase());
        pattern.append("%");
        return pattern.toString();
    }
}
