package service;

import DAO.InvoiceDAO;
import model.Invoice;

import java.util.Collection;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceDAO dao;

    public InvoiceServiceImpl(InvoiceDAO dao) {
        this.dao = dao;
    }

    public Collection<Invoice> getUserInvoices(String id) {
        return dao.getUserInvoices(id);
    }

    public void addInvoice(Invoice invoice) {
        dao.addInvoice(invoice);
    }

    public Collection<Invoice> getInvoices() {
        return dao.getInvoices();
    }
}
