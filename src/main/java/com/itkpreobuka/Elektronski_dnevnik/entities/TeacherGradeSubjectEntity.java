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
public class TeacherGradeSubjectEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Version
	private Integer version;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="teacher")
	protected TeacherEntity teacher;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="gradesub")
	protected GradeSubjectEntity gradesub;
	@JsonIgnore
	@OneToMany(mappedBy="tgs",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	protected List<TeacherGradeSubjectClassEntity> tgsc=new ArrayList<>();
	public TeacherGradeSubjectEntity() {}
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
	public TeacherEntity getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}
	public GradeSubjectEntity getGradesub() {
		return gradesub;
	}
	public void setGradesub(GradeSubjectEntity gradesub) {
		this.gradesub = gradesub;
	}
	public List<TeacherGradeSubjectClassEntity> getTgsc() {
		return tgsc;
	}
	public void setTgsc(List<TeacherGradeSubjectClassEntity> tgsc) {
		this.tgsc = tgsc;
	}
	
	
}
