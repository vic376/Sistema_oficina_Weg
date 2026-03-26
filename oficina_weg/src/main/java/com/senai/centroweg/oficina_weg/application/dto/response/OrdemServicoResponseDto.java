package com.senai.centroweg.oficina_weg.application.dto.response;

import com.senai.centroweg.oficina_weg.domain.model.StatusOrdemServico;

import java.util.List;

public record OrdemServicoResponseDto(
         Long id,
         String equipamento,
         String defeito,
         String materiaisQuantidade,
         String laudoTecnico,
         StatusOrdemServico status,
         Long idProfessorResponsavel,
         List<AlunoResponseDto> alunosResponsaveis
) {
}
