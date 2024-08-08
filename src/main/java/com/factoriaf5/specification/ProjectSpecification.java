package com.factoriaf5.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProjectSpecification {

    public static Specification<Project> hasName(String name) {
        return (project, cq, cb) -> cb.like(cb.lower(project.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Project> hasManager(String manager) {
        return (project, cq, cb) -> cb.like(cb.lower(project.get("manager")), "%" + manager.toLowerCase() + "%");
    }

    public static Specification<Project> hasLocation(String location) {
        return (project, cq, cb) -> cb.like(cb.lower(project.get("location")), "%" + location.toLowerCase() + "%");
    }

    public static Specification<Project> startsAfter(LocalDate date) {
        return (project, cq, cb) -> cb.greaterThanOrEqualTo(project.get("startDate"), date);
    }

    public static Specification<Project> endsBefore(LocalDate date) {
        return (project, cq, cb) -> cb.lessThanOrEqualTo(project.get("endDate"), date);
    }
}

