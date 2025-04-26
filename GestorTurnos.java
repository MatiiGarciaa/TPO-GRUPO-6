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
        ConjuntoTDA claves = turnosPorId.Claves();

        while (!claves.ConjuntoVacio()) {
            int id = claves.Elegir();
            Turno turnoExistente = turnosPorId.Recuperar(id);
            claves.Sacar(id);

            if (turnoExistente.getDni() == dni) {
                if (!turnoExistente.getNombreCliente().equalsIgnoreCase(nombre)) {
                    System.out.println("❌ Error: Ya existe un turno para este DNI pero con otro nombre registrado.");
                    return null; 
                }
            }
        }

        Turno nuevoTurno = new Turno(dni, nombre, tramite, fechaHora);
        turnosPorId.Agregar(idContador, nuevoTurno); // Guardado con ID único
        colaPrioridad.AcolarPrioridad(nuevoTurno, nuevoTurno.getPrioridad());
        idContador++;
        return nuevoTurno;
    }


    public void mostrarTurnosPorDni(int dni) {
        ConjuntoTDA claves = turnosPorId.Claves();
        boolean encontrado = false;

        while (!claves.ConjuntoVacio()) {
            int id = claves.Elegir();
            Turno t = turnosPorId.Recuperar(id);
            claves.Sacar(id);

            if (t.getDni() == dni) {
                System.out.println("🆔 ID: " + id + " → " + t);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("❌ No se encontró ningún turno con ese DNI.");
        }
    }

    public Turno llamarSiguienteTurno() {
        if (colaPrioridad.ColaVacia()) {
            return null;
        }

        Turno siguiente = colaPrioridad.Primero();
        colaPrioridad.Desacolar();

        int idTurno = obtenerIdDelTurno(siguiente);

        if (idTurno != -1) {
            siguiente.marcarComoAtendido();   // Modificamos el turno
            turnosPorId.Eliminar(idTurno);     // Eliminamos del diccionario
            turnosPorId.Agregar(idTurno, siguiente); // Volvemos a agregarlo actualizado
        }

        historialAtendidos.Apilar(siguiente);

        return siguiente;
    }
    
    private int obtenerIdDelTurno(Turno turnoBuscado) {
        ConjuntoTDA claves = turnosPorId.Claves();

        while (!claves.ConjuntoVacio()) {
            int id = claves.Elegir();
            Turno t = turnosPorId.Recuperar(id);
            claves.Sacar(id);

            if (t.equals(turnoBuscado)) { // Comparación por referencia (mismo objeto)
                return id;
            }
        }
        return -1; // No encontrado
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
    public boolean marcarTurnoComoFinalizado(int id) {
        Turno turno = turnosPorId.Recuperar(id);

        if (turno != null && turno.isAtendido() && !turno.isFinalizado()) {
            turno.marcarComoFinalizado();

            turnosPorId.Eliminar(id);    // Elimino del diccionario
            turnosPorId.Agregar(id, turno); // Lo vuelvo a agregar actualizado

            return true;
        }
        return false;
    }
    
    public boolean eliminarTurno(int id) {
        Turno turno = turnosPorId.Recuperar(id);

        if (turno != null) {
            // 1. Eliminar del diccionario
            turnosPorId.Eliminar(id);

            // 2. Eliminar de la cola de prioridad
            ColaPrioridadPU aux = new ColaPrioridadPU();
            aux.InicializarCola();

            while (!colaPrioridad.ColaVacia()) {
                Turno t = colaPrioridad.Primero();
                int prioridad = colaPrioridad.Prioridad();
                colaPrioridad.Desacolar();

                if (!t.equals(turno)) {
                    aux.AcolarPrioridad(t, prioridad);
                }
            }

            while (!aux.ColaVacia()) {
                Turno t = aux.Primero();
                int prioridad = aux.Prioridad();
                aux.Desacolar();
                colaPrioridad.AcolarPrioridad(t, prioridad);
            }

            // 3. Eliminar de la pila del historial si ya fue atendido
            PilaPU auxHistorial = new PilaPU();
            auxHistorial.InicializarPila();

            while (!historialAtendidos.PilaVacia()) {
                Turno t = historialAtendidos.Tope();
                historialAtendidos.Desapilar();

                if (!t.equals(turno)) {
                    auxHistorial.Apilar(t);
                }
            }

            while (!auxHistorial.PilaVacia()) {
                Turno t = auxHistorial.Tope();
                auxHistorial.Desapilar();
                historialAtendidos.Apilar(t);
            }

            return true;
        }
        return false;
    }
}