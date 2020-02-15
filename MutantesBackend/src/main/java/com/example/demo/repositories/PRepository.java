package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Ente;

@Repository
public interface PRepository extends JpaRepository<Ente,Integer> {

}
