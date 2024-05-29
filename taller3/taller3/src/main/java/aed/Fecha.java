package aed;

public class Fecha {
    private int dia;
    private int mes;
    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }

    public String toString() {
        String res = String.format("%d/%d",this.dia, this.mes);
        return res;
    }

    @Override
    public boolean equals(Object otra) {
        if (!(otra instanceof Fecha)){
            // throw new UnsupportedOperationException("Deben compararse dos objetos Fecha");
            return false;
        }

        Fecha otraFecha = (Fecha) otra;

        return (this.dia == otraFecha.dia() && this.mes == otraFecha.mes());
    }

    public void incrementarDia() {
        int old_dia = this.dia;
        int old_mes = this.mes;
        if (old_dia == this.diasEnMes(old_mes)){
            this.dia = 1;

            if (old_mes == 12){
                this.mes = 1;
                return;
            }

            this.mes += 1;
            return;
        }
        
        this.dia += 1;
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

    // public static void main(String[] args){
    //     Fecha fecha = new Fecha(1, 1);
    //     Fecha otrFecha = new Fecha(1, 1);

    //     System.out.println(fecha.equals(otrFecha));
    // }
}
