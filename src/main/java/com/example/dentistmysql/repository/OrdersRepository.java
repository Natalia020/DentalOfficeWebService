package com.example.dentistmysql.repository;

import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {
    @Transactional
    @Modifying
    @Query("update Orders o set o.status = ?1 where o.id = ?2")
    int updateOrderStatus(String status, Long id);

    @Query(value = "select * from Orders o where o.patient_name like %:parameter% or o.patient_surname like %:parameter% or o.date_from like %:parameter% or o.date_to like %:parameter% or o.info like %:parameter% or o.order_type like %:parameter% or o.status like %:parameter%", nativeQuery = true)
    List<Orders> findByKeyword(@Param("parameter") String parameter, Pageable pageable);

    @Query("select u from Users u where u.username = ?1 ")
    public <Users> Users getUserId(String searchTerm);


}
