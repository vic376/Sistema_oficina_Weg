package com.senai.centroweg.oficina_weg.infrastructure.controller;

import com.senai.centroweg.oficina_weg.application.dto.request.ProfessorRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.request.TurmaRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.ProfessorResponseDto;
import com.senai.centroweg.oficina_weg.application.dto.response.TurmaResponseDto;
import com.senai.centroweg.oficina_weg.application.mapper.ProfessorMapper;
import com.senai.centroweg.oficina_weg.application.mapper.TurmaMapper;
import com.senai.centroweg.oficina_weg.domain.model.Professor;
import com.senai.centroweg.oficina_weg.domain.model.Turma;
import com.senai.centroweg.oficina_weg.service.ProfessorService;
import com.senai.centroweg.oficina_weg.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProfessorMapper professorMapper;

    @PostMapping
    public ResponseEntity<ProfessorResponseDto> criarTurma(@RequestBody ProfessorRequestDto dto) {
        Professor professor = professorMapper.toEntity(dto);
        Professor salva = professorService.criarProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorMapper.toDTO(salva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> buscarPorId(@PathVariable Long id) {
        return professorService.buscarPorId(id)
                .map(professor -> ResponseEntity.ok(professorMapper.toDTO(professor)))
                .orElse(ResponseEntity.notFound().build());
    }
}

