#include <stdio.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>

int main(){
    int tamanhoGrafo;
    int faixaquantArestas;
    int teste = 0;
    int arestaA, arestaB;
    float peso;

    FILE* arqGrafo;

    printf("\nDigite o tamanho do grafo desejado: ");
    scanf("%d",&tamanhoGrafo);    
    printf("Digite a quantidade de arestas do grafo desejado (As arestas são geradas aleatoriamente): ");
    scanf("%d", &faixaquantArestas);
    
    srand ((unsigned)time(NULL));   
    
    while(teste < 10){
        //mude o nome do arquivo gerado caso não deseje substituir arquivos com mesmo nome 
        char nome_arqGrafo [20] = "data/exemplo/grafo";
        
        char numTeste [2];
        
        teste++;
        
        sprintf(numTeste,"%i",teste);

        strcat(nome_arqGrafo,numTeste);
        strcat(nome_arqGrafo,".txt");
        arqGrafo = fopen(nome_arqGrafo,"w");

        fprintf(arqGrafo,"%d\r\n",tamanhoGrafo);
        
        for(int i=0; i<faixaquantArestas;i++){
            arestaA = 1 + (rand() % tamanhoGrafo);
            arestaB = 1 + rand() % (tamanhoGrafo);
            peso = 0 + (rand() % (100));
            if(arestaA != arestaB && peso > 0)
                fprintf(arqGrafo,"%d %d %f\r\n",arestaA,arestaB,peso);
        }
        fclose(arqGrafo);
    }
    
    return 0;
}
