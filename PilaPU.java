package TPO;

public class PilaPU implements PilaTDA {
	private class Nodo {
        Turno dato;
        Nodo sig;
    }
	private Nodo tope;

    public void InicializarPila() {
        tope = null;
    }

    public void Apilar(Turno turno) {
        Nodo nuevo = new Nodo();
        nuevo.dato = turno;
        nuevo.sig = tope;
        tope = nuevo;
    }

    public void Desapilar() {
        if (tope != null) {
            tope = tope.sig;
        }
    }

    public boolean PilaVacia() {
        return tope == null;
    }

    public Turno Tope() {
        return tope.dato;
    }
    
    public int tamanio() {
        int contador = 0;
        Nodo actual = tope;
        
        while (actual != null) {
            contador++;
            actual = actual.sig;
        }
        
        return contador;
    }
    
    public Turno[] verElementos() {
        int tamaño = tamanio();
        Turno[] elementos = new Turno[tamaño];

        Nodo actual = tope;
        int i = 0;
        
        while (actual != null) {
            elementos[i++] = actual.dato;
            actual = actual.sig;
        }

        return elementos;
    }

    

    
}
