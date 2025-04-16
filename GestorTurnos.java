package TPO;

public class GestorTurnos {
    private final ColaPrioridadPU colaPrioridad;
    private final PilaPU historialAtendidos;
    private int contadorTurnos;

    public GestorTurnos() {
        colaPrioridad = new ColaPrioridadPU();
        colaPrioridad.InicializarCola();
        historialAtendidos = new PilaPU();
        historialAtendidos.InicializarPila();
        contadorTurnos = 0;
    }

    public Turno asignarTurno(int dni, String nombre, String tramite, String fechaHora) {
        Turno nuevoTurno = new Turno(dni, nombre, tramite, fechaHora);
        colaPrioridad.AcolarPrioridad(nuevoTurno, nuevoTurno.getPrioridad());
        contadorTurnos++;
        return nuevoTurno;
    }

    public Turno llamarSiguienteTurno() {
        if (colaPrioridad.ColaVacia()) {
            return null;
        }
        
        Turno siguiente = colaPrioridad.Primero();
        siguiente.marcarComoAtendido();
        colaPrioridad.Desacolar();
        historialAtendidos.Apilar(siguiente);
        
        return siguiente;
    }

    public Turno[] obtenerTurnosEnEspera() {
        ColaPrioridadPU auxiliar = new ColaPrioridadPU();
        auxiliar.InicializarCola();
        
        Turno[] pendientes = new Turno[contadorTurnos];
        int i = 0;

        while (!colaPrioridad.ColaVacia()) {
            Turno t = colaPrioridad.Primero();
            pendientes[i++] = t;
            auxiliar.AcolarPrioridad(t, t.getPrioridad());
            colaPrioridad.Desacolar();
        }

        while (!auxiliar.ColaVacia()) {
            Turno t = auxiliar.Primero();
            colaPrioridad.AcolarPrioridad(t, t.getPrioridad());
            auxiliar.Desacolar();
        }

        Turno[] resultado = new Turno[i];
        for (int j = 0; j < i; j++) {
            resultado[j] = pendientes[j];
        }
        return resultado;
    }

    public Turno[] obtenerHistorialAtendidos() {
        PilaPU copia = new PilaPU();
        copia.InicializarPila();

        int count = 0;
        while (!historialAtendidos.PilaVacia()) {
            Turno t = (Turno) historialAtendidos.Tope();
            copia.Apilar(t);
            historialAtendidos.Desapilar();
            count++;
        }

        Turno[] historial = new Turno[count];
        for (int i = count - 1; i >= 0; i--) {
            Turno t = (Turno) copia.Tope();
            historial[i] = t;
            historialAtendidos.Apilar(t);
            copia.Desapilar();
        }

        return historial;
    }

    public int getCantidadTurnosPendientes() {
        ColaPrioridadPU auxiliar = new ColaPrioridadPU();
        auxiliar.InicializarCola();
        int cantidad = 0;

        while (!colaPrioridad.ColaVacia()) {
            Turno t = colaPrioridad.Primero();
            auxiliar.AcolarPrioridad(t, t.getPrioridad());
            colaPrioridad.Desacolar();
            cantidad++;
        }

        while (!auxiliar.ColaVacia()) {
            Turno t = auxiliar.Primero();
            colaPrioridad.AcolarPrioridad(t, t.getPrioridad());
            auxiliar.Desacolar();
        }

        return cantidad;
    }

}