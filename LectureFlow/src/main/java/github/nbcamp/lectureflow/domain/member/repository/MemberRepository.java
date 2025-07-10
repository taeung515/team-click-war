package github.nbcamp.lectureflow.domain.member.repository;

import github.nbcamp.lectureflow.global.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
