package com.senai.centroweg.oficina_weg.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String equipamento;

    @Column(nullable = false)
    private String defeito;

    @Column(nullable = false)
    private String materiaisQuantidade;

    @Column(nullable = false)
    private String laudoTecnico;

    @Column(nullable = false)
    private Long IdProfessorResponsavel;

    @ManyToMany
    @JoinTable(name = "os_alunos",
    joinColumns = @JoinColumn(name = "os_id"),
    inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunosResponsaveis;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

}
