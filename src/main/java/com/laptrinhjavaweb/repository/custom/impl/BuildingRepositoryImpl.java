package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> getBuilding(BuildingSearchBuilder buildingSearchBuilder) {

		String query = "select * from building b ";
		StringBuilder queryBuilder = new StringBuilder(query);

		if(buildingSearchBuilder.getEmployee() != null) {
			queryBuilder.append("LEFT JOIN " + "assignmentbuilding ab ON b.id = ab.buildingid ");
		}
		
		queryBuilder.append("WHERE 1=1 ");
		buildBuildingSearchPart1(buildingSearchBuilder, queryBuilder);
		buildBuildingSearchPart2(buildingSearchBuilder, queryBuilder);

		if(!StringUtils.isNullOrEmpty(buildingSearchBuilder.getDistrict())) {
			queryBuilder.append(" AND b.district like '%"+buildingSearchBuilder.getDistrict()+"%'");
		}
		if(buildingSearchBuilder.getEmployee() != null) {
			queryBuilder.append(" AND ab.staffid = '"+buildingSearchBuilder.getEmployee()+"'");
		}

		queryBuilder.append(" group by b.id");
		
		Query sql = entityManager.createNativeQuery(queryBuilder.toString(), BuildingEntity.class);
		// TODO Auto-generated method stub
		return sql.getResultList();
	}

	private void buildBuildingSearchPart1(BuildingSearchBuilder buildingSearchBuilder, StringBuilder queryBuilder) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field field: fields){
				field.setAccessible(true);
				String name = field.getName();
				if(!name.equals("district") && !name.equals("employee") && !name.equals("buildingTypes")
					&& !name.startsWith("rentCost") && !name.startsWith("rentArea")){
					Object objectValue = field.get(buildingSearchBuilder);
					if(objectValue != null){
						if(field.getType().getName().equals("java.lang.String")){
							String value = (String)objectValue;
							if(value != ""){
								queryBuilder.append(" AND b."+name.toLowerCase()+" LIKE '%"+value+"%'");
							}
						}else if(field.getType().getName().equals("java.lang.Integer")){
							Integer value = (Integer)objectValue;
							queryBuilder.append(" AND b."+name.toLowerCase()+" = "+value+"");
						}
					}
				}

			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	private void buildBuildingSearchPart2(BuildingSearchBuilder buildingSearchBuilder, StringBuilder queryBuilder) {
		if (buildingSearchBuilder.getRentAreaFrom() != null || buildingSearchBuilder.getRentAreaTo() != null) {
			queryBuilder.append(" AND EXISTS( SELECT * FROM rentarea r WHERE r.buildingid = b.id");

			if (buildingSearchBuilder.getRentAreaFrom() != null) {
				queryBuilder.append(" AND r.value >= '" + buildingSearchBuilder.getRentAreaFrom() + "'");
			}
			if (buildingSearchBuilder.getRentAreaTo() != null) {
				queryBuilder.append(" AND r.value <= '" + buildingSearchBuilder.getRentAreaTo() + "'");
			}
			queryBuilder.append(")");
		}

		if(buildingSearchBuilder.getRentCostFrom() != null) {
			queryBuilder.append(" AND b.rentPrice >= '"+buildingSearchBuilder.getRentCostFrom()+"'");
		}
		if(buildingSearchBuilder.getRentCostTo() != null) {
			queryBuilder.append(" AND b.rentPrice <= '"+buildingSearchBuilder.getRentCostTo()+"'");
		}

		if (buildingSearchBuilder.getBuildingTypes().length != 0) {
			queryBuilder.append(" AND (");

			/*String[] types = buildingSearchBuilder.getBuildingTypes();
			int i = 0;
			for(String item : types) {
				types[i] = " b.type like '%"+ item +"%'";
				i++;
			}
			String typesSQL = String.join("or", types);*/
			//java 8
			String typesSQL = Arrays.stream(buildingSearchBuilder.getBuildingTypes())
					.map(item -> "b.type like '%"+item+"%'").collect(Collectors.joining(" or "));
			queryBuilder.append(typesSQL);
			queryBuilder.append(")");
		}
	}

	@Override
	public List<BuildingEntity> findBuildingByUser(Long staffId) {
		String sql = "select * from building\n" +
				"inner join assignmentbuilding ab on ab.buildingid = building.id \n" +
				"where ab.staffid = '"+staffId+"'";
		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
		return query.getResultList();
	}

}
