package com.harshchoudhary.projects.AirBnb_SpringBoot.repository;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}