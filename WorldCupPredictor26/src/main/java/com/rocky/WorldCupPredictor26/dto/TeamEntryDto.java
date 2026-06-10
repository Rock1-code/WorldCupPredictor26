package com.rocky.WorldCupPredictor26.dto;

public class TeamEntryDto {

    private Integer id;
    private String teamName;
    private String countryCode;
    private Long votes;

    public TeamEntryDto() {
    }

    public TeamEntryDto(Integer id, String teamName, String countryCode, Long votes) {
        this.id = id;
        this.teamName = teamName;
        this.countryCode = countryCode;
        this.votes = votes;
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

	public Long getVotes() {
        return votes;
    }

	public void setVotes(Long votes) {
        this.votes = votes;
    }
}