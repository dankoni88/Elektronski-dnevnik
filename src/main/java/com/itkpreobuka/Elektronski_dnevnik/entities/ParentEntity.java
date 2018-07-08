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
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="Parents")
public class ParentEntity extends UserEntity {
	@JsonIgnore
	@OneToMany(mappedBy="parent",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	private List<StudentEntity> children=new ArrayList<>();
	public ParentEntity() {}
	
	public List<StudentEntity> getChildren() {
		return children;
	}
	public void setChildren(List<StudentEntity> children) {
		this.children = children;
	}
	

}
