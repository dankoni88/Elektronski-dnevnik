package com.itkpreobuka.Elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class GradeSubjectEntity {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;
@Version
private Integer version;
private Integer weekhours;
protected Boolean active;
@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
@JoinColumn(name="grade")
private GradeEntity grade;
@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
@JoinColumn(name="subject")
private SubjectEntity subject;
@JsonIgnore
@OneToMany(mappedBy="gradesub",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
protected List<TeacherGradeSubjectEntity> tgs=new ArrayList<>();
private GradeSubjectEntity() {}
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
public Integer getWeekhours() {
	return weekhours;
}
public void setWeekhours(Integer weekhours) {
	this.weekhours = weekhours;
}
public Boolean getActive() {
	return active;
}
public void setActive(Boolean active) {
	this.active = active;
}
public GradeEntity getGrade() {
	return grade;
}
public void setGrade(GradeEntity grade) {
	this.grade = grade;
}
public SubjectEntity getSubject() {
	return subject;
}
public void setSubject(SubjectEntity subject) {
	this.subject = subject;
}
public List<TeacherGradeSubjectEntity> getTgs() {
	return tgs;
}
public void setTgs(List<TeacherGradeSubjectEntity> tgs) {
	this.tgs = tgs;
}


}
