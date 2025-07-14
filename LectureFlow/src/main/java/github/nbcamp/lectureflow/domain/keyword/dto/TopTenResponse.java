package github.nbcamp.lectureflow.domain.keyword.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopTenResponse {

    private List<String> topTenKeywords;

    public static TopTenResponse of(List<String> keywords) {
        return new TopTenResponse(keywords);
    }

}
