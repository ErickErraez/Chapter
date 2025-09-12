package com.library.repository;

import com.library.model.Loan;
import java.util.List;

public interface LoanRepository {
    Loan save(Loan loan);
    Loan findById(Long id);
    List<Loan> findAll();
    void deleteById(Long id);
}