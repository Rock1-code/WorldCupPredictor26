package com.rocky.WorldCupPredictor26.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TeamEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String teamName;
    private String countryCode;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<PredictionEntry> predictions = new ArrayList<>();

    public TeamEntry() {
    }

    public TeamEntry(String teamName, String countryCode) {
        this.teamName = teamName;
        this.countryCode = countryCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getTeamName() {
        return teamName;
    }

	public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<PredictionEntry> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<PredictionEntry> predictions) {
        this.predictions = predictions;
    }
}