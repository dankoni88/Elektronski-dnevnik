package com.itkpreobuka.Elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Subjects")
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})
public class SubjectEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	protected Integer id;
	@Version
	@Column
	protected Integer version;
	@Column(nullable=false)
	protected String name;
	@JsonIgnore
	@OneToMany(mappedBy="subject",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	protected List<GradeSubjectEntity> list=new ArrayList<>();
	private SubjectEntity() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GradeSubjectEntity> getList() {
		return list;
	}
	public void setList(List<GradeSubjectEntity> list) {
		this.list = list;
	}
	
	
	
	

}
