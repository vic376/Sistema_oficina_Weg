package com.senai.centroweg.oficina_weg.service;

import com.senai.centroweg.oficina_weg.domain.interfaces.IOrdemServicoRepository;
import com.senai.centroweg.oficina_weg.domain.interfaces.ITurmaRepository;
import com.senai.centroweg.oficina_weg.domain.model.Turma;

import java.util.List;
import java.util.Optional;

public class TurmaService {

    private final ITurmaRepository repository;

    public TurmaService(ITurmaRepository repositoy) {
        this.repository = repositoy;
    }

    public Turma criarTurma (Turma turma){
        return repository.save(turma);
    }

    public Optional<Turma> buscarPorId( Long id){
        return repository.findById(id);
    }

    public List<Turma> listarTurmas (){
        return repository.findAll();
    }
}
