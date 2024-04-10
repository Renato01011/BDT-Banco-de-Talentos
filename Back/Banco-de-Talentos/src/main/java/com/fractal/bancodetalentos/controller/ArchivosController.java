package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.DocumentoReq;
import com.fractal.bancodetalentos.model.request.UpdateFileReq;
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

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping()
    public ResponseEntity<Map<String, String>> addNewFile(@Valid @RequestBody DocumentoReq documento) {
        Map<String, String> resp = archivosService.addNewFile(documento, documento.getIdTalent());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateCv(@Valid @RequestBody UpdateFileReq documento) {
        Map<String, String> resp = archivosService.updateCV(documento, documento.getIdTalent(), documento.getIdFile());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
