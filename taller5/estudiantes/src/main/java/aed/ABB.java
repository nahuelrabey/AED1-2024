package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    int cardinal = 0;
    Nodo raiz = null;

    private class Nodo {
        // Agregar atributos privados del Nodo
        private Nodo izq = null;
        private Nodo der = null;
        private Nodo padre = null;
        private T val = null;

        // Crear Constructor del nodo
        Nodo(T value){
            val = value;
            return;
        }
        Nodo(Nodo izq, Nodo der, Nodo padre, T value){
            this.izq = izq;
            this.der = der;
            this.padre = padre;
            this.val = value;
            return;
        }

        public void setPadre(Nodo padre){
            this.padre = padre;
        }
        public Nodo getPadre(){
            return this.padre;
        }
        public void setIzq(Nodo izq){
            this.izq = izq;
        }
        public Nodo getIzq(){
            return this.izq;
        }
        public void setDer(Nodo der){
            this.der = der;
        }
        public Nodo getDer(){
            return this.der;
        }
        public void setValue(T value){
            this.val = value;
        }
        public T getValue(){
            return this.val;
        }

        public boolean tieneHijoDer(){
            return this.der != null;
        }
        public boolean tieneHijoIzq(){
            return this.izq != null;
        }
        public boolean tieneHijos(){
            return this.tieneHijoIzq() || this.tieneHijoDer();
        }

        public boolean tienePadre(){
            return this.padre != null;
        }
        public boolean esDer(){
            return tienePadre() && this.padre.der == this;
        }
        public boolean esIzq(){
            return tienePadre() && this.padre.izq == this;
        }
        public Nodo hermanoDerecho(){
            return this.padre.der;
        }
        public Nodo hermanoIzq(){
            return this.padre.izq;
        }

        public Nodo padreMayor(){
            T value = this.getValue();
            Nodo padre = this;
            while(padre.tienePadre()){
                padre = padre.getPadre();
                T p_value = padre.getValue();

                if (p_value.compareTo(value) > 0){
                    return padre;
                }
            }
            return null;
        }
        public boolean tienePadreMayor(){
            if(!this.tienePadre()){
                return false;
            }

            // while(true){

            // }

            return false;
        }
    }
    public boolean sonNodosIguales(Nodo A, Nodo B){
        T valueA = A.getValue();
        T valueB = B.getValue();

        if (valueA != valueB){
            return false;
        }

        if (
            A.getDer() == null && B.getDer() == null 
        ){
            return true && sonNodosIguales(A.getIzq(), B.getIzq());
        }

        if (
            A.getIzq() == null && B.getIzq() == null 
        ){
            return true && sonNodosIguales(A.getDer(), B.getDer());
        }

        return false;
    }

    public ABB() {
    }
    public ABB(Nodo raiz) {
        this.raiz = raiz;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){

        Nodo actual = this.raiz;
        while (actual.getIzq() != null){
            actual = actual.getIzq();
        }
        return actual.getValue();
    }

    public Nodo nodo_minimo(Nodo n){
        Nodo actual = n;
        while (actual.getIzq() != null){
            actual = actual.getIzq();
        }
        return actual;
    }

    public T maximo(){
        Nodo actual = this.raiz;
        while (actual.getDer() != null){
            actual = actual.getDer();
        }
        return actual.getValue();
    }
    public Nodo nodo_maximo(Nodo n){
        Nodo actual = n;
        while (actual.getDer() != null){
            actual = actual.getDer();
        }
        return actual;
    }

    public void insertar(T elem){
        Nodo actual = this.raiz;
        if (actual == null){
            Nodo nuevo = new Nodo(elem);
            this.raiz = nuevo;
            this.cardinal ++;
            return;
        }

        while(true){
            T value = actual.getValue();
            if (elem.compareTo(value) == 0){
                return;
            }
            if (elem.compareTo(value) < 0){
                if (actual.getIzq() == null){
                    Nodo nuevo = new Nodo(elem);
                    nuevo.padre = actual;
                    actual.setIzq(nuevo);
                    break;
                }
                actual = actual.getIzq();
                continue;
            }
            if (elem.compareTo(value) > 0){
                if (actual.getDer() == null){
                    Nodo nuevo = new Nodo(elem);
                    nuevo.padre = actual;
                    actual.setDer(nuevo);
                    break;
                }
                actual = actual.getDer();
                continue;
            }
            break;
        }

        this.cardinal ++;
    }

    public Nodo buscar(T elem){
        Nodo actual = this.raiz;

        while(true){
            if (actual == null){
                return null;
            }
            if (elem.compareTo(actual.getValue())==0){
                return actual;
            }

            if (elem.compareTo(actual.getValue()) < 0){
                actual = actual.getIzq();
                continue;
            }
            if (elem.compareTo(actual.getValue()) > 0){
                actual = actual.getDer();
                continue;
            }
            return null;
        }
    }
    public boolean pertenece(T elem){
        Nodo buscado = this.buscar(elem);
        if (buscado == null){
            return false;
        }
        return true;
    }

    public void eliminar(T elem){
        Nodo buscado = this.buscar(elem);
        Nodo padre = buscado.getPadre();

        Nodo hijoIzq = buscado.getIzq();
        Nodo hijoDer = buscado.getDer();


        // no tiene hijos
        if(!buscado.tieneHijos()){
            if (buscado.esIzq()){
                // es el hermano izquierdo
                padre.setIzq(null);
            }
            if (buscado.esDer()){
                // es el hermano derecho
                padre.setDer(null);
            }
        }
        // sólo tiene rama izq
        if (buscado.tieneHijoIzq() && !buscado.tieneHijoDer()){
            if (buscado.esIzq()){
                // es el hermano izquierdo
                padre.setIzq(hijoIzq);
            }
            if (buscado.esDer()){
                // es el hermano derecho;
                padre.setDer(hijoIzq);
            }
        }

        // sólo tiene rama der
        if (!buscado.tieneHijoIzq() && buscado.tieneHijoDer()){
            if (buscado.esIzq()){
                // es el hermano izquierdo
                padre.setIzq(hijoDer);
            }
            if (buscado.esDer()){
                // es el hermano derecho;
                padre.setDer(hijoDer);
            }
        }


        // tiene ambas ramas
        // busco mínimo de la derecha
        Nodo minDer = this.nodo_minimo(hijoDer);
        buscado.setValue(minDer.getValue());

        if (minDer.esDer()){
            minDer.getPadre().setDer(null);
        }
        if (minDer.esIzq()){
            minDer.getPadre().setIzq(null);
        }

        this.cardinal --;
    }

    public String toString(){
        Nodo minimo = nodo_minimo(this.raiz);
        T value = minimo.getValue();
        ABB<T>.ABB_Iterador_mio iterador = new ABB_Iterador_mio(minimo);
        String res = "{";
        while (iterador.haySiguiente()){
            res+=value.toString() + ",";
            value = iterador.siguiente();
            System.out.println("current value: " +value);
        }
        res += value+"}";
        return res;
    }

    private class ABB_Iterador_mio implements Iterador<T> {
        private Nodo _actual;
        private Nodo _siguiente;
        // private Nodo[] lista;

        ABB_Iterador_mio(Nodo n){
            // acá, voy a armar una lista en orden de todo lo que hay
            _actual = n;
        }

        public boolean haySiguiente() {
            if(_actual == null){
                return false;
            }

            if (_actual.tieneHijoDer()){
                return true;
            }
            // if (_actual.tienePadre()){
            // }
            Nodo padreMayor = _actual.padreMayor();
            if (padreMayor == null){
                return false;
            }
            return true;
            
            // return false;
        }
    
        public T siguiente() {
            if (_actual == null){
                return null;
            }

            // T value = _actual.getValue();

            if (_actual.tieneHijoDer()){
                Nodo minimo = nodo_minimo(_actual.getDer());
                _actual = minimo;
                T value = _actual.getValue();
                return value;
            }

            Nodo padreMayor = _actual.padreMayor();
            if (padreMayor == null){
                return null;
            }

            _actual = padreMayor;
            T value = _actual.getValue();

            return value;
        }
    }
    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private Nodo _siguiente;
        // private Nodo[] lista;

        ABB_Iterador(Nodo n){
            // acá, voy a armar una lista en orden de todo lo que hay
            _actual = n;
        }

        public boolean haySiguiente() {
            if(_actual == null){
                return false;
            }

            if (_actual.tieneHijoDer()){
                return true;
            }
            // if (_actual.tienePadre()){
            // }
            Nodo padreMayor = _actual.padreMayor();
            if (padreMayor == null){
                return false;
            }
            return true;
            
            // return false;
        }
    
        public T siguiente() {
            if (_actual == null){
                return null;
            }

            T value = _actual.getValue();

            if (_actual.tieneHijoDer()){
                Nodo minimo = nodo_minimo(_actual.getDer());
                _actual = minimo;
                return value;
            }

            Nodo padreMayor = _actual.padreMayor();
            if (padreMayor == null){
                return null;
            }

            _actual = padreMayor;

            return value;
        }
    }
    public Iterador<T> iterador() {
        Nodo minimo = nodo_minimo(raiz);
        return new ABB_Iterador(minimo);
    }

    public static void main(String[] args){
        ABB<Integer> conjunto = new ABB<Integer>();
        
        conjunto.insertar(5);
        conjunto.insertar(4);
        conjunto.insertar(20);
        conjunto.insertar(15);
        conjunto.insertar(12);
        conjunto.insertar(24);
        conjunto.insertar(22);
        conjunto.insertar(25);
        conjunto.insertar(19);
        conjunto.insertar(21);
        conjunto.eliminar(20);
        System.out.println(conjunto.cardinal());
        System.out.println(conjunto.minimo());
        System.out.println(conjunto.maximo());
        System.out.println(conjunto.toString());
        // assertEquals("{4,5,12,15,19,21,22,24,25}", conjunto.toString());

    }
}
