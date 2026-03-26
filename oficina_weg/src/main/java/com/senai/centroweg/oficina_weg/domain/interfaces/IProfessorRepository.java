package com.senai.centroweg.oficina_weg.domain.interfaces;

import com.senai.centroweg.oficina_weg.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfessorRepository extends JpaRepository<Professor, Long> {
}
