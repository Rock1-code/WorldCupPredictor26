package com.rocky.WorldCupPredictor26.dto;

public class PredictionEntryDto {

    private Integer id;
    private Integer userId;
    private String userName;
    private Integer teamId;
    private String teamName;
    private String countryCode;
    private String updatedAt;

    public PredictionEntryDto() {
    }

    public PredictionEntryDto(Integer id, Integer userId, String userName,
                              Integer teamId, String teamName, String countryCode,
                              String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.teamId = teamId;
        this.teamName = teamName;
        this.countryCode = countryCode;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getUserId() {
        return userId;
    }

	public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public String getUserName() {
        return userName;
    }

	public void setUserName(String userName) {
        this.userName = userName;
    }

	public Integer getTeamId() {
        return teamId;
    }

	public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

	public String getUpdatedAt() {
        return updatedAt;
    }

	public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}