#ifndef PRINTTER_H_INCLUDED
#define PRINTTER_H_INCLUDED
#include <stdio.h>
#include "grafo.h"
#include "mst.h"
//---Printters (Hp, é claro)---//    
    void imprimeGrafo(Grafo* grafo);
    
    void imprimeAresta(Grafo* grafo, int a, int n, FILE* dijkstra);
    
    void imprimeDistancia(float* d, int size);

    void resultadoDijkstra(Grafo* grafo, float* d,int origem, int destino, FILE* dijkstra);
    
    void imprimeMST(Grafo* grafo, Aresta* mstArestas, FILE* mst);
#endif