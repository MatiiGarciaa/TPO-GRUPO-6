package TPO;

public class DiccionarioSimplePU implements DiccionarioSimpleTDA {
    
    private class Nodo {
        int clave;
        Turno valor;
        Nodo siguiente;
    }

    private Nodo origen;
	
    public void InicializarDiccionario() {
        origen = null;
    }

    public void Agregar(int clave, Turno valor) {
        Nodo actual = origen;
        while (actual != null && actual.clave != clave) {
            actual = actual.siguiente;
        }

        if (actual != null) {
            actual.valor = valor; // Piso el valor si la clave ya existe
        } else {
            Nodo nuevo = new Nodo();
            nuevo.clave = clave;
            nuevo.valor = valor;
            nuevo.siguiente = origen;
            origen = nuevo;
        }
    }

    public void Eliminar(int clave) {
        if (origen == null) {
            return;
        }

        if (origen.clave == clave) {
            origen = origen.siguiente;
        } else {
            Nodo actual = origen;
            while (actual.siguiente != null && actual.siguiente.clave != clave) {
                actual = actual.siguiente;
            }
            if (actual.siguiente != null) {
                actual.siguiente = actual.siguiente.siguiente;
            }
        }
    }

    public Turno Recuperar(int clave) {
        Nodo actual = origen;
        while (actual != null) {
            if (actual.clave == clave) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null; // No encontrado
    }

    @Override
    public ConjuntoTDA Claves() {
        ConjuntoTDA c = new ConjuntoPU();
        c.InicializarConjunto();
        Nodo actual = origen;
        while (actual != null) {
            c.Agregar(actual.clave);
            actual = actual.siguiente;
        }
        return c;
    }
}

