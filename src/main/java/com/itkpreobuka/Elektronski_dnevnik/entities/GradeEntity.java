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
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GradeEntity {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column
private Integer id;
@Version
private Integer version;
private String value;
@JsonIgnore
@OneToMany(mappedBy="grade",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
private List<ClassEntity> classes=new ArrayList<>();
@JsonIgnore
@OneToMany(mappedBy="grade",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
protected List<GradeSubjectEntity> list=new ArrayList<>();
public GradeEntity() {}
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
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public List<ClassEntity> getClasses() {
	return classes;
}
public void setClasses(List<ClassEntity> classes) {
	this.classes = classes;
}
public List<GradeSubjectEntity> getList() {
	return list;
}
public void setList(List<GradeSubjectEntity> list) {
	this.list = list;
}


}
