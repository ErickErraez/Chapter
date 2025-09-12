package com.library.patterns.builder;

import com.library.model.Member;

public class MemberBuilder {
    private String name;
    private String contactInfo;

    public MemberBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MemberBuilder setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    public Member build() {
        Member member = new Member();
        member.setName(this.name);
        member.setContactInfo(this.contactInfo);
        return member;
    }
}