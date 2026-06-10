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
public class UserEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PredictionEntry> predictions = new ArrayList<>();

    public UserEntry() {
    }

    public UserEntry(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getEmail() {
        return email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public String getPassword() {
        return password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

    public List<PredictionEntry> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<PredictionEntry> predictions) {
        this.predictions = predictions;
    }
}