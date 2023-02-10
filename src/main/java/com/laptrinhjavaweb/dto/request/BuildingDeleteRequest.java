package com.laptrinhjavaweb.dto.request;

public class BuildingDeleteRequest {
    Long[] buildingIds;

    public Long[] getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(Long[] buildingIds) {
        this.buildingIds = buildingIds;
    }
}
