/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package mvc.modelo;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alejandra
 */
public class JugadorDAOTest {

    private JugadorDAO jugadorDAO;

    @Before
    public void setUp() {
        jugadorDAO = new JugadorDAO();
    }

    @Test
    public void testCreateAndRead() {
        // Crea un nuevo jugador
        Jugador jugador = new Jugador(0, "Pablo", 10, 5, 100);
        jugadorDAO.create(jugador);

        // Asegura de que se puede leer correctamente
        Jugador result = jugadorDAO.read(jugador.getId());
        assertEquals(jugador.getNick(), result.getNick());
        assertEquals(jugador.getExperience(), result.getExperience());
        assertEquals(jugador.getLifeLevel(), result.getLifeLevel());
        assertEquals(jugador.getCoins(), result.getCoins());
    }

    @Test
    public void testUpdate() {
        // Crea y guarda un jugador
        Jugador jugador = new Jugador(0, "Pablo", 10, 5, 100);
        jugadorDAO.create(jugador);

        // Modifica el jugador
        jugador.setNick("Juanita");
        jugador.setExperience(20);
        jugador.setLifeLevel(6);
        jugador.setCoins(200);
        jugadorDAO.update(jugador);

        // Verifica que los cambios se han guardado
        Jugador updatedJugador = jugadorDAO.read(jugador.getId());
        assertEquals("Juanita", updatedJugador.getNick());
        assertEquals(20, updatedJugador.getExperience());
        assertEquals(6, updatedJugador.getLifeLevel());
        assertEquals(200, updatedJugador.getCoins());
    }

    @Test
    public void testDelete() {
        // Crea y guarda un jugador
        Jugador jugador = new Jugador(0, "Pablo", 10, 5, 100);
        jugadorDAO.create(jugador);

        // Elimina el jugador
        jugadorDAO.delete(jugador.getId());

        // Verifica que no se puede encontrar el jugador
        Jugador result = jugadorDAO.read(jugador.getId());
        assertNull(result);
    }

    @Test
    public void testGetAll() {
        // Crea y guarda dos jugadores
        Jugador jugador1 = new Jugador(0, "Pablo", 10, 5, 100);
        Jugador jugador2 = new Jugador(0, "Juanita", 20, 6, 200);
        jugadorDAO.create(jugador1);
        jugadorDAO.create(jugador2);

        // Obtiene todos los jugadores
        List<Jugador> jugadores = jugadorDAO.getAll();

        // Verifica que la lista contiene dos jugadores
        assertEquals(2, jugadores.size());
        assertTrue(jugadores.contains(jugador1));
        assertTrue(jugadores.contains(jugador2));
    }

    @Test
    public void testReadNonExistent() {
        // Intenta leer un jugador que no existe
        Jugador result = jugadorDAO.read(999);
        assertNull(result); // Debe devolver null
    }
}
