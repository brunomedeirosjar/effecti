package com.bids.effecti.repository;

import com.bids.effecti.entity.SendEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SendEmailRepository extends JpaRepository<SendEmail, Long> {
    List<SendEmail> findByEmployerId(Long idEmployer);

    SendEmail findByEmployerIdAndGuid(Long idEmployer, Long guild);
}
