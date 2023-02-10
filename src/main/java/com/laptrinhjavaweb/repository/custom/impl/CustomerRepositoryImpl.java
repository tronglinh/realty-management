package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.request.CustomerSearchRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> getCustomer(CustomerSearchRequest customerSearchRequest) {
        String str = "select customer.id, customer.fullname, group_concat(users.fullname) as staffname, customer.phone" +
                ", customer.email, customer.demand, customer.createdby, customer.createddate, customer.modifiedby" +
                ", customer.modifieddate, customer.company, customer.note " +
                "from customer " +
                "left join assignmentcustomer on assignmentcustomer.customerid = customer.id " +
                "left join users on users.id = assignmentcustomer.staffid where 1 = 1";
        StringBuilder queryBuilder =  new StringBuilder(str);

        if(!StringUtils.isNullOrEmpty(customerSearchRequest.getFullName())){
            queryBuilder.append(" AND customer.fullname like '%"+customerSearchRequest.getFullName()+"%'");
        }
        if(!StringUtils.isNullOrEmpty(customerSearchRequest.getPhone())){
            queryBuilder.append(" AND customer.phone like '%"+customerSearchRequest.getPhone()+"%'");
        }
        if(!StringUtils.isNullOrEmpty(customerSearchRequest.getEmail())){
            queryBuilder.append(" AND customer.email like '%"+customerSearchRequest.getEmail()+"%'");
        }
        if(customerSearchRequest.getStaffId() != null) {
            queryBuilder.append(" AND assignmentcustomer.staffid = '"+customerSearchRequest.getStaffId()+"'");
        }
        queryBuilder.append(" group by customer.id");
        Query query = entityManager.createNativeQuery(queryBuilder.toString(), CustomerEntity.class);
        return query.getResultList();

    }
 /*   private void buildCustomerSearchQuery(CustomerSearchRequest customerSearchRequest, StringBuilder queryBuilder){

    }*/

    @Override
    public List<CustomerEntity> findCustomerByUser(Long staffId) {
        String sql = "select * from customer " +
                "inner join assignmentcustomer ac on ac.customerid = customer.id \n" +
                "where ac.staffid = '"+staffId+"'";
        Query query = entityManager.createNativeQuery(sql, CustomerEntity.class);
        return query.getResultList();

    }
}
