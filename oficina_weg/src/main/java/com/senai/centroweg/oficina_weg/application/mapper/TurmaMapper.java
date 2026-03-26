package com.senai.centroweg.oficina_weg.application.mapper;

import com.senai.centroweg.oficina_weg.application.dto.request.TurmaRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.TurmaResponseDto;
import com.senai.centroweg.oficina_weg.domain.model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    @Autowired
    private AlunoMapper alunoMapper;

    public Turma toEntity(TurmaRequestDto dto) {
        Turma turma = new Turma();
        turma.setNome(dto.nome());
        return turma;
    }

    public TurmaResponseDto toDTO(Turma turma) {
        return new TurmaResponseDto(
                turma.getId(),
                turma.getNome(),
                turma.getAlunos()
                        .stream()
                        .map(alunoMapper::toDTO)
                        .toList()
        );
    }
}
