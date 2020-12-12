package service;

import model.Invoice;

import java.util.Collection;

public interface InvoiceService {
    Collection<Invoice> getUserInvoices(String id);
    void addInvoice(Invoice invoice);
    Collection<Invoice> getInvoices();
}
