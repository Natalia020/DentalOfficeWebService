package com.example.dentistmysql.controller;

import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.repository.OrdersRepository;
import com.example.dentistmysql.service.OrdersService;
import com.example.dentistmysql.service.RaportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class TechnicianController {

    @Autowired
    RaportService raportService;

    @Autowired
    OrdersService ordersService;
    @Autowired
    OrdersRepository ordersRepository;


    /*@RequestMapping("/ordersTech")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String ordersPage(Model model) {
        model.addAttribute("ordersTech", raportService.getAllRaports());

        return "ordersTech";
    }*/

    @RequestMapping(value = "/technician", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_TECHNICIAN')")
    public String technicianPage() {return "technician";}

    @RequestMapping("/ordersTech{pageNo}")
    @PreAuthorize("hasAuthority('ROLE_TECHNICIAN')")
    public String ordersFromDentist(Model model, String parameter, @RequestParam(defaultValue = "30") Integer pageSize, @PathVariable(required = false) Integer pageNo) {

        if(pageNo == null) {
            pageNo=1;
        }

        pageNo = pageNo-1;

        if (parameter!= null) {
            model.addAttribute("ordersTech", raportService.findByKeyword(parameter, pageNo, pageSize));
        } else {
            model.addAttribute("ordersTech", raportService.getAllRaports(pageNo, pageSize));
        }

        return "ordersTech";
    }

    @GetMapping("/changeOrderStatusForm")
    @PreAuthorize("hasAuthority('ROLE_TECHNICIAN')")
    public ModelAndView changeOrderStatusForm(@RequestParam Long orderId) {
        ModelAndView mav = new ModelAndView("changeOrderStatusForm");
        Orders orders = ordersRepository.findById(orderId).get();
        mav.addObject("orders", orders);
        return mav;
    }

    @PostMapping("/saveOrderStatus")
    @PreAuthorize("hasAuthority('ROLE_TECHNICIAN')")
    public String saveOrderStatus(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam("dateFrom") LocalDate DateFrom,
                                  @RequestParam("dateTo") LocalDate DateTo,
                                  @RequestParam("patientName") String PatientName,
                                  @RequestParam("patientSurname")String PatientSurname,
                                  @RequestParam("orderType") String OrderType,
                                  @RequestParam("info") String Info,
                                  @RequestParam("status") String Status
                                  /*@RequestParam("users") Users users*/){

        Orders orders = ordersService.updateOrder(id, DateFrom, DateTo, PatientName, PatientSurname, OrderType, Info, Status /*users*/);
        System.out.println(orders);
        ordersRepository.save(orders);
        return "redirect:/ordersTech";
    }
}
