package ssafy.modo.jamkkaebi.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.modo.jamkkaebi.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    @Query("""
            SELECT m FROM Member m
            LEFT JOIN ManagerAndDriver md ON m.id = md.driver.id
            WHERE m.role = 'DRIVER' AND md.driver.id IS NULL
            """)
    List<Member> findUnmanagedDriver();

    @Query("""
            SELECT m FROM Member m
            LEFT JOIN ManagerAndDriver md ON m.id = md.driver.id
            WHERE m.role = 'DRIVER' AND md.manager.id = :managerId
            """)
    List<Member> findManagedDriver(Long managerId);
}
