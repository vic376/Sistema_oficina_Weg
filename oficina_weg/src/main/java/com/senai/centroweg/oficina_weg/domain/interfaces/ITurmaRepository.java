package com.senai.centroweg.oficina_weg.domain.interfaces;

import com.senai.centroweg.oficina_weg.domain.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurmaRepository extends JpaRepository<Turma, Long> {
}
