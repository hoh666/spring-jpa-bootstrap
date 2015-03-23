package com.hjq.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hjq.entity.FeedBackContent;
import com.hjq.repository.specification.FeedBackContentSpecifications;

/**
 * 扩展仓库，用于不能用jpaRepository直接完成的查询
 * @author hoh666
 *
 */
@Repository
public class ReportExpandRepository {

	@PersistenceContext
    private EntityManager entityManager;

	/**
	 * 查询反馈数量
	 * @param username
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public long getReportCount(String username, String starttime, String endtime) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<FeedBackContent> report = cq.from(FeedBackContent.class);

		List<Predicate> list = FeedBackContentSpecifications.commonSpecificationCollection(starttime, endtime, username, report, cb);

		cq.where(list.toArray(new Predicate[list.size()]));
		cq.select(cb.countDistinct(report.<String>get("userId")));
		TypedQuery<Long> q = entityManager.createQuery(cq);
		return q.getSingleResult();
	}

	/**
	 * 查询反馈集合
	 * @param username
	 * @param starttime
	 * @param endtime
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<FeedBackContent> getReportList(String username, String starttime, String endtime, int page, int pageSize) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FeedBackContent> cq = cb.createQuery(FeedBackContent.class);
		Root<FeedBackContent> report = cq.from(FeedBackContent.class);

		List<Predicate> list = FeedBackContentSpecifications.commonSpecificationCollection(starttime, endtime, username, report, cb);

		cq.where(list.toArray(new Predicate[list.size()]));
		cq.groupBy(report.<String>get("userId"));
		cq.select(report);

		TypedQuery<FeedBackContent> q = entityManager.createQuery(cq);
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);

		return q.getResultList();
	}
}
