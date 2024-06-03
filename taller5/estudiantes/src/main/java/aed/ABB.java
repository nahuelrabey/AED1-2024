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

            return false;
        }

        public Nodo sucesor(){
            if (this.tieneHijoDer()){
                return nodo_minimo(this.getDer());
            }
            if (!this.tienePadre()){
                return null;
            }
            Nodo padreMayor = this.padreMayor();
            if (padreMayor == null){
                return null;
            }

            return padreMayor;
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
        System.out.println(buscado.getValue());
        if (buscado == null){
            return;
        }

        if(!buscado.tieneHijos()){
            if(buscado.esDer()){
                buscado.getPadre().setDer(null);
            }
            if(buscado.esIzq()){
                buscado.getPadre().setIzq(null);
            }
            this.cardinal--;
            return;
        }

        System.err.println("tiene hijo der: "+ buscado.tieneHijoDer());
        System.err.println("tiene hijo izq: "+ buscado.tieneHijoIzq());

        if(buscado.tieneHijoIzq() && !buscado.tieneHijoDer()){
            Nodo hijo = buscado.getIzq();

            if(buscado.esDer()){
                buscado.getPadre().setDer(hijo);
            }
            if(buscado.esIzq()){
                buscado.getPadre().setIzq(hijo);
            }

            this.cardinal--;
            return;
        }
        
        if(!buscado.tieneHijoIzq() && buscado.tieneHijoDer()){
            Nodo hijo = buscado.getDer();

            if(buscado.esDer()){
                buscado.getPadre().setDer(hijo);
            }
            if(buscado.esIzq()){
                buscado.getPadre().setIzq(hijo);
            }

            this.cardinal--;
            return;

        }

        Nodo sucesor = buscado.sucesor();
        System.err.println("sucesor: " + sucesor.getValue());
        // cortamos lazos del padre del sucesor con el mismo
        if(sucesor.esDer()){
            sucesor.getPadre().setDer(null);
        }
        if(sucesor.esIzq()){
            sucesor.getPadre().setIzq(null);
        }

        // asignamos rama der del eliminado al sucesor
        sucesor.setDer(buscado.getDer());
        // reemplazamos padre rama der por sucesor
        buscado.getDer().setPadre(sucesor);

        // asignamos rama izq del eliminado al sucesor
        sucesor.setIzq(buscado.getIzq());
        // reemplazamos padre rama izq por sucesor
        buscado.getIzq().setPadre(sucesor);

        // el padre del sucesor debe ser el padre del eliminado
        sucesor.setPadre(buscado.getPadre());


        if(buscado.esDer()){
            // si el eliminado estaba a la derecha
            // al padre del eliminado lo reemplazamos por el sucesor
            buscado.getPadre().setDer(sucesor);
        }

        if(buscado.esIzq()){
            // si el eliminado estaba a la izquierda
            // al padre del eliminado lo reemplazamos por el sucesor
            buscado.getPadre().setIzq(sucesor);
        }

        this.cardinal--;
        return;

    }

    public String toString(){
        Nodo minimo = nodo_minimo(this.raiz);
        ABB_Iterador iterador = new ABB_Iterador(minimo);
        String res = "{";
        while(iterador.haySiguiente()){
            T value = iterador.siguiente();
            res += value + ",";
        }
        res = res.substring(0, res.length() - 1)+"}";

        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private Nodo _siguiente;

        ABB_Iterador(Nodo n){
            // acá, voy a armar una lista en orden de todo lo que hay
            _actual = n;
            _siguiente = null;
        }

        public boolean haySiguiente() {
            if (_actual == null){
                return false;
            }
            _siguiente = _actual.sucesor();
            // if (_siguiente != null){
            //     System.err.println("_siguiente NO ES NULL");
            // }
            return true;
        }
    
        public T siguiente() {

            T valor_actual = _actual.getValue();


            if(_actual == null){
                System.err.println("_actual ES NULL");
            }
            if (_siguiente != null){
                System.err.println("_siguiente value: " + _siguiente.getValue());
                _actual = _siguiente;
                return valor_actual;
            }
            if (_siguiente == null){
                System.err.println("_siguiente ES null");
                _actual = _siguiente;
            }

            return valor_actual;

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
