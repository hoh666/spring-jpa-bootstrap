package com.hjq.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.hjq.entity.FeedBackContent;

public interface ReportService extends BaseIService<FeedBackContent, Long> {

	public List<FeedBackContent> getReportsById(String id);

	public Page<FeedBackContent> getReportBySpecification(Map<String, Object> param, Sort sort);

	public List<FeedBackContent> getReportDetail(Map<String, Object> param, Sort sort);

	public long getReportCount(Map<String, Object> param);
}
