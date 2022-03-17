package com.samin.dosan.domain.homepage.impression;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.impression.repository.ImpressionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImpressionService {

    private final ImpressionRepository impressionRepository;

    public Page<Impression> findAll(SearchParam searchParam, Pageable pageable) {
        return impressionRepository.findAll(searchParam, pageable);
    }

//    public ImpressionResponse getImpression_admin(Long id) {
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qImpression.id.eq(id));
//
//        Impression impression = select().from(qImpression)
//                .where(builder)
//                .leftJoin(qImpression.impressionFileList, qImpressionFile)
//                .fetchJoin()
//                .fetchOne();
//
//        return new ImpressionResponse(impression);
//    }
//
//    @Transactional
//    public boolean impressionDelete_admin(List<Long> idList) {
//        boolean result = false;
//
//        if (isNotEmpty(idList)) {
//            for (Long id : idList) {
//                impressionRepository.deleteById(id);
//            }
//
//            result = true;
//        }
//
//
//        return result;
//    }
}
