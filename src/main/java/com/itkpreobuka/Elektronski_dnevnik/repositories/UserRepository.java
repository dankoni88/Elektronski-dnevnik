package com.itkpreobuka.Elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itkpreobuka.Elektronski_dnevnik.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Integer>{

}
