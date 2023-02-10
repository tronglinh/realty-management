package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.response.BuildingSearchResponse;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IBuildingService;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBuildingService buildingService;

    public BuildingDTO convertToDTO (BuildingEntity entity){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        return result;
    }
    public RentAreaDTO covertToRentAreaDTO(RentAreaEntity entity){
        RentAreaDTO result = modelMapper.map(entity, RentAreaDTO.class);
        return  result;
    }


    public BuildingSearchBuilder convertRequestToBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder().setName(buildingSearchRequest.getName())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setDistrict(buildingSearchRequest.getDistrict())
                .setWard(buildingSearchRequest.getWard())
                .setStreet(buildingSearchRequest.getStreet())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setDirection(buildingSearchRequest.getDirection())
                .setLevel(buildingSearchRequest.getLevel())
                .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                .setRentCostFrom(buildingSearchRequest.getRentCostFrom())
                .setRentCostTo(buildingSearchRequest.getRentCostTo())
                .setManagerName(buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setEmployee(buildingSearchRequest.getStaffId())
                .setBuildingTypes(buildingSearchRequest.getBuildingTypes())
                .build();
        return builder;
    }
    public BuildingSearchResponse convertToBuildingSearchResponse (BuildingEntity entity){
        BuildingSearchResponse result = modelMapper.map(entity, BuildingSearchResponse.class);
        result.setAddress(result.getStreet() + ", " + result.getWard() + ", " + buildingService.getDistricts().get(result.getDistrict()));
        return result;
    }

    public BuildingEntity convertToEntity (BuildingDTO dto){
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
        return result;
    }
}
