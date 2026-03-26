package com.senai.centroweg.oficina_weg.infrastructure.controller;

import com.senai.centroweg.oficina_weg.application.dto.request.AlunoRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.AlunoResponseDto;
import com.senai.centroweg.oficina_weg.application.mapper.AlunoMapper;
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
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<AlunoResponseDto> criarAluno(@RequestBody AlunoRequestDto dto) {
        Turma turma = turmaService.buscarPorId(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
        Aluno aluno = alunoMapper.toEntity(dto, turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoMapper.toDTO(alunoService.cadastrarAluno(aluno)));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDto>> listar() {
        return ResponseEntity.ok(alunoService.listarAlunos()
                .stream()
                .map(alunoMapper::toDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(aluno -> ResponseEntity.ok(alunoMapper.toDTO(aluno)))
                .orElse(ResponseEntity.notFound().build());
    }
}
