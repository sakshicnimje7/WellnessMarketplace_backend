package com.infosys.wmat.service;

import com.infosys.wmat.entity.TherapySession;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.TherapySessionRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired private TherapySessionRepository sessionRepository;
    @Autowired private UserRepository userRepository;

    public TherapySession bookSession(Long patientId, Long practitionerId, LocalDateTime date) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        User practitioner = userRepository.findById(practitionerId)
                .orElseThrow(() -> new RuntimeException("Practitioner not found"));

        TherapySession session = new TherapySession();
        // NOW WE SET OBJECTS, NOT IDs
        session.setPatient(patient);
        session.setPractitioner(practitioner);
        session.setDate(date);
        session.setStatus("SCHEDULED");

        return sessionRepository.save(session);
    }
}