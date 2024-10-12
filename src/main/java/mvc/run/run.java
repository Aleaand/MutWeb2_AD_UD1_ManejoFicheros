/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.run;

import mvc.controlador.Controlador;
import mvc.modelo.IDAO;
import mvc.modelo.Jugador;
import mvc.modelo.JugadorDAO;
import mvc.vista.Vista;

/**
 *
 * @author Alejandra
 */
public class run {
    public static void main(String[] args) {
        Vista vista = new Vista();
        IDAO<Jugador> jugadorDAO = new JugadorDAO();
        Controlador controlador = new Controlador(vista, jugadorDAO);
        controlador.iniciar();
    }
}
