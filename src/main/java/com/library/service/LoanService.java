package com.library.service;

import com.library.model.Loan;
import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.LoanRepository;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public Loan lendBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

        if (book.isAvailable()) {
            Loan loan = new Loan(book, member, LocalDate.now(), null);
            book.setStatus("Loaned");
            bookRepository.save(book);
            return loanRepository.save(loan);
        } else {
            throw new RuntimeException("Book is not available for loan");
        }
    }

    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        Book book = loan.getBook();
        book.setStatus("Available");
        bookRepository.save(book);
        loanRepository.delete(loan);
    }
}