package com.bids.effecti.repository;

import com.bids.effecti.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Employer findByCpnj(String cpnj);
}
