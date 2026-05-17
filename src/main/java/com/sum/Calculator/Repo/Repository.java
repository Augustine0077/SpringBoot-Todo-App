package com.sum.Calculator.Repo;

import com.sum.Calculator.Entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Entity, Long>

{

}
