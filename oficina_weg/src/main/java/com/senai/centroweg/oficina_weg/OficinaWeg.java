package com.senai.centroweg.oficina_weg;

import java.util.Scanner;

public class OficinaWeg {

    // - "BANCO DE DADOS" EM VETORES (ESTÁTICO) -
    static String[] usuariosNomes = new String[100];
    static String[] usuariosTipos = new String[100]; // PROFESSOR ou ALUNO
    static int totalPessoas = 0;

    // ESTRUTURA DE 4 TURMAS
    static String[] turmasNomes = {"Cibersistemas T1", "Cibersistemas T2", "Mecânica T3", "Mecânica T4"};
    static int[][] turmasAlunos = new int[4][15];
    static int[] qtdAlunosPorTurma = new int[4];

    // GESTÃO DE ORDENS DE SERVIÇO (OS)
    static int totalOS = 0;
    static int[] osId = new int[100];
    static String[] osEquipamento = new String[100];
    static String[] osDefeitoRelatado = new String[100];
    static String[] osStatus = new String[100];
    static String[] osMateriaisUsados = new String[100];
    static String[] osConclusaoTecnica = new String[100];
    static int[] osProfessorResponsavel = new int[100];
    static int[][] osAlunosEscalados = new int[100][3];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        popularSistema();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========================================");
            System.out.println(" SISTEMA DE MANUTENÇÃO - ESCOLA WEG");
            System.out.println("========================================");
            System.out.println("1 - Sinalizar Problema (Qualquer Usuário)");
            System.out.println("2 - Abrir Ordem de Serviço (Apenas Professor)");
            System.out.println("3 - Registrar Execução e Materiais (Alunos da OS)");
            System.out.println("4 - Aprovar e Encerrar OS (Professor Responsável)");
            System.out.println("5 - Listar Turmas e Alunos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Seu Nome: ");
                    String q = sc.nextLine();

                    System.out.print("Equipamento com defeito: ");
                    String e = sc.nextLine();

                    System.out.print("Descrição do problema: ");
                    String d = sc.nextLine();

                    System.out.println("\n[REGISTRO]: " + q + " sinalizou falha no equipamento " + e + ".");
                    break;

                case 2:
                    System.out.print("Digite seu ID de Professor: ");
                    int idProf = sc.nextInt();

                    if (idProf >= totalPessoas || !usuariosTipos[idProf].equals("PROFESSOR")) {
                        System.out.println("ERRO: Acesso negado. Apenas professores podem abrir OS.");
                        break;
                    }

                    osId[totalOS] = totalOS + 1;

                    System.out.print("Equipamento: ");
                    sc.nextLine();
                    osEquipamento[totalOS] = sc.nextLine();

                    System.out.print("Defeito encontrado: ");
                    osDefeitoRelatado[totalOS] = sc.nextLine();

                    osProfessorResponsavel[totalOS] = idProf;

                    System.out.println("Escalar 2 alunos para a tarefa (Digite os IDs):");
                    osAlunosEscalados[totalOS][0] = sc.nextInt();
                    osAlunosEscalados[totalOS][1] = sc.nextInt();

                    osStatus[totalOS] = "EXECUTANDO";

                    System.out.println("OS nº " + osId[totalOS] + " aberta e atribuída aos alunos.");
                    totalOS++;
                    break;

                case 3:
                    System.out.print("ID da OS: ");
                    int idEx = sc.nextInt() - 1;

                    System.out.print("Seu ID de Aluno: ");
                    int idAlu = sc.nextInt();

                    boolean autorizado = false;

                    for (int i = 0; i < 3; i++) {
                        if (osAlunosEscalados[idEx][i] == idAlu) {
                            autorizado = true;
                        }
                    }

                    if (autorizado && osStatus[idEx].equals("EXECUTANDO")) {
                        System.out.print("Materiais e Quantidades utilizadas: ");
                        sc.nextLine();
                        osMateriaisUsados[idEx] = sc.nextLine();

                        System.out.print("Descrição técnica do que foi feito: ");
                        osConclusaoTecnica[idEx] = sc.nextLine();

                        osStatus[idEx] = "AGUARDANDO_APROVACAO";

                        System.out.println("Dados salvos. OS enviada para aprovação do professor.");
                    } else {
                        System.out.println("ERRO: Você não está escalado para esta OS ou ela não está em execução.");
                    }
                    break;

                case 4:
                    System.out.print("ID da OS: ");
                    int idAp = sc.nextInt() - 1;

                    System.out.print("ID do Professor: ");
                    int idPrAp = sc.nextInt();

                    if (osProfessorResponsavel[idAp] == idPrAp &&
                            osStatus[idAp].equals("AGUARDANDO_APROVACAO")) {

                        osStatus[idAp] = "CONCLUIDA";

                        System.out.println("\n - OS ENCERRADA COM SUCESSO -");
                        System.out.println("Equipamento: " + osEquipamento[idAp]);
                        System.out.println("Materiais: " + osMateriaisUsados[idAp]);
                        System.out.println("Laudo: " + osConclusaoTecnica[idAp]);

                    } else {
                        System.out.println("ERRO: Aprovação negada. Verifique se você é o responsável.");
                    }
                    break;

                case 5:
                    for (int t = 0; t < 4; t++) {
                        System.out.println("\n" + turmasNomes[t] +
                                " (Prof. " + usuariosNomes[t < 2 ? 0 : 1] + ")");

                        for (int a = 0; a < qtdAlunosPorTurma[t]; a++) {
                            int idA = turmasAlunos[t][a];
                            System.out.println(" ID " + idA + ": " + usuariosNomes[idA]);
                        }
                    }
                    break;
            }
        }
    }

    private static void popularSistema() {

        // PROFESSORES
        usuariosNomes[0] = "Mestre Ricardo (WEG)";
        usuariosTipos[0] = "PROFESSOR";

        usuariosNomes[1] = "Mestre Alexandre (WEG)";
        usuariosTipos[1] = "PROFESSOR";

        int idAtual = 2;

        // ALUNOS
        for (int t = 0; t < 4; t++) {
            int alunosNaTurma = 10 + t;
            qtdAlunosPorTurma[t] = alunosNaTurma;

            for (int i = 0; i < alunosNaTurma; i++) {
                usuariosNomes[idAtual] = "Estudante_" + idAtual;
                usuariosTipos[idAtual] = "ALUNO";
                turmasAlunos[t][i] = idAtual;
                idAtual++;
            }
        }

        totalPessoas = idAtual;

        System.out.println("Sistema WEG Inicializado: 2 Professores e "
                + (totalPessoas - 2) + " Alunos carregados.");
    }
}