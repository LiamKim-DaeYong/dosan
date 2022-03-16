package com.samin.dosan.domain.homepage.report;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.report.dto.ReportRequest;
import com.samin.dosan.domain.homepage.report.dto.ReportResponse;
import com.samin.dosan.domain.homepage.report.repository.ReportRepository;
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
public class ReportService extends BaseService<Report, Long> {

    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        super(reportRepository);
        this.reportRepository = reportRepository;
    }

    public Page<ReportResponse> getReportList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qReport.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qReport.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qReport.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Report> results = select().from(qReport)
                .where(builder)
                .leftJoin(qReport.childList, qReportFile)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qReport.regDt.desc(), qReport.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(report -> new ReportResponse(report))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public ReportResponse getReport_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qReport.id.eq(id));

        Report report = select().from(qReport)
                .where(builder)
                .leftJoin(qReport.childList, qReportFile)
                .fetchJoin()
                .fetchOne();

        return new ReportResponse(report);
    }

    @Transactional
    public Long reportSave_admin(ReportRequest request) {
        String username = "도산서원 선비문화수련원";

        Long id = null;
        if (request != null) {
            request.setAuthor(username);
            id = save(request.toEntity()).getId();
        }

        return id;
    }

    @Transactional
    public boolean reportDelete_admin(List<Long> idList) {
        boolean result = false;

        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                reportRepository.deleteById(id);
            }
            result = true;
        }

        return result;
    }
}
