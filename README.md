
# README –  TDE3 -Comparativo de Algoritmos de Ordenação

## 1. Introdução

O objetivo deste trabalho é realizar um estudo comparativo entre diferentes algoritmos de ordenação implementados em **Java**, analisando seu comportamento quanto ao número de **trocas** e **iterações** realizadas durante a execução.
Os algoritmos foram aplicados sobre três tipos distintos de vetores:

1. **Vetor 1 – Desordenado**
2. **Vetor 2 – Ordenado (Crescente)**
3. **Vetor 3 – Decrescente**

Essa comparação permite compreender o desempenho relativo de cada método em diferentes situações, analisando tanto a **eficiência computacional** quanto a **quantidade de movimentações de elementos**.



## **2. Algoritmos Implementados**

Os seguintes algoritmos de ordenação foram implementados **sem o uso de funções prontas** como `length`, calculando manualmente o tamanho dos vetores:

1. **Bubble Sort (com flag)** – variação do Bubble Sort tradicional que detecta se o vetor já está ordenado, interrompendo o laço antecipadamente.
2. **Selection Sort** – busca o menor elemento em cada iteração e o posiciona corretamente; realiza o mínimo possível de trocas.
3. **Gnome Sort** – similar ao Insertion Sort, porém movimentando os elementos para trás sempre que necessário.
4. **Comb Sort** – extensão do Bubble Sort, utilizando um “gap” que diminui gradualmente para eliminar pequenas bolhas mais rapidamente.
5. **Cocktail Sort** – variação bidirecional do Bubble Sort, percorrendo o vetor em ambas as direções a cada ciclo.
6. **Bucket Sort** – divide o vetor em “baldes”, ordenando individualmente e recompondo o vetor final; excelente para distribuições uniformes.



## **3. Metodologia**

Cada algoritmo foi testado sobre três tipos de vetores (desordenado, ordenado e decrescente).
Durante a execução, foram contabilizados:

* **Número de trocas:** quantas vezes dois elementos trocaram de posição.
* **Número de iterações:** quantas vezes os laços principais foram executados.

Os dados foram coletados com base na execução do programa e analisados comparativamente em tabelas.



## **4. Resultados Obtidos**

### **Vetor 1 – Desordenado**

| Algoritmo          | Trocas | Iterações |
| ------------------ | ------ | --------- |
| Comb Sort          | 22     | 129       |
| Gnome Sort         | 78     | 176       |
| Bucket Sort        | 19     | 58        |
| Bubble Sort (flag) | 78     | 285       |
| Selection Sort     | 18     | 190       |
| Cocktail Sort      | 78     | 154       |

### **Vetor 2 – Ordenado**

| Algoritmo          | Trocas | Iterações |
| ------------------ | ------ | --------- |
| Comb Sort          | 0      | 110       |
| Gnome Sort         | 0      | 20        |
| Bucket Sort        | 0      | 39        |
| Bubble Sort (flag) | 0      | 19        |
| Selection Sort     | 0      | 190       |
| Cocktail Sort      | 0      | 19        |

### **Vetor 3 – Decrescente**

| Algoritmo          | Trocas | Iterações |
| ------------------ | ------ | --------- |
| Comb Sort          | 18     | 129       |
| Gnome Sort         | 190    | 400       |
| Bucket Sort        | 20     | 59        |
| Bubble Sort (flag) | 190    | 380       |
| Selection Sort     | 10     | 190       |
| Cocktail Sort      | 190    | 190       |


## **5. Análise Comparativa**

### **Tabela 1 – Ranking por número de trocas (menos trocas = melhor)**

| Posição | Algoritmo          | Vetor 1 | Vetor 2 | Vetor 3 | Observação                                  |
| ------- | ------------------ | ------- | ------- | ------- | ------------------------------------------- |
| 1º      | Selection Sort     | 18      | 0       | 10      | Realiza o mínimo de trocas possível         |
| 2º      | Bucket Sort        | 19      | 0       | 20      | Desempenho consistente                      |
| 3º      | Comb Sort          | 22      | 0       | 18      | Bom equilíbrio                              |
| 4º      | Bubble Sort (flag) | 78      | 0       | 190     | Sensível à desordem                         |
| 5º      | Cocktail Sort      | 78      | 0       | 190     | Igual ao Bubble Sort nos piores casos       |
| 6º      | Gnome Sort         | 78      | 0       | 190     | Piora significativamente em vetores grandes |

**Análise:**
O **Selection Sort** teve o menor número de trocas em todos os casos, mostrando-se mais eficiente nesse critério.
O **Bucket Sort** e o **Comb Sort** também apresentaram bons resultados, com poucas movimentações em qualquer cenário.



### **Tabela 2 – Ranking por número de iterações (menos iterações = melhor)**

| Posição | Algoritmo          | Vetor 1 | Vetor 2 | Vetor 3 | Observação                                 |
| ------- | ------------------ | ------- | ------- | ------- | ------------------------------------------ |
| 1º      | Bucket Sort        | 58      | 39      | 59      | Melhor eficiência geral                    |
| 2º      | Bubble Sort (flag) | 285     | 19      | 380     | Excelente em vetor ordenado                |
| 3º      | Cocktail Sort      | 154     | 19      | 190     | Bom desempenho em vetor ordenado           |
| 4º      | Gnome Sort         | 176     | 20      | 400     | Mediano no geral                           |
| 5º      | Comb Sort          | 129     | 110     | 129     | Regular                                    |
| 6º      | Selection Sort     | 190     | 190     | 190     | Mesmo número de iterações em qualquer caso |

**Análise:**
O **Bucket Sort** teve o melhor desempenho geral em iterações, sendo o mais eficiente para todos os tipos de entrada.
Já o **Selection Sort**, apesar de fazer poucas trocas, apresentou alto número de iterações, o que pode impactar o tempo de execução.



## **6. Conclusão**

* **Melhor algoritmo em trocas:** *Selection Sort*
* **Melhor algoritmo em iterações:** *Bucket Sort*
* **Melhor desempenho geral:** *Bucket Sort*, seguido de *Comb Sort*

O **Bucket Sort** apresentou o comportamento mais equilibrado, mantendo poucas trocas e baixo número de iterações em todas as situações testadas.
O **Selection Sort** é previsível e estável, mas seu número fixo de iterações o torna menos eficiente em grandes vetores.
Por outro lado, algoritmos simples como **Bubble Sort**, **Cocktail Sort** e **Gnome Sort** mostraram desempenho satisfatório apenas em vetores já ordenados, sendo ineficientes em cenários de desordem.



7. Conclusão Geral do Trabalho

Este estudo evidencia que a escolha do algoritmo de ordenação ideal depende do tipo de entrada e da prioridade de desempenho.
Se o critério principal for **mínimo número de trocas**, o **Selection Sort** é o mais indicado.
Se a prioridade for **velocidade e eficiência em iterações**, o **Bucket Sort** se destaca como a melhor opção geral.


