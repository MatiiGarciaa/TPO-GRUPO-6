package TPO;

public class Turno {
    private final int dni;
    private final String nombre;
    private final String tramite;
    private final int prioridad;

    public Turno(int dni, String nombre, String tramite) {
        this.dni = dni;
        this.nombre = nombre;
        this.tramite = tramite;
        this.prioridad = calcularPrioridad(tramite);
    }

    private int calcularPrioridad(String tramite) {
        tramite = tramite.toLowerCase();
        if (tramite.equals("apertura de cuenta")) {
            return 3;
        }
        if (tramite.equals("pago de servicios")){
            return 2;
        } 
        return 1;
    }

    // Getter y Setter para DNI
    public int getDni() {
        return this.dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    // Getter y Setter para Nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para Trámite
    public String getTramite() {
        return this.tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
        this.prioridad = calcularPrioridad(tramite); // recalcular si cambia
    } 

    // Getter y Setter para Prioridad
    public int getPrioridad() {
        return this.prioridad;
    }

    // Prioridad no se modifica directamente desde afuera (opcional)
    private void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String toString() {
        return "[DNI: " + dni + "] " + nombre + " - " + tramite + " (P: " + prioridad + ")";
    }
}