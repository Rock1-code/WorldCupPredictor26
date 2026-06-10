package com.rocky.WorldCupPredictor26;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rocky.WorldCupPredictor26.model.TeamEntry;
import com.rocky.WorldCupPredictor26.repository.TeamEntryRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final TeamEntryRepository teamRepo;

    public DataLoader(TeamEntryRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        addTeam("Argentina", "ar");
        addTeam("Brazil", "br");
        addTeam("France", "fr");
        addTeam("England", "gb-eng");
        addTeam("Germany", "de");
        addTeam("Spain", "es");
        addTeam("Portugal", "pt");
        addTeam("Netherlands", "nl");
        addTeam("Italy", "it");
        addTeam("United States", "us");
        addTeam("Mexico", "mx");
        addTeam("Canada", "ca");
        addTeam("Japan", "jp");
        addTeam("South Korea", "kr");
        addTeam("Morocco", "ma");
        addTeam("Nigeria", "ng");
        addTeam("Ghana", "gh");
        addTeam("Senegal", "sn");
        addTeam("Australia", "au");
        addTeam("Switzerland", "ch");
        addTeam("Denmark", "dk");
        addTeam("Croatia", "hr");
    }

    private void addTeam(String teamName, String countryCode) {
        TeamEntry team = teamRepo.findByTeamName(teamName);

        if (team == null) {
            teamRepo.save(new TeamEntry(teamName, countryCode));
        } else if (team.getCountryCode() == null || team.getCountryCode().trim().isEmpty()) {
            team.setCountryCode(countryCode);
            teamRepo.save(team);
        }
    }
}