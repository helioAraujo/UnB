#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "headers/printter.h"
#include "headers/mst.h"
#include "headers/grafo.h"
#define INFINITO INT_MAX/2.0f

//---Printters (Hp, é claro)---//
    
    void imprimeGrafo(Grafo* grafo){
        FILE* null;
        for (int i = 0; i < grafo->size; i++) {
            for(int j = 0; j < getGrau(grafo, i); j++){
                imprimeAresta(grafo, i, j,null);
            }
            printf("\n");
        }
    }

    void imprimeAresta(Grafo* grafo, int a, int n, FILE *dijkstra){
        if (n >= getGrau(grafo, a)) {
            printf("Aresta Inexixtente!\n");
            exit(1);
        }

        printf("%d ", a + 1);
        printf("%d ",grafo->vertice[a].listaAresta[n].destino + 1);
        printf("%.2f\n",grafo->vertice[a].listaAresta[n].peso);
        fprintf(dijkstra,"%d %d %.2f\r\n", a+1,grafo->vertice[a].listaAresta[n].destino + 1,grafo->vertice[a].listaAresta[n].peso);
        
        if(grafo->vertice[a].ant + 1 == 0){
            printf("vertice inicial\n");
            return;
        }
    }

    void imprimeDistancia(float* d, int size){
        for(int i = 0; i < size; i++){
            if(d[i] == INFINITO){
                printf("%d: Infinito\n", i);
            } else {
                printf(("%d: %.2f\n"),i,d[i]);
            }
        }
    }

    void resultadoDijkstra(Grafo* grafo, float* d, int origem, int destino, FILE* dijkstra){
        
        if (d[destino] == INFINITO){
            printf("O vértice %d não é alcançável a partir de %d\n", destino + 1, origem + 1);
            return;
        }
        
        int size = getSize(grafo);
        int i, j = size - 1;
        int back[size];
        float peso = 0.0f;
        
        for(int cont = 0; cont < size; cont++){
            back[cont] = -1;
        }
       
        back[j] = destino;
        j--;
        for(i = destino;  i != origem; ){
            i = grafo->vertice[i].ant;
            back[j] = i;
            j --;
        }
        int numeroSaltos = size - j - 2;
        printf("%d\n",numeroSaltos);
        fprintf(dijkstra,"%d\r\n", numeroSaltos);
        for(j = j + 1; j < size - 1; j++){
            int temp = buscaVerticeAdj(grafo, back[j], back[j+1]);
            peso += grafo->vertice[back[j]].listaAresta[temp].peso;
            imprimeAresta(grafo, back[j], temp, dijkstra);
        }
        fprintf(dijkstra,"%.2f\r\n", peso);
        printf("%.2f\n",peso);
    }

    void imprimeMST(Grafo* grafo, Aresta* mstArestas,FILE* mst){
        float pesoMST = 0;
        printf("%d\n",getSize(grafo));
        fprintf(mst,"%d\r\n", getSize(grafo));
        for(int u = 0;u < getSize(grafo); u++){
            pesoMST += mstArestas[u].peso;
            if(mstArestas[u].peso != 0.0){
                fprintf(mst,"%d %d %.2f\r\n",mstArestas[u].a,mstArestas[u].b,mstArestas[u].peso);
                printf("%d %d %.2f\n",mstArestas[u].a,mstArestas[u].b,mstArestas[u].peso);
            }
        }
        printf("%.2f\n",pesoMST);
        fprintf(mst,"%.2f\r\n", pesoMST);
    }