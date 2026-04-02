package com.senai.centroweg.oficina_weg.infrastructure.controller;

import com.senai.centroweg.oficina_weg.application.dto.request.AlunoRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.request.TurmaRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.AlunoResponseDto;
import com.senai.centroweg.oficina_weg.application.dto.response.TurmaResponseDto;
import com.senai.centroweg.oficina_weg.application.mapper.AlunoMapper;
import com.senai.centroweg.oficina_weg.application.mapper.TurmaMapper;
import com.senai.centroweg.oficina_weg.domain.model.Aluno;
import com.senai.centroweg.oficina_weg.domain.model.Turma;
import com.senai.centroweg.oficina_weg.service.AlunoService;
import com.senai.centroweg.oficina_weg.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private TurmaMapper turmaMapper;

    @PostMapping
    public ResponseEntity<TurmaResponseDto> criarTurma(@RequestBody TurmaRequestDto dto) {
        Turma turma = turmaMapper.toEntity(dto);
        Turma salva = turmaService.criarTurma(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaMapper.toDTO(salva));
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponseDto>> listar() {
        return ResponseEntity.ok(turmaService.listarTurmas()
                .stream()
                .map(turmaMapper::toDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDto> buscarPorId(@PathVariable Long id) {
        return turmaService.buscarPorId(id)
                .map(turma -> ResponseEntity.ok(turmaMapper.toDTO(turma)))
                .orElse(ResponseEntity.notFound().build());
    }
}


