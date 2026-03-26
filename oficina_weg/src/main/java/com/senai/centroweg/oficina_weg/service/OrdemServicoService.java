package com.senai.centroweg.oficina_weg.service;

import com.senai.centroweg.oficina_weg.domain.interfaces.IOrdemServicoRepository;
import com.senai.centroweg.oficina_weg.domain.model.OrdemServico;

public class OrdemServicoService {

    private final IOrdemServicoRepository repositoy;

    public OrdemServicoService(IOrdemServicoRepository repositoy) {
        this.repositoy = repositoy;
    }
}
