package com.dev.example.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> optionalRun = runRepository.findById(id);

        if (optionalRun.isEmpty()) {
            throw new RunNotFoundException();
        }

        return optionalRun.get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void createRun(@Valid @RequestBody Run run) {
        runRepository.createRun(run);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRun(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.updateRun(run, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRun(@PathVariable Integer id) {
        runRepository.deleteRun(id);
    }
}
