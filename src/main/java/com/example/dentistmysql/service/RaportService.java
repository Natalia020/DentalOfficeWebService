package com.example.dentistmysql.service;

import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Raport;
import com.example.dentistmysql.repository.OrdersRepository;
import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Raport;
import com.example.dentistmysql.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaportService {
    @Autowired
    OrdersRepository ordersRepository;

    public List<Raport> getAllRaports(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Orders> pagedResult = ordersRepository.findAll(paging);
        //List<Orders> orders = ordersRepository.findAll();

        return pagedResult
                .stream()
                .map(order -> new Raport(order.getId(),order.getDateFrom(), order.getDateTo(), order.getPatientName(), order.getPatientSurname(), order.getOrderType(), order.getInfo(), order.getStatus(), order.getUsers()))
                .collect(Collectors.toList());
    }

    public List<Orders> findByKeyword(String parameter, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Orders> pagedResult = ordersRepository.findAll(paging);
        return ordersRepository.findByKeyword(parameter, paging);
    }
}


