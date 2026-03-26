package com.senai.centroweg.oficina_weg.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "aluno")

public class Aluno extends Usuario {

    @ManyToOne
    @Column(nullable = false)
    private Turma turma;

    //turmaid ou chav estrangeira

}
