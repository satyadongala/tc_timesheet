package com.tc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tc.model.AssignmentModel;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentModel, Long> {

}
