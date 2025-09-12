package com.library.patterns.abstractfactory;

import com.library.model.Member;

public class MemberEntityFactory implements EntityFactory {
    @Override
    public Member createMember(String name, String contactInfo) {
        return new Member(name, contactInfo);
    }
}