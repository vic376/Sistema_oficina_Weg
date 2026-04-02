package com.senai.centroweg.oficina_weg.service;

import com.senai.centroweg.oficina_weg.domain.interfaces.IOrdemServicoRepository;
import com.senai.centroweg.oficina_weg.domain.interfaces.IProfessorRepository;
import com.senai.centroweg.oficina_weg.domain.model.OrdemServico;
import com.senai.centroweg.oficina_weg.domain.model.Professor;
import com.senai.centroweg.oficina_weg.domain.model.StatusOrdemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private IOrdemServicoRepository repository;

    @Autowired
    private  IProfessorRepository professorRepository;

    public List<OrdemServico> listarOrdemServico(){
        return repository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Long id){
        return repository.findById(id);
    }

    public OrdemServico criarOrdemServico(OrdemServico ordemServico) {
        professorRepository.findById(ordemServico.getIdProfessorResponsavel())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));

        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        return repository.save(ordemServico);
    }

    public OrdemServico executarOS(Long osId, Long alunoId) {
        OrdemServico os = repository.findById(osId)
                .orElseThrow(() -> new RuntimeException("OS não encontrada."));
        if (!os.getStatus().equals(StatusOrdemServico.ABERTA)) {
            throw new RuntimeException("OS não está disponível para execução.");
        }

        boolean alunoEscalado = os.getAlunosResponsaveis()
                .stream()
                .anyMatch(aluno -> aluno.getId().equals(alunoId));

        if (!alunoEscalado) {
            throw new RuntimeException("Aluno não está escalado para esta OS.");
        }

        os.setStatus(StatusOrdemServico.EXECUTANDO);
        return repository.save(os);
    }

    public OrdemServico aprovarOS(Long osId, Long professorId) {
        OrdemServico os = repository.findById(osId)
                .orElseThrow(() -> new RuntimeException("OS não encontrada."));

        if (!os.getIdProfessorResponsavel().equals(professorId)) {
            throw new RuntimeException("Somente o professor que abriu a OS pode encerrá-la.");

        }

        if (!os.getStatus().equals(StatusOrdemServico.AGUARDANDO_APROVACAO)) {

            throw new RuntimeException("OS não está em execução para ser aprovada.");
        }

        os.setStatus(StatusOrdemServico.CONCLUIDA);

        return repository.save(os);
    }


}
