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
public class FeedbackResp {
    private Integer idFeedback;
    private Integer starCount;
    private String description;
    private Integer idUserFrom;
    private String userFromName;
    @Lob
    private byte[] userFromPhoto;
}
