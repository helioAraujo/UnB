#include "headers/printter.h"
#include "headers/grafo.h"
#include "headers/mst.h"
#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <limits.h>

#define INFINITO (float)INT_MAX/2.0f

/*
    Função que dado um grafo passado por argumento e o número do vértice raiz executa o algoritmo
    de Prim, retornando um vetor de Arestas que contém as arestas selecionadas pelo algoritmo.
*/
Aresta* mstPrim(Grafo* grafo, int r){
    int atribui = 1;
    Vertice* lista = (Vertice*)malloc(getSize(grafo)*sizeof(Vertice));
    Aresta* mstArestas = (Aresta*)malloc(getSize(grafo)*sizeof(Aresta));
    int vertAdj;
    float pesoMST;
    int i, u, indAdj;
    int count = 0 ;
    
    //inicializa os valores
    for (int i = 0; i<getSize(grafo); i++){
        lista[i] = grafo->vertice[i];
        lista[i].valor = INFINITO;
        lista[i].statusMST = 0;
        grafo->vertice[i].statusMST = 0;
        mstArestas[i].a = 0;
        mstArestas[i].b = 0;
        mstArestas[i].peso = 0.0;
    }

    //marca o valor do vertice raiz como 0, assim ele será selecionado na primeira chamada da função minimum()
    lista[r].valor = 0;    
    
    //Repete-se selecionando todos os vértices do grafo.
    while(count < getSize(grafo)-1){
        atribui = 0;
        int u = minimum(lista,getSize(grafo));//seleciona o vértice que tem menor valor entre todos os outros ainda não visitados
        lista[u].statusMST = 1;
        
        //para o vértice selecionado, percorre toda a lista de adjacência atualizando o valor
        //dos vértices vizinhos quando forem maior que o peso da aresta
        for(indAdj=0; indAdj < getGrau(grafo,u); indAdj++){//Adjacentes de 0 até grau do vértice                
            vertAdj = lista[u].listaAresta[indAdj].destino;
            if (lista[vertAdj].statusMST == 0 && getPeso(grafo,u,indAdj) < lista[vertAdj].valor){
                lista[vertAdj].valor = getPeso(grafo,u,indAdj);
                mstArestas[lista[u].listaAresta[indAdj].destino].a = u + 1;
                mstArestas[lista[u].listaAresta[indAdj].destino].b = lista[u].listaAresta[indAdj].destino + 1;
                mstArestas[lista[u].listaAresta[indAdj].destino].peso = getPeso(grafo,u,indAdj);
                atribui = 1;
            }               
        }
        count++;
    }
    return mstArestas; 
}

/*Função que recebe uma lista de vertices e o tamanho do grafo
  Retorna o índice do vértice que possui menor valor
*/
int minimum(Vertice* lista, int V){
    float min = INFINITO;
    int minInd;;
    int i,indAdj;
    int existeMenor = 0;
    for(int indAdj = 0; indAdj < V; indAdj++){
        if(lista[indAdj].valor < min && lista[indAdj].statusMST == 0){
            min = lista[indAdj].valor;
            minInd = indAdj;
            existeMenor = 1;
        }
    }

    return minInd;
}