package com.senai.centroweg.oficina_weg.service;

import com.senai.centroweg.oficina_weg.domain.interfaces.IOrdemServicoRepository;
import com.senai.centroweg.oficina_weg.domain.interfaces.IProfessorRepository;

public class ProfessorService {

    private final IProfessorRepository repositoy;

    public ProfessorService(IProfessorRepository repositoy) {
        this.repositoy = repositoy;
    }
}
