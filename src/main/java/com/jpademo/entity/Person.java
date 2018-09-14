package com.jpademo.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpademo.enums.Gender;
import com.oneToOne.entity.IDCard;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint COMMENT '主键,自动生成'")
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, columnDefinition = "varchar(10) COMMENT '姓名'")
	private String name;
	
	@OneToOne(optional = false, cascade = { CascadeType.ALL })
	@JoinColumn(name = "idCard_id")
	private IDCard idCard;

//	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", columnDefinition = "DATE COMMENT '出生日期'")
	private Date birthday;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "gender", nullable = false, columnDefinition = "tinyint(1) COMMENT '性别'")
	private Gender gender = Gender.getGenderById(1010);
	
	@Lob//存放大文本数据，用注解是DDL语句无效
//	@Column(name = "info", columnDefinition = "longtext COMMENT '自我介绍'")
	@Column(name = "info")
	private String info;
	
	@Lob//存放二进制字节码数据，用注解是DDL语句无效
//	@Column(name = "file", columnDefinition = "longblob COMMENT '二进制文件'")
	@Column(name = "file")
	@Basic(fetch = FetchType.LAZY)//当查询时不想加载该字段是可以设为懒加载
	private Byte[] file;
	
	@Transient//该字段不对数据库进行映射
	private String imagepath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Byte[] getFile() {
		return file;
	}

	public void setFile(Byte[] file) {
		this.file = file;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public Person() {
		super();
	}

	@Column(name = "create_user", updatable = false, columnDefinition = "varchar(255) COMMENT '创建者'")
	@CreatedBy
	private String createUser;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = " gmt_create", updatable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
	private Date gmtCreate;

	@LastModifiedBy
	@Column(name = "modified_user", columnDefinition = "varchar(255) COMMENT '修改者'")
	private String modifiedUser;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "gmt_modified", columnDefinition = "DATETIME COMMENT '修改时间'")
	private Date gmtModified;

	@Column(name = "version", columnDefinition = "bigint COMMENT '版本号' default 0")
	@Version
	private Long version;

	@Column(name = "remark", columnDefinition = "varchar(255) COMMENT '备注'")
	private String remark;

	@Column(name = "id_delete", nullable = false, columnDefinition = "tinyint(1) COMMENT '1 表示是，0 表示否' default 0")
	private Boolean delete = false;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = "any";
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Person(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthday=" + birthday + ", gender=" + gender + ", info="
				+ info + ", file=" + Arrays.toString(file) + ", imagepath=" + imagepath + ", createUser=" + createUser
				+ ", gmtCreate=" + gmtCreate + ", modifiedUser=" + modifiedUser + ", gmtModified=" + gmtModified
				+ ", version=" + version + ", remark=" + remark + ", delete=" + delete + "]";
	}

	public IDCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}

}
