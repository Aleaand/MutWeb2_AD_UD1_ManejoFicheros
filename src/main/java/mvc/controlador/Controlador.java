/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controlador;

import mvc.modelo.GestorAlmacenamiento;
import mvc.modelo.IDAO;
import mvc.modelo.Jugador;
import mvc.vista.Vista;

/**
 * La clase Controlador es responsable de manejar la lógica del flujo de la
 * aplicación en un modelo de arquitectura MVC (Modelo-Vista-Controlador). Se
 * comunica tanto con la vista para interactuar con el usuario como con el DAO
 * para gestionar los datos de los jugadores.
 *
 * @author Alejandra
 */
public class Controlador {

    private Vista vista;
    private IDAO<Jugador> jugadorDAO;
    private GestorAlmacenamiento gestorAlmacenamiento;

    /**
     * Constructor que inicializa el controlador con la vista y el DAO de
     * jugadores.
     *
     * @param vista la vista que interactúa con el usuario.
     * @param jugadorDAO el DAO que gestiona las operaciones sobre los
     * jugadores.
     */
    public Controlador(Vista vista, IDAO<Jugador> jugadorDAO) {
        this.vista = vista;

        this.jugadorDAO = jugadorDAO;
    }

    /**
     * Método principal que inicia el flujo del programa. Presenta el menú
     * principal al usuario y ejecuta las operaciones correspondientes según la
     * opción seleccionada. Las opciones incluyen: alta, baja, modificación,
     * consulta y visualización de jugadores.
     */
    public void iniciar() {
        boolean salir = false;
        int tipoAlmacenamiento = 0;
        // Seleccionar tipo de almacenamiento
        vista.mostrarSubmenuConfiguracion();
        tipoAlmacenamiento = vista.leerEntero(5);
        // Inicializar gestor de almacenamiento
        gestorAlmacenamiento = new GestorAlmacenamiento(); // Inicializar gestor para texto
        gestorAlmacenamiento.cargarJugadores(jugadorDAO.getAll(), tipoAlmacenamiento);
        System.out.println("\n========================");
        System.out.println("    FLUJO INICIADO      ");
        System.out.println("========================");

        while (!salir) {
            vista.mostrarMenuPrincipal();
            int opcion = vista.leerEntero(6);
            switch (opcion) {
                case 1://Alta jugador
                    System.out.println("\n========================");
                    System.out.println("     ALTA DE JUGADOR    ");
                    System.out.println("========================");
                    Jugador nuevoJugador = vista.leerDatosJugador();
                    jugadorDAO.create(nuevoJugador);
                    break;
                case 2://Baja
                    System.out.println("\n========================");
                    System.out.println("      BAJA DE JUGADOR   ");
                    System.out.println("========================");
                    int idBaja = vista.solicitarIdJugador();
                    jugadorDAO.delete(idBaja);
                    break;
                case 3: // Modificación 
                    System.out.println("\n========================");
                    System.out.println("   MODIFICACIÓN DE JUGADOR   ");
                    System.out.println("========================");
                    int idMod = vista.solicitarIdJugador();
                    Jugador jugadorModificado = jugadorDAO.read(idMod);
                    if (jugadorModificado != null) {
                        vista.mostrarMensaje("Los datos a modificar son los del jugador: " + jugadorModificado.getNick());
                        // Modificar cada atributo por separado
                        if (vista.preguntarModificar("nombre")) {
                            String nuevoNick = vista.leerNuevoNick();
                            jugadorModificado.setNick(nuevoNick);
                        }
                        if (vista.preguntarModificar("experiencia")) {
                            int nuevaExperiencia = vista.leerNuevaExperiencia();
                            jugadorModificado.setExperience(nuevaExperiencia);
                        }
                        if (vista.preguntarModificar("nivel de vida")) {
                            int nuevoNivelVida = vista.leerNuevoNivelVida();
                            jugadorModificado.setLifeLevel(nuevoNivelVida);
                        }
                        if (vista.preguntarModificar("monedas")) {
                            int nuevasMonedas = vista.leerNuevasMonedas();
                            jugadorModificado.setCoins(nuevasMonedas);
                        }

                        jugadorDAO.update(jugadorModificado);
                        vista.mostrarMensaje("Datos actualizados exitosamente");
                    } else {
                        vista.mostrarMensaje("Jugador no encontrado");
                    }
                    break;
                case 4:// Consulta por ID
                    System.out.println("\n========================");
                    System.out.println("   CONSULTA POR ID     ");
                    System.out.println("========================");
                    int idConsulta = vista.solicitarIdJugador();
                    Jugador jugadorConsultado = jugadorDAO.read(idConsulta);
                    if (jugadorConsultado != null) {
                        vista.mostrarJugador(jugadorConsultado);
                    } else {
                        vista.mostrarMensaje("Jugador no encontrado");
                    }
                    break;
                case 5:// Todos los jugadores
                    System.out.println("\n========================");
                    System.out.println("   LISTADO DE JUGADORES ");
                    System.out.println("========================");
                    for (Jugador j : jugadorDAO.getAll()) {
                        vista.mostrarJugador(j);
                    }
                    break;
                case 6:// Salir
                    salir = true;
                    vista.mostrarMensaje("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida");
            }
        }
        // Guardar los jugadores al finalizar el programa
        gestorAlmacenamiento.guardarJugadores(jugadorDAO.getAll(), tipoAlmacenamiento);
    }
}
