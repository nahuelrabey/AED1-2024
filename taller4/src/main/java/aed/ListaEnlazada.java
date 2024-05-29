package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private Nodo inicio;
    private Nodo fin;
    private int cardinal;

    private class Nodo {
        public Nodo anterior;
        public T value;
        public Nodo siguiente;

        public Nodo(T value) {
            this.anterior = null;
            this.siguiente = null;
            this.value = value;
        }

        public Nodo(Nodo anterior, T value) {
            this.anterior = anterior;
            this.value = value;
            this.siguiente = null;
        }

        public Nodo(Nodo nodo) {
            this.anterior = null;
            this.siguiente = null;
            this.value = nodo.value;
        }

        public void agregarAdelante(Nodo n) {
            this.siguiente = n;
        }
    }

    public ListaEnlazada() {
        this.inicio = null;
        this.fin = null;
    }

    public int longitud() {

        return cardinal;
    }

    public void agregarAdelante(T elem) {
        // AGREGO AL PRINCIPIO DE LA LISTA
        this.cardinal++;
        Nodo nuevo = new Nodo(elem);
        if (this.inicio == null && this.fin == null) {
            this.inicio = nuevo;
            this.fin = nuevo;
            return;
        }

        // el siguiente al nuevo es el viejo inicio
        nuevo.siguiente = this.inicio;
        // el anterior del viejo inicio es el nuevo
        this.inicio.anterior = nuevo;
        // el inicio ahora es el nuevo.
        this.inicio = nuevo;
    }

    public void agregarAtras(T elem) {
        // AGREGO AL FINAL DE LA LISTA
        this.cardinal++;
        Nodo nuevo = new Nodo(elem);
        if (this.inicio == null && this.fin == null) {
            this.inicio = nuevo;
            this.fin = nuevo;
            return;
        }

        // el anterior al nuevo es el viejo final
        nuevo.anterior = this.fin;
        // el siguiente del viejo final es el actual final
        this.fin.siguiente = nuevo;
        // el nuevo final, es el final
        this.fin = nuevo;
    }

    private Nodo buscar(int i) {
        Nodo res = this.inicio;
        for (int lugar = 0; lugar < i; lugar++) {
            res = res.siguiente;
        }
        return res;
    }

    public T obtener(int i) {

        // int pasos = 0;
        Nodo objetivo = this.buscar(i);
        return objetivo.value;
    }

    public void eliminar(int i) {
        Nodo objetivo = this.buscar(i);
        Nodo anterior = objetivo.anterior;
        Nodo siguiente = objetivo.siguiente;

        if (anterior == null) {
            this.inicio = siguiente;
            this.cardinal--;
            return;
        }

        if (siguiente == null) {
            this.fin = anterior;
            // objetivo = null;
            this.cardinal--;
            return;
        }

        // if (siguiente == null && anterior == null){
        // this.fin = null;
        // this.inicio = null;
        // }
        anterior.siguiente = siguiente;
        siguiente.anterior = anterior;
        // objetivo = null;
        this.cardinal--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo objetivo = this.buscar(indice);
        objetivo.value = elem;
    }

    public ListaEnlazada<T> copiar() {
        return new ListaEnlazada(this);
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        this.cardinal = 0;
        this.inicio = null;
        this.fin = null;

        Nodo viejo = lista.inicio;
        while (viejo != null) {
            Nodo nuevo = new Nodo(viejo);
            this.agregarAtras(nuevo.value);
            viejo = viejo.siguiente;
        }

        // throw new UnsupportedOperationException("No implementada aun");
    }

    @Override
    public String toString() {
        String res = "[";
        Nodo actual = this.inicio;
        while (actual != null) {
            if (actual.value != this.fin.value) {
                res += actual.value + ", ";
                actual = actual.siguiente;
                continue;
            }
            res += actual.value;
            actual = actual.siguiente;
        }
        res += "]";
        return res;
    }

    private class ListaIterador implements Iterador<T> {
        // Completar atributos privados
        // private Integer sentido = null;
        private Nodo anterior;
        private Nodo posicion;
        private Nodo siguiente;

        ListaIterador(Nodo nodo) {
            if (nodo == null){
                anterior = null;
                posicion = null;
                siguiente = null;
                return;
            }
            anterior = nodo.anterior;
            posicion = nodo;
            siguiente = nodo.siguiente;
        }

        public boolean haySiguiente() {
            if (siguiente == null && posicion == null){
                return false;
            }
            // if (siguiente == null){
            //     return false;
            // }
            return true;
        }

        public boolean hayAnterior() {
            if (anterior == null && posicion != null){
                return false;
            }
            if (anterior == null){
                return false;
            }
            return true;
        }

        public T siguiente() {
            T res = this.posicion.value;

            if (this.siguiente == null){
                this.anterior = this.posicion;
                this.posicion = null;
                this.siguiente = null;
                return res;
            }

            // avanzo a la siguiente posición
            this.posicion = this.siguiente;
            this.siguiente = this.posicion.siguiente;
            this.anterior = this.posicion.anterior;
            return res;
        }

        public T anterior() {
            // T res = this.posicion.value;

            if (this.anterior == null){
                T res = this.posicion.value;
                this.anterior = null;
                this.posicion = null;
                this.siguiente = this.posicion;
                return res;
            }

            // retrocedo a la anterior posición
            this.posicion = this.anterior;
            this.siguiente = this.posicion.siguiente;
            this.anterior = this.posicion.anterior;

            // devuelvo el valor anterior
            return this.posicion.value;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador(this.inicio);
    }

}
