package TPO;

public interface DiccionarioSimpleTDA {
    void InicializarDiccionario(); // pre: no aplica.
    void Agregar(int clave, int valor); // pre: diccionario inicializado.
    void Eliminar(int clave); // pre: diccionario inicializado.
    int Recuperar(int clave); // pre: diccionario inicializado y clave existente.
    ConjuntoTDA Claves(); // pre: diccionario inicializado.
}
