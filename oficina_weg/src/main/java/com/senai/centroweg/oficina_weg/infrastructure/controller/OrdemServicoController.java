package com.senai.centroweg.oficina_weg.infrastructure.controller;

import com.senai.centroweg.oficina_weg.application.dto.request.OrdemServicoRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.request.ProfessorRequestDto;
import com.senai.centroweg.oficina_weg.application.dto.response.OrdemServicoResponseDto;
import com.senai.centroweg.oficina_weg.application.dto.response.ProfessorResponseDto;
import com.senai.centroweg.oficina_weg.application.mapper.OrdemServicoMapper;
import com.senai.centroweg.oficina_weg.domain.model.Aluno;
import com.senai.centroweg.oficina_weg.domain.model.OrdemServico;
import com.senai.centroweg.oficina_weg.domain.model.Professor;
import com.senai.centroweg.oficina_weg.service.AlunoService;
import com.senai.centroweg.oficina_weg.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-servicos")
public class OrdemServicoController {


    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoMapper ordemServicoMapper;

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDto> criarOS(@RequestBody OrdemServicoRequestDto dto) {
        List<Aluno> alunos = dto.alunosIds().stream()
                .map(id -> alunoService.buscarPorId(id)
                        .orElseThrow(() -> new RuntimeException("Aluno não encontrado: " + id)))
                .toList();

        OrdemServico os = ordemServicoMapper.toEntity(dto, alunos);
        OrdemServico salva = ordemServicoService.criarOrdemServico(os);

        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoMapper.toDTO(salva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDto> buscarPorId(@PathVariable Long id) {
        return ordemServicoService.buscarPorId(id)
                .map(os -> ResponseEntity.ok(ordemServicoMapper.toDTO(os)))
                .orElse(ResponseEntity.notFound().build());
    }

     @PatchMapping("/{osId}/executar")
        public ResponseEntity<OrdemServicoResponseDto> executarOS(
            @PathVariable Long osId,
             @RequestParam Long alunoId)
     {
       OrdemServico os = ordemServicoService.executarOS(osId, alunoId);


        return ResponseEntity.ok(ordemServicoMapper.toDTO(os));
    }

    @PatchMapping("/{osId}/concluir")
       public ResponseEntity<OrdemServicoResponseDto> concluirExecucao(

            @PathVariable Long osId,
            @RequestParam Long alunoId) {

      OrdemServico os = ordemServicoService.concluirExecucao(osId, alunoId);
           return ResponseEntity.ok(ordemServicoMapper.toDTO(os));
    }

    @PatchMapping("/{osId}/aprovar")
      public ResponseEntity<OrdemServicoResponseDto> aprovarOS(
          @PathVariable Long osId,
            @RequestParam Long professorId) {
        OrdemServico os = ordemServicoService.aprovarOS(osId, professorId);
       return ResponseEntity.ok(ordemServicoMapper.toDTO(os));
    }
}




}
