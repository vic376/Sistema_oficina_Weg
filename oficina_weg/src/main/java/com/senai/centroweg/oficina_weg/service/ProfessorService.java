package com.senai.centroweg.oficina_weg.service;

import com.senai.centroweg.oficina_weg.domain.interfaces.IProfessorRepository;
import com.senai.centroweg.oficina_weg.domain.model.Professor;

import java.util.Optional;

public class ProfessorService {

    private final IProfessorRepository repositoy;

    public ProfessorService(IProfessorRepository repositoy) {
        this.repositoy = repositoy;
    }

    public Professor criarProfessor (Professor professor){
        return repositoy.save(professor);
    }

    public Optional<Professor> buscarPorId (Long id){
        return repositoy.findById(id);
    }
}
