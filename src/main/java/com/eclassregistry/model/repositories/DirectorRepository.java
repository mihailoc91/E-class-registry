/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.DirectorEntity;
import com.eclassregistry.model.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */

@Repository
public interface DirectorRepository extends PagingAndSortingRepository<DirectorEntity, Integer>{
    
    DirectorEntity findByJmbg(UserEntity userEntity);
}
