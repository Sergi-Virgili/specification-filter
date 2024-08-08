package com.factoriaf5.specification;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Schema(description = "Details about the project")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the project", example = "1")
    private Long id;

    @Schema(description = "Name of the project", example = "Project Alpha")
    private String name;

    @Schema(description = "Name of the project manager", example = "Alice")
    private String manager;

    @Schema(description = "Location where the project is based", example = "New York")
    private String location;

    @Schema(description = "Start date of the project", example = "2024-01-01")
    private LocalDate startDate;

    @Schema(description = "End date of the project", example = "2024-06-01")
    private LocalDate endDate;

    @Schema(description = "Detailed description of the project", example = "This project aims to...")
    private String description;

}

