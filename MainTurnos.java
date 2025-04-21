package TPO;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MainTurnos {
    public static void main(String[] args) {
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    	String fechaHora = LocalDateTime.now().format(formato);
        Scanner input = new Scanner(System.in);
        SucursalBanco sucursal = SucursalBanco.getInstance("Banco Principal");
        GestorTurnos gestor = sucursal.getGestorTurnos();

        while (true) {
            System.out.println("\n--- SISTEMA DE TURNOS BANCARIOS ---");
            System.out.println("1. Asignar nuevo turno");
            System.out.println("2. Llamar siguiente turno");
            System.out.println("3. Ver turnos en espera");
            System.out.println("4. Ver historial de atendidos");
            System.out.println("5. Buscar turno por DNI");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = input.nextInt();
            input.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese DNI: ");
                    int dni = input.nextInt();
                    input.nextLine();
                    System.out.print("Ingrese nombre: ");
                    String nombre = input.nextLine();
                    System.out.print("Ingrese trámite: ");
                    String tramite = input.nextLine();
                    System.out.println("Fecha y Hora: " + fechaHora);
                    Turno nuevoTurno = gestor.asignarTurno(dni, nombre, tramite, fechaHora);
                    System.out.println("Turno asignado: " + nuevoTurno);
                    break;
                    
                case 2:
                    Turno turnoActual = gestor.llamarSiguienteTurno();
                    if (turnoActual != null) {
                        System.out.println("Turno actual: " + turnoActual);
                    } else {
                        System.out.println("No hay turnos pendientes");
                    }
                    break;
                    
                case 3:
                    System.out.println("\n--- TURNOS EN ESPERA ---");
                    Turno[] enEspera = gestor.obtenerTurnosEnEspera();
                    for (Turno t : enEspera) {
                        System.out.println(t);
                    }
                    break;
                    
                case 4:
                    System.out.println("\n--- HISTORIAL DE ATENDIDOS ---");
                    Turno[] historial = gestor.obtenerHistorialAtendidos();
                    for (Turno t : historial) {
                        System.out.println(t);
                    }
                    break;
                    
                case 5:
                	 System.out.print("Ingrese DNI para buscar: ");
                	    int dniBuscar = input.nextInt();
                	    input.nextLine();

                	    Turno encontrado = gestor.buscarTurnoPorDni(dniBuscar);

                	    if (encontrado != null) {
                	        System.out.println("Turno encontrado: " + encontrado);
                	    } else {
                	        System.out.println("No se encontró turno para ese DNI");
                	    }
                	    break;
                    
                case 0:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}