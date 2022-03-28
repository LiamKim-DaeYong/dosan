package com.samin.dosan.web.controller.employee.clients;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.clients.ClientType;
import com.samin.dosan.domain.clients.Clients;
import com.samin.dosan.domain.clients.repository.ClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/clients")
public class ClientsController {

    private final ClientsService clientsService;

    @ModelAttribute("clientTypes")
    public ClientType[] clientTypes() {
        return ClientType.values();
    }

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable,
                           Model model) {

        Page<Clients> result = clientsService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "employee/clients/mainView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {

        Clients clients = clientsService.findById(id);
        model.addAttribute("clients", clients);

        return "employee/clients/detailView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Clients clients) {
        clients.setClientType(ClientType.ELEMENTARY_SCHOOL_STUDENTS);
        return "employee/clients/addView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {

        Clients clients = clientsService.findById(id);
        model.addAttribute("clients", clients);

        return "employee/clients/editView";
    }
}
