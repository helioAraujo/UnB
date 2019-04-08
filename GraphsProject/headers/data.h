#ifndef DATA_H_INCLUDED
#define DATA_H_INCLUDED

Grafo* recebeDados(int argc, char **argv);
void saidaPadrao(Grafo* grafoIn,Aresta* mstArestas, float* d, char* argv[]);
void abreMenu(Grafo* grafoIn);
#endif //!DATA_H_INCLUDED