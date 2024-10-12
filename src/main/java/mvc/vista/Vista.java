/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.vista;

import java.util.InputMismatchException;
import java.util.Scanner;
import mvc.modelo.Jugador;

/**
 * Clase Vista que se encarga de la interacción con el usuario.
 *
 * @author Alejandra
 */
public class Vista {

    private Scanner scanner;

    /**
     * Constructor que inicializa el scanner.
     */
    public Vista() {
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal de opciones al usuario.
     */
    public void mostrarMenuPrincipal() {
        System.out.println("\n========================");
        System.out.println("     MENÚ PRINCIPAL     ");
        System.out.println("========================");
        System.out.println("1. Alta de jugadores");
        System.out.println("2. Baja de jugadores");
        System.out.println("3. Modificación de jugadores");
        System.out.println("4. Listado por ID");
        System.out.println("5. Listado general");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    /**
     * Muestra el submenú de configuración de almacenamiento.
     */
    public void mostrarSubmenuConfiguracion() {
        System.out.println("\n==============================");
        System.out.println("    CONFIGURACIÓN DE           ");
        System.out.println("         ALMACENAMIENTO        ");
        System.out.println("==============================");
        System.out.println("1. Fichero secuencial de texto");
        System.out.println("2. Fichero secuencial binario");
        System.out.println("3. Fichero de objetos binario");
        System.out.println("4. Fichero de acceso aleatorio binario");
        System.out.println("5. Fichero de texto XML");
        System.out.print("Seleccione el tipo de almacenamiento: ");
    }

    /**
     * Lee una cadena de texto del usuario.
     *
     * @return La cadena ingresada.
     */
    public String leerTXT() {
        return scanner.next();
    }

    /**
     * Lee los datos de un nuevo jugador desde la entrada del usuario.
     *
     * @return Un objeto Jugador con los datos ingresados.
     */
    public Jugador leerDatosJugador() {
        System.out.print("Ingrese el nombre del jugador: ");
        String nick = scanner.next();
        System.out.print("Ingrese experiencia del jugador: ");
        int experiencia = leerEntero();
        System.out.print("Ingrese nivel de vida del jugador: ");
        int lifeLevel = leerEntero();
        System.out.print("Ingrese monedas del jugador: ");
        int monedas = leerEntero();
        return new Jugador(0, nick, experiencia, lifeLevel, monedas);
    }

    /**
     * Solicita al usuario el ID de un jugador.
     *
     * @return El ID ingresado por el usuario.
     */
    public int solicitarIdJugador() {
        System.out.print("Ingrese ID del jugador: ");
        int numero = leerEntero();
        return numero;
    }

    /**
     * Pregunta al usuario si desea modificar un atributo específico.
     *
     * @param atributo El nombre del atributo a modificar.
     * @return true si desea modificarlo, false si no.
     */
    public boolean preguntarModificar(String atributo) {
        String respuesta = "";
        while (true) {
            System.out.print("Desea modificar el " + atributo + "? (s/n): ");
            respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals("s")) {
                return true;
            } else if (respuesta.equals("n")) {
                return false;
            } else {
                System.out.println("Ingrese 's' para sí o 'n' para no");
            }
        }
    }

    /**
     * Lee un nuevo nick para un jugador.
     *
     * @return El nuevo nick ingresado.
     */
    public String leerNuevoNick() {
        System.out.print("Ingrese el nuevo nombre: ");
        return scanner.nextLine().trim();
    }

    /**
     * Lee una nueva experiencia para un jugador.
     *
     * @return La nueva experiencia ingresada.
     */
    public int leerNuevaExperiencia() {
        System.out.print("Ingrese la nueva experiencia: ");
        return leerEntero();
    }

    /**
     * Lee un nuevo nivel de vida para un jugador.
     *
     * @return El nuevo nivel de vida ingresado.
     */
    public int leerNuevoNivelVida() {
        System.out.print("Ingrese el nuevo nivel de vida: ");
        return leerEntero();
    }

    /**
     * Lee una nueva cantidad de monedas para un jugador.
     *
     * @return La nueva cantidad de monedas ingresadas.
     */
    public int leerNuevasMonedas() {
        System.out.print("Ingrese la nueva cantidad de monedas: ");
        return leerEntero();
    }

    /**
     * Lee un número entero del usuario, manejando excepciones.
     *
     * @return El número entero ingresado.
     */
    public int leerEntero() {
        while (true) {
            try {
                int numero = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer 
                return numero;
            } catch (InputMismatchException e) {
                System.out.print("Por favor, ingrese un número válido: ");
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }

    /**
     * Lee un número entero del usuario dentro de un rango específico.
     *
     * @param hasta El límite superior del rango.
     * @return El número entero ingresado.
     */
    public int leerEntero(int hasta) {
        while (true) {
            try {
                int numero = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                if (numero >= 1 && numero <= hasta) {
                    return numero;
                } else {
                    System.out.print("Por favor, ingrese un número entre 1 y " + hasta + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Por favor, ingrese un número válido: ");
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }

    /**
     * Muestra un mensaje al usuario.
     *
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra los detalles de un jugador.
     *
     * @param jugador El jugador a mostrar.
     */
    public void mostrarJugador(Jugador jugador) {
        System.out.println(jugador.toString());
    }
}
