package com.library.patterns.abstractfactory;

public interface EntityFactory {
    BookEntityFactory createBookEntityFactory();
    MemberEntityFactory createMemberEntityFactory();
}