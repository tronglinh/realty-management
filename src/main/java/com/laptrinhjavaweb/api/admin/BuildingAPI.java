package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingDeleteRequest;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding){
        buildingService.save(newBuilding);
        return newBuilding;
    }
    @PutMapping
    public BuildingDTO updateBuilding(@RequestBody BuildingDTO newBuilding){
        buildingService.save(newBuilding);
        return newBuilding;
    }

    @GetMapping("/{buildingid}/staffs")
    public ResponseDTO loadStaff(@PathVariable("buildingid") Long buildingid){
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        System.out.println(buildingid);
        result.setData(buildingService.findStaffs(buildingid));

        return result;
    }

    @PostMapping("/assignment")
    public void assignStaff(@RequestBody StaffBuildingRequest staffBuildingRequest){
        buildingService.assignStaff(staffBuildingRequest);
    }
    @DeleteMapping
    public void deleteBuilding(@RequestBody BuildingDeleteRequest  buildingDeleteRequest ){
        buildingService.deleteById(buildingDeleteRequest);
    }


}
