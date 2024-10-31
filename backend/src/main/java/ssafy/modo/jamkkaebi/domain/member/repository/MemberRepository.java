package ssafy.modo.jamkkaebi.domain.member.repository;

import ssafy.modo.jamkkaebi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);
}