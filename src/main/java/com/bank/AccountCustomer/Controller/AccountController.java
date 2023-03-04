package com.bank.AccountCustomer.Controller;


import com.bank.AccountCustomer.Entity.CustomerData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.bank.AccountCustomer.Entity.AccountData;
import com.bank.AccountCustomer.Service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.util.Scanner;

//@RestController is applied to class to mark it as request handler
@RestController
//@RequestMapping is used to map web requests to Spring controller methods
@RequestMapping("/account")
public class AccountController {
    private final static Logger logger = Logger.getLogger(AccountService.class);
    //@Autowired enables to inject the object dependency implicitly
    @Autowired
    private AccountService accountService;

    //@Autowired enables to inject the object dependency implicitly
    @Autowired
    //Rest Template is used to create applications that consume RESTful Web Services.
    private RestTemplate restTemplate;

    public AccountController(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    //This Method is used for getting account details page
    @GetMapping(value = "/default")
    public String defaultMethod()
    {
        return "This is Account Details Page";
    }

    //This Method is used for creating new account
    @PostMapping(value = "/addAccount")
    public String addAccountDetails(@RequestBody AccountData accountData) {
        accountService.addAccount(accountData);
        return "Account details added successfully";
    }
    //This Method is used for updating or altering the details of existing account.
    @PutMapping(value = "/updateAccount")
    public AccountData updateCustomer(@RequestBody AccountData accountData)
    {
        return accountService.updateAccount(accountData);
    }

    //This Method is used for deleting customer by specifying id.
    @DeleteMapping(value = "/deleteAccountById/{id}")
    public String deleteCustomerById(@PathVariable int id)
    {
        return accountService.deleteAccountById(id);
    }

    @RequestMapping(value = "/template/customerDetails")
    public CustomerData[] getCustomers()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://localhost:9091/customerDetails", HttpMethod.GET, entity, CustomerData[].class).getBody();
    }

    @RequestMapping(value = "/template/customerDetails", method = RequestMethod.POST)
    public String createCustomer(@RequestBody CustomerData customerData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<CustomerData> entity = new HttpEntity<CustomerData>(customerData,headers);
        return restTemplate.exchange("http://localhost:8080/customerDetails", HttpMethod.POST, entity, String.class).getBody();
    }

    @RequestMapping(value = "/template/customerDetails/{id}", method = RequestMethod.PUT)
    public String updateCustomer(@PathVariable("id") String id, @RequestBody CustomerData customerData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<CustomerData> entity = new HttpEntity<CustomerData>(customerData,headers);
        return restTemplate.exchange("http://localhost:8080/customerDetails/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }

    @RequestMapping(value = "/template/customerDetailsData/{id}", method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<CustomerData> entity = new HttpEntity<CustomerData>(headers);
        return restTemplate.exchange("http://localhost:8080/customerDetailsData/"+id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}


