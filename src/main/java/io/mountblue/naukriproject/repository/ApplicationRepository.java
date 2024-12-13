package io.mountblue.naukriproject.repository;

import io.mountblue.naukriclone.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    List<Application> findByJobSeekerId(UUID jobSeekerId);

    List<Application> findByJobId(UUID jobId);

    int countByJobId(UUID id);

    @Query("SELECT a FROM Application a WHERE a.jobSeeker.id = :jobSeekerId AND a.status = 'Applied'")
    List<Application> findByJobSeekerIdAndStatus(UUID jobSeekerId);
}
