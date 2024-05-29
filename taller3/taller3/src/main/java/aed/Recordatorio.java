package aed;

public class Recordatorio {

    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = horario;
    }

    public Horario horario() {
        return this.horario;
    }

    public Fecha fecha() {
        return new Fecha(this.fecha);
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return String.format("%s @ %s %s", this.mensaje, this.fecha, this.horario);
    }

    @Override
    public boolean equals(Object otro) {
        if (!(otro instanceof Recordatorio)){
            throw new UnsupportedOperationException("Deben compararse dos objetos Recordatorio");
        }
        Recordatorio otroRecordatorio = (Recordatorio) otro;
        return (
            this.fecha.equals(otroRecordatorio.fecha())&&
            this.horario.equals(otroRecordatorio.horario())&&
            this.mensaje == otroRecordatorio.mensaje()
        );
    }

}
