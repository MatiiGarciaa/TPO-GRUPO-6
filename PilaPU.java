package TPO;

public class PilaPU implements PilaTDA {
	Turno[] valores;
    int tope;

    public void InicializarPila() {
        valores = new Turno[100];
        tope = 0;
    }

    public void Apilar(Turno x) {
        valores[tope] = x;
        tope++;
    }

    public void Desapilar() {
        if (tope > 0) {
            tope--;
        }
    }

    public Turno Tope() {
        return valores[tope - 1];
    }

    public boolean PilaVacia() {
        return (tope == 0);
    }
}
