package com.samin.dosan.domain.homepage.mainImage;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.mainImage.dto.MainImageChangePostRequest;
import com.samin.dosan.domain.homepage.mainImage.dto.MainImageRequest;
import com.samin.dosan.domain.homepage.mainImage.dto.MainImageResponse;
import com.samin.dosan.domain.homepage.mainImage.repository.MainImageRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainImageService extends BaseService<MainImage, Long> {

    private MainImageRepository mainImageRepository;

    public MainImageService(MainImageRepository mainImageRepository) {
        super(mainImageRepository);
        this.mainImageRepository = mainImageRepository;
    }

    public Page<MainImageResponse> getMainImageList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qMainImage.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qMainImage.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qMainImage.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<MainImage> results = select().from(qMainImage)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qMainImage.postSeq.desc(), qMainImage.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(main -> new MainImageResponse(main))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public MainImageResponse getMainImage_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qMainImage.id.eq(id));

        MainImage mainImage = select().from(qMainImage)
                .where(builder)
                .fetchOne();

        return new MainImageResponse(mainImage);
    }

    public boolean mainImageInputValidation(MultipartFile multipartFile, String width, String height) {
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

                if (fileWidth < Integer.parseInt(width) || fileHeight < Integer.parseInt(height)) {
                    result = false;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Transactional
    public Long mainImageSave_admin(MainImageRequest request) {
        if (!request.getPostYn().equals("N")) {
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qMainImage.postYn.eq("Y"));
            builder.and(qMainImage.postSeq.eq(request.getPostSeq()));

            MainImage mainImage = select().from(qMainImage).where(builder).fetchOne();

            if (mainImage != null) {
                MainImageChangePostRequest changeRequest = new MainImageChangePostRequest(mainImage);
                save(changeRequest.toEntity("N", 0)).getId();
            }

        }

        Long id = save(request.toEntity()).getId();

        return id;
    }

    @Transactional
    public boolean mainImageDelete_admin(List<Long> idList) {
        boolean result = false;
        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                MainImage mainImage = mainImageRepository.findById(id).get();

                mainImageRepository.delete(mainImage);
            }

            result = true;
        }

        return result;
    }
}
