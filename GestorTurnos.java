package TPO;

public class GestorTurnos {
    private final ColaPrioridadPU colaPrioridad;
    private final PilaPU historialAtendidos;
    private int contadorTurnos;
    private final DiccionarioSimplePU turnosPorDni;

    public GestorTurnos() {
        colaPrioridad = new ColaPrioridadPU();
        colaPrioridad.InicializarCola();
        historialAtendidos = new PilaPU();
        historialAtendidos.InicializarPila();
        contadorTurnos = 0;
        turnosPorDni = new DiccionarioSimplePU();
        turnosPorDni.InicializarDiccionario();

    }

    public Turno asignarTurno(int dni, String nombre, String tramite, String fechaHora) {
        Turno nuevoTurno = new Turno(dni, nombre, tramite, fechaHora);
        colaPrioridad.AcolarPrioridad(nuevoTurno, nuevoTurno.getPrioridad());
        turnosPorDni.Agregar(nuevoTurno.getDni(), nuevoTurno); // Ahora se guarda internamente
        contadorTurnos++;
        return nuevoTurno;
    }
    
    public Turno buscarTurnoPorDni(int dni) {
        return (Turno) turnosPorDni.Recuperar(dni); /*el casting no es necesario pero por las dudas xs*/
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
        return colaPrioridad.verElementos();
    }

    public Turno[] obtenerHistorialAtendidos() {
        return historialAtendidos.verElementos();
    }

}