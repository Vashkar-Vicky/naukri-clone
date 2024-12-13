package io.mountblue.naukriproject.repository;

import io.mountblue.naukriclone.entity.JobSeeker;
import io.mountblue.naukriclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, UUID> {
    JobSeeker findByUser(User user);

    JobSeeker findByExperiencesId(UUID id);
}
