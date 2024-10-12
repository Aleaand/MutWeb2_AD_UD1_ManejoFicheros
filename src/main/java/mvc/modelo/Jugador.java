/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.modelo;

import java.io.Serializable;

/**
 * Esta clase representa un jugador con atributos como ID, apodo, experiencia,
 * nivel de vida y monedas. Implementa Serializable para permitir la
 * serialización de objetos de esta clase.
 *
 * @author Alejandra
 */
public class Jugador implements Serializable {

    private int id;
    private String nick;
    private int experience;
    private int lifeLevel;
    private int coins;

    /**
     * Constructor de la clase Jugador.
     *
     * @param id El identificador único del jugador.
     * @param nick El apodo del jugador.
     * @param experience La cantidad de experiencia del jugador.
     * @param lifeLevel El nivel de vida del jugador.
     * @param coins La cantidad de monedas que posee el jugador.
     */
    public Jugador(int id, String nick, int experience, int lifeLevel, int coins) {
        this.id = id;
        this.nick = nick;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
    }

    // Getters y Setters con validaciones básicas
    /**
     * Obtiene el identificador del jugador.
     *
     * @return El ID del jugador.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece un nuevo ID para el jugador. Solo permite valores no negativos.
     *
     * @param id El nuevo ID del jugador.
     */
    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
    }

    /**
     * Obtiene el nombre (nick) del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getNick() {
        return nick;
    }

    /**
     * Establece un nuevo nombre para el jugador.
     *
     * @param nick El nuevo nombre del jugador.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Obtiene la experiencia del jugador.
     *
     * @return La experiencia del jugador.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Establece una nueva cantidad de experiencia para el jugador. Solo permite
     * valores no negativos.
     *
     * @param experience La nueva cantidad de experiencia del jugador.
     */
    public void setExperience(int experience) {
        if (experience >= 0) {
            this.experience = experience;
        }
    }

    /**
     * Obtiene el nivel de vida del jugador.
     *
     * @return El nivel de vida del jugador.
     */
    public int getLifeLevel() {
        return lifeLevel;
    }

    /**
     * Establece un nuevo nivel de vida para el jugador. Solo permite valores no
     * negativos.
     *
     * @param lifeLevel El nuevo nivel de vida del jugador.
     */
    public void setLifeLevel(int lifeLevel) {
        if (lifeLevel >= 0) {
            this.lifeLevel = lifeLevel;
        }
    }

    /**
     * Obtiene la cantidad de monedas del jugador.
     *
     * @return La cantidad de monedas del jugador.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Establece una nueva cantidad de monedas para el jugador. Solo permite
     * valores no negativos.
     *
     * @param coins La nueva cantidad de monedas del jugador.
     */
    public void setCoins(int coins) {
        if (coins >= 0) {
            this.coins = coins;
        }
    }

    /**
     * Devuelve una representación en cadena de texto del jugador, que incluye
     * todos sus atributos.
     *
     * @return Una cadena con el ID, apodo, experiencia, nivel de vida y monedas
     * del jugador.
     */
    @Override
    public String toString() {
        return "[USER_ID = " + id + ", NICK_NAME = " + nick + ", EXPERIENCE = " + experience
                + ", LIFE_LEVEL = " + lifeLevel + ", COINS = " + coins + "]";
    }
}
