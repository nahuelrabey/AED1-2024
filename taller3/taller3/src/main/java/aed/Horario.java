package aed;

public class Horario {

    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        return String.format("%d:%d", this.hora,this.minutos);
    }

    @Override
    public boolean equals(Object otro) {
        if (!(otro instanceof Horario)){
            return false;
        }
        Horario otroHorario = (Horario) otro;
        return (this.hora == otroHorario.hora() && this.minutos == otroHorario.minutos());
    }

}
