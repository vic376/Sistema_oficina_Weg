package com.senai.centroweg.oficina_weg.domain.interfaces;

import com.senai.centroweg.oficina_weg.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
