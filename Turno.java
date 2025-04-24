package TPO;

public class Turno {
    private final int dni;
    private final String nombreCliente;
    private final String tramite;
    private final int prioridad;
    private final String fechaHoraLlegada;
    private boolean atendido;
    private boolean finalizado;

    public Turno(int dni, String nombreCliente, String tramite, String fechaHoraLlegada) {
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.tramite = tramite;
        this.prioridad = calcularPrioridad(tramite);
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.atendido = false;
        this.setFinalizado(false);
    }

    private int calcularPrioridad(String tramite) {
        if (tramite.equalsIgnoreCase("apertura de cuenta")) {
            return 3; // Mayor prioridad
        }
        if (tramite.equalsIgnoreCase("pago de servicios")) {
            return 1; // Menor prioridad
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

    // Setters
    public void marcarComoAtendido() { this.atendido = true; }
    
    public void marcarComoFinalizado() { this.setFinalizado(true); }


    @Override
    public String toString() {
        return String.format("[DNI: %d] %s - %s (Prioridad: %d) - %s %s %s",
            dni, nombreCliente, tramite, prioridad, fechaHoraLlegada,
            atendido ? "[ATENDIDO]" : "[PENDIENTE]",
            finalizado ? "[FINALIZADO]" : "");
    }

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
}
