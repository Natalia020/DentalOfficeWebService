package com.example.dentistmysql.controller;

import com.example.dentistmysql.model.OrderForm;
import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Store;
import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.service.OrdersService;
import com.example.dentistmysql.service.RaportService;
import com.example.dentistmysql.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class DentistController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    StoreService storeService;
    @Autowired
    RaportService raportService;


    //@GetMapping("/ordersDent")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //public String orderPage() {
       // return "ordersDent";
  //  }

    @RequestMapping(value = "/dentist", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String dentistPage() {return "dentist";}

    @GetMapping("/store{pageNo}")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String storePage(Model model, String parameter, @RequestParam(defaultValue = "10") Integer pageSize, @PathVariable(required = false) Integer pageNo) {

        if(pageNo == null) {
            pageNo=1;
        }
        pageNo = pageNo -1;

        if (parameter!= null) {
            model.addAttribute("store", storeService.findByKeywordProduct(parameter, pageNo, pageSize));
        } else {
            model.addAttribute("store", storeService.getAllProducts(pageNo, pageSize));
        }

        return "store";
    }

    @GetMapping(path = "/newOrder")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String getReserveForm() {
        return "newOrder";
    }

    @PostMapping(path = "/order-confirm")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String submitReserveForm(@RequestParam("dateFrom") String DateFromString,
                                    @RequestParam("dateTo") String DateToString,
                                    @RequestParam("patientName") String PatientName,
                                    @RequestParam("patientSurname")String PatientSurname,
                                    @RequestParam("orderType") String OrderType,
                                    @RequestParam("info") String Info,
                                    @RequestParam("status") String Status)
    {
        LocalDate DateFrom = LocalDate.parse(DateFromString);
        LocalDate DateTo = LocalDate.parse(DateToString);

        Orders orders = ordersService.createOrder(DateFrom, DateTo, PatientName, PatientSurname, OrderType, Info, Status);

        return "redirect:/book-confirm";
    }

    @GetMapping(path = "book-confirm")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String confirm() {
        return "neworder-success";
    }

    @RequestMapping("/ordersDent{pageNo}")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String ordersList(Model model, String parameter, @RequestParam(defaultValue = "30") Integer pageSize, @PathVariable(required = false) Integer pageNo) {

        if(pageNo == null) {
            pageNo=1;
        }
        pageNo = pageNo -1;
        if (parameter!= null) {
            model.addAttribute("ordersDent", raportService.findByKeyword(parameter, pageNo, pageSize));
        } else {
            model.addAttribute("ordersDent", raportService.getAllRaports(pageNo, pageSize));
        }

        return "ordersDent";
    }

    @GetMapping(path = "/newStore")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String newStore() {
        return "newStore";
    }

    @PostMapping(path = "/store-confirm")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String createStore(@RequestParam("storeType") String storeType,
                                    @RequestParam("Quantity") int Quantity){
        Store store = storeService.createStore(storeType, Quantity);

        return "redirect:/store-success";
    }

    @GetMapping(path = "store-success")
    @PreAuthorize("hasAuthority('ROLE_DENTIST')")
    public String confirmStore() {
        return "store-success";
    }



}
