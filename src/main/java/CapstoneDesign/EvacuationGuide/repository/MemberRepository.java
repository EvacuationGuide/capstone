package CapstoneDesign.EvacuationGuide.repository;

import CapstoneDesign.EvacuationGuide.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMail(String mail);

    //List로 받으면 전체 조회
    List<Member> findByNickname(String nickname);
}