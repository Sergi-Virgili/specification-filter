package com.factoriaf5.specification;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ProjectSpecificationsBuilder {
    private Specification<Project> specification;

    public ProjectSpecificationsBuilder() {
        this.specification = Specification.where(null);
    }

    public ProjectSpecificationsBuilder withName(Optional<String> name) {
        name.ifPresent(n -> specification = specification.and(ProjectSpecification.hasName(n)));
        return this;
    }

    public ProjectSpecificationsBuilder withManager(Optional<String> manager) {
        manager.ifPresent(m -> specification = specification.and(ProjectSpecification.hasManager(m)));
        return this;
    }

    public ProjectSpecificationsBuilder withLocation(Optional<String> location) {
        location.ifPresent(l -> specification = specification.and(ProjectSpecification.hasLocation(l)));
        return this;
    }

    public ProjectSpecificationsBuilder withStartDate(Optional<String> startDate) {
        startDate.ifPresent(d -> {
            LocalDate date = LocalDate.parse(d, DateTimeFormatter.ISO_DATE);
            specification = specification.and(ProjectSpecification.startsAfter(date));
        });
        return this;
    }

    public ProjectSpecificationsBuilder withEndDate(Optional<String> endDate) {
        endDate.ifPresent(d -> {
            LocalDate date = LocalDate.parse(d, DateTimeFormatter.ISO_DATE);
            specification = specification.and(ProjectSpecification.endsBefore(date));
        });
        return this;
    }

    public Specification<Project> build() {
        return this.specification;
    }
}

