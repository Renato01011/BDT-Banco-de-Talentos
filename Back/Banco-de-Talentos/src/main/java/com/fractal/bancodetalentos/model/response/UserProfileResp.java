package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResp {

    @Lob
    private byte[] img;
}
