package service;

import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.Customer;

import java.util.Collection;

public interface CustomerService {
    void addCustomer(Customer customer) throws  InvalidValueException, ObjectAlreadyExistException;
    void removeCustomer(Customer customer) throws ObjectDoesNotExistException;
    void updateCustomer(Customer customer) throws ObjectDoesNotExistException;
    Customer getCustomer(String id);
    Customer getCustomerByEmail(String email);
    Collection<Customer> getCustomersByName(String name);
    Collection<Customer> getCustomers();

}
