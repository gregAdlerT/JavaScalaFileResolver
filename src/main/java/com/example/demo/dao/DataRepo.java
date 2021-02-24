package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Greg Adler
 */
@Repository
public interface DataRepo extends JpaRepository<DataModel,Long> {
}
