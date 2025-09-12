package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Loan;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository, MemberRepository memberRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Loan lendBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        
        Loan loan = new Loan(book, member);
        return loanRepository.save(loan);
    }

    public void returnBook(Long loanId) {
        loanRepository.deleteById(loanId);
    }
}