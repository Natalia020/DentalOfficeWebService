package com.example.dentistmysql.controller;

import com.example.dentistmysql.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class PublicController<principal> {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/pricelist")
    public String priceListPage() {
        return "pricelist";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

}
