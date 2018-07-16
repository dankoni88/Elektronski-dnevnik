package com.itkpreobuka.Elektronski_dnevnik.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itkpreobuka.Elektronski_dnevnik.enums.MarkType;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MarkDescription {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	
	protected MarkType type;
	
	@NotNull(message = "Notation must be provided.")
	protected String notation;
	
	@NotNull(message = "Date must be provided.")
	protected LocalDate date;
	
	@JsonBackReference
	@OneToMany(mappedBy="description",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	protected List<Mark> marks=new ArrayList<>();
	
	public MarkDescription() {}
	
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
	public MarkType getType() {
		return type;
	}
	public void setType(MarkType type) {
		this.type = type;
	}
	public String getNotation() {
		return notation;
	}
	public void setNotation(String notation) {
		this.notation = notation;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<Mark> getMarks() {
		return marks;
	}
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	
	
}
