package com.samin.dosan.web.api.employee.clients;

import com.samin.dosan.domain.clients.Clients;
import com.samin.dosan.domain.clients.repository.ClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee/clients")
public class ClientsApiController {

    private final ClientsService clientsService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody Clients saveData) {
        Long id = clientsService.save(Clients.of(saveData));
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody Clients updateData) {
        clientsService.update(id, updateData);
        return ResponseEntity.ok(id);
    }
}
