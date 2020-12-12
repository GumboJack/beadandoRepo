package JSON;

import DAO.InvoiceDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import model.Customer;
import model.Invoice;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class InvoiceDAOJSON implements InvoiceDAO {
    private File jsonFile;
    private ObjectMapper mapper;

    public InvoiceDAOJSON(String jsonFilePath) throws IOException {
        File jsonFile = new File(jsonFilePath);
        if (!jsonFile.exists()){
            jsonFile.createNewFile();
            FileWriter writer = new FileWriter(jsonFile);
            writer.write("[]");
            writer.flush();
            writer.close();
        }
        this.jsonFile = jsonFile;
        mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
    }

    @Override
    public Collection<Invoice> getUserInvoices(String id) {
        return getInvoices().stream().filter(invoice -> invoice.getCustomerId().equals(id)).collect(Collectors.toList());
    }

    @Override
    public void addInvoice(Invoice invoice) {
        Collection<Invoice> invoices = getInvoices();
        if (invoices != null){
            invoices.add(invoice);
            try {
                mapper.writeValue(jsonFile, invoices);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Collection<Invoice> getInvoices() {
        Collection<Invoice> invoices = null;
        TypeReference type = new TypeReference<ArrayList<Invoice>>(){};
        try{
            invoices = (Collection<Invoice>) mapper.readValue(jsonFile, type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return invoices;
    }
}
