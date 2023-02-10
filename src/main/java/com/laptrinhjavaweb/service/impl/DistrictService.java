package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public Map<Long, String> getDistrictList() {
        Map<Long, String> result = new HashMap<>();
        List<DistrictEntity> district = districtRepository.findAll();
        for(DistrictEntity item: district){
            result.put(item.getId(), item.getName());
        }
        return result;
    }
}
