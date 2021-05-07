/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */
public class No<T> {
    
    
    public T valor;
    public No<T> pai;
    public No<T> filhoEsquerdo;
    public No<T> filhoDireito;
    
    // CONSTRUTOR RAIZ
    public No(T valor) {
        this.valor = valor;
        this.pai = null;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }
    // CONTRUTOR FILHOS
    public No(T valor, No<T> pai) {
        this.valor = valor;
        this.pai = pai;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }
    
    public boolean verificarNoFolha() {
        if ((this.getFilhoEsquerdo() == null) && (this.getFilhoDireito() == null)) {
                return true;
        }
        return false;
    }
    
    public boolean possuiFilhoEsquerdo() {
        if (this.filhoEsquerdo == null) {
            return false;
        }
        return true;
    }
    public boolean possuiFilhoDireito() {
        if (this.filhoDireito == null) {
            return false;
        }
        return true;
    }
  
    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getPai() {
        return pai;
    }

    public void setPai(No<T> pai) {
        this.pai = pai;
    }

    public No<T> getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(No<T> filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public No<T> getFilhoDireito() {
        return filhoDireito;
    }

    public void setFilhoDireito(No<T> filhoDireito) {
        this.filhoDireito = filhoDireito;
    }
    
    
    public String toString(){
        return " " + this.valor;
    }
}
