package com.itkpreobuka.Elektronski_dnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.itkpreobuka.Elektronski_dnevnik.enums.Semester;
@Entity
public class Mark {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Version
	private Integer version;
	protected Integer value;
	protected Semester semester;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="description")
	protected MarkDescription description;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="student")
	protected StudentEntity student;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="tgbc")
	protected TeacherGradeSubjectClassEntity tgbc;
	private Mark() {}
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
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Semester getSemester() {
		return semester;
	}
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	public MarkDescription getDescription() {
		return description;
	}
	public void setDescription(MarkDescription description) {
		this.description = description;
	}
	public StudentEntity getStudent() {
		return student;
	}
	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	public TeacherGradeSubjectClassEntity getTgbc() {
		return tgbc;
	}
	public void setTgbc(TeacherGradeSubjectClassEntity tgbc) {
		this.tgbc = tgbc;
	}
	
	
	
	

}
