/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package mvc.modelo;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alejandra
 */
public class JugadorTest {

    private Jugador jugador;

    @Before
    public void setUp() {
        // Configura un jugador de prueba antes de cada prueba
        jugador = new Jugador(1, "Pablo", 10, 5, 100);
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int result = jugador.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 2;
        jugador.setId(id);
        assertEquals(id, jugador.getId());
    }

    @Test
    public void testGetNick() {
        System.out.println("getNick");
        String expResult = "Pablo";
        String result = jugador.getNick();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNick() {
        System.out.println("setNick");
        String nick = "juan";
        jugador.setNick(nick);
        assertEquals(nick, jugador.getNick());
    }

    @Test
    public void testGetExperience() {
        System.out.println("getExperience");
        int expResult = 10;
        int result = jugador.getExperience();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetExperience() {
        System.out.println("setExperience");
        int experience = 20;
        jugador.setExperience(experience);
        assertEquals(experience, jugador.getExperience());
    }

    @Test
    public void testGetLifeLevel() {
        System.out.println("getLifeLevel");
        int expResult = 5;
        int result = jugador.getLifeLevel();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetLifeLevel() {
        System.out.println("setLifeLevel");
        int lifeLevel = 10;
        jugador.setLifeLevel(lifeLevel);
        assertEquals(lifeLevel, jugador.getLifeLevel());
    }

    @Test
    public void testGetCoins() {
        System.out.println("getCoins");
        int expResult = 100;
        int result = jugador.getCoins();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCoins() {
        System.out.println("setCoins");
        int coins = 200;
        jugador.setCoins(coins);
        assertEquals(coins, jugador.getCoins());
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "[USER_ID = 1, NICK_NAME = Pablo, EXPERIENCE = 10, LIFE_LEVEL = 5, COINS = 100]";
        String result = jugador.toString();
        assertEquals(expResult, result);
    }
}
