package github.nbcamp.lectureflow.domain.keyword.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TopTenResponse {

    private final List<String> topTenKeywords;

    public static TopTenResponse of(List<String> keywords) {
        return new TopTenResponse(keywords);
    }

}
