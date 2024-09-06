package com.example.demo.service;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.User;

import java.util.List;

public interface InvoiceService {
    public Invoice getById(int id);
    public Boolean createInvoice(Invoice invoice);
    public Boolean deleteInvoice(Invoice invoice);
    public List<Invoice> findInvoiceByUser (User user);
}
