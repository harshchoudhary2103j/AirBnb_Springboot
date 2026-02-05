package com.harshchoudhary.projects.AirBnb_SpringBoot.repository;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Guest;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}