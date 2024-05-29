package aed;

class Debugging {
    boolean xor(boolean a, boolean b) {
        boolean case1 = a || b;
        boolean case2 = !(a && b);
        // return a || b && !(a && b);
        return case1 && case2;
    }

    boolean iguales(int[] xs, int[] ys) {
        if (xs.length != ys.length){
            return false;
        }

        boolean res = true;

        for (int i = 0; i < xs.length; i++) {
            if (xs[i] != ys[i]) {
                res = false;
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        for (int i = 0; i < xs.length - 1; i++) {
            if (xs[i] > xs [i+1]) {
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0];
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > res) res = xs[i];
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        if (xs.length == 0){
            return true;
        }

        for (int x : xs) {
            if (x<=0) return false;
        }
        return true;
    }
}
