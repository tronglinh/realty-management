package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    void deleteByBuildingId(Long buildingId);
    List<RentAreaEntity> findByBuildingId(Long id);
    void deleteByValueAndBuildingId(Integer value, Long buildingId);
}
