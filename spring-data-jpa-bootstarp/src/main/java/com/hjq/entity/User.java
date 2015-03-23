package com.hjq.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.hjq.entity.enumType.SexType;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "username", nullable = false)
	private String userName;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "age", nullable = false)
	private int age;

	@Enumerated(EnumType.STRING)
	@Column(name = "sex", nullable = false)
	private SexType sex = SexType.MALE;

	@Version
	private long version = 0;

	@ManyToOne
	@JoinColumn(name = "profession_id", nullable = false)
	private Profession profession;

	@Column(name="description")
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public SexType getSex() {
		return sex;
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
