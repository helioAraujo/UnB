#ifndef MST_H_INCLUDED
#define MST_H_INCLUDED

typedef struct aresta{
    int a;
    int b;
    float peso;
}Aresta;

Aresta* mstPrim(Grafo* grafo, int r);
int minimum(Vertice* lista, int V);
#endif//! MST_H_INCLUDED