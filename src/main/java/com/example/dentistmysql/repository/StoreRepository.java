package com.example.dentistmysql.repository;

import com.example.dentistmysql.model.Store;
import com.example.dentistmysql.model.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends PagingAndSortingRepository<Store, Long> {

    @Query(value = "select * from Store s where s.quantity like %:parameter% or s.store_type like %:parameter%", nativeQuery = true)
    List<Store> findByKeywordProduct(@Param("parameter") String parameter, Pageable pageable);

    @Query("select u from Users u where u.username = ?1 ")
    public <Users> Users getUserId(String searchTerm);

}
