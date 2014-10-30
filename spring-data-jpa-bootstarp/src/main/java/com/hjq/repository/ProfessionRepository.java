package com.hjq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hjq.entity.Profession;

@Repository("prosessionRepository")
public interface ProfessionRepository extends JpaRepository<Profession, Long> {

}
