package com.infosys.wmat.repository;

import com.infosys.wmat.entity.PractitionerProfile;
import com.infosys.wmat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PractitionerProfileRepository extends JpaRepository<PractitionerProfile, Long> {
    // CHANGE: Search by the "User" object, not "UserId"
    Optional<PractitionerProfile> findByUser(User user);
}