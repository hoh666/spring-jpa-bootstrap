package com.hjq.repository.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.hjq.entity.FeedBackContent;
import com.hjq.entity.activity.ActivityRecord;

public class FeedBackContentSpecifications {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 自定义条件查询
	 * @param searchTerm
	 * @return
	 */
	public static Specification<ActivityRecord> reportListQuery(final String searchTerm, final String starttime, final String endtime) {
	        
	        return new Specification<ActivityRecord>() {
	            @Override
	            public Predicate toPredicate(Root<ActivityRecord> feedBackContentRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
	
	            	List<Predicate> list = commonSpecificationCollection(starttime, endtime, searchTerm, feedBackContentRoot, cb);
	                
	            	query.groupBy(feedBackContentRoot.<String>get("userId"));
	                return cb.and(list.toArray(new Predicate[list.size()]));
	            }
	            
	    };
	}

	public static List<Predicate> commonSpecificationCollection(String starttime, String endtime, String searchTerm, Root<?> root, CriteriaBuilder cb) {

		List<Predicate> list = new ArrayList<Predicate>();
		
        if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
        	Predicate datePredicate;
			try {
				datePredicate = cb.between(root.<Date>get("reportDate"), sdf.parse(starttime), sdf.parse(endtime));
				list.add(datePredicate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 时间条件
        }

        if (StringUtils.isNotBlank(searchTerm)) {
        	String likePattern = getLikePattern(searchTerm);
        	Predicate usernamePredicate = cb.like(cb.lower(root.<String>get("username")), likePattern);// 按姓名模糊查询
        	list.add(usernamePredicate);
        }
        Predicate onlyReportPredicate = cb.equal(root.<String>get("replyToUid"), "0");
        list.add(onlyReportPredicate);

        return list;
	}

	private static String getLikePattern(final String searchTerm) {
        StringBuilder pattern = new StringBuilder();
        pattern.append(searchTerm.toLowerCase());
        pattern.append("%");
        return pattern.toString();
    }

	/**
	 * 反馈详情查询
	 * @param uid
	 * @return
	 */
	public static Specification<FeedBackContent> reportDetailQuery(final String uid) {

		return new Specification<FeedBackContent>() {

			@Override
			public Predicate toPredicate(Root<FeedBackContent> feedBackContentRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.or(cb.equal(feedBackContentRoot.<String>get("userId"), uid), cb.equal(feedBackContentRoot.<String>get("replyToUid"), uid));
			}
			
		};
	}
}
