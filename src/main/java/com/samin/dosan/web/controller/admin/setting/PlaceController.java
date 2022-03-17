package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place.Place;
import com.samin.dosan.domain.setting.place.PlaceService;
import com.samin.dosan.domain.setting.place.PlaceType;
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
@RequestMapping("/admin/setting/place/{type}")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        PlaceType placeType = PlaceType.valueOf(type.toUpperCase());

        Page<Place> result = placeService.findAll(searchParam, placeType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("placeTypes", PlaceType.values());
        model.addAttribute("placeType", PlaceType.valueOf(type.toUpperCase()));

        return "admin/setting/place/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Place saveData) {
        return "admin/setting/place/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        Place place = placeService.findById(id);
        model.addAttribute("place", place);

        return "admin/setting/place/editView::#form";
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
