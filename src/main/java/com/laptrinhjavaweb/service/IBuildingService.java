package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingDeleteRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingSearchResponse;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBuildingService {
    Map<String, String> getDistricts();
    Map<String, String> getBuildingTypes();
    List<BuildingSearchResponse> findAll();
    void save(BuildingDTO buildingDTO);
    void deleteById(BuildingDeleteRequest buildingDeleteRequest);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) throws SQLException;
    List<StaffResponseDTO> findStaffs(Long buildingId);
    void assignStaff(StaffBuildingRequest staffBuildingRequest);
    BuildingDTO findById(Long id);

}
