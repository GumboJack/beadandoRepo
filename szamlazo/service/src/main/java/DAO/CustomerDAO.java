package DAO;

import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.Customer;

import java.util.Collection;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws ObjectAlreadyExistException, InvalidValueException;
    void removeCustomer(Customer customer) throws ObjectDoesNotExistException;
    void updateCustomer(Customer customer) throws ObjectDoesNotExistException;
    Customer getCustomer(String id);
    Customer getCustomerByEmail(String email);
    Collection<Customer> getCustomersByName(String name);
    Collection<Customer> getCustomers();
}
