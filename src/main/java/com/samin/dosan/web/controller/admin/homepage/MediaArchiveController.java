package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.domain.homepage.type.MediaType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.media_archive.promotional_video.PromotionalVideo;
import com.samin.dosan.domain.homepage.media_archive.promotional_video.PromotionalVideoService;
import com.samin.dosan.domain.homepage.media_archive.webtoon.Webtoon;
import com.samin.dosan.domain.homepage.media_archive.webtoon.WebtoonService;
import com.samin.dosan.web.dto.homepage.webtoon.WebtoonSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/media-archive")
public class MediaArchiveController {

    private final PromotionalVideoService promotionService;
    private final WebtoonService webtoonService;

    @ModelAttribute("mediaArchiveTypes")
    public MediaType[] mediaArchiveTypes() {
        return MediaType.values();
    }

    @GetMapping("/{type}")
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        MediaType mediaArchiveType = MediaType.valueOf(type.toUpperCase().replace("-", "_"));

        switch (mediaArchiveType) {
            case PROMOTIONAL_VIDEO:
                Page<PromotionalVideo> promotionalVideoResult = promotionService.findAll(searchParam, pageable);
                model.addAttribute("result", promotionalVideoResult);
                break;

            case WEBTOON:
                Page<Webtoon> webtoonResult = webtoonService.findAll(searchParam, pageable);
                model.addAttribute("result", webtoonResult);
                break;
        }

        model.addAttribute("mediaArchiveType", mediaArchiveType);

        return "admin/homepage/media_archive/mainView";
    }

    /* 홍보동영상 */
    @GetMapping("/promotional-video/add")
    public String promotionAddView(@ModelAttribute PromotionalVideo promotion, Model model) {
        model.addAttribute("mediaArchiveType", MediaType.PROMOTIONAL_VIDEO);
        return "admin/homepage/media_archive/promotional_video/addView";
    }

    @GetMapping("/promotional-video/{id}/detail")
    public String promotionDetailView(@PathVariable Long id, @ModelAttribute PromotionalVideo promotion,
                                      Model model) {
        model.addAttribute("promotionalVideo", promotionService.findById(id));
        model.addAttribute("mediaArchiveType", MediaType.PROMOTIONAL_VIDEO);
        return "admin/homepage/media_archive/promotional_video/detailView";
    }

    @GetMapping("/promotional-video/{id}/edit")
    public String promotionEditView(@PathVariable Long id, Model model) {
        model.addAttribute("promotionalVideo", promotionService.findById(id));
        model.addAttribute("mediaArchiveType", MediaType.PROMOTIONAL_VIDEO);

        return "admin/homepage/media_archive/promotional_video/editView";
    }

    /* 만화퇴계 */
    @GetMapping("/webtoon/add")
    public String webtoonAddView(@ModelAttribute("webtoon") WebtoonSave webtoon, Model model) {
        model.addAttribute("mediaArchiveType", MediaType.WEBTOON);

        return "admin/homepage/media_archive/webtoon/addView";
    }

    @GetMapping("/webtoon/{id}/detail")
    public String webtoonDetailView(@PathVariable Long id, Model model) {
        model.addAttribute("webtoon", webtoonService.findById(id));
        model.addAttribute("mediaArchiveType", MediaType.WEBTOON);

        return "admin/homepage/media_archive/webtoon/detailView";
    }

    @GetMapping("/webtoon/{id}/edit")
    public String webtoonEditView(@PathVariable Long id, Model model) {
        model.addAttribute("webtoon", webtoonService.findById(id));
        model.addAttribute("mediaArchiveType", MediaType.WEBTOON);

        return "admin/homepage/media_archive/webtoon/editView";
    }

    @GetMapping("/webtoon/attach/{id}")
    public ResponseEntity download(@PathVariable Long id) throws MalformedURLException {
        Webtoon webtoon = webtoonService.findById(id);

        String originalFileName = webtoon.getOriginPdfName();
        String storedFileName = webtoon.getStorePdfName();

        return FileUtils.downloadFile(originalFileName, storedFileName);
    }
}
