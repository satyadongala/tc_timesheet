package com.tc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tc.model.ManagerModel;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerModel, Long> {

}
