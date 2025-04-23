package TPO;

public class GestorTurnos {
    private final ColaPrioridadPU colaPrioridad;
    private final PilaPU historialAtendidos;
    public int contadorTurnos;
    private final DiccionarioSimplePU turnosPorId;
    private int idContador; // ID único por turno

    public GestorTurnos() {
        colaPrioridad = new ColaPrioridadPU();
        colaPrioridad.InicializarCola();
        historialAtendidos = new PilaPU();
        historialAtendidos.InicializarPila();
        contadorTurnos = 0;
        turnosPorId = new DiccionarioSimplePU();
        turnosPorId.InicializarDiccionario();
        idContador = 0;
    }

    public Turno asignarTurno(int dni, String nombre, String tramite, String fechaHora) {
        Turno nuevoTurno = new Turno(dni, nombre, tramite, fechaHora);
        turnosPorId.Agregar(idContador, nuevoTurno); // Guardado con ID único
        colaPrioridad.AcolarPrioridad(nuevoTurno, nuevoTurno.getPrioridad());
        idContador++;
        return nuevoTurno;
    }

    public Turno[] buscarTurnosPorDni(int dni) {
        ConjuntoTDA claves = turnosPorId.Claves();
        Turno[] posibles = new Turno[100];
        int i = 0;

        while (!claves.ConjuntoVacio()) {
            int id = claves.Elegir();
            Turno t = turnosPorId.Recuperar(id);
            claves.Sacar(id);

            if (t.getDni() == dni) {
                posibles[i++] = t;
            }
        }

        Turno[] resultado = new Turno[i];
        for (int j = 0; j < i; j++) {
            resultado[j] = posibles[j];
        }

        return resultado;
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

    public void mostrarTurnosEnEspera() {
        if (colaPrioridad.ColaVacia()) {
            System.out.println("📭 No hay turnos en espera.");
            return;
        }
        colaPrioridad.Mostrar();
    }

    public void mostrarHistorialAtendidos() {
        if (historialAtendidos.PilaVacia()) {
            System.out.println("📭 Aún no se atendió ningún turno.");
            return;
        }
        historialAtendidos.Mostrar();
    }
}