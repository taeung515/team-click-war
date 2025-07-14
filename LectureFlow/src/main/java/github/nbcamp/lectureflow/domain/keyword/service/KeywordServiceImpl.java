package github.nbcamp.lectureflow.domain.keyword.service;

import github.nbcamp.lectureflow.domain.keyword.dto.TopTenResponse;
import github.nbcamp.lectureflow.domain.keyword.repository.KeywordRepository;
import github.nbcamp.lectureflow.global.entity.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Override
    @Transactional(readOnly = true)
    public TopTenResponse getPopularKeywords() {
        return TopTenResponse.of(keywordRepository.findPopularKeywords());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "topKeywords")
    public TopTenResponse getPopularKeywordsV2() {
        return TopTenResponse.of(keywordRepository.findPopularKeywords());
    }

    @Scheduled(cron = "0 0 2 * * *") // 매일 새벽 2시
    @Transactional
    public void deleteOldKeywords() {
        keywordRepository.deleteOldKeywords();
    }

    @Scheduled(fixedRate = 1800000) // 30분
    @CacheEvict(value = "topKeywords", allEntries = true)
    public void evictTopKeywordsCache() {}
}
