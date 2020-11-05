package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {



}
