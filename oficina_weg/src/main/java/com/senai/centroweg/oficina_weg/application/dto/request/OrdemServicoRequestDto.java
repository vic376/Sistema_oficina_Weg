package com.senai.centroweg.oficina_weg.application.dto.request;

import java.util.List;

public record OrdemServicoRequestDto(
         String equipamento,
         String defeito,
         String materiaisQuantidade,
         String laudoTecnico,
         Long idProfessorResponsavel,
         List<Long> alunosIds
) {
}
