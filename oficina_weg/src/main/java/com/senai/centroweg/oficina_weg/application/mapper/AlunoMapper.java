package com.senai.centroweg.oficina_weg.application.mapper;

import com.senai.centroweg.oficina_weg.application.dto.request.AlunoRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.request.ProfessorRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.AlunoResponseDto;
import com.senai.centroweg.oficina_weg.application.dto.response.ProfessorResponseDto;
import com.senai.centroweg.oficina_weg.domain.model.Aluno;
import com.senai.centroweg.oficina_weg.domain.model.Professor;
import com.senai.centroweg.oficina_weg.domain.model.Turma;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

        public Aluno toEntity(AlunoRequestDto dto, Turma turma) {
            Aluno aluno = new Aluno();
            aluno.setNome(dto.nome());
            aluno.setTurma(turma);
            return aluno;
        }

    public AlunoResponseDto toDTO(Aluno aluno) {
        return new AlunoResponseDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getTurma().getId()
        );
    }
    }
