package TPO;

public class Turno {
    public final int dni;
    public final String nombreCliente;
    public final String tramite;
    public final int prioridad;
    public final String fechaHoraLlegada;
    public boolean atendido;
    private static int contadorTurnos = 1; // Genera IDs
    private int id;


    public Turno(int dni, String nombreCliente, String tramite, String fechaHoraLlegada) {
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.tramite = tramite;
        this.prioridad = calcularPrioridad(tramite);
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.atendido = false;
        this.id = contadorTurnos++;
    }

    private int calcularPrioridad(String tramite) {
        tramite = tramite.toLowerCase();
        if (tramite.contains("apertura de cuenta")) {
            return 1; // Mayor prioridad
        }
        if (tramite.contains("pago de servicios")) {
            return 3; // Menor prioridad
        }
        return 2; // Prioridad media
    }

    // Getters
    public int getDni() { return dni; }
    public String getNombreCliente() { return nombreCliente; }
    public String getTramite() { return tramite; }
    public int getPrioridad() { return prioridad; }
    public String getFechaHoraLlegada() { return fechaHoraLlegada; }
    public boolean isAtendido() { return atendido; }
    public int getId() {return id;  }


    // Setters
    public void marcarComoAtendido() { this.atendido = true; }

    @Override
    public String toString() {
        return String.format("[DNI: %d] %s - %s (Prioridad: %d) - %s %s",
                dni, nombreCliente, tramite, prioridad, fechaHoraLlegada,
                atendido ? "[ATENDIDO]" : "[PENDIENTE]");
    }
}
