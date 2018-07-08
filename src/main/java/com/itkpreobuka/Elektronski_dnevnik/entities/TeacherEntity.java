package com.itkpreobuka.Elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@Table(name="Teachers")
@Entity
public class TeacherEntity extends UserEntity{
@JsonIgnore
@OneToMany(mappedBy="teacher",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
protected List<TeacherGradeSubjectEntity> tgs=new ArrayList<>();
	public TeacherEntity() {}
	public List<TeacherGradeSubjectEntity> getTgs() {
		return tgs;
	}
	public void setTgs(List<TeacherGradeSubjectEntity> tgs) {
		this.tgs = tgs;
	}
	

}
