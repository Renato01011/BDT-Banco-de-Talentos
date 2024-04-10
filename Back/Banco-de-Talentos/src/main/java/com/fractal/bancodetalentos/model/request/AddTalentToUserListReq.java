package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddTalentToUserListReq {
    @NotNull(message = "El Id del talento es obligatorio y no puede ser nulo.")
    private Integer talentId;

    @NotNull(message = "El Id del talento es obligatorio y no puede ser nulo.")
    private Integer listId;
}
