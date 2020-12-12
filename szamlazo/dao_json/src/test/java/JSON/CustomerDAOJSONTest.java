package JSON;

import DAO.CustomerDAO;
import exception.EmptyStringException;
import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.Customer;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static helper.FileHelper.deleteFile;
import static org.junit.Assert.*;

public class CustomerDAOJSONTest {

    String filePath = "customers.json";

    @Test(expected = ObjectAlreadyExistException.class)
    public void addCustomer() throws EmptyStringException, IOException, ObjectAlreadyExistException, InvalidValueException {
        deleteFile(filePath);

        CustomerDAO jsonDAO = new CustomerDAOJSON(filePath);

        Customer customer1 = new Customer();
        customer1.setName("Test Tamas");
        customer1.setCompany(false);
        customer1.setEmail("test1@test.com");
        customer1.setShipping_address_city("test");
        customer1.setInvoicing_address_city("test");
        customer1.setShipping_address_postcode("test");
        customer1.setInvoicing_address_postcode("test");
        customer1.setShipping_address_street("test");
        customer1.setInvoicing_address_street("test");
        customer1.setShipping_address_other("test");
        customer1.setInvoicing_address_other("test");
        customer1.setPhoneNumber("0000000000");
        customer1.setTaxID("");
        customer1.setPassword("test1");

        Customer customer2 = new Customer();
        customer2.setName("Test Peter");
        customer2.setCompany(false);
        customer2.setEmail("test2@test.com");
        customer2.setShipping_address_city("test");
        customer2.setInvoicing_address_city("test");
        customer2.setShipping_address_postcode("test");
        customer2.setInvoicing_address_postcode("test");
        customer2.setShipping_address_street("test");
        customer2.setInvoicing_address_street("test");
        customer2.setShipping_address_other("test");
        customer2.setInvoicing_address_other("test");
        customer2.setPhoneNumber("0000000000");
        customer2.setTaxID("");
        customer2.setPassword("test2");

        Customer customer3 = new Customer();
        customer3.setName("Test Kft");
        customer3.setCompany(true);
        customer3.setTaxID("000000000");
        customer3.setEmail("test3@test.com");
        customer3.setShipping_address_city("test");
        customer3.setInvoicing_address_city("test");
        customer3.setShipping_address_postcode("test");
        customer3.setInvoicing_address_postcode("test");
        customer3.setShipping_address_street("test");
        customer3.setInvoicing_address_street("test");
        customer3.setShipping_address_other("test");
        customer3.setInvoicing_address_other("test");
        customer3.setPhoneNumber("0000000000");
        customer3.setPassword("test3");

        jsonDAO.addCustomer(customer1);
        jsonDAO.addCustomer(customer2);
        jsonDAO.addCustomer(customer3);

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        assertArrayEquals(jsonDAO.getCustomers().toArray(), customers.toArray());

        jsonDAO.addCustomer(customer1);
    }

    @Test(expected = ObjectDoesNotExistException.class)
    public void removeCustomer() throws IOException, EmptyStringException, ObjectAlreadyExistException, ObjectDoesNotExistException, InvalidValueException {
        deleteFile(filePath);

        CustomerDAO jsonDAO = new CustomerDAOJSON(filePath);

        Customer customer1 = new Customer();
        customer1.setName("Test Tamas");
        customer1.setCompany(false);
        customer1.setEmail("test1@test.com");
        customer1.setShipping_address_city("test");
        customer1.setInvoicing_address_city("test");
        customer1.setShipping_address_postcode("test");
        customer1.setInvoicing_address_postcode("test");
        customer1.setShipping_address_street("test");
        customer1.setInvoicing_address_street("test");
        customer1.setShipping_address_other("test");
        customer1.setInvoicing_address_other("test");
        customer1.setPhoneNumber("0000000000");
        customer1.setTaxID("");
        customer1.setPassword("test1");

        jsonDAO.addCustomer(customer1);
        jsonDAO.removeCustomer(customer1);

        assertTrue(jsonDAO.getCustomers().isEmpty());

        jsonDAO.removeCustomer(customer1);
    }

    @Test(expected = ObjectDoesNotExistException.class)
    public void updateCustomer() throws IOException, EmptyStringException, ObjectAlreadyExistException, ObjectDoesNotExistException, InvalidValueException {
        deleteFile(filePath);

        CustomerDAO jsonDAO = new CustomerDAOJSON(filePath);

        Customer customer1 = new Customer();
        customer1.setName("Test Tamas");
        customer1.setCompany(false);
        customer1.setEmail("test1@test.com");
        customer1.setShipping_address_city("test");
        customer1.setInvoicing_address_city("test");
        customer1.setShipping_address_postcode("test");
        customer1.setInvoicing_address_postcode("test");
        customer1.setShipping_address_street("test");
        customer1.setInvoicing_address_street("test");
        customer1.setShipping_address_other("test");
        customer1.setInvoicing_address_other("test");
        customer1.setPhoneNumber("0000000000");
        customer1.setTaxID("");
        customer1.setPassword("test1");

        jsonDAO.addCustomer(customer1);

        Customer customer1_alter = new Customer();
        customer1_alter.setName("Test Tamas");
        customer1_alter.setCompany(false);
        customer1_alter.setEmail("test1@test.com");
        customer1_alter.setShipping_address_city("Budapest");
        customer1_alter.setInvoicing_address_city("test");
        customer1_alter.setShipping_address_postcode("test");
        customer1_alter.setInvoicing_address_postcode("test");
        customer1_alter.setShipping_address_street("test");
        customer1_alter.setInvoicing_address_street("test");
        customer1_alter.setShipping_address_other("test");
        customer1_alter.setInvoicing_address_other("test");
        customer1_alter.setPhoneNumber("0000000000");
        customer1_alter.setTaxID("");
        customer1_alter.setPassword("test1");

        jsonDAO.updateCustomer(customer1_alter);

        assertNotNull(jsonDAO.getCustomerByEmail(customer1.getEmail()));
        assertEquals(jsonDAO.getCustomerByEmail(customer1.getEmail()).getShipping_address_city(), customer1_alter.getShipping_address_city());

        jsonDAO.removeCustomer(customer1);
        jsonDAO.updateCustomer(customer1);
    }

    @Test
    public void getCustomer() throws IOException, EmptyStringException, ObjectAlreadyExistException, InvalidValueException {
        deleteFile(filePath);

        CustomerDAO jsonDAO = new CustomerDAOJSON(filePath);

        Customer customer1 = new Customer();
        customer1.setName("Test Tamas");
        customer1.setCompany(false);
        customer1.setEmail("test1@test.com");
        customer1.setShipping_address_city("test");
        customer1.setInvoicing_address_city("test");
        customer1.setShipping_address_postcode("test");
        customer1.setInvoicing_address_postcode("test");
        customer1.setShipping_address_street("test");
        customer1.setInvoicing_address_street("test");
        customer1.setShipping_address_other("test");
        customer1.setInvoicing_address_other("test");
        customer1.setPhoneNumber("0000000000");
        customer1.setTaxID("");
        customer1.setPassword("test1");

        jsonDAO.addCustomer(customer1);

        assertEquals(jsonDAO.getCustomer(customer1.getId()), customer1);
        assertNull(jsonDAO.getCustomer(new Customer().getId()));
    }

    @Test
    public void getCustomersByName() throws IOException, EmptyStringException, ObjectAlreadyExistException, InvalidValueException {
        deleteFile(filePath);

        CustomerDAO jsonDAO = new CustomerDAOJSON(filePath);

        Customer customer1 = new Customer();
        customer1.setName("Test Tamas");
        customer1.setCompany(false);
        customer1.setEmail("test1@test.com");
        customer1.setShipping_address_city("test");
        customer1.setInvoicing_address_city("test");
        customer1.setShipping_address_postcode("test");
        customer1.setInvoicing_address_postcode("test");
        customer1.setShipping_address_street("test");
        customer1.setInvoicing_address_street("test");
        customer1.setShipping_address_other("test");
        customer1.setInvoicing_address_other("test");
        customer1.setPhoneNumber("0000000000");
        customer1.setTaxID("");
        customer1.setPassword("test1");

        Customer customer2 = new Customer();
        customer2.setName("Test Peter");
        customer2.setCompany(false);
        customer2.setEmail("test2@test.com");
        customer2.setShipping_address_city("test");
        customer2.setInvoicing_address_city("test");
        customer2.setShipping_address_postcode("test");
        customer2.setInvoicing_address_postcode("test");
        customer2.setShipping_address_street("test");
        customer2.setInvoicing_address_street("test");
        customer2.setShipping_address_other("test");
        customer2.setInvoicing_address_other("test");
        customer2.setPhoneNumber("0000000000");
        customer2.setTaxID("");
        customer2.setPassword("test2");

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        jsonDAO.addCustomer(customer1);
        jsonDAO.addCustomer(customer2);

        System.out.println(jsonDAO.getCustomersByName("test"));
        assertArrayEquals(customers.toArray(), jsonDAO.getCustomersByName("test").toArray());
    }

    @Test
    public void getCustomerByEmail() throws IOException, EmptyStringException, ObjectAlreadyExistException, InvalidValueException {
        deleteFile(filePath);

        CustomerDAO jsonDAO = new CustomerDAOJSON(filePath);

        Customer customer1 = new Customer();
        customer1.setName("Test Tamas");
        customer1.setCompany(false);
        customer1.setEmail("test1@test.com");
        customer1.setShipping_address_city("test");
        customer1.setInvoicing_address_city("test");
        customer1.setShipping_address_postcode("test");
        customer1.setInvoicing_address_postcode("test");
        customer1.setShipping_address_street("test");
        customer1.setInvoicing_address_street("test");
        customer1.setShipping_address_other("test");
        customer1.setInvoicing_address_other("test");
        customer1.setPhoneNumber("0000000000");
        customer1.setTaxID("");
        customer1.setPassword("test1");

        jsonDAO.addCustomer(customer1);

        assertEquals(jsonDAO.getCustomerByEmail(customer1.getEmail()).getEmail(), customer1.getEmail());
    }
}