package com.rocky.WorldCupPredictor26.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PredictionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private UserEntry user;

    @ManyToOne
    private TeamEntry team;

    public PredictionEntry() {
    }

    public PredictionEntry(UserEntry user, TeamEntry team) {
        this.user = user;
        this.team = team;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public LocalDateTime getCreatedAt() {
        return createdAt;
    }

	public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

	public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

	public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

	public UserEntry getUser() {
        return user;
    }

	public void setUser(UserEntry user) {
        this.user = user;
    }

	public TeamEntry getTeam() {
        return team;
    }

	public void setTeam(TeamEntry team) {
        this.team = team;
    }
}
