package com.library.model;

import java.time.LocalDate;

public class Loan {
    private Long loanId;
    private Long associatedBookId;
    private Long associatedMemberId;
    private LocalDate startDate;
    private LocalDate dueDate;

    public Loan(Long loanId, Long associatedBookId, Long associatedMemberId, LocalDate startDate, LocalDate dueDate) {
        this.loanId = loanId;
        this.associatedBookId = associatedBookId;
        this.associatedMemberId = associatedMemberId;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getAssociatedBookId() {
        return associatedBookId;
    }

    public void setAssociatedBookId(Long associatedBookId) {
        this.associatedBookId = associatedBookId;
    }

    public Long getAssociatedMemberId() {
        return associatedMemberId;
    }

    public void setAssociatedMemberId(Long associatedMemberId) {
        this.associatedMemberId = associatedMemberId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}