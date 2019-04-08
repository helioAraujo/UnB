#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <stdbool.h>
#include "headers/grafo.h"
#include "headers/dijkstra.h"
#define INFINITO (float)INT_MAX/2.0f

//---Dijkstra e Auxiliares---//

    /*Função que executa o algoritmo de Dijkstra.
      Recebe um grafo como argumento e o número do vértice a partir do qual
      Retorna uma lista das menores distâncias entre o vértice recebido como argumento
      e os restantes.
    */
    float* dijkstra(Grafo* grafo, int s){
        int size = getSize(grafo);
        float* d = (float*)malloc(size*sizeof(float));
        bool aberto[size];
        incializaD(grafo, d, s);
        incializaAberto(aberto, size);
        while(existeAberto(aberto, size)){

            // Seleciona o vértice de menor distância até o momento
            int u = menorDist(grafo, aberto, d);
            
            // Fecha o vértice selecionado
            aberto[u] = false;

            // Percorre a lista de adjacencia de u relaxando as arestas
            for(int i = 0; i < getGrau(grafo, u) && getGrau(grafo, u) > 0; i++){
                relaxa(grafo, d, u, grafo->vertice[u].listaAresta[i].destino, i);
            }
        }    
        return d;
        
    }

    /*
        Função que realiza uma operação de relaxação se a distância do vértice v for
        maior que a distância do vértice u mais o peso da aresta
    */
    void relaxa(Grafo* grafo, float* distancia, int u, int v, int i){
        
        /*
            Verifica se o peso atual para alcanaçar a aresta v é maior que
            o peso da aresta u mais o peso de u para v 
        */ 
        if(distancia[v] > distancia[u] + getPeso(grafo, u, i)){
            grafo->vertice[v].ant = u;
            distancia[v] = distancia[u] + getPeso(grafo, u, i);
        }
    }

    /*
        Retorna o índice do vertice com menor distância na lista d
     */
    int menorDist(Grafo* g, bool* aberto, float* d){
        int  i;
        // Descobre o primeiro aberto
        for(i=0; i < g->size; i++){
            if(aberto[i]){
                break;
            }
        }
        // Verifica se o grafo está todo fechado
        if(i == g->size){
            return -1;
        }
        int menor = i;

        // Seleciona o menor aberto
        for(i = i +1; i < g->size; i++){
            if(aberto[i] && (d[menor]) > d[i]){
                menor = i;
            }
        }
        return menor;
    }

    // Verifica se ainda existem vértices não visitados (aberto)
    bool existeAberto(bool* aberto, int size){
        for(int i = 0; i < size; i++){
            if(aberto[i]){
                return true;
            }
        }
        return false;
    }

//---To Inicializar---//

    // Inicializa lista de distâncias com valor infinito, exceto s
    void incializaD(Grafo* grafo, float* distancia, int s){
       
        for(int v = 0; v < grafo->size; v++){
            distancia[v] = INFINITO;
        }

        // s recebe zero pois ele é o ponto de partida
        distancia[s] =0.0f;
    }

    // Inicializa lista de aberto: default = true    
    void incializaAberto(bool* aberto, int size){
        for(int i=0; i< size;i++){
            aberto[i] = true;
        }
    }