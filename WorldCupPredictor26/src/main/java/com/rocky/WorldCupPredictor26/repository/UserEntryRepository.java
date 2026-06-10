package com.rocky.WorldCupPredictor26.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rocky.WorldCupPredictor26.model.UserEntry;

public interface UserEntryRepository extends JpaRepository<UserEntry, Integer> {

    UserEntry findByEmail(String email);
}