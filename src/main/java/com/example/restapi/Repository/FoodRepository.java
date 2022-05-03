package com.example.restapi.Repository;

import com.example.restapi.Entity.Crab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrabRepository extends JpaRepository<Crab, Integer> {
}
