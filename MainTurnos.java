package TPO;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainTurnos {
    public static void main(String[] args) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la sucursal: ");
        String sucursalInput = input.nextLine();
        SucursalBanco sucursal = SucursalBanco.getInstance(sucursalInput);
        GestorTurnos gestor = sucursal.getGestorTurnos();

        while (true) {
            System.out.println("\n=====================================");
            System.out.println("   BANCO UADE - SUCURSAL "+ sucursal.getNombreSucursal().toUpperCase());
            System.out.println("=====================================");
            System.out.println("  1️  Asignar nuevo turno");
            System.out.println("  2️  Llamar siguiente turno");
            System.out.println("  3️  Ver turnos en espera");
            System.out.println("  4️  Ver historial de atendidos");
            System.out.println("  5️  Buscar turno por DNI");
            System.out.println("  0️  Salir");
            System.out.println("=====================================");
            System.out.print("Seleccione una opción ▶️: ");

            int opcion = input.nextInt();
            input.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n📄 NUEVO TURNO");
                    int dni;
                    while (true) {
                        System.out.print("🪪 Ingrese DNI (7 u 8 dígitos): ");

                        if (!input.hasNextInt()) {
                            System.out.println("❌ Ingrese solo números.");
                            input.nextLine(); 
                            continue;
                        }

                        dni = input.nextInt();
                        input.nextLine(); 

                        if (dni < 1_000_000 || dni > 99_999_999) {
                            System.out.println("❌ El DNI debe tener 7 u 8 dígitos.");
                            continue;
                        }

                        break; 
                    }

                    System.out.print("🧑 Ingrese nombre: ");
                    String nombre = input.nextLine();

                    System.out.println("\n📌 Tipo de trámite:");
                    System.out.println("  1️ Apertura de cuenta");
                    System.out.println("  2️ Otro trámite");
                    System.out.println("  3️ Pago de servicios");
                    System.out.print("Seleccione el número del trámite ▶️: ");
                    int opcionTramite = input.nextInt();
                    input.nextLine();

                    String tramite;
                    switch (opcionTramite) {
                        case 1:
                            tramite = "apertura de cuenta";
                            break;
                        case 3:
                            tramite = "pago de servicios";
                            break;
                        default:
                            tramite = "otro trámite";
                    }

                    String fechaHora = LocalDateTime.now().format(formato);
                    System.out.println("\n🕒 Fecha y hora registrada: " + fechaHora);

                    Turno nuevoTurno = gestor.asignarTurno(dni, nombre, tramite, fechaHora);
                    System.out.println("\n✅ Turno asignado exitosamente:");
                    System.out.println(nuevoTurno);
                    break;

                case 2:
                    System.out.println("\n📢 LLAMAR SIGUIENTE TURNO");
                    Turno turnoActual = gestor.llamarSiguienteTurno();
                    if (turnoActual != null) {
                        System.out.println("🎟️ Turno llamado: " + turnoActual);
                    } else {
                        System.out.println("⚠️ No hay turnos pendientes");
                    }
                    break;

                case 3:
                    System.out.println("\n🕒 TURNOS EN ESPERA:");
                    gestor.mostrarTurnosEnEspera();
                    break;

                case 4:
                    System.out.println("\n📚 HISTORIAL DE ATENDIDOS:");
                    gestor.mostrarHistorialAtendidos();
                    break;

                case 5:
                	System.out.print("🔍 Ingrese DNI para buscar: ");
                    int dniBuscar = input.nextInt();
                    input.nextLine();

                    Turno[] encontrados = gestor.buscarTurnosPorDni(dniBuscar);

                    if (encontrados.length == 0) {
                        System.out.println("❌ No se encontró ningún turno con ese DNI.");
                    } else {
                        System.out.println("✅ Turnos encontrados:");
                        for (Turno t : encontrados) {
                            System.out.println(t);
                        }
                    }
                    break;
                case 0:
                    System.out.println("👋 Saliendo del sistema... ¡Hasta luego!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❗ Opción no válida. Intente nuevamente.");
            }
        }
    }
}
