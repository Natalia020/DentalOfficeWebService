package com.example.dentistmysql.controller;

import com.example.dentistmysql.model.Orders;
import com.example.dentistmysql.model.Store;
import com.example.dentistmysql.model.StoreRaport;
import com.example.dentistmysql.repository.OrdersRepository;
import com.example.dentistmysql.repository.StoreRepository;
import com.example.dentistmysql.service.RaportService;
import com.example.dentistmysql.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    RaportService raportService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    StoreService storeService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/storeAdmin{pageNo}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String storeAdmin(Model model, String parameter, @RequestParam(defaultValue = "10") Integer pageSize, @PathVariable(required = false) Integer pageNo) {
        if(pageNo == null) {
            pageNo=1;
        }
        pageNo = pageNo -1;

        if (parameter!= null) {
            model.addAttribute("storeAdmin", storeService.findByKeywordProduct(parameter, pageNo, pageSize));
        } else {
            model.addAttribute("storeAdmin", storeService.getAllProducts(pageNo, pageSize));
        }
        return "storeAdmin";
    }

    @RequestMapping("/ordersAdmin{pageNo}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String ordersAdmin(Model model, String parameter, @RequestParam(defaultValue = "30") Integer pageSize, @PathVariable(required = false) Integer pageNo) {

        if(pageNo == null) {
            pageNo=1;
        }

        pageNo = pageNo-1;

        if (parameter!= null) {
            model.addAttribute("ordersAdmin", raportService.findByKeyword(parameter, pageNo, pageSize));
        } else {
            model.addAttribute("ordersAdmin", raportService.getAllRaports(pageNo, pageSize));
        }

        return "ordersAdmin";
    }

    @RequestMapping("/change-quantity")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String changeQuantity() {
        return "updateStore";
    }

    @GetMapping("/deleteOrder")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteOrder(@RequestParam Long deleteOrderId) {
        ordersRepository.deleteById(deleteOrderId);
        return "redirect:/ordersAdmin";
    }

    @GetMapping("/deleteFromStore")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteFromStore(@RequestParam Long deleteProductId) {
        storeRepository.deleteById(deleteProductId);
        return "redirect:/storeAdmin";
    }
    @GetMapping("/showUpdateProductForm")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showUpdateProductForm(@RequestParam Long updateProductId) {
        ModelAndView mav = new ModelAndView("updateProductForm");
        Store store = storeRepository.findById(updateProductId).get();
        mav.addObject("store", store);
        return mav;
    }

    @PostMapping("/saveProduct")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    /*public String saveProduct(@ModelAttribute Store store) {
        storeRepository.save(store);
        return "redirect:/storeAdmin";
    }*/

    public String saveProduct(@RequestParam(value = "id", required = false) Long id,
                              @RequestParam("storeType") String storeType,
                              @RequestParam("Quantity") int Quantity){
            /*@RequestParam("users") Users users*/

        Store store = storeService.updateStore(id, storeType, Quantity);
        System.out.println(store);
        storeRepository.save(store);
        return "redirect:/storeAdmin";
    }

    @GetMapping("/modalDelete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String popupWindow() {
        return "modalDelete";
    }

}
