package com.mitrais.chipper.tk.be.profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitrais.chipper.tk.be.profileservice.entity.Profile;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT a from Profile a WHERE a.userId = :userId")
    Optional<Profile> findByUserId(@Param("userId") Long userId);
    
	@Query("SELECT a from Profile a WHERE a.photoProfileFilename = :fileName")
	Optional<Profile> findByPhotoProfileFilename(@Param("fileName") String fileName);

}
