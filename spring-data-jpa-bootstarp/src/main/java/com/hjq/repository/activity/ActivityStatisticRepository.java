package com.hjq.repository.activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hjq.entity.activity.ActivityStatisticInfo;

@Repository
public interface ActivityStatisticRepository extends JpaRepository<ActivityStatisticInfo, Long>, JpaSpecificationExecutor<ActivityStatisticInfo> {

	@Query("select count(a.id) from ActivityStatisticInfo a where a.activityRecord.id = :actId GROUP BY a.channel")
	public List<Long> getCountGourpByActId(@Param("actId") long actId);
}
