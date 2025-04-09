public class FuncionTurnos{
    private ColaPrioridadPU cola;
    private PilaPU atendidosPila;
    private Turno [] turnos;
    private int contador;

    public Turno llamarTurno (){
        if (cola.ColaVacia()){ //si la cola con prioridad esta vacia entonces no hay turno
            return null
        }
        int pos = cola.Primero ();
        cola.Desacolar();
        atendidosPila.Apilar (pos); // pila que se llama historial 
        return turnos [pos];
    }
    public Turno [] obtenerHistorial { //mostrar la pila con turnos atendidos xd
        PilaPu copia = new PilaPU ();
        copia.InicializarPila ();
        int contador = 0
        while (!atendidosPila.PilaVacia()){ //contar cuantos hay
            int pos = atendidosPila.Tope ();
            copia.Apilar (pos);
            atendidosPila.Desapilar();
            contador++;
        }
        Turno [] historial = new Turno [contador]; //hacer historial
        for (int i = contador - 1; i >= 0; i--) {
            int pos = copia.Tope();
            historial[i] = turnos[pos];
            pila.Apilar(pos);
            copia.Desapilar();
        }
        return historial;
    }

}