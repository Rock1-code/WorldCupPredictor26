package com.rocky.WorldCupPredictor26.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rocky.WorldCupPredictor26.model.TeamEntry;

public interface TeamEntryRepository extends JpaRepository<TeamEntry, Integer> {

    TeamEntry findByTeamName(String teamName);

    List<TeamEntry> findAllByOrderByTeamNameAsc();
}