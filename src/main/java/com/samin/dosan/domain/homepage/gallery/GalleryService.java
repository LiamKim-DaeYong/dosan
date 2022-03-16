package com.samin.dosan.domain.homepage.gallery;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.gallery.dto.GalleryRequest;
import com.samin.dosan.domain.homepage.gallery.dto.GalleryResponse;
import com.samin.dosan.domain.homepage.gallery.repository.GalleryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService extends BaseService<Gallery, Long> {

    private GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        super(galleryRepository);
        this.galleryRepository = galleryRepository;
    }

    public Page<GalleryResponse> getGalleryList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qGallery.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qGallery.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qGallery.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Gallery> results = select().from(qGallery)
                .where(builder)
                .leftJoin(qGallery.childList, qgalleryFile)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qGallery.regDt.desc(), qGallery.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(gallery -> new GalleryResponse(gallery))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public GalleryResponse getGallery_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qGallery.id.eq(id));

        Gallery gallery = select().from(qGallery)
                .where(builder)
                .leftJoin(qGallery.childList, qgalleryFile)
                .fetchJoin()
                .fetchOne();

        return new GalleryResponse(gallery);
    }

    @Transactional
    public Long gallerySave_admin(GalleryRequest request) {
        String username = "도산서원 선비문화수련원";

        Long id = null;
        if (request != null) {
            request.setAuthor(username);
            id = save( request.toEntity()).getId();
        }

        return id;
    }

    @Transactional
    public boolean galleryDelete_admin(List<Long> idList) {
        boolean result = false;

        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                galleryRepository.deleteById(id);
            }

            result = true;
        }

        return result;
    }
}
