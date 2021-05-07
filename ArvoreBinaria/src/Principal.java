
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */
public class Principal {
    public static void main(String[] args) throws IOException {
        //Criando a árvore
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria();
        
        
        //Criando nós para povoar a árvore
        No<Integer> no52 = new No(52);
        No<Integer> no31 = new No(31);
        No<Integer> no63 = new No(63);
        No<Integer> no26 = new No(26);
        No<Integer> no39 = new No(39);
        
        
        
        //Inserindo elementos na árvore
        arvore.adicionar(no52, null, "raiz");
        arvore.adicionar(no31, no52, "esquerda");
        arvore.adicionar(no63, no52, "direita");
        arvore.adicionar(no26, no31, "esquerda");
        arvore.adicionar(no39, no31, "direita");
        
        
        
//        //Consultando se um nó específico existe na árvore
//        System.out.println("Consulta de nós (18,6,20): ");
//        System.out.println(arvore.consultar(no18));
//        System.out.println(arvore.consultar(no6));
//        System.out.println(arvore.consultar(no20));
//        System.out.println();
//        
//        
//        //Verificando o grau de um nó
//        System.out.println("Grau dos nós (9,7): ");
//        System.out.println(arvore.grau(no9));//folha
//        System.out.println(arvore.grau(no7));
//        System.out.println();
//        
//        //Verificando a profundidade de um nó
//        System.out.println("Profundidade dos nós (9,5,2,1): ");
//        System.out.println(arvore.profundidade(no9));
//        System.out.println(arvore.profundidade(no5));
//        System.out.println(arvore.profundidade(no2));
//        System.out.println(arvore.profundidade(no1));
//        System.out.println();
//        
//        //Verificando a altura dos nós
//        System.out.println("Altura dos nós (1,2,5,10,16): ");
//        System.out.println(arvore.altura(no1));
//        System.out.println(arvore.altura(no2));
//        System.out.println(arvore.altura(no5));
//        System.out.println(arvore.altura(no10));
//        System.out.println(arvore.altura(no16));
//        System.out.println();
//        
//        //Checando o nível dos nós
//        System.out.println("Nível dos nós (1,2,5,10,15): ");
//        System.out.println(arvore.nivel(no1));
//        System.out.println(arvore.nivel(no2));
//        System.out.println(arvore.nivel(no5));
//        System.out.println(arvore.nivel(no10));
//        System.out.println(arvore.nivel(no15));
//        System.out.println();
//        
//        //Verificando a quantidade de nós na árvore
//        System.out.println("Número total de nós da presente árvore: ");
//        System.out.println(arvore.quantidadeNos());
//        System.out.println();
        
        //imprimindo a árvore em Pré-ordem
        System.out.println("Imprimindo em pré-ordem(NLR): ");
        System.out.println(arvore.imprimirPreOrdem());
        System.out.println();
        
        //imprimindo a árvore em Pós-ordem
        System.out.println("Imprimindo em pós-ordem(LRN): ");
        System.out.println(arvore.imprimirPosOrdem());
        System.out.println();
        
        //imprimindo a árvore em In-ordem
        System.out.println("Imprimindo em in-ordem(LNR): ");
        System.out.println(arvore.imprimirInOrdem());
        
        /////////////////   
        //imprimindo a media dos valores
        System.out.println("Média: ");
        System.out.println(arvore.media());

        //imprimindo menores nos
        System.out.println("Menores nós: ");
        System.out.println(arvore.menoresNos());
        
        //imprimindo o metodo estritamente binaria
        System.out.println("A árvore é estritamente binaria? ");
        System.out.println(arvore.estritamenteBinaria());
        
        //imprimindo pai e o maior filho
        System.out.println("Pais e maiores filhos: ");
        System.out.println(arvore.impressao());
        
        //primeira questão
        //Recebendo preordem e imprimindo a árvore original
        System.out.println("Árvore, recebendo o pré-ordem: ");
        ArvoreBinaria arvore1 = new ArvoreBinaria();
        int pre[] = new int[] {52, 31, 26, 39, 63};
        NoBin raiz = arvore1.construirArvore(pre);
        arvore1.printInorder(raiz);
        
        //Recebendo posordem e imprimindo a árvore original
        System.out.println();
        System.out.println("Árvore, recebendo o pós-ordem: ");
        ArvoreBinaria arvore2 = new ArvoreBinaria();
        int pos[] = new int[] { 26, 39, 31, 63, 52};
        NoBin raiz2 =arvore2.construir(pos);
        arvore2.printInorder(raiz2);      
    }   
}
