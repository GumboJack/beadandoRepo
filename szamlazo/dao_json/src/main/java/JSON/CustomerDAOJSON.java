package JSON;

import DAO.CustomerDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import helper.RegexHelper;
import model.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomerDAOJSON implements CustomerDAO {

    private File jsonFile;
    private ObjectMapper mapper;

    public CustomerDAOJSON(String jsonFilePath) throws IOException {
        File jsonFile = new File(jsonFilePath);
        System.out.println(jsonFile.getCanonicalPath());
        if (!jsonFile.exists()){
            jsonFile.createNewFile();
            FileWriter writer = new FileWriter(jsonFile);
            writer.write("[]");
            writer.flush();
            writer.close();
        }
        this.jsonFile = jsonFile;
        mapper = new ObjectMapper();
    }

    @Override
    public void addCustomer(@NotNull Customer customer) throws ObjectAlreadyExistException, InvalidValueException {
        Collection<Customer> customers = getCustomers();
        String id = customer.getId();
        String email = customer.getEmail();
        if (customers != null && getCustomer(id) == null && getCustomerByEmail(email) == null){
            if (customer.isMissingArgument()){
                throw new InvalidValueException("Customer have missing arguments");
            }
            customers.add(customer);
            try {
                mapper.writeValue(jsonFile, customers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new ObjectAlreadyExistException(String.format("Customer with ID [%s] or e-mail [%s] already exist", id, email));
        }
    }

    @Override
    public void removeCustomer(@NotNull Customer customer) throws ObjectDoesNotExistException {
        Collection<Customer> customers = getCustomers();
        String id = customer.getId();
        String email = customer.getEmail();
        if (customers != null && !customers.isEmpty() &&
                customers.removeIf(listItem -> listItem.getId().equals(id)) ||
                customers.removeIf(listItem -> listItem.getEmail().equals(email))){
            try {
                mapper.writeValue(jsonFile, customers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            throw new ObjectDoesNotExistException(String.format("Customer with id [%s] or e-mail [%s] does not exist.", id, email));
        }
    }

    @Override
    public void updateCustomer(@NotNull Customer customer) throws ObjectDoesNotExistException {
        Collection<Customer> customers = getCustomers();
        String id = customer.getId();
        String email = customer.getEmail();
        if (customers != null && !customers.isEmpty()){
            if (customers.removeIf(listItem -> listItem.getId().equals(id))){
                customers.add(customer);
            } else if (customers.removeIf(listItem -> listItem.getEmail().equals(email))){
                customers.add(customer);
            } else {
                throw new ObjectDoesNotExistException(String.format("Customer with id [%s] or e-mail [%s] does not exist.", id, email));
            }
            try {
                mapper.writeValue(jsonFile, customers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            throw new ObjectDoesNotExistException(String.format("Customer with id [%s] or e-mail [%s] does not exist.", id, email));
        }
    }

    @Override
    public Customer getCustomer(String id) {
        return getCustomers().stream().filter(customer -> customer.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return getCustomers().stream().filter(customer -> customer.getEmail().equals(email)).findAny().orElse(null);
    }

    @Override
    public Collection<Customer> getCustomersByName(String name) {
        return getCustomers().stream().filter(customer -> RegexHelper.containsWord(customer.getName(), name))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Customer> getCustomers() {
        Collection<Customer> customers = null;
        TypeReference type = new TypeReference<ArrayList<Customer>>(){};
        try{
            customers = (Collection<Customer>) mapper.readValue(jsonFile, type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return customers;
    }
}
