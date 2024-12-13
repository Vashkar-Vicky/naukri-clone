package io.mountblue.naukriproject.repository;

import io.mountblue.naukriclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<Object> findUserByEmail(String email);
}
