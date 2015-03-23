package com.hjq.repository.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hjq.entity.activity.ActivityRecord;

@Repository
public interface ActivitySettingRepository extends JpaRepository<ActivityRecord, Long>, JpaSpecificationExecutor<ActivityRecord> {

}
