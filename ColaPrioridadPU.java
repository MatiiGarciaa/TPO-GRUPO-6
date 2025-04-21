package TPO;

public class ColaPrioridadPU implements ColaPrioridadTDA {
    Turno[] elementos;
    int[] prioridades;
    int indice;

    public void InicializarCola() {
        elementos = new Turno[100];
        prioridades = new int[100];
        indice = 0;
    }

    public void AcolarPrioridad(Turno x, int p) {
        int j = indice;
        while (j > 0 && prioridades[j - 1] < p) {
            elementos[j] = elementos[j - 1];
            prioridades[j] = prioridades[j - 1];
            j--;
        }
        elementos[j] = x;
        prioridades[j] = p;
        indice++;
    }


    public void Desacolar() {
        if (!ColaVacia()) {
            indice--;
        }
    }

    public Turno Primero() {
        return elementos[indice - 1];
    }

    public int Prioridad() {
        return prioridades[indice - 1];
    }

    public boolean ColaVacia() {
        return (indice == 0);
    }
}