package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.Documento;
import com.fractal.bancodetalentos.service.ArchivosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/file")
@RequiredArgsConstructor
public class ArchivosController {

    private final ArchivosService archivosService;

    @PreAuthorize("hasRole('RECLUTADOR')")
    @PostMapping("/add/{id}")
    public ResponseEntity<Map<String, String>> addNewFile(@PathVariable Integer id, @Valid @RequestBody Documento documento) {
        Map<String, String> resp = archivosService.addNewFile(documento, id);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
