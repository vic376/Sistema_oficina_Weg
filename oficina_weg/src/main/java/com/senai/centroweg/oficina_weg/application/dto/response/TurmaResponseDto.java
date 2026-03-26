package com.senai.centroweg.oficina_weg.application.dto.response;

import java.util.List;

public record TurmaResponseDto(

         Long id,
         String nome,
         List<AlunoResponseDto> alunos
) {
}
