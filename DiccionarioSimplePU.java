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
        Nodo aux = origen;
        while (aux != null && aux.clave != clave) {
            aux = aux.siguiente;
        }

        if (aux != null) {
            aux.valor = valor; // Piso el valor si la clave ya existe
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
            Nodo aux = origen;
            while (aux.siguiente != null && aux.siguiente.clave != clave) {
                aux = aux.siguiente;
            }
            if (aux.siguiente != null) {
                aux.siguiente = aux.siguiente.siguiente;
            }
        }
    }

    public Turno Recuperar(int clave) {
        Nodo aux = origen;
        while (aux != null) {
            if (aux.clave == clave) {
                return aux.valor;
            }
            aux = aux.siguiente;
        }
        return null; // No encontrado
    }

    @Override
    public ConjuntoTDA Claves() {
        ConjuntoTDA c = new ConjuntoPU();
        c.InicializarConjunto();
        Nodo aux = origen;
        while (aux != null) {
            c.Agregar(aux.clave);
            aux = aux.siguiente;
        }
        return c;
    }
}

