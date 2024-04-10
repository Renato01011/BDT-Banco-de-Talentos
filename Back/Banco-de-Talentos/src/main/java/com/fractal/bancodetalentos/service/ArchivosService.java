package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.DocumentoReq;
import com.fractal.bancodetalentos.model.request.UpdateFileReq;

import java.util.Map;

public interface ArchivosService {

    Map<String, String> addNewFile(DocumentoReq documento, Integer id);

    Map<String, String> updateCV(UpdateFileReq documento, Integer id, Integer idFile);
}
