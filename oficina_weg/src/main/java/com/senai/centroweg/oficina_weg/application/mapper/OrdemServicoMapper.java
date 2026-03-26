package com.senai.centroweg.oficina_weg.application.mapper;

import com.senai.centroweg.oficina_weg.application.dto.request.OrdemServicoRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.OrdemServicoResponseDto;
import com.senai.centroweg.oficina_weg.domain.model.Aluno;
import com.senai.centroweg.oficina_weg.domain.model.OrdemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdemServicoMapper {

    @Autowired
    private AlunoMapper alunoMapper;

    public OrdemServico toEntity(OrdemServicoRequestDto dto, List<Aluno> alunos) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setEquipamento(dto.equipamento());
        ordemServico.setDefeito(dto.defeito());
        ordemServico.setMateriaisQuantidade(dto.materiaisQuantidade());
        ordemServico.setLaudoTecnico(dto.laudoTecnico());
        ordemServico.setIdProfessorResponsavel(dto.idProfessorResponsavel());
        ordemServico.setAlunosResponsaveis(alunos);
        return ordemServico;
    }

    public OrdemServicoResponseDto toDTO(OrdemServico ordemServico) {
        return new OrdemServicoResponseDto(
                ordemServico.getId(),
                ordemServico.getEquipamento(),
                ordemServico.getDefeito(),
                ordemServico.getMateriaisQuantidade(),
                ordemServico.getLaudoTecnico(),
                ordemServico.getStatus(),
                ordemServico.getIdProfessorResponsavel(),
                ordemServico.getAlunosResponsaveis()
                        .stream()
                        .map(alunoMapper::toDTO)
                        .toList()
        );
    }
}
