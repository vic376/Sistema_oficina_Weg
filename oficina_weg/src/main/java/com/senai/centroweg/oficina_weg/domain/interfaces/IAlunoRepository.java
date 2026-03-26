package com.senai.centroweg.oficina_weg.domain.interfaces;

import com.senai.centroweg.oficina_weg.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
}
