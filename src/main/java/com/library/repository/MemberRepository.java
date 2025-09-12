package com.library.repository;

import com.library.model.Member;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
    List<Member> findAll();
    void delete(Long id);
}