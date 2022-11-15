package com.myshortener.repository;

import com.myshortener.entity.Member;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findByEmail(String email);

    void deleteAll();

}
