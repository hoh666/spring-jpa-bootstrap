package com.hjq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hjq.entity.FeedBackContent;

@Repository
public interface FeedBackContentRepository extends JpaRepository<FeedBackContent, Long>, JpaSpecificationExecutor<FeedBackContent> {

}
