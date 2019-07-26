package com.test.task.controller;

import com.test.task.domain.Employee;
import com.test.task.repos.EmployeeRepo;
import com.test.task.repos.GoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private GoodRepo goodsRepo;

    @RequestMapping("/main")
    public String index () {

        Iterable<Employee> users = employeeRepo.findAll();
        String uList = users.toString();

        System.out.println(users);

        System.out.println(goodsRepo.findByType(1));

        return "ok =>" + uList;
    }

    @RequestMapping("/user")
    public String showUserMsg()
    {
        return "User has logged in!!!";
    }
}
