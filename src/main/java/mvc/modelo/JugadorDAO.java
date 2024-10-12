/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase JugadorDAO que implementa la interfaz IDAO para realizar operaciones
 * CRUD sobre la entidad Jugador.
 *
 * @author Alejandra
 */
public class JugadorDAO implements IDAO<Jugador> {

    private List<Jugador> jugadores;

    /**
     * Constructor que inicializa la lista de jugadores.
     */
    public JugadorDAO() {
        this.jugadores = new ArrayList<>();
    }

    /**
     * Crea un nuevo jugador, le asigna un ID único y lo agrega a la lista.
     *
     * @param t El jugador a crear.
     */
    @Override
    public void create(Jugador t) {
        t.setId(generarIdUnico());
        jugadores.add(t);
    }

    /**
     * Busca y devuelve un jugador por su ID.
     *
     * @param id El ID del jugador a buscar.
     * @return El jugador correspondiente al ID, o null si no se encuentra.
     */
    @Override
    public Jugador read(int id) {
        for (Jugador j : jugadores) {
            if (j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un jugador existente.
     *
     * @param t El jugador con los nuevos datos.
     */
    @Override
    public void update(Jugador t) {
        for (Jugador j : jugadores) {
            if (j.getId() == t.getId()) {
                j.setNick(t.getNick());
                j.setExperience(t.getExperience());
                j.setLifeLevel(t.getLifeLevel());
                j.setCoins(t.getCoins());
                return;
            }
        }
    }

    /**
     * Elimina un jugador de la lista usando su ID.
     *
     * @param id El ID del jugador a eliminar.
     */
    @Override
    public void delete(int id) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getId() == id) {
                jugadores.remove(i);
                break;
            }
        }
    }

    /**
     * Obtiene una lista con todos los jugadores.
     *
     * @return Lista de todos los jugadores.
     */
    @Override
    public List<Jugador> getAll() {
        return jugadores;
    }

    /**
     * Genera un ID único para cada jugador nuevo.
     *
     * @return Un nuevo ID, que es el mayor ID existente más uno.
     */
    private int generarIdUnico() {
        if (jugadores.isEmpty()) {
            return 0;
        } else {
            int maxId = 0;
            for (Jugador jugador : jugadores) {
                if (jugador.getId() > maxId) {
                    maxId = jugador.getId();
                }
            }
            return maxId + 1;
        }
    }
}
