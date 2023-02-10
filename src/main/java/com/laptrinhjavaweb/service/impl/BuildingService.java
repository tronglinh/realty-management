package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.request.BuildingDeleteRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingSearchResponse;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserConverter userConverter;

    @Override
    public Map<String, String> getDistricts() {
        Map<String, String> districts = new HashMap<>();
        for (DistrictsEnum item : DistrictsEnum.values()) {
            districts.put(item.toString(), item.getDistrictValue());
        }
        return districts;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) throws SQLException {
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            buildingSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        BuildingSearchBuilder buildingSearchBuilder = buildingConverter.convertRequestToBuilder(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.getBuilding(buildingSearchBuilder);
        for(BuildingEntity item: buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.convertToBuildingSearchResponse(item);

            result.add(buildingSearchResponse);
        }

        return result;
    }
//tham khao them exist... để rút gọn code
    @Override
    public List<StaffResponseDTO> findStaffs(Long buildingId) {

        List<StaffResponseDTO> result = new ArrayList<>();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        for(UserEntity item: staffs){

            StaffResponseDTO staffResponseDTO = userConverter.convertToStaffDTO(item);

            result.add(staffResponseDTO);
        }
        for(StaffResponseDTO staffResponseDTO: result){
            List<BuildingEntity> buildingEntities = buildingRepository.findBuildingByUser(staffResponseDTO.getId());

            for(BuildingEntity item: buildingEntities){
                BuildingSearchResponse buildingSearchResponse = buildingConverter.convertToBuildingSearchResponse(item);
                if(buildingSearchResponse.getId() == buildingId){
                    staffResponseDTO.setChecked("checked");

                }

            }
        }

        return result;
    }

    @Override
    @Transactional
    public void assignStaff(StaffBuildingRequest staffBuildingRequest){
        Long[] checkedRequestStaffsId = staffBuildingRequest.getStaffIds();
        List<Long> checkedRequestStaffsIdList = new ArrayList<>();
        List<UserEntity> userList = new ArrayList<>();
        BuildingEntity buildingEntity = buildingRepository.findOne(staffBuildingRequest.getBuildingId());
        for(Long item: checkedRequestStaffsId){
            checkedRequestStaffsIdList.add(item);
            userList.add(userRepository.findOne(item));
        }

        buildingEntity.setUsers(userList);
        buildingRepository.save(buildingEntity);

    }
    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findOne(id);
        BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
        if(!StringUtils.isNullOrEmpty(buildingDTO.getType())){
            buildingDTO.setBuildingTypes(buildingDTO.getType().split(","));

        }

        List<RentAreaEntity> data = rentAreaRepository.findByBuildingId(id);
        List<Long> rentArea= new ArrayList<>();

        for(RentAreaEntity rentAreaEntity: data) {
            rentArea.add(buildingConverter.covertToRentAreaDTO(rentAreaEntity).getValue());
        }

        String[] rentArea2 = new String[rentArea.size()];

        for(int i = 0; i < rentArea.size(); i++){
            rentArea2[i] = String.valueOf(rentArea.get(i));
        }
        buildingDTO.setRentArea(String.join(",", rentArea2));
        return buildingDTO;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String, String> buildingTypes = new HashMap<>();
        for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
            buildingTypes.put(item.toString(), item.getBuildingTypeValue());
        }
        return buildingTypes;
    }
    @Override
    public List<BuildingSearchResponse> findAll() {
        List<BuildingSearchResponse> results = new ArrayList<>();
        List<BuildingEntity> entities = buildingRepository.findAll();
        for(BuildingEntity item: entities){
            BuildingSearchResponse buildingSearchResponse = buildingConverter.convertToBuildingSearchResponse(item);
            results.add(buildingSearchResponse);
        }
        return results;
    }

    @Override
    @Transactional
    public void save(BuildingDTO buildingDTO) {
        String buildingType = String.join(",", buildingDTO.getBuildingTypes());
        buildingDTO.setType(buildingType);
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);

        if(!StringUtils.isNullOrEmpty(buildingDTO.getRentArea())){
            List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findByBuildingId(buildingDTO.getId());
            List<RentAreaDTO> rentAreaDTOList = new ArrayList<>();
            for(RentAreaEntity rentAreaEntity: rentAreaEntityList){
                RentAreaDTO rentAreaDTO = buildingConverter.covertToRentAreaDTO(rentAreaEntity);
                rentAreaDTOList.add(rentAreaDTO);
            }
            List<Long> rentAreaValue = new ArrayList<>();
            for(RentAreaDTO item: rentAreaDTOList){
                rentAreaValue.add(item.getValue());
            }
            String rentAreaTotal = buildingDTO.getRentArea();
            for(Long item: rentAreaValue){
                if(!rentAreaTotal.contains(item.toString())){
                    int value = Integer.valueOf(item.toString());
                    rentAreaRepository.deleteByValueAndBuildingId(value,buildingDTO.getId());
                }
            }

            String[] str = buildingDTO.getRentArea().split(",");
            List<RentAreaEntity> list = new ArrayList<>();
            for(String data : str) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaEntity.setValue(Integer.valueOf(data));
                list.add(rentAreaEntity);

            }
            buildingEntity.setRentAreas(list);
        }
        if (buildingDTO.getId() != null) {
            List<UserEntity> userEntityList = userRepository.findByBuildings_Id(buildingDTO.getId());
            buildingEntity.setUsers(userEntityList);
        }
        buildingRepository.save(buildingEntity);

    }
    @Transactional
    @Override
    public void deleteById(BuildingDeleteRequest buildingDeleteRequest) {
        for(Long data: buildingDeleteRequest.getBuildingIds()) {
            rentAreaRepository.deleteByBuildingId(data);
            buildingRepository.delete(data);
        }
    }


}
