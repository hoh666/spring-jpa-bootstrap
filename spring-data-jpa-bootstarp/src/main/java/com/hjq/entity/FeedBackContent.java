package com.hjq.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenerationTime;

import com.hjq.entity.enumType.ReportType;
import com.hjq.serializer.JsonDateSerializer;

@Entity
@Table(name="feed_back")
public class FeedBackContent implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="content", nullable=false)
	private String content;

	@Column(name="report_date", insertable=false, updatable=false, columnDefinition="timestamp default current_timestamp")
	@org.hibernate.annotations.Generated(value=GenerationTime.INSERT)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date reportDate;

//	@ElementCollection(targetClass = ReportType.class)
//	@Enumerated(EnumType.ORDINAL)
	@Column(name="reportType", nullable=false)
	private String reportType;

	@Column(name="reply_to_uid", nullable=true)
	private long replyToUid;

	@Column(name="phone", nullable=true)
	private String phone;

	@Column(name="mail", nullable=true)
	private String mail;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "username", nullable = false)
	private String username;

	@Transient
	private String reportTypeValue;

	@Column(name = "platform", nullable = true)
	private String platform;

	@Column(name = "source", nullable = true)
	private String source;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public long getReplyToUid() {
		return replyToUid;
	}

	public void setReplyToUid(long replyToUid) {
		this.replyToUid = replyToUid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
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

	public String getReportTypeValue() {
		if (StringUtils.isBlank(this.reportType)) {
			return "";
		}

		String[] array = this.reportType.split(",");
		StringBuilder sb = new StringBuilder();
		for (String value : array) {
			sb.append(ReportType.getReportTypeByCode(Integer.valueOf(value)).getValue());
			sb.append(",");
		}
		return sb.substring(0, sb.length()-1).toString();
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
