public class Main{
    public static int fibonacci(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }
    public static int maximo(int[] numbers){
        int res = 0;
        for (int n: numbers){
            res = (n>res) ? n : res;
        }
        return res;
    }
    public static int[] sumarArreglos(int[] a, int[] b){
        final int len = a.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++){
            res[i] = a[i]+b[i];
        }
        return res;
    }
    public static String iniciales(String texto){
        String[] palabras = texto.split(" ");
        String iniciales = "";
        for (String palabra: palabras){
            iniciales += palabra.charAt(0);
        }
        return iniciales;
    }
    public static void main(String[] args){
        String text = "Mi vieja mula ya no es lo que era";
        String res = iniciales(text);
        System.err.println(res);
    }
}