package aed;
import java.util.Arrays;

class ArregloRedimensionableDeRecordatorios implements SecuenciaDeRecordatorios {
    private Recordatorio[] data;
    public ArregloRedimensionableDeRecordatorios() {
        this.data = new Recordatorio[0];
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.data = vector.data.clone();
    }

    public int longitud() {
        return this.data.length;
    }

    public void agregarAtras(Recordatorio recordatorio) {
        Recordatorio[] oldData = this.data;
        int len = this.longitud();
        this.data = Arrays.copyOf(oldData, this.longitud()+1);
        this.data[len] = recordatorio;
    }

    public Recordatorio obtener(int i) {
        return this.data[i];
    }

    public void quitarAtras() {
        Recordatorio[] oldData = this.data;
        int len = this.longitud();
        this.data = Arrays.copyOfRange(oldData,0,len-1);
    }

    public void modificarPosicion(int indice, Recordatorio recordatorio) {
        this.data[indice]=recordatorio;
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);
    }

    public static void main(String[] args) {
    }

}
