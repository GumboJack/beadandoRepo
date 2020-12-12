package service;

import DAO.CustomerDAO;
import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.Customer;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO dao;

    public CustomerServiceImpl(CustomerDAO dao) {
        this.dao = dao;
    }

    public void addCustomer(Customer customer) throws ObjectAlreadyExistException, InvalidValueException {
        dao.addCustomer(customer);
    }

    public void removeCustomer(Customer customer) throws ObjectDoesNotExistException {
        dao.removeCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws ObjectDoesNotExistException {
        dao.updateCustomer(customer);
    }

    public Customer getCustomer(String id) {
        return dao.getCustomer(id);
    }

    public Customer getCustomerByEmail(String email) {
        return dao.getCustomerByEmail(email);
    }

    public Collection<Customer> getCustomersByName(String name) {
        return dao.getCustomersByName(name);
    }

    public Collection<Customer> getCustomers() {
        return dao.getCustomers();
    }
}
