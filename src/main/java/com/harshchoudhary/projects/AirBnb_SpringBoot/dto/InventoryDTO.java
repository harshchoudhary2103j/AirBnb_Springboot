package com.harshchoudhary.projects.AirBnb_SpringBoot.dto;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InventoryDTO {
    private Long id;
    private LocalDate date;
    private Integer bookedCount;
    private Integer reservedCount;
    private Integer totalCount;
    private BigDecimal surgeFactor;
    private BigDecimal price; //Price = basePrice*surgeFactor
    private Boolean closed;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
}
