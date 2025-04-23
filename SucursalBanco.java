package TPO;

public class SucursalBanco {
    private static SucursalBanco instancia;
    private GestorTurnos gestorTurnos;
    private String nombreSucursal;

    private SucursalBanco(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
        this.gestorTurnos = new GestorTurnos();
    }

    public static SucursalBanco getInstance(String nombreSucursal) {
        if (instancia == null) {
            instancia = new SucursalBanco(nombreSucursal);
        }
        return instancia;
    }

    public GestorTurnos getGestorTurnos() {
        return gestorTurnos;
    }
    
    public String getNombreSucursal() {
        return this.nombreSucursal;
    }
}