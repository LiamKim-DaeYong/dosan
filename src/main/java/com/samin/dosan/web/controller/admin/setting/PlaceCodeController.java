package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place_code.PlaceCode;
import com.samin.dosan.domain.setting.place_code.PlaceCodeType;
import com.samin.dosan.domain.setting.place_code.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/setting/place-code/{type}")
public class PlaceCodeController {

    private final PlaceService placeService;

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        PlaceCodeType placeCodeType = PlaceCodeType.valueOf(type.toUpperCase());

        Page<PlaceCode> result = placeService.findAll(searchParam, placeCodeType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("placeTypes", PlaceCodeType.values());
        model.addAttribute("placeType", PlaceCodeType.valueOf(type.toUpperCase()));

        return "admin/setting/place_code/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute PlaceCode saveData) {
        return "admin/setting/place_code/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        PlaceCode placeCode = placeService.findById(id);
        model.addAttribute("place", placeCode);

        return "admin/setting/place_code/editView::#form";
    }

//    @PostConstruct
//    public void init() {
//        for (int i = 1; i <= 1000; i++) {
//            Place place = Place.builder()
//                    .location(i+"장소")
//                    .placeType(PlaceType.EXPLR)
//                    .used(Used.Y)
//                    .build();
//
//            placeService.save(place);
//        }
//    }
}
