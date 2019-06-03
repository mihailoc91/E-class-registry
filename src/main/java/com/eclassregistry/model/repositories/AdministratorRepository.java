/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.AdministratorEntity;
import com.eclassregistry.model.entity.UserEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Integer>{
    
    AdministratorEntity findByJmbg(UserEntity userEntity);
    
}
