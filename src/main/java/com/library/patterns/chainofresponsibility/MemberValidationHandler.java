package com.library.patterns.chainofresponsibility;

public class MemberValidationHandler extends ValidationHandler {

    @Override
    public boolean handleRequest(Member member) {
        if (member.getName() == null || member.getName().isEmpty()) {
            System.out.println("Member validation failed: Name is required.");
            return false;
        }
        if (member.getContactInfo() == null || member.getContactInfo().isEmpty()) {
            System.out.println("Member validation failed: Contact information is required.");
            return false;
        }
        System.out.println("Member validation passed.");
        return handleNext(member);
    }
}