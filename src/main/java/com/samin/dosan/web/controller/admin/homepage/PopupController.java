package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.domain.homepage.popup.Popup;
import com.samin.dosan.domain.homepage.popup.PopupService;
import com.samin.dosan.domain.homepage.type.PostType;
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

import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/popup")
public class PopupController {

    private final PopupService popupService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Popup> result = popupService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/homepage/popup/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Popup popup, Model model) {
        model.addAttribute("popupPostYnTypes", PostType.values());

        return "admin/homepage/popup/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("popup", popupService.findById(id));

        return "admin/homepage/popup/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("popupPostYnTypes", PostType.values());
        model.addAttribute("popup", popupService.findById(id));

        return "admin/homepage/popup/editView";
    }

    @GetMapping("/attach/{id}")
    public ResponseEntity download(@PathVariable Long id) throws MalformedURLException {
        Popup popup = popupService.findById(id);

        String originFilename = popup.getOriginFileName();
        String storedFilename = popup.getStoreFileName();

        return FileUtils.downloadFile(originFilename, storedFilename);
    }
}
