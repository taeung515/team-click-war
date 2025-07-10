package github.nbcamp.lectureflow.domain.keyword.service;

import github.nbcamp.lectureflow.domain.keyword.repository.KeywordRepository;
import github.nbcamp.lectureflow.global.entity.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveKeyword(String keyword) {
        if (StringUtils.hasText(keyword)) {
            keywordRepository.save(Keyword.of(keyword));
        }
    }
}
