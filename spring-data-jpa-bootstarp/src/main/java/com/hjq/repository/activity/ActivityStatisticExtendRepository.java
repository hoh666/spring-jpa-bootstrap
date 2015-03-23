package com.hjq.repository.activity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ActivityStatisticExtendRepository {

	@PersistenceContext
    private EntityManager entityManager;

	//TODO extend query
}
