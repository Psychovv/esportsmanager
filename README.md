# Esports Manager

Esports Manager — Java OOP academic project for managing players, teams, and transfers across CS, Valorant, and League of Legends. Built with layered architecture (model, repository, service, view) featuring console UI, Swing GUI, file persistence, and multithreading.

**Status: Sprint 1 e Sprint 2 completas.** ✅

## Estrutura do Projeto

```
src/
├── app/            → Main.java (menu console completo)
├── model/
│   ├── jogador/    → Jogador (abstract), JogadorCS, JogadorValorant, JogadorLOL
│   ├── time/       → Time
│   ├── temporada/  → Temporada
│   └── enums/      → Jogo, Posicao
├── interfaces/     → Ranqueavel, Exibivel
├── repository/     → JogadorRepository, TimeRepository
├── service/        → JogadorService, TransferenciaService, RankingService, ArquivoService
├── exception/      → JogadorNaoEncontradoException, TimeNaoEncontradoException, TransferenciaInvalidaException
├── thread/         → RankingThread (atualiza ranking a cada 10s em background)
└── view/           → TelaPrincipal, TelaJogador, TelaTime, TelaRanking (Swing)
```

## O que o sistema faz

**Console (`app/Main.java`)**
- Cadastrar, buscar, listar e remover jogadores (CS, Valorant, LOL — cada um com stats próprias)
- Cadastrar e listar times, ver elenco de cada time
- Transferir jogador entre times (valida jogo compatível e limite de 5 jogadores por time)
- Ranking geral, por jogo, por posição, e ranking de times
- Salvar e carregar jogadores/times em arquivo `.txt`
- Thread em segundo plano que recalcula e imprime o ranking a cada 10 segundos

**Interface gráfica (Swing)**
- `TelaPrincipal`: menu com botões para navegar entre as telas
- `TelaJogador`: tabela de jogadores, cadastro via formulário, remover e transferir
- `TelaTime`: tabela de times, cadastro via formulário, ver elenco
- `TelaRanking`: tabela de ranking com filtro por jogo ou por times

## Como rodar no IntelliJ IDEA

1. **File → Open** → selecione a pasta do projeto
2. Clique com botão direito na pasta `src` → **Mark Directory as → Sources Root**
3. **File → Project Structure → Project** → selecione Java 17+ como SDK
4. Para rodar o **console**: abra `app/Main.java` → Run
5. Para rodar a **interface gráfica**: abra `view/TelaPrincipal.java` → Run

## Regras de negócio implementadas

- Não é possível cadastrar dois jogadores com o mesmo nick.
- Um jogador só pode entrar em um time do mesmo jogo (CS só vai pra time de CS, etc).
- Um time aceita no máximo 5 jogadores.
- Times com menos de 5 jogadores sofrem uma penalidade de 20% na pontuação geral.
- A pontuação de cada jogador é calculada por uma fórmula própria do jogo (rating/abates/HS% para CS, ACS/KDA para Valorant, KDA/CS por minuto para LOL).

## Arquivos gerados

Ao usar "Salvar em arquivo", o sistema cria `jogadores.txt` e `times.txt` na raiz do projeto. Eles não vão para o Git (estão no `.gitignore`).
