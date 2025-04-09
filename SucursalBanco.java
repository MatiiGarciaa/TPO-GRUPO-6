package TPO;

public class SucursalBanco {
    private static SucursalBanco instancia;
    private FuncionTurnos funcion;
    private DiccionarioSimpleTDA diccionario;

    private SucursalBanco() {
        funcion = new FuncionTurnos();
        diccionario = new DiccionarioSimplePU();
        diccionario.InicializarDiccionario();
    }

    public static SucursalBanco getInstance() {
        if (instancia == null) {
            instancia = new SucursalBanco();
        }
        return instancia;
    }

    public void mostrarHistorial() {
        Turno[] historial = funcion.obtenerHistorial();
        if (historial.length == 0) {
            System.out.println("📦 Historial vacío.");
        } else {
            for (int i = 0; i < historial.length; i++) {
                System.out.println("  " + historial[i]);
            }
        }
    }

    
}
