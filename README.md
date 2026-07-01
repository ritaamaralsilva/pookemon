# Pookémon RPG — Kanto Console Edition

Um motor de jogo de RPG em texto baseado em turnos, desenvolvido em **Java (POO)**. O projeto replica as mecânicas clássicas da franquia Pokémon através duma arquitetura extensível e robusta executada inteiramente na consola do terminal.

---

##  Objetivos Core do Sistema

*   **Simulação de Ciclo de Vida:** Gerir o estado completo de um treinador (inventário, progresso, moedas) e a progressão do seu Pookémon inicial (nível, hp, evoluções).
*   **Método de Combate por turnos com alterações de estado e cura através de poções e consumíveis:** Consolidar a lógica de batalha por turnos interpretando atributos mutáveis, restrições de estado e tomada de decisão com Random em tempo real.
*   **Encapsulamento de Fluxo:** Centralizar as interações de exploração, comércio (Pooké Shop) e recuperação (Pooké Center) num ciclo de jogo controlado e livre de fugas de memória.

---

##  Funcionalidades Principais & Arquitetura

*   **Motor de Batalha por Turnos:** Sistema dinâmico que processa prioridade por velocidade, gestão de recursos (PP) e cálculo de dano. O consumo de itens consome a ação do turno do jogador, permitindo o contra-ataque imediato do oponente gerido com Random para utilização de ataque especial.
*   **Arquitetura Polimórfica de Itens:** Gestão de inventário baseada em herança a partir de uma classe abstrata para a aplicação segmentada e segura de consumíveis e poções.
*   **Ciclo de Progressão Escalável:** Algoritmos para ganho de experiência, evolução proporcional de atributos em *Level Up* e substituição dinâmica de instâncias através de árvores de evolução.
*   **Módulo de Áudio Integrado:** Sistema de threads em background para reprodução de efeitos sonoros e bandas sonoras temáticas em formato digital, sincronizadas com os eventos do jogo.
*   **Tratamento de Erros e UX:** Interface modular via ANSI (`ConsoleColors`) com isolamento de falhas via `try-catch` (`InputMismatchException`), garantindo resiliência contra inputs inválidos do utilizador.

---

##  Estrutura de Finais Isolados

O motor avalia o estado do jogador e o cumprimento de condições de vitória específicas para despoletar **três finais logicamente independentes**:
1.  **Vitória da Liga (Standard):** Ativado após a derrota sequencial dos 8 Líderes de Ginásio da região de Kanto.
2.  **Vitória do Indigo Plateau (Mew vs Trainer Blue):** Desbloqueado via fluxo alternativo de exploração ao aceder à *Secret Cave*, culminando na batalha contra Mew, sua captura e no confronto final com o rival (Trainer Blue) no Indigo Plateau.
3.  **Desafio Secreto (Mewtwo):** Desbloqueado via fluxo alternativo de exploração ao aceder à *Secret Cave*, culminando no confronto final com o Pookémon Lendário.
4.  **O Fim de um Herói (Derrota):** Acionado instantaneamente quando o Pookémon principal do jogador fica sem pontos de vida (HP = 0) em combate sem possibilidade de reviver, resultando em *Game Over*.

---

##  Organização do Projeto

```text
├── src/
│   ├── game/          # Motor de Jogo, Controladores, Utilidades e Fluxo de Áudio
│   ├── entities/      # Camada de Dados: Superclasse Pokemon, Subclasses de Espécies e classe Trainer
│   └── items/         # Sistema de Inventário: Classe Abstrata TrainerItem e Subclasses (Potion, Consumable, BattleConsumable)
├── resources/
│   ├── art/           # Ficheiros estruturados de Arte ASCII (.txt)
│   └── audio/         # Ficheiros de som e bandas sonoras do sistema
└── LICENSE            # Licença MIT + Isenção de Responsabilidade 
```


##  Instruções de Execução

Para compilar e correr o projeto através do terminal, execute os seguintes comandos a partir da raiz do repositório:

### 1. Compilação
Compilar de forma recursiva todos os pacotes e subpastas de código-fonte (`game`, `entities`, `items`):
```
bash
javac -d bin src/**/*.java
```

### 2. Execução
```
bash
java -cp bin game.Main
```

## Licença e Termos Legais
Este projeto está sob a [Licença MIT](LICENSE). 

<sub style="color: gray;">
<b>Legal Notice:</b> Pokémon e marcas associadas são propriedade da Nintendo, Game Freak e The Pokémon Company. Este projeto possui fins estritamente académicos e não comerciais. A licença aplica-se exclusivamente ao código Java proprietário desenvolvido neste repositório.
</sub>
