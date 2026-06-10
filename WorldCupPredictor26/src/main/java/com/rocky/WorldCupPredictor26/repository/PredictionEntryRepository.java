package com.rocky.WorldCupPredictor26.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rocky.WorldCupPredictor26.model.PredictionEntry;
import com.rocky.WorldCupPredictor26.model.UserEntry;

public interface PredictionEntryRepository extends JpaRepository<PredictionEntry, Integer> {

    PredictionEntry findByUser(UserEntry user);

    List<PredictionEntry> findAllByOrderByUpdatedAtDesc();

    long countByTeamId(Integer teamId);
}
