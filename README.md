# Esports Manager

Projeto de gerenciamento de jogadores e times de esports.  
Desenvolvido para a disciplina de Programação Orientada a Objetos.

## Estrutura do Projeto

```
src/
├── app/            → Main.java (menu console) — Pessoa 4
├── model/
│   ├── jogador/    → Jogador (abstract), JogadorCS, JogadorValorant, JogadorLOL — Pessoa 1
│   ├── time/       → Time — Pessoa 1
│   ├── temporada/  → Temporada — Pessoa 1
│   └── enums/      → Jogo, Posicao — Pessoa 1
├── interfaces/     → Ranqueavel, Exibivel — Pessoa 1
├── repository/     → JogadorRepository, TimeRepository — Pessoa 2
├── service/        → JogadorService, TransferenciaService — Pessoa 3
│                     RankingService, ArquivoService — Sprint 2
├── exception/      → Exceções customizadas — Sprint 2 (Pessoa 3)
├── thread/         → RankingThread — Sprint 2
└── view/           → Telas Swing — Sprint 2 (Pessoa 4)
```

## Divisão de Tarefas — Sprint 1

| Pessoa | Responsabilidade | Pacotes |
|--------|-----------------|---------|
| Pessoa 1 | Modelagem | `model/`, `interfaces/`, `model/enums/` |
| Pessoa 2 | Repositórios | `repository/` |
| Pessoa 3 | Serviços | `service/JogadorService`, `service/TransferenciaService` |
| Medina | Interface Console | `app/Main.java` |

## Divisão de Tarefas — Sprint 2

| Pessoa | Responsabilidade |
|--------|-----------------|
| Pessoa 1 | `service/RankingService` (ranking geral, por jogo, por posição) |
| Pessoa 2 | `service/ArquivoService` (salvar/carregar em arquivos .txt) |
| Pessoa 3 | `exception/` — integrar exceções nos services |
| Medina | `view/` — telas Swing (TelaPrincipal, TelaJogador, TelaRanking, TelaTime) |

## Como configurar no IntelliJ IDEA

1. **File → Open** → selecione a pasta `EsportsManager`
2. Clique com botão direito na pasta `src` → **Mark Directory as → Sources Root**
3. **File → Project Structure → Project** → selecione Java 17+ como SDK
4. Para rodar: abra `app/Main.java` → clique no botão verde de Run

## Convenção de branches Git

```
main          → código estável (merge apenas ao fim da Sprint)
sprint1/p1    → Pessoa 1 — Sprint 1
sprint1/p2    → Pessoa 2 — Sprint 1
sprint1/p3    → Pessoa 3 — Sprint 1
sprint1/p4    → Pessoa 4 — Sprint 1
```

## Regras do Projeto

- Os métodos marcados com `// TODO Pessoa X` são sua responsabilidade.
- Não mexa nos arquivos de outra pessoa sem combinar antes.
- Faça commit com mensagens descritivas: `feat: implementa buscarPorNick no JogadorRepository`
