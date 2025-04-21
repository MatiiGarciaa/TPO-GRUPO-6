package TPO;

public interface DiccionarioSimpleTDA {
    void InicializarDiccionario(); // pre: no aplica.
    void Agregar(int clave, Turno valor); // Turno en vez de int
    void Eliminar(int clave); // pre: diccionario inicializado.
    Turno Recuperar(int clave); // Turno en vez de int
    ConjuntoTDA Claves(); 
}
