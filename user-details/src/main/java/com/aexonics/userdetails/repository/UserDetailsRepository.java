package com.aexonics.userdetails.repository;

import com.aexonics.userdetails.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID> {

    @Query(value = "select * from user_details where email = :email",nativeQuery = true)
    List<UserDetails> findAllByEmail(@Param("email") String email);
}
