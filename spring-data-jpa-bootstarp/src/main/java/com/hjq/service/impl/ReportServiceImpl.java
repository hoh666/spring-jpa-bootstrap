package com.hjq.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hjq.entity.FeedBackContent;
import com.hjq.repository.FeedBackContentRepository;
import com.hjq.repository.ReportExpandRepository;
import com.hjq.repository.specification.FeedBackContentSpecifications;
import com.hjq.service.ReportService;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	private static Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	private @Inject FeedBackContentRepository feedBackContentRepository;
	private @Inject ReportExpandRepository reportExpandRepository;

	@Override
	public FeedBackContent create(FeedBackContent entity) {

		try {
			entity = feedBackContentRepository.save(entity);
		} catch (Exception e) {
			logger.error("ex: {}", e);
		}
		return entity;
	}

	@Override
	public Page<FeedBackContent> findAll(FeedBackContent item, Sort sort) {

		if (item == null) {
			Pageable pageable = new PageRequest(DEFAULT_PAGE, DEFAULT_PAGE_SIZE, sort);
			return feedBackContentRepository.findAll(pageable);
		} else {
			return new PageImpl<FeedBackContent>(feedBackContentRepository.findAll(sort));
		}
	}

	@Override
	public FeedBackContent findOne(Long id) {
		// TODO Auto-generated method stub
		return feedBackContentRepository.findOne(id);
	}

	@Override
	public FeedBackContent deleteById(Long id) {
		FeedBackContent content = findOne(id);
		try {
			feedBackContentRepository.delete(content);
		} catch (Exception e) {
			logger.error("删除反馈失败： {}", content);
			content = null;
		}
		return content;
	}

	@Override
	public FeedBackContent update(FeedBackContent entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(List<FeedBackContent> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FeedBackContent> saveInBatch(List<FeedBackContent> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(FeedBackContent entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FeedBackContent> getReportsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FeedBackContent> getReportBySpecification(
			Map<String, Object> param, Sort sort) {

		Pageable pageable = new PageRequest(Integer.valueOf((String) param.get("page")) - 1, DEFAULT_PAGE_SIZE, sort);
		List<FeedBackContent> content = reportExpandRepository.getReportList((String) param.get("username"), (String) param.get("starttime"), (String) param.get("endtime"), Integer.valueOf((String) param.get("page")), DEFAULT_PAGE_SIZE);
		long total = reportExpandRepository.getReportCount((String) param.get("username"), (String) param.get("starttime"), (String) param.get("endtime"));
//		return feedBackContentRepository.findAll(FeedBackContentSpecifications.reportListQuery((String) param.get("username"), (String) param.get("starttime"), (String) param.get("endtime")), pageable);
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public List<FeedBackContent> getReportDetail(Map<String, Object> param, Sort sort) {

		return feedBackContentRepository.findAll(FeedBackContentSpecifications.reportDetailQuery((String) param.get("uid")), sort);
	}

	@Override
	public long getReportCount(Map<String, Object> param) {

		return reportExpandRepository.getReportCount((String) param.get("username"), (String) param.get("starttime"), (String) param.get("endtime"));
	}

}
