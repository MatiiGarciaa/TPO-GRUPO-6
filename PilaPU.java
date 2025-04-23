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
    
    public void Mostrar() {
        PilaPU aux = new PilaPU();
        aux.InicializarPila();

        while (!this.PilaVacia()) {
            Turno t = this.Tope();
            System.out.println(t);
            aux.Apilar(t);
            this.Desapilar();
        }

        while (!aux.PilaVacia()) {
            Turno t = aux.Tope();
            this.Apilar(t);
            aux.Desapilar();
        }
    }


    

    
}