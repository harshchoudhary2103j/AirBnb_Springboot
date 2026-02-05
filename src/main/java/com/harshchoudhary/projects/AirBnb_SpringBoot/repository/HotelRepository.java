package com.harshchoudhary.projects.AirBnb_SpringBoot.repository;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);
}