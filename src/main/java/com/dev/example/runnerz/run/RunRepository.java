package com.dev.example.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream().filter(run -> run.id().equals(id)).findFirst();
    }

    void createRun(Run run) {
        runs.add(run);
    }

    void updateRun(Run run, Integer id) {
        Optional<Run> optionalRun = findById(id);
        if (optionalRun.isPresent()) {
            runs.set(runs.indexOf(optionalRun.get()), run);
        }
    }

    void deleteRun(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(1,
                "First Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                5,
                Location.OUTDOOR));

        runs.add(new Run(2,
                "First Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(2, ChronoUnit.HOURS),
                4,
                Location.INDORE));
    }
}
