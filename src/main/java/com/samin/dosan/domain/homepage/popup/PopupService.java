package com.samin.dosan.domain.homepage.popup;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.popup.dto.PopupChangePostYnRequest;
import com.samin.dosan.domain.homepage.popup.dto.PopupRequest;
import com.samin.dosan.domain.homepage.popup.dto.PopupResponse;
import com.samin.dosan.domain.homepage.popup.repository.PopupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PopupService extends BaseService<Popup, Long> {

    private PopupRepository popupRepository;

    public PopupService(PopupRepository popupRepository) {
        super(popupRepository);
        this.popupRepository = popupRepository;
    }

    public Page<PopupResponse> getPopupList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qPopup.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                LocalDateTime start = LocalDateTime.of(LocalDate.parse(filterDto.getStart()), LocalTime.of(0, 0));
                builder.and(qPopup.regDt.goe(start));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                LocalDateTime end = LocalDateTime.of(LocalDate.parse(filterDto.getEnd()), LocalTime.of(23, 59));
                builder.and(qPopup.regDt.loe(end));
            }
        }

        QueryResults<Popup> results = select().from(qPopup)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qPopup.postYn.desc(), qPopup.regDt.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(pop -> new PopupResponse(pop))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public PopupResponse getPopup_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPopup.id.eq(id));

        Popup popup = select().from(qPopup)
                .where(builder)
                .fetchOne();

        return new PopupResponse(popup);
    }

    public boolean popupImageInputValidation(MultipartFile multipartFile, String width, String height) {
        boolean result = true;
        String filename = multipartFile.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".") +1);

        int size = (int) multipartFile.getSize();
        String[] extList = {"png", "jpg", "jpeg"};

        if (!Arrays.asList(extList).contains(extension.toLowerCase()) && size > 3 * 1024 * 1024) {
            result = false;
        } else {
            try {
                BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
                int fileWidth = bufferedImage.getWidth();
                int fileHeight = bufferedImage.getHeight();

                if (fileWidth != Integer.parseInt(width) || fileHeight != Integer.parseInt(height)) {
                    result = false;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Transactional
    public Long popupSave_admin(PopupRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        if (request.getPostYn().equals("Y")) {
            builder.and(qPopup.postYn.eq(request.getPostYn()));

            List<Popup> popupList = select().from(qPopup)
                    .where(builder)
                    .orderBy(qPopup.regDt.asc())
                    .fetch();

            if (popupList.size() == 2) {
                Popup popup = popupList.get(0);
                save(new PopupChangePostYnRequest(popup, "N").toEntity());
            }
        }
        return save(request.toEntity()).getId();
    }

    @Transactional
    public boolean popupDelete_admin(List<Long> idList) {
        boolean result = false;
        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                Popup popup = popupRepository.findById(id).get();

                popupRepository.delete(popup);
            }

            result = true;
        }

        return result;
    }
}
