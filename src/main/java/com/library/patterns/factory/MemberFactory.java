package com.library.patterns.factory;

import com.library.model.Member;

public class MemberFactory {
    
    public static Member createMember(String name, String contactInfo) {
        return new Member(name, contactInfo);
    }
}