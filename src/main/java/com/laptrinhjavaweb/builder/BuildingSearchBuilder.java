package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	
	private String name;
	private Integer floorArea;
	private String district;
	private String ward;
	private String street;
	private Integer numberOfBasement;
	private String direction;
	private String level;
	private Integer rentAreaFrom;
	private Integer rentAreaTo;
	private Integer rentCostFrom;
	private Integer rentCostTo;
	private String managerName;
	private String managerPhone;
	private Long employee;
	private String[] buildingTypes = new String[] {};
	
	
	public String getName() {
		return name;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public String getDistrict() {
		return district;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}

	public Integer getRentAreaTo() {
		return rentAreaTo;
	}

	public Integer getRentCostFrom() {
		return rentCostFrom;
	}

	public Integer getRentCostTo() {
		return rentCostTo;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public Long getEmployee() {
		return employee;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.district = builder.district;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
		this.rentCostFrom = builder.rentCostFrom;
		this.rentCostTo = builder.rentCostTo;
		this.managerName = builder.managerName;
		this.managerPhone = builder.managerPhone;
		this.employee = builder.employee;
		this.buildingTypes = builder.buildingTypes;
		
	}
	
	public static class Builder {

		private String name;
		private Integer floorArea;
		private String district;
		private String ward;
		private String street;
		private Integer numberOfBasement;
		private String direction;
		private String level;
		private Integer rentAreaFrom;
		private Integer rentAreaTo;
		private Integer rentCostFrom;
		private Integer rentCostTo;
		private String managerName;
		private String managerPhone;
		private Long employee;
		private String[] buildingTypes = new String[] {};
		
		
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		
		
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		
		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}
		
		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}
		
		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}

		public Builder setRentAreaFrom(Integer rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}

		public Builder setRentAreaTo(Integer rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}

		public Builder setRentCostFrom(Integer rentCostFrom) {
			this.rentCostFrom = rentCostFrom;
			return this;
		}
		
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		
		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}

		public Builder setRentCostTo(Integer rentCostTo) {
			this.rentCostTo = rentCostTo;
			return this;
		}
		
		public Builder setEmployee(Long employee) {
			this.employee = employee;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
