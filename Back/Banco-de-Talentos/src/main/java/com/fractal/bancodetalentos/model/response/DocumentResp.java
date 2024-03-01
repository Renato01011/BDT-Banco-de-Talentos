package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResp {
    private Integer idDocument;
    private String documentName;
    private String documentType;
    @Lob
    private byte[] document;
}
