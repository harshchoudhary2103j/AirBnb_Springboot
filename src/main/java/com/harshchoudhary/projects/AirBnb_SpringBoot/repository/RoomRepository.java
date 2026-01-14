package com.harshchoudhary.projects.AirBnb_SpringBoot.repository;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}