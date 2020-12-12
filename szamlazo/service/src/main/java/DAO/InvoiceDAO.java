package DAO;

import model.Invoice;

import java.util.Collection;

public interface InvoiceDAO {
    Collection<Invoice> getUserInvoices(String id);
    void addInvoice(Invoice invoice);
    Collection<Invoice> getInvoices();
}
