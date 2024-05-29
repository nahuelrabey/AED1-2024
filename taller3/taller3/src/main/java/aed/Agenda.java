package aed;

public class Agenda {
    
    private Fecha fechaActual;
    private ArregloRedimensionableDeRecordatorios recordatorios;
    public Agenda(Fecha fechaActual) {
        this.fechaActual = new Fecha(fechaActual);
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {

        // busco los recordatorios de hoy, y LOS IMPRIMO
        // int contador = 0;
        int largo = this.recordatorios.longitud();
        // Recordatorio[] recordatorios_hoy = new Recordatorio[largo];

        String textito = this.fechaActual.toString() + "\n=====\n";
        for (int i = 0; i< largo; i++){
            Recordatorio rec = this.recordatorios.obtener(i);
            if (rec.fecha().equals(this.fechaActual)){
                textito += rec.toString() + "\n";
            }
        }

        return textito;
    }

    public void incrementarDia() {
        this.fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(this.fechaActual);
    }

}
