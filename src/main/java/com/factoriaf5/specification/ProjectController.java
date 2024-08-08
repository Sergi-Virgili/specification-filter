package com.factoriaf5.specification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "Project Management API", description = "API for managing projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    @Operation(summary = "Get projects", description = "Get a list of projects with optional filters, pagination, and sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found projects", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public Page<Project> getProjects(
            @Parameter(description = "Filter by project name", example = "Project Alpha") @RequestParam Optional<String> name,
            @Parameter(description = "Filter by project manager", example = "Alice") @RequestParam Optional<String> manager,
            @Parameter(description = "Filter by project location", example = "New York") @RequestParam Optional<String> location,
            @Parameter(description = "Filter by start date (ISO format)", example = "2024-01-01") @RequestParam Optional<String> startDate,
            @Parameter(description = "Filter by end date (ISO format)", example = "2024-12-31") @RequestParam Optional<String> endDate,
            @Parameter(description = "Sort by field", example = "startDate") @RequestParam Optional<String> sortBy,
            @Parameter(description = "Sort order (asc/desc)", example = "desc") @RequestParam Optional<String> order,
            @Parameter(description = "Page number", example = "0") @RequestParam Optional<Integer> page,
            @Parameter(description = "Page size", example = "10") @RequestParam Optional<Integer> size) {

        Specification<Project> spec = new ProjectSpecificationsBuilder()
                .withName(name)
                .withManager(manager)
                .withLocation(location)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .build();

        Sort.Direction direction = order.orElse("asc").equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy.orElse("startDate"));
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), sort);

        return projectRepository.findAll(spec, pageable);
    }
}