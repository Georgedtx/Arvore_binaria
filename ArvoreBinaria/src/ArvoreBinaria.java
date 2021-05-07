
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */
public class ArvoreBinaria<T extends Comparable> {
    
    private NoBin raiz1;
    private No<T> raiz;
    List<No<T>> listamenores = new ArrayList<No<T>>();
    List<String> listamaiores = new ArrayList<String>();
    //construtor da árvore binária colocando o nó raiz já como nulo
    public ArvoreBinaria() {
        this.raiz = null;
        
    }
    //método que vai inserindo um nó desejado nas posições almejadas, sempre buscando saber se aquela posição já está ocupada.
    public void adicionar(No<T> no, No<T> Pai, String posicionamento) throws IOException {
        
        if (posicionamento.equals("raiz")) {
            if (Pai == null) {
                if (this.raiz == null) {
                    this.raiz = no;
                } else {
                    throw new IOException("Atenção, a árvore já possui um nó raiz.");
                }
            }
    
        }
        else if (posicionamento.equals("esquerda")) {
            if (Pai.possuiFilhoEsquerdo()) {
                throw new IOException("Atenção, o nó pai já possui um filho esquerdo.");
            } else {
                no.setPai(Pai);
                Pai.setFilhoEsquerdo(no);
            }
        } 
        else if (posicionamento.equals("direita")) {
            if (Pai.possuiFilhoDireito()) {
                throw new IOException("Atenção, o nó pai já possui um filho direito.");
            } else {
                no.setPai(Pai);
                Pai.setFilhoDireito(no);
            }
        }
    }
    //Método que consulta de o nó está presente na árvore, chamando recursivamente uma função auxiliar(existe)
    public boolean consultar(No<T> no) {
        return this.existe(this.raiz, no);
    }
    
    //O método compara um nó com o nó que se quer saber se existe na árvore. Se não for igual de primeira, vai comparando
    //com os nós filhos, se não encontrar na árvore, retorna false.
    public boolean existe(No<T> no1, No<T> no2) {
        if (no1 != null) {
            if (no1.equals(no2)) {
                return true;
            }
                return existe(no1.getFilhoEsquerdo(), no2) || existe(no1.getFilhoDireito(), no2);
            }
            return false;
        }
    
    //Grau é o número de filhos, então inicia um contador e vai somando a ele o número de filhos do nó.
    public int grau(No<T> no) {
        int grau = 0;
        if (no.possuiFilhoEsquerdo()) {
            grau += 1;
        }
        if (no.possuiFilhoDireito()) {
            grau += 1;
        }
        return grau;
    }
    //A profundidade é o número de ancestrais, então se o nó for a raiz, retorna 0, senão, contabiliza 1 e chama recursivamente
    //a função até chegar à raiz e não contabilizar mais.
    public int profundidade(No<T> no) {
        if (no.equals(this.raiz)) {
            return 0;
        }
        return 1 + profundidade(no.getPai());
    }
    //não entendi esse
    public int altura(No<T> no) {
        if (no != null) {
            int alturaEsquerda;
            int alturaDireita;
            alturaEsquerda = altura(no.getFilhoEsquerdo());
            alturaDireita = altura(no.getFilhoDireito());
            if (alturaEsquerda > alturaDireita) {
                return alturaEsquerda + 1;
            } else {
                return alturaDireita + 1;
            }
        }
        return -1;
    }
    //verifica o nível de cada nó. Se o nó for a raiz, o nível dela é sempre zero. Se não for a raiz
    //no mínimo será o nível 1 e chama recursivamente a função para checar o nível do pai, até chegar
    //à raiz, que tem nível 0 
    public int nivel(No<T> no) {
        if (no.equals(this.raiz)) {
            return 0;
        }
        return 1 + nivel(no.getPai());
    }
    // método recursivo que chama uma outra função
    public int quantidadeNos() {
        if (this.raiz != null) {
            return this.contagemNos(this.raiz);
        }
        return 0;
    }
    //Neste método, checamos se o nó possui filhos, caso possua, chamamos recursivamente a função somando sempre "1"
    //à medida que contabilizamos um nó
    public int contagemNos(No<T> no) {
        //se tiver os dois filhos, contabiliza um e chama recursivamente a função para cada filho
        if ((no.possuiFilhoEsquerdo()) && (no.possuiFilhoDireito())) {
            return 1 + contagemNos(no.getFilhoEsquerdo()) + contagemNos(no.getFilhoDireito());
        }
        //contabiliza o nó e chama recursivamente a função para o filho direito, nesse caso
        else if (no.possuiFilhoDireito()) {
            return 1 + contagemNos(no.getFilhoDireito());
        }
        // se for uma folha, contabiliza um
        else if (this.Folha(no)) {
            return 1;
        }
        //se não for nenhuma das opções anteriores, só resta contabilizar o nó e chamar recursivamente a função para o filho esquerdo
        return 1 + contagemNos(no.getFilhoEsquerdo());
        }
    //Aqui, uma função auxiliar para saber se o nó é uma folha, ou seja, não possui filhos
    public boolean Folha(No<T> no) {
        if ((no.possuiFilhoEsquerdo() == false) && (no.possuiFilhoDireito() == false)) {
            return true;
        }
        return false;
    }
    //utiliza recursão chamando uma outra função
    public String imprimirPreOrdem() {
        return this.preOrdem(this.raiz).toString();
    }
    //utiliza-se o StringBuilder para poder concatenar na hora de mostrar o resultado
    public StringBuilder preOrdem(No<T> no) {
        if (no != null) {
            //transforma o valor do nó para String, para poder concatenar depois
            String noAuxiliar = no.getValor().toString();
            //chama o método novamente para testar os valores dos filhos
            StringBuilder filhoesquerdo = preOrdem(no.getFilhoEsquerdo());
            StringBuilder filhodireito = preOrdem(no.getFilhoDireito());
            //se tiver os dois filhos, concatena primeiro o nó e depois o da esquerda e depois o da direita (NLR)
            if (filhoesquerdo != null && filhodireito != null) {
                return new StringBuilder(noAuxiliar).append(" - ").append(filhoesquerdo).append(" - ").append(filhodireito);
            }
            //se só tiver o filho esquerdo, concatena primeiro o nó e depois o da esquerda
            if (filhoesquerdo != null && filhodireito == null) {
                return new StringBuilder(noAuxiliar).append(" - ").append(filhoesquerdo);
            }
            //se só tiver o filho da direita, concatena primeiro o nó e depois o da direita
            if (filhoesquerdo == null && filhodireito != null) {
                return new StringBuilder(noAuxiliar).append(" - ").append(filhodireito);
            }
            //se for uma folha, coloca somente ela
            if (filhoesquerdo == null && filhodireito == null) {
                return new StringBuilder(noAuxiliar);
            }
            
        }
        return null;
    }
    //utiliza recursão chamando uma outra função
    public String imprimirPosOrdem() {
        return this.posOrdem(this.raiz).toString();
    }
    //utiliza o StringBuilder para poder concatenar na hora de mostrar o resultado
    public StringBuilder posOrdem(No<T> no) {
        if (no != null) {
            //transforma o valor do nó para String, para poder concatenar depois
            String noAuxiliar = no.getValor().toString();
            //chama o método novamente para testar os valores dos filhos
            StringBuilder filhoesquerdo = posOrdem(no.getFilhoEsquerdo());
            StringBuilder filhodireito = posOrdem(no.getFilhoDireito());
            
            //se tiver os dois filhos, concatena primeiro o filho da esquerda, depois o da direita e por último o nó (LRN)
            if (filhoesquerdo != null && filhodireito != null) {
                return new StringBuilder(filhoesquerdo).append(" - ").append(filhodireito).append(" - ").append(noAuxiliar);
            }
            //se só tiver o filho da esquerda, concatena ele primeiro e depois o nó
            if (filhoesquerdo != null && filhodireito == null) {
                return new StringBuilder(filhoesquerdo).append(" - ").append(noAuxiliar);
            }
            //se só tiver o filho da direita, concatena ele primeiro e depois o nó
            if (filhoesquerdo == null && filhodireito != null) {
                return new StringBuilder(filhodireito).append(" - ").append(noAuxiliar);
            }
            //se for uma folha, coloca somente ela
            if (filhoesquerdo == null && filhodireito == null) {
                return new StringBuilder(noAuxiliar);
            }
        }
        return null;
    }
    //utiliza recursão chamando uma outra função
    public String imprimirInOrdem() {
        return this.inOrdem(raiz).toString();
    }
    //utiliza o StringBuilder para poder concatenar na hora de mostrar o resultado
    public StringBuilder inOrdem(No<T> no) {
        if (no != null) {
            //transforma o valor do nó para String, para poder concatenar depois
            String noAuxiliar = no.getValor().toString();
            //chama o método novamente para testar os valores dos filhos
            StringBuilder filhoesquerdo = inOrdem(no.getFilhoEsquerdo());
            StringBuilder filhodireito = inOrdem(no.getFilhoDireito());
            //se tiver os dois filhos, primeiro concatena o da esquerda, depois o nó principal e depois o da direita (LNR)
            if (filhoesquerdo != null && filhodireito != null) {
                return new StringBuilder(filhoesquerdo).append(" - ").append(noAuxiliar).append(" - ").append(filhodireito);
            }
            //se só tiver o filho da esquerda, concatena ele primeiro e depois o nó
            if (filhoesquerdo != null && filhodireito == null) {
                return new StringBuilder(filhoesquerdo).append(" - ").append(noAuxiliar);
            }
            //se só tiver o da direita, concatena o nó primeiro e depois o da direita
            if (filhoesquerdo == null && filhodireito != null) {
                return new StringBuilder(noAuxiliar).append(" - ").append(filhodireito);
            }
            //se for uma folha, coloca somente ela
            if (filhoesquerdo == null && filhodireito == null) {
                return new StringBuilder(noAuxiliar);
        }
        }
        return null;
    } 
    public int media(){
        int quantidadeDeNos = this.quantidadeNos();
        int total = this.somaTotal(this.raiz, 0);
        
        return total/quantidadeDeNos;
    }
    
    public int somaTotal(No<T> noAuxiliar, int total) {
            if (noAuxiliar != null) {
                total += somaTotal(noAuxiliar.getFilhoEsquerdo(), 0);
                total += noAuxiliar.getValor().hashCode();
                total += somaTotal(noAuxiliar.getFilhoDireito(), 0);
            }
            return total;
    }
    public List<No<T>> menoresNos(){   
        return comparar(this.raiz, media());
    }
    private List<No<T>> comparar(No<T> raiz, int media){         
        if (raiz != null) {
            if (raiz.possuiFilhoEsquerdo()){
                 comparar(raiz.getFilhoEsquerdo(), media);
            }
            if (raiz.possuiFilhoDireito()){
                 comparar(raiz.getFilhoDireito(), media);
            }
            if (Integer.parseInt(raiz.getValor().toString())< media){                 
                listamenores.add(raiz);
            }
            return listamenores;
        } else {	
            return null;
        }                
    }
    
    public boolean estritamenteBinaria() {
        return estritamenteBinaria(this.raiz);
    }

    private boolean estritamenteBinaria(No<T> noAuxiliar) {
            if (noAuxiliar.possuiFilhoEsquerdo() && noAuxiliar.possuiFilhoDireito()){                
                return estritamenteBinaria(noAuxiliar.getFilhoEsquerdo()) && estritamenteBinaria(noAuxiliar.getFilhoDireito());
            }else if( noAuxiliar.verificarNoFolha()){             
                return true;
            }else{
                return false;
            }        
    }
    
    public List<String> impressao(){
        return impressaoPais(this.raiz);
    }
    public List<String> impressaoPais(No<T> noAuxiliar){
        
        if (Folha(noAuxiliar)== false){
            String pai =  noAuxiliar.getValor().toString();
            listamaiores.add("Pai: "+pai);
            if (noAuxiliar.possuiFilhoDireito() && noAuxiliar.possuiFilhoEsquerdo()){
                if(Integer.parseInt(noAuxiliar.getFilhoDireito().getValor().toString()) > Integer.parseInt(noAuxiliar.getFilhoEsquerdo().getValor().toString() )){
                   listamaiores.add("Filho maior: "+noAuxiliar.getFilhoDireito().getValor().toString());
                   
                   impressaoPais(noAuxiliar.getFilhoDireito());
                   impressaoPais(noAuxiliar.getFilhoEsquerdo());
                   
                }
                if(Integer.parseInt(noAuxiliar.getFilhoEsquerdo().getValor().toString()) > Integer.parseInt(noAuxiliar.getFilhoDireito().getValor().toString() )){
                   listamaiores.add("Filho maior: "+noAuxiliar.getFilhoEsquerdo().getValor().toString());
                   
                   impressaoPais(noAuxiliar.getFilhoEsquerdo());
                   impressaoPais(noAuxiliar.getFilhoDireito());
                   
                }
                               
                }
            if (noAuxiliar.possuiFilhoDireito()== false && noAuxiliar.possuiFilhoEsquerdo()){
                listamaiores.add("Filho maior: "+noAuxiliar.getFilhoEsquerdo().getValor().toString());
                
                impressaoPais(noAuxiliar.getFilhoEsquerdo());
                }
            
            if (noAuxiliar.possuiFilhoDireito() && noAuxiliar.possuiFilhoEsquerdo()== false){
                listamaiores.add("Filho maior: "+noAuxiliar.getFilhoDireito().getValor().toString());
                
                impressaoPais(noAuxiliar.getFilhoDireito());
                }
               
                
            }
        else{
            return null;
            
        }
        return listamaiores;
    }
    
  // Nessa parte, estão os métodos para impressão da árvore a partir do recebimento dos caminhos ( Pré-ordem, Pós-ordem e In-ordem)
  //Este indice foi criado para manter o controle de onde se encontra na leitura do vetor
    Indice indice = new Indice();
    // NoBin foi criado somente para lidarmos com valores inteiros
    // Este método constrói a Árvore, recebendo um vetor com a  pré-ordem
    public NoBin construirArvorepre( int pre[], Indice preIndice, int menor, int maior, int tamanho){
        //um teste para saber se a construção foi chamada com valores possíveis
        if (preIndice.indice >= tamanho || menor > maior){
            return null;
        }
    // delimitando o valor da raiz como sendo o primeiro elemento de pré-ordem, pois o primeiro elemento é sempre a raiz
    NoBin raiz = new NoBin(pre[preIndice.indice]);
    // depois incrementamos o valor do indice para irmos percorrendo o vetor pré-ordem para irmos, mais à frente, construindo recursivamente a árvore com os demais nós
    preIndice.indice = preIndice.indice + 1;
    // quando o valor do menor se igualar ao maior, terminou a leitura de cada sub-árvore
    if (menor == maior){
        return raiz;
        
        }
    
    int i;
    //Nesta parte, se procura o primeiro valor maior que seja maior que a raiz, pq os valores entra a raiz(índice 0) e este primeiro
    // elemento maior que ela, pertencerão à sub-árvore esquerda, e os elementos com índice maior que este número, até o final do vetor
    //estarão na sub-árvore direita.
    for(i=menor; i<= maior; i++){
        if (pre[i]>raiz.valor){
            break;
        }
    }
    //método recursivo que constrói a sub-árvore esquerda. Neste caso, passa-se o o valor do indice
    // no campo "menor" pois ele já foi incrementado, senão tomaria o valor de 0, que já é a raiz.
    //No campo "maior", o i -1 significa que a subárvore esquerda será completada somente com os valores
    //que estão no vetor até o índice do primeiro nó com valor maior que a raiz.
    raiz.esquerdo = construirArvorepre(pre, preIndice, preIndice.indice, i - 1, tamanho);
    //método recursivo que constrói a sub-árvore direita. Neste caso, o campo "menor" fica delimitado
    //pelo índice do primeiro valor maior que a raiz, para que somente sejam criados os nós à direita
    //desse valor.
    raiz.direito = construirArvorepre(pre, preIndice, i, maior, tamanho);
    
    return raiz;
    }
    //método que chama recursivamente a construção da árvore
    public NoBin construirArvore (int pre[]){
        
        return construirArvorepre(pre, indice, 0, pre.length-1, pre.length);
        
    
    
    }
    // método para imprimir a árvore que criamos através da pré-ordem em inordem
    public void printInorder (NoBin no){
        if (no ==null){
            return;
        }
        printInorder(no.esquerdo);
        System.out.print(no.valor + " ");
        printInorder(no.direito);
    }
    
    //Essa parte trata da construção da árvore, ao receber o caminho Pós-ordem
    
    
    public NoBin construirArvorePos( int pos[], int minimo, int maximo){
        // Teste base, para valores possíveis
        if (minimo>maximo){
            return null;
        }
        // A raiz no caminho pós-ordem é o último valor
        NoBin raiz1 = new NoBin(pos[maximo]);
        
        if (minimo == maximo){
            return raiz1;
        }
        int i;
        //O raciocínio é parecido com o da pré-ordem. Como o último valor do vetor é a raiz,
        // nós procuramos, de trás para a frente, o primeiro valor menor que a raiz. Ao acharmos,
        //os valores entre o índice 0 e o índice do primeiro valor menor que a raiz encontrado
        //farão parte da sub-árvore esquerda. Os valores que estão nos índices após o primeiro menor valor
        // encontrado que a raiz até antes da raiz em si, povoarão a sub-árvore direita.
        for (i = maximo; i>= minimo; i--){
            if (pos[i] < raiz1.valor){
                break;
            }
        }
        //Construção da sub-árvore esquerda, utilizando o índice do primeiro menor valor que a raiz encontrado
        // como limite superior
        raiz1.esquerdo = construirArvorePos(pos, minimo,i);
        //Construção da sub-árvore direita, utilizando como limite inferior o índice do primeiro
        //menor valor que a raiz encontrado + 1 e como limite superior um índice menor que o índice
        //que contém a raiz, uma vez que essa é a última do vetor.
        raiz1.direito = construirArvorePos(pos, i+1, maximo-1);
        
        return raiz1;
    }
    
    // método recursivo para a construção da árvore através do Pós-ordem
    public NoBin construir(int pos[]){
        
        return construirArvorePos(pos, 0, pos.length-1);
        
    }      
}
