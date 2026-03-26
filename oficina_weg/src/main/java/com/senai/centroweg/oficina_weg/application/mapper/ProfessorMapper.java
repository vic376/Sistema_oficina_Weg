package com.senai.centroweg.oficina_weg.application.mapper;

import com.senai.centroweg.oficina_weg.application.dto.request.ProfessorRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.ProfessorResponseDto;
import com.senai.centroweg.oficina_weg.domain.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor toEntity(ProfessorRequestDto dto) {
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setRegistro(dto.registro());
        return professor;
    }

    public ProfessorResponseDto toDTO(Professor professor) {
        return new ProfessorResponseDto(
                professor.getId(),
                professor.getNome(),
                professor.getRegistro()
        );
    }
}
