package com.infosys.wmat.repository;

import com.infosys.wmat.entity.TherapySession;
import com.infosys.wmat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapySessionRepository extends JpaRepository<TherapySession, Long> {

    // For Patients: Find sessions where I am the patient
    List<TherapySession> findByPatient(User patient);

    // For Doctors: Find sessions where I am the practitioner
    // (This makes the dashboard useful for doctors too!)
    List<TherapySession> findByPractitioner(User practitioner);
}