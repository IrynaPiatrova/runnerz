package com.dev.example.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdk.jfr.DataAmount;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record Run(
        @NotNull
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
) {
    public Run {
            if (!completedOn.isAfter(startedOn)){
                throw new IllegalArgumentException("Completed on must be after started on");
            }
    }

}
