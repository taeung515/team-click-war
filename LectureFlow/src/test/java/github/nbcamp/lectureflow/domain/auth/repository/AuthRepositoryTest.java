//package github.nbcamp.lectureflow.domain.auth.repository;
//
//import github.nbcamp.lectureflow.domain.user.enums.Role;
//import github.nbcamp.lectureflow.global.entity.Member;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@ActiveProfiles("test")
//class AuthRepositoryTest {
//
//    @Autowired
//    private AuthRepository authRepository;
//
//    @Test
//    void 이메일로_멤버_조회_테스트() {
//        // given
//        Member member = Member.of("test@email.com", "testPassword", "testName", "010-1234-1234", Role.STUDENT);
//        authRepository.save(member);
//        // when
//        Member member1 = authRepository.findByEmail("test@email.com").orElseThrow();
//        // then
//        assertEquals(member.getEmail(), member1.getEmail());
//    }
//
//    @Test
//    void 존재하는_이메일인지_확인() {
//        // given
//        Member member = Member.of("test@email.com", "testPassword", "testName", "010-1234-1234", Role.STUDENT);
//        authRepository.save(member);
//        // when
//        boolean existsByEmail = authRepository.existsByEmail("test@email.com");
//        // then
//        assertTrue(existsByEmail);
//    }
//
//}