package com.harshchoudhary.projects.AirBnb_SpringBoot.repository;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    boolean existsByRoom_Id(Long roomId);

    @Modifying
    @Query("DELETE FROM Booking b WHERE b.room.id = :roomId")
    void deleteAllByRoomId(@Param("roomId") Long roomId);
}
