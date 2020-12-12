package controllers;

import JSON.CustomerDAOJSON;
import JSON.InvoiceDAOJSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.CustomerServiceImpl;
import service.InvoiceServiceImpl;

import java.io.IOException;

@Configuration
public class CustomerConfig {

    @Bean("customerJsonDB")
    public CustomerServiceImpl serviceJSON() throws IOException {
        return new CustomerServiceImpl(new CustomerDAOJSON("customer.json"));
    }

    @Bean("customerMysqlDB")
    public CustomerServiceImpl serviceMySQL(){
        return new CustomerServiceImpl(null);
    }

    @Bean("invoiceJsonDB")
    public InvoiceServiceImpl serviceInvoiceJSON() throws IOException {
        return new InvoiceServiceImpl(new InvoiceDAOJSON("invoice.json"));
    }
}
