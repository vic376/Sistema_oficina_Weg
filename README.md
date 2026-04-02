# Oficina WEG — Refatoração do Sistema de Ordens de Serviço

Projeto desenvolvido como recuperação prática da Unidade Curricular **Arquitetura de Sistemas** (SENAI — AI MIDS 2024/2 INT2).

O objetivo foi refatorar o monolito `SistemaOficinaWegCompleto` em uma arquitetura orientada a objetos com Spring Boot, aplicando os princípios SOLID e padrões de Clean Code.

---

## Problemas do código original

O sistema original concentrava toda a lógica em uma única classe (`SistemaOficinaWegCompleto`), caracterizando uma **God Class** com os seguintes problemas:

- **Dados em vetores estáticos de tamanho fixo** (`String[100]`, `int[100]`), impedindo escalabilidade. Ao atingir o limite, o sistema quebra sem aviso.
- **Sem separação de responsabilidades**: cadastro de usuários, regras de negócio, validações de acesso e exibição de dados estavam misturados no mesmo bloco de código.
- **Regras de acesso frágeis**: a verificação de quem pode aprovar uma OS era feita com `if/else` avulsos, sem encapsulamento. Qualquer mudança exigia alterar múltiplos pontos do código, aumentando o risco de bugs e falhas de segurança.
- **Sem persistência real**: os dados existiam apenas em memória durante a execução.
- **Ausência de polimorfismo**: os tipos de usuário (`PROFESSOR` e `ALUNO`) eram diferenciados por strings comparadas manualmente, sem herança ou interfaces.

---

## Como a nova arquitetura resolve esses problemas

### Separação de responsabilidades

Cada classe tem uma única responsabilidade, respeitando o **Single Responsibility Principle (SRP)**.

### Escalabilidade com coleções dinâmicas

Os vetores fixos foram substituídos por `List<>` gerenciados pelo JPA/Hibernate, com persistência em banco de dados relacional. O sistema agora suporta qualquer quantidade de alunos, turmas e ordens de serviço sem limite estático.

## Princípios SOLID aplicados

### S — Single Responsibility Principle
Cada classe tem uma única razão para mudar. `TurmaService` cuida apenas de turmas; `AlunoMapper` converte apenas entidades de aluno; `OrdemServicoController` apenas recebe e delega requisições HTTP.

**Classes:** todos os `*Service`, `*Mapper`, `*Controller`, `*Repository`.

### O — Open/Closed Principle
A entidade `Usuario` é aberta para extensão (herança) e fechada para modificação. Para adicionar um novo tipo de usuário (ex: Coordenador), basta criar uma nova subclasse sem alterar `Usuario` nem os services existentes.

**Classes:** `Usuario`, `Aluno`, `Professor`.

### L — Liskov Substitution Principle
`Aluno` e `Professor` herdam de `Usuario` e podem ser usados em qualquer lugar onde `Usuario` é esperado, sem quebrar o comportamento do sistema. A estratégia `TABLE_PER_CLASS` no JPA garante que cada tipo tenha sua própria tabela sem conflitos.

**Classes:** `Usuario`, `Aluno`, `Professor`.

### I — Interface Segregation Principle
Os repositórios são interfaces específicas por entidade, cada uma expondo apenas os métodos necessários para aquele domínio. Nenhum repositório carrega métodos de outro.

**Classes:** `IAlunoRepository`, `ITurmaRepository`, `IProfessorRepository`, `IOrdemServicoRepository`.

### D — Dependency Inversion Principle
Os controllers e services dependem de abstrações (interfaces de repositório), não de implementações concretas. A injeção de dependência é gerenciada pelo Spring via `@Autowired`, desacoplando as camadas.

**Classes:** todos os `*Controller` e `*Service` dependem das interfaces `I*Repository`.

---

## Fluxo das Ordens de Serviço

```
POST   /ordens-servico              → Professor cria a OS            (status: ABERTA)
PATCH  /{id}/executar?alunoId=X    → Aluno escalado inicia           (status: EXECUTANDO)
PATCH  /{id}/concluir?alunoId=X    → Aluno escalado conclui          (status: AGUARDANDO_APROVACAO)
PATCH  /{id}/aprovar?professorId=X → Professor responsável encerra   (status: CONCLUIDA)
```

---

## Como rodar o sistema

### Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL (ou outro banco relacional — ajuste o `application.properties`)


```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/oficina_weg
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Executando

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/oficina-weg.git
cd oficina-weg

# Compilar e rodar
./mvnw spring-boot:run
```

O servidor sobe em `http://localhost:8080`.

## Melhorias implementadas além do solicitado

- **Tratamento de erros com mensagens claras**: tentativas de acesso não autorizado (aluno não escalado, professor incorreto, status inválido) retornam `400 Bad Request` com mensagem descritiva em vez de erro genérico.
- **DTOs separados por request/response**: evita exposição desnecessária de campos internos da entidade na resposta da API.
- **Proteção contra `NullPointerException`**: listas de relacionamento são inicializadas com `new ArrayList<>()` nas entidades, evitando falhas ao criar registros sem dependências.
- **Validação de status no fluxo da OS**: cada transição de status verifica se a OS está no estado correto antes de prosseguir, impedindo transições inválidas (ex: aprovar uma OS que ainda está `ABERTA`).
