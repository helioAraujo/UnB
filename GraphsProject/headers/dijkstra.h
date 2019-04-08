#ifndef DIJKSTRA_H_INCLUDED
#define DIJKSTRA_H_INCLUDED
#include "grafo.h"
#include <stdbool.h>

//---Dijkstra e Auxiliares---//

    float* dijkstra(Grafo* grafo, int s);

    void relaxa(Grafo* grafo, float* distancia, int u, int v, int i);

    int menorDist(Grafo* g, bool* aberto, float* d);

    bool existeAberto(bool* aberto, int size);

//---To Inicializar---//

    void incializaD(Grafo* grafo, float* distancia, int s);

    void incializaAberto(bool* aberto, int size);

#endif