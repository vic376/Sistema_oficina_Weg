package com.senai.centroweg.oficina_weg.service;

import com.senai.centroweg.oficina_weg.domain.interfaces.IAlunoRepository;
import com.senai.centroweg.oficina_weg.domain.interfaces.IOrdemServicoRepository;
import com.senai.centroweg.oficina_weg.domain.model.Aluno;

import java.util.List;
import java.util.Optional;

public class AlunoService {

    private final IAlunoRepository repositoy;

    public AlunoService(IAlunoRepository repositoy) {
        this.repositoy = repositoy;
    }

    public Aluno criarAluno (Aluno aluno){
        return repositoy.save(aluno);
    }

    public List<Aluno> listarAlunos (){
        return repositoy.findAll();
    }

    public Optional<Aluno> buscarPorId (Long id){
        return repositoy.findById(id);
    }
}
