#include <stdio.h>
#include <stdlib.h>
#include "headers/grafo.h"

//---Getter e Setters e Semelhantes---//

    int getSize(Grafo* grafo){
        return grafo->size;
    }

    int getEdge(Grafo* grafo){
        return grafo->edge;
    }

    int getGrau(Grafo* grafo, int v){
        return grafo->vertice[v].grau;
    }

    float getPeso(Grafo* grafo, int v, int a){
        return grafo->vertice[v].listaAresta[a].peso;
    }   

    void setEdge(Grafo* grafo, int E){
        grafo->edge = E;
    }

    void setGrau(Grafo* grafo, int a, int grau){
        grafo->vertice[a].grau =  grau;
    }

    void nextGrau(Grafo* grafo, int a){
        grafo->vertice[a].grau++;
    }

//---Creators(Gods)---//
    Grafo* criaGrafo(int n){
        
        //Aloca grafo e vetor de vertices
        Grafo* grafo  = (Grafo*)malloc(sizeof(Grafo));
        Vertice* v = (Vertice*)malloc(n*sizeof(Vertice));
        grafo->size = n;

        //Inicializa o grau dos vertices como 0
        for (int i =0; i < n; i++) {
            v[i].grau  = 0;
        }
        grafo->vertice = v;
        return grafo;
    }

//---Funções de Validação---//
    
    int validaGrafo(Grafo* grafo, int a, int b, float peso){
        if (a == b) {
            return 0;
        }
        
        if (a < 0 || a >= getSize(grafo)) {
            return 0;
        }
        
        if (b < 0 || b >= getSize(grafo)) {
            return 0;
        }
        
        if (peso < 0) {
            return 0;
        }

        return 1;    
    }

//---Funções de Adição de Arestas---//

    void adicionaAresta(Grafo* grafo, int a, int b, float peso){
        // Se o gráfico não passar na validação feche na ignorância o programa
        if (!validaGrafo(grafo, a, b, peso)) {
            printf("Aresta Inválida!\n");
            exit(1);
        }
        
        add(grafo, a, b, peso);
        
        nextGrau(grafo, a);
        nextGrau(grafo, b);
        
    }
    void add(Grafo* grafo, int a, int b, float peso){
        int grauA = getGrau(grafo, a);
        int grauB = getGrau(grafo, b);
        
        // aloque a e aloque b
        if(grauA == 0 && grauB == 0){
            addAlocando(grafo, a, b, peso);
            addAlocando(grafo, b, a, peso);
            return;
        }

        // aloque a e realoque b
        if(grauA == 0 && grauB > 0){
            addAlocando(grafo, a, b, peso);
            addRealocando(grafo, b, a, peso);
            return;
        
        }

        // realoque a e aloque b
        if(grauA > 0 && grauB == 0){
            addRealocando(grafo, a, b, peso);
            addAlocando(grafo, b, a, peso);
            return;
        }

        // realoque a e realoque b
        addRealocando(grafo, a, b, peso);
        addRealocando(grafo, b, a, peso);

    } 

    // Adiciona aresta no vértice v alocando
    void addAlocando(Grafo* grafo, int a, int b, float peso){
        grafo->vertice[a].listaAresta = (Adjacencia*)malloc(sizeof(Adjacencia));
        grafo->vertice[a].listaAresta[0].destino = b;
        grafo->vertice[a].listaAresta[0].peso = peso;
    }

    // Adiciona aresta no vértice v realocando
    void addRealocando(Grafo* grafo, int a, int b, float peso){
        int n = getGrau(grafo, a) + 1;
        grafo->vertice[a].listaAresta = realloc(grafo->vertice[a].listaAresta, n * sizeof(Adjacencia));
        grafo->vertice[a].listaAresta[n - 1].destino = b;
        grafo->vertice[a].listaAresta[n - 1].peso = peso;
    }

//---Utils---//
    /*
        Retorna o indice do vertice na lista de arestas do vertice u
    */
    int buscaVerticeAdj(Grafo* grafo, int u, int v){
        for(int i = 0; i < getGrau(grafo, u); i++){
            if(grafo->vertice[u].listaAresta[i].destino == v){
                return  i;
            }
        }
        return -1;
    }