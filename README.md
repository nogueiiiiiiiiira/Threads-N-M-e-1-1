# Comparação de Desempenho entre Modelos de Threads N:M e 1:1

## Objetivo

Este projeto implementa dois programas em Java que simulam os modelos de execução de threads N:M e 1:1, comparando o desempenho entre eles com base no tempo total de execução. O objetivo é compreender como o modelo de mapeamento de threads influencia a eficiência da execução concorrente.

## Descrição da Tarefa

### Modelo N:M
- Múltiplas threads de usuário são mapeadas para um número menor de threads do sistema operacional.
- Utiliza `Executors.newVirtualThreadPerTaskExecutor()` para simular o multiplexamento de N threads de usuário em M threads do sistema.
- Permite que múltiplas threads de usuário sejam gerenciadas simultaneamente, mas com controle parcial do escalonamento pela aplicação.

### Modelo 1:1
- Múltiplas threads de usuário mapeadas diretamente para threads do sistema operacional.
- Utiliza `Thread` padrão do Java, onde cada thread de usuário corresponde a uma thread do sistema.

## Métrica de Avaliação

- Tempo total de execução (em milissegundos), medido do início ao fim da execução de todas as threads.
- Testado com diferentes quantidades de threads: 10, 50, 100, 500, 1000.

## Resultados dos Testes

| Número de Threads | Modelo N:M (ms) | Modelo 1:1 (ms) |
|-------------------|-----------------|-----------------|
| 10               | 224            | 264            |
| 50               | 521            | 762            |
| 100              | 1429           | 1626           |
| 500              | 10177          | 7155           |
| 1000             | 18044          | 13477          |

## Análise dos Resultados

### Observações Gerais
- O modelo N:M (usando virtual threads) geralmente apresenta melhor desempenho para números menores de threads (10, 50, 100), com tempos menores ou comparáveis ao modelo 1:1.
- Para números maiores de threads (500, 1000), o modelo 1:1 começa a se tornar mais vantajoso, especialmente em 500 threads onde o 1:1 foi mais rápido.

### Interpretação dos Resultados
- **Modelo N:M (Virtual Threads)**: As virtual threads são leves e eficientes para I/O-bound ou tarefas com muitos threads. Elas são multiplexadas em poucas threads do sistema operacional, reduzindo overhead de criação e gerenciamento de threads.
- **Modelo 1:1 (Platform Threads)**: Cada thread corresponde diretamente a uma thread do SO, o que pode ser mais eficiente para CPU-bound tasks com poucos threads, mas pode sofrer com overhead quando há muitas threads devido a context switching e recursos do sistema.

### Ponto de Virada
- O modelo 1:1 se torna mais vantajoso quando o número de threads é alto (acima de 100), possivelmente devido ao overhead das virtual threads em cenários com alta concorrência ou quando o sistema operacional pode otimizar melhor o escalonamento direto.

## Conclusão

O modelo N:M com virtual threads oferece vantagens significativas para aplicações com muitos threads leves, reduzindo latência e overhead. No entanto, para cargas com alto número de threads concorrentes, o modelo 1:1 pode ser mais eficiente devido ao escalonamento direto do SO. A escolha do modelo depende do tipo de aplicação: virtual threads para servidores web e aplicações I/O-bound, platform threads para computação intensiva com poucos threads.