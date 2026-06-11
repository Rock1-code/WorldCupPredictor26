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

        // Hosts
        addTeam("Canada", "ca");
        addTeam("Mexico", "mx");
        addTeam("United States", "us");

        // AFC
        addTeam("Australia", "au");
        addTeam("Iraq", "iq");
        addTeam("Iran", "ir");
        addTeam("Japan", "jp");
        addTeam("Jordan", "jo");
        addTeam("Korea Republic", "kr");
        addTeam("Qatar", "qa");
        addTeam("Saudi Arabia", "sa");
        addTeam("Uzbekistan", "uz");

        // CAF
        addTeam("Algeria", "dz");
        addTeam("Cabo Verde", "cv");
        addTeam("Congo DR", "cd");
        addTeam("Cote d'Ivoire", "ci");
        addTeam("Egypt", "eg");
        addTeam("Ghana", "gh");
        addTeam("Morocco", "ma");
        addTeam("Senegal", "sn");
        addTeam("South Africa", "za");
        addTeam("Tunisia", "tn");

        // CONCACAF
        addTeam("Curacao", "cw");
        addTeam("Haiti", "ht");
        addTeam("Panama", "pa");

        // CONMEBOL
        addTeam("Argentina", "ar");
        addTeam("Brazil", "br");
        addTeam("Colombia", "co");
        addTeam("Ecuador", "ec");
        addTeam("Paraguay", "py");
        addTeam("Uruguay", "uy");

        // OFC
        addTeam("New Zealand", "nz");

        // UEFA
        addTeam("Austria", "at");
        addTeam("Belgium", "be");
        addTeam("Bosnia and Herzegovina", "ba");
        addTeam("Croatia", "hr");
        addTeam("Czechia", "cz");
        addTeam("England", "gb-eng");
        addTeam("France", "fr");
        addTeam("Germany", "de");
        addTeam("Netherlands", "nl");
        addTeam("Norway", "no");
        addTeam("Portugal", "pt");
        addTeam("Scotland", "gb-sct");
        addTeam("Spain", "es");
        addTeam("Sweden", "se");
        addTeam("Switzerland", "ch");
        addTeam("Türkiye", "tr");
    }

    private void addTeam(String teamName, String countryCode) {

        TeamEntry team = teamRepo.findByTeamName(teamName);

        if (team == null) {
            teamRepo.save(new TeamEntry(teamName, countryCode));
        }
        else if (team.getCountryCode() == null || team.getCountryCode().trim().isEmpty()) {
            team.setCountryCode(countryCode);
            teamRepo.save(team);
        }
    }
}