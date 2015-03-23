package com.hjq.entity.activity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Index;
import javax.jdo.annotations.Indices;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenerationTime;

import com.hjq.entity.enumType.ActivityStatus;
import com.hjq.serializer.JsonDateSerializer;

@Entity
@Table(name="activity_record")
@Indices({
	@Index(members={"startDate"}, unique="true"),
	@Index(members={"endDate"}, unique="true"),
	@Index(members={"startDate", "endDate"})
})
public class ActivityRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="activity_name", nullable=false)
	private String activityName;

	@Column(name="create_date", insertable=false, updatable=false, columnDefinition="timestamp default current_timestamp")
	@org.hibernate.annotations.Generated(value=GenerationTime.INSERT)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name="start_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name="end_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(name="status", nullable=false)
	private int status;

	@Column(name="discription", nullable=true)
	private String discription;

	// 之后属性为焦点轮播图数据
	@Column(name="href", nullable=false)
	private String href;

	@Column(name="redirect", nullable=false)
	private boolean redirect;

	@Column(name="img", nullable=false)
	private String img;

	@Column(name="count_name", nullable=false)
	private String countName;

	@Column(name="un_support", nullable=false)
	private String unSupport;

	public long getId() {	
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ActivityStatus getStatus() {
		return ActivityStatus.getActivityStatusFromCode(status);
	}

	public void setStatus(ActivityStatus activityStatus) {
		this.status = activityStatus.getCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCountName() {
		return countName;
	}

	public void setCountName(String countName) {
		this.countName = countName;
	}

	public String getUnSupport() {
		return unSupport;
	}

	public void setUnSupport(String unSupport) {
		this.unSupport = unSupport;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
