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
    
    public int tamanio() {
    	return indice;
    }
    //el indice tiene cuantos turnos se agrego osea el tamanio
    
    public Turno [] verElementos() {
    	Turno[] copia = new Turno[indice];
    	for (int i = 0; i < indice; i++) {
            copia[i] = elementos[i];
        }
    	return copia;
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