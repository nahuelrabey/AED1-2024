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

    public T minimo(Nodo n){
        Nodo actual = n;
        while (actual.getIzq() != null){
            actual = actual.getIzq();
        }
        return actual.getValue();
    }

    public T maximo(){
        Nodo actual = this.raiz;
        while (actual.getDer() != null){
            actual = actual.getDer();
        }
        return actual.getValue();
    }
    public T maximo(Nodo n){
        Nodo actual = n;
        while (actual.getDer() != null){
            actual = actual.getDer();
        }
        return actual.getValue();
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

        Nodo hermanoIzq = padre.getIzq();
        Nodo hermanoDer = padre.getDer();

        Nodo hijoIzq = buscado.getIzq();
        Nodo hijoDer = buscado.getDer();

        if (hijoIzq == null && hijoDer == null){
            if (hermanoIzq.getValue() == buscado.getValue()){
                // es el hermano izquierdo
                padre.setIzq(null);
            }
            if (hermanoDer.getValue() == buscado.getValue()){
                padre.setDer(null);
            }
        }

        // sólo tiene rama izq
        if (hijoDer == null && hijoIzq != null){
            if (hermanoIzq.getValue() == buscado.getValue()){
                // es el hermano izquierdo
                padre.setIzq(hijoIzq);
            }
            if (hermanoDer.getValue() == buscado.getValue()){
                // es el hermano derecho;
                padre.setDer(hijoIzq);
            }
        }

        // sólo tiene rama der
        if (hijoDer == null && hijoIzq != null){
            if (hermanoIzq.getValue() == buscado.getValue()){
                // es el hermano izquierdo
                padre.setIzq(hijoDer);
            }
            if (hermanoDer.getValue() == buscado.getValue()){
                // es el hermano derecho;
                padre.setDer(hijoDer);
            }
        }
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
