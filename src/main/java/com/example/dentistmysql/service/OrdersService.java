package com.example.dentistmysql.service;

import com.example.dentistmysql.Security.ApplicationSecurityConfiguration;
import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.repository.OrdersRepository;
import com.example.dentistmysql.repository.UserRepository;
import com.example.dentistmysql.Security.ApplicationSecurityConfiguration;
import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.repository.OrdersRepository;
import com.example.dentistmysql.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ApplicationSecurityConfiguration applicationSecurityConfiguration;

    Orders orders;

    public Orders updateOrder(Long id, LocalDate dateFrom, LocalDate dateTo, String patientName, String patientSurname, String orderType, String info, String status/*, Users users*/) {
        Users users2 = userRepository.getUserId(applicationSecurityConfiguration.getUserName());
        Orders orders1 = new Orders();
        orders1.setId(id);
        orders1.setDateFrom(dateFrom);
        orders1.setDateTo(dateTo);
        orders1.setPatientName(patientName);
        orders1.setPatientSurname(patientSurname);
        orders1.setOrderType(orderType);
        orders1.setInfo(info);
        orders1.setStatus(status);
        orders1.setUsers(users2);

        return orders1;

    }

    public Optional<Orders> getOrderById(Long id) {return ordersRepository.findById(id);}
    public Orders createOrder(LocalDate dateFrom, LocalDate dateTo, String patientName, String patientSurname, String orderType, String info, String status) {

        Orders o = new Orders();
        Users users = userRepository.getUserId(applicationSecurityConfiguration.getUserName());
        o.setDateFrom(dateFrom);
        o.setDateTo(dateTo);
        o.setPatientName(patientName);
        o.setPatientSurname(patientSurname);
        o.setOrderType(orderType);
        o.setInfo(info);
        o.setStatus(status);
        o.setUsers(users);

        return ordersRepository.save(o);
    }

    public void deleteOrder() {
        ordersRepository.deleteById(orders.getId());
    }


}
