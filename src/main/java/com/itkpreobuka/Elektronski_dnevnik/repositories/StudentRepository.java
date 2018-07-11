package com.itkpreobuka.Elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itkpreobuka.Elektronski_dnevnik.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity,Integer> {

}
