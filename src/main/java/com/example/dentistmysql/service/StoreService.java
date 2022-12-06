package com.example.dentistmysql.service;

import com.example.dentistmysql.Security.ApplicationSecurityConfiguration;
import com.example.dentistmysql.model.*;
import com.example.dentistmysql.repository.StoreRepository;
import com.example.dentistmysql.repository.UserRepository;
import com.example.dentistmysql.Security.ApplicationSecurityConfiguration;
import com.example.dentistmysql.model.Store;
import com.example.dentistmysql.model.StoreRaport;
import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.repository.StoreRepository;
import com.example.dentistmysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationSecurityConfiguration applicationSecurityConfiguration;

    public Store createStore(String storeType, int Quantity) {
        Store s = new Store();
        Users users = userRepository.getUserId(applicationSecurityConfiguration.getUserName());
        s.setStoreType(storeType);
        s.setQuantity(Quantity);
        s.setUsers(users);
        return storeRepository.save(s);
    }

    public Store updateStore(Long id, String storeType, int Quantity) {
        Users users2 = userRepository.getUserId(applicationSecurityConfiguration.getUserName());
        Store store = new Store();
        store.setId(id);
        store.setStoreType(storeType);
        store.setQuantity(Quantity);
        store.setUsers(users2);

        return store;

    }

    public List<StoreRaport> getAllProducts(int pageNo, int pageSize) {
        //List<Store> stores= storeRepository.findAll();
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Store> stores = storeRepository.findAll(paging);

        return stores
                .stream()
                .map(store -> new StoreRaport(store.getId(),store.getStoreType(), store.getQuantity(), store.getUsers()))
                .collect(Collectors.toList());
    }

    public List<Store> findByKeywordProduct(String parameter, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Store> pagedResult = storeRepository.findAll(paging);
        return storeRepository.findByKeywordProduct(parameter, paging);
    }
}
