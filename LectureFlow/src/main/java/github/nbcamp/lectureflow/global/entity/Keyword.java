package github.nbcamp.lectureflow.global.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keywords")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String searchKeyword;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static Keyword of(String keyword) {
        Keyword keywordKeyword = new Keyword();
        keywordKeyword.searchKeyword = keyword;
        return keywordKeyword;
    }

}
