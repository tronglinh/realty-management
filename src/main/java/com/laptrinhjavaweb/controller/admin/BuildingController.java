package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;


@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @Autowired
    private BuildingConverter buildingConverter;


    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequest buildingSearchRequest) throws SQLException {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        mav.addObject("buildings", buildingService.findAll(buildingSearchRequest));
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("typemaps", buildingService.getBuildingTypes());
        mav.addObject("districtmaps",buildingService.getDistricts());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingModel", new BuildingDTO());
        mav.addObject("typemaps", buildingService.getBuildingTypes());
        mav.addObject("districtmaps",buildingService.getDistricts());
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable(value = "id") Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findById(id);
        mav.addObject("buildingModel", buildingDTO);
        mav.addObject("typemaps", buildingService.getBuildingTypes());
        mav.addObject("districtmaps",buildingService.getDistricts());
        return mav;
    }


}
