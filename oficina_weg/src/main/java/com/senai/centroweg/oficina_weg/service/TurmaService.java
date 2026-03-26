package com.senai.centroweg.oficina_weg.service;

import com.senai.centroweg.oficina_weg.domain.interfaces.IOrdemServicoRepository;
import com.senai.centroweg.oficina_weg.domain.interfaces.ITurmaRepository;

public class TurmaService {

    private final ITurmaRepository repositoy;

    public TurmaService(ITurmaRepository repositoy) {
        this.repositoy = repositoy;
    }
}
