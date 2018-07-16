package com.itkpreobuka.Elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itkpreobuka.Elektronski_dnevnik.entities.dto.CreateUserDTO;

public interface DtoRepository extends CrudRepository<CreateUserDTO,Integer> {

}
