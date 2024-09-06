package com.example.demo.repository;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    public List<Invoice> findInvoiceByUser (User user);
}
