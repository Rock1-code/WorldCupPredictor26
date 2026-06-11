package com.rocky.WorldCupPredictor26.controller;

import com.rocky.WorldCupPredictor26.dto.PredictionEntryDto;
import com.rocky.WorldCupPredictor26.dto.TeamEntryDto;
import com.rocky.WorldCupPredictor26.dto.UserEntryDto;
import com.rocky.WorldCupPredictor26.model.PredictionEntry;
import com.rocky.WorldCupPredictor26.model.TeamEntry;
import com.rocky.WorldCupPredictor26.model.UserEntry;
import com.rocky.WorldCupPredictor26.repository.PredictionEntryRepository;
import com.rocky.WorldCupPredictor26.repository.TeamEntryRepository;
import com.rocky.WorldCupPredictor26.repository.UserEntryRepository;
import com.rocky.WorldCupPredictor26.service.EmailService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ZoneId losAngelesZone = ZoneId.of("America/Los_Angeles");

    private final UserEntryRepository userRepo;
    private final TeamEntryRepository teamRepo;
    private final PredictionEntryRepository predictionRepo;
    private final EmailService emailService;

    public ApiController(UserEntryRepository userRepo,
                         TeamEntryRepository teamRepo,
                         PredictionEntryRepository predictionRepo,
                         EmailService emailService) {

        this.userRepo = userRepo;
        this.teamRepo = teamRepo;
        this.predictionRepo = predictionRepo;
        this.emailService = emailService;
    }

    private String formatLosAngelesTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        return dateTime
                .atZone(ZoneId.systemDefault())
                .withZoneSameInstant(losAngelesZone)
                .format(formatter);
    }

    @PostMapping("/register")
    public UserEntryDto register(@RequestBody UserEntryDto dto) {

        if (dto.getName() == null || dto.getName().trim().isEmpty()
                || dto.getEmail() == null || dto.getEmail().trim().isEmpty()
                || dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            return null;
        }

        if (userRepo.findByEmail(dto.getEmail()) != null) {
            return null;
        }

        UserEntry user = new UserEntry(
                dto.getName().trim(),
                dto.getEmail().trim(),
                dto.getPassword()
        );

        userRepo.save(user);

        return new UserEntryDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                ""
        );
    }

    @PostMapping("/login")
    public UserEntryDto login(@RequestBody UserEntryDto dto) {

        UserEntry user = userRepo.findByEmail(dto.getEmail());

        if (user == null || !user.getPassword().equals(dto.getPassword())) {
            return null;
        }

        return new UserEntryDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                ""
        );
    }

    @GetMapping("/teams")
    public List<TeamEntryDto> teams() {

        List<TeamEntryDto> dtos = new ArrayList<>();

        for (TeamEntry team : teamRepo.findAllByOrderByTeamNameAsc()) {
            dtos.add(new TeamEntryDto(
                    team.getId(),
                    team.getTeamName(),
                    team.getCountryCode(),
                    predictionRepo.countByTeamId(team.getId())
            ));
        }

        return dtos;
    }

    @GetMapping("/predictions")
    public List<PredictionEntryDto> predictions() {

        List<PredictionEntryDto> dtos = new ArrayList<>();

        for (PredictionEntry prediction : predictionRepo.findAllByOrderByUpdatedAtDesc()) {
            dtos.add(new PredictionEntryDto(
                    prediction.getId(),
                    prediction.getUser().getId(),
                    prediction.getUser().getName(),
                    prediction.getTeam().getId(),
                    prediction.getTeam().getTeamName(),
                    prediction.getTeam().getCountryCode(),
                    formatLosAngelesTime(prediction.getUpdatedAt())
            ));
        }

        return dtos;
    }

    @GetMapping("/prediction")
    public PredictionEntryDto myPrediction(@RequestParam Integer userId) {

        UserEntry user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        PredictionEntry prediction = predictionRepo.findByUser(user);

        if (prediction == null) {
            return null;
        }

        return new PredictionEntryDto(
                prediction.getId(),
                user.getId(),
                user.getName(),
                prediction.getTeam().getId(),
                prediction.getTeam().getTeamName(),
                prediction.getTeam().getCountryCode(),
                formatLosAngelesTime(prediction.getUpdatedAt())
        );
    }

    @PostMapping("/prediction")
    public PredictionEntryDto savePrediction(@RequestBody PredictionEntryDto dto) {

        UserEntry user = userRepo.findById(dto.getUserId()).orElse(null);
        TeamEntry team = teamRepo.findById(dto.getTeamId()).orElse(null);

        if (user == null || team == null) {
            return null;
        }

        PredictionEntry prediction = predictionRepo.findByUser(user);

        if (prediction == null) {
            prediction = new PredictionEntry(user, team);
        } else {
            prediction.setTeam(team);
            prediction.setUpdatedAt(LocalDateTime.now());
        }

        predictionRepo.save(prediction);

        emailService.sendPredictionConfirmation(
                user.getEmail(),
                user.getName(),
                team.getTeamName()
        );

        return new PredictionEntryDto(
                prediction.getId(),
                user.getId(),
                user.getName(),
                team.getId(),
                team.getTeamName(),
                team.getCountryCode(),
                formatLosAngelesTime(prediction.getUpdatedAt())
        );
    }
}