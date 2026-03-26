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
}
