package com.senai.centroweg.oficina_weg.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record AlunoResponseDto(
         Long id,
         String nome,
         Long turmaId
) {

}
