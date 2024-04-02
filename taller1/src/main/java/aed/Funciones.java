package aed;

class Funciones {
    int cuadrado(int x) {
        // COMPLETAR
        return x*x;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        return Math.sqrt(x*x+y*y);
    }

    boolean esPar(int n) {
        // COMPLETAR
        return (n%2 == 0);
    }

    boolean esBisiesto(int n) {
        if (n % 400 == 0){
            return true;
        }

        if (n % 4 == 0 && n % 100 != 0){
            return true;
        }
        // COMPLETAR
        return false;
    }

    public static int factorialIterativo(int n) {
        if (n == 0){
            return 1;
        }

        System.out.println("n value: "+n);
        int res = 1;
        for(int i = n; i >= 1; i--){
            System.out.println(i);
            res*=i;
        }
        // COMPLETAR
        return res;
    }

    int factorialRecursivo(int n) {
        

        if (n == 0){
            return 1;
        }

        // COMPLETAR
        return n*factorialRecursivo(n-1);
    }

    boolean esPrimo(int n) {
        if (n == 1 || n == 0){
            return false;
        }
        // COMPLETAR
        // int divisors = 0;
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int res = 0;
        for (int num : numeros){
            res += num;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        for (int i = 0; i < numeros.length; i++){
            if (buscado == numeros[i]){
                return i;
            }
        }
        return -1;
    }

    boolean tienePrimo(int[] numeros) {
        for (int num : numeros){
            if (esPrimo(num)){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        for (int num : numeros){
            if (!esPar(num)){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        final int len_s1 = s1.length();
        final int len_s2 = s2.length();
        
        if(len_s1 > len_s2 ){
            return false;
        }

        for (int i = 0; i < len_s1; i++){
            if (s1.charAt(i)!=s2.charAt(i)) return false;
        }

        return true;
    }
    String reverseToken(String str){
        String new_str = "";
        for (int i = str.length()-1; i >= 0; i--){
            new_str += str.charAt(i);
        }
        return new_str;
    }
    boolean esSufijo(String s1, String s2) {
        String s1_reversed = reverseToken(s1);
        String s2_reversed = reverseToken(s2);

        return esPrefijo(s1_reversed, s2_reversed);
    }
    public static void main(String[] args) {
        System.err.println(factorialIterativo(5));
    }
}
