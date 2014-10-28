package com.hjq.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BaseIService<T, Y> {

	public final static int DEFAULT_PAGE = 0;
	public final static int DEFAULT_PAGE_SIZE = 20;
	public final static String DIRECTION_DESC = "DESC";
	public final static String DIRECTION_ASC = "ASC";

	/**
	 * save a <T> entity into db
	 * @param entity
	 * @return
	 */
	public T create(T entity);

	public Page<T> findAll(T item, Sort sort);

	public T findOne(Y id);

	/**
	 * delete record by id
	 * @param id
	 * @return the record which has deleted
	 */
	public T deleteById(Y id);

	public T update(T entity);

	public void deleteInBatch(List<T> entities);

	public List<T> saveInBatch(List<T> entities);

	public long count(T entity);

}
