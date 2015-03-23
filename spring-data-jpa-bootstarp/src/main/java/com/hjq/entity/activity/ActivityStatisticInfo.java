package com.hjq.entity.activity;

import java.util.Date;

import javax.jdo.annotations.Index;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name="wireless_touch_activity_statistic")
public class ActivityStatisticInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Index
	@Column(name="user_id", nullable=false)
	private String userId;

	@Index
	@Column(name="username", nullable=false)
	private String username;

	@Column(name="join_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate;

	@ManyToOne
	@JoinColumn(name = "activity_id", nullable = false)
	private ActivityRecord activityRecord;

	@Index
	@Column(name="channel", nullable=false)
	private int channel; //参与渠道 touch、ios、andoird

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public ActivityRecord getActivityRecord() {
		return activityRecord;
	}

	public void setActivityRecord(ActivityRecord activityRecord) {
		this.activityRecord = activityRecord;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
}
