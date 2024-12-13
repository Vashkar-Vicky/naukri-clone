package io.mountblue.naukriproject.repository;

import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.entity.enums.WorkMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
//    Page<Job> findByLocationContainingIgnoreCase(String location, Pageable pageable);
    Page<Job> findByEmployerId(UUID employerId, Pageable pageable);

//    Page<Job> findAllWithFilters(String location, String salaryRange, Integer experienceRequired, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE " +
            "(:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
            "(:experienceRequired IS NULL OR j.experienceRequired = :experienceRequired) AND " +
            "(:companyName IS NULL OR LOWER(j.employer.companyName) LIKE LOWER(CONCAT('%', :companyName, '%')))")
    List<Job> searchJobs(
            @Param("title") String title,
            @Param("location") String location,
            @Param("experienceRequired") Integer experienceRequired,
            @Param("companyName") String companyName);

    @Query("SELECT j FROM Job j WHERE (:locations IS NULL OR j.location IN :locations)")
    List<Job> filterJobsByLocation(@Param("locations") List<String> locations);


    @Query("SELECT j FROM Job j WHERE j.employer.industry IN :industries")
    List<Job> filterJobsByIndustry(List<String> industries);

    @Query("SELECT j FROM Job j WHERE " +
            "(:locations IS NULL OR j.location IN :locations) AND " +
            "(:industry IS NULL OR j.employer.industry IN :industry) AND " +
            "(:jobType IS NULL OR j.jobType IN :jobType)")
    List<Job> filterJobs(@Param("locations") List<String> locations,
                         @Param("industry") List<String> industry,
                         @Param("jobType") List<String> jobType);



    @Query("SELECT j FROM Job j WHERE j.location IN :locations AND j.employer.industry IN :industries")
    List<Job> filterJobsByLocationAndIndustry(List<String> locations, List<String> industries);

    @Query("SELECT j FROM Job j WHERE j.jobType IN :jobType")
    List<Job> filterJobsByJobType(@Param("jobType") List<String> jobType);


    @Query("SELECT j FROM Job j WHERE " +
                  "(:locations IS NULL OR j.location IN :locations) AND " +
                  "(:industries IS NULL OR j.employer.industry IN :industries) AND " +
                  "(:jobTypes IS NULL OR j.jobType IN :jobTypes)")
    List<Job> filterJobsByLocationIndustryAndJobType(
            @Param("locations") List<String> locations,
            @Param("industries") List<String> industries,
            @Param("jobTypes") List<String> jobTypes
    );
    @Query("SELECT j FROM Job j WHERE j.workMode IN :workModes")
    List<Job> findByWorkModeIn(List<WorkMode> workModes);


}
