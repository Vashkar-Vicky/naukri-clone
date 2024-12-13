package io.mountblue.naukriproject.repository;

import io.mountblue.naukriclone.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, UUID> {
    Employer getEmployerById(UUID id);
}
