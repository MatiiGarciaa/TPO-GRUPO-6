package TPO;

public interface ColaPrioridadTDA {
    void InicializarCola();
    void AcolarPrioridad(Turno x, int prioridad);
    void Desacolar();
    Turno Primero();
    int Prioridad();
    boolean ColaVacia();
    void Mostrar ();
}

