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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ClassEntity {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;

@Version
private Integer version;

@NotNull(message = "Name must be provided.")
@Pattern(regexp="^[a-zA-Z]{1}$")
private String name;

@JsonBackReference
@OneToMany(mappedBy="clasS",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
private List<StudentEntity> students=new ArrayList<>();

@NotNull(message = "Grade must be provided.")
@JsonManagedReference
@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
@JoinColumn(name="grade")
private GradeEntity grade;

@JsonBackReference
@OneToMany(mappedBy="clasS",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
protected List<TeacherGradeSubjectClassEntity> tgsc=new ArrayList<>();

public ClassEntity() {}

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
public List<StudentEntity> getStudents() {
	return students;
}
public void setStudents(List<StudentEntity> students) {
	this.students = students;
}
public GradeEntity getGrade() {
	return grade;
}
public void setGrade(GradeEntity grade) {
	this.grade = grade;
}
public List<TeacherGradeSubjectClassEntity> getTgsc() {
	return tgsc;
}
public void setTgsc(List<TeacherGradeSubjectClassEntity> tgsc) {
	this.tgsc = tgsc;
}

}
