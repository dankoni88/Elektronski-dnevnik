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
public class TeacherGradeSubjectClassEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Version
	private Integer version;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="tgs")
	private TeacherGradeSubjectEntity tgs;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="clasS")
	private ClassEntity clasS;
	@JsonIgnore
	@OneToMany(mappedBy="tgbc",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	protected List<Mark> marks=new ArrayList<>();
	public TeacherGradeSubjectClassEntity() {}
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
	public TeacherGradeSubjectEntity getTgs() {
		return tgs;
	}
	public void setTgs(TeacherGradeSubjectEntity tgs) {
		this.tgs = tgs;
	}
	public ClassEntity getClasS() {
		return clasS;
	}
	public void setClasS(ClassEntity clasS) {
		this.clasS = clasS;
	}
	public List<Mark> getMarks() {
		return marks;
	}
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	
	
}
