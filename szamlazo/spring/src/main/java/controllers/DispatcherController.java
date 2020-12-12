package controllers;

import helper.Credentials;
import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {

    @RequestMapping("/items")
    public String itemsPage(){
        return "items";
    }

    @RequestMapping("/register")
    public String customerForm(Model model){
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @RequestMapping("/login")
    public String customerLoginForm(Model model){
        model.addAttribute("credentials", new Credentials());
        return "login";
    }

    @RequestMapping("/cart")
    public String cartPage(){
        return "cart";
    }

    @RequestMapping("/user")
    public String userPage(){
        return "user";
    }
}
