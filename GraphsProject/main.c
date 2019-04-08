#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "headers/grafo.h"
#include "headers/mst.h"
#include "headers/data.h"
#include "headers/dijkstra.h"
#include "headers/printter.h"
#include <limits.h>

/********************************************************************************************************
Desenvolvedores: Lucas Hélio e Juliano Balcante
Início de desenvolvimento: 15/06/2018
Última versão: 30/06/2018

Este programa foi desenvolvido para realizar operações sobre grafos: calcular MST e calcular o menor caminho
entre dois vértices
********************************************************************************************************/

int main(int argc, char* argv[]){
    //ENTRADA DE DADOS
    Grafo* grafoIn = recebeDados(argc, argv);
    if(argc == 2){
        //PROCESSAMENTO
        Aresta* mstArestas = mstPrim(grafoIn,0);//mst gerado com raiz 1
        float* d = dijkstra(grafoIn, 0);

        //SAIDA DE DADOS
        saidaPadrao(grafoIn,mstArestas,d,argv);
    }
    else if(!strcmp(argv[2],"1")){
        abreMenu(grafoIn);
    }
    else{
        printf("ERRO : Parece que houve uma tentativa de acesso ao menu, porém o terceiro argumentO passado não é válido.\n");
        printf("Tente seguir o padrão: ./projGrafo arqGrafo.txt 1\n");
    }
    return 0;
}