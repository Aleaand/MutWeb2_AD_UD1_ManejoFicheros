/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Clase encargada de gestionar el almacenamiento de objetos {@link Jugador}.
 * Proporciona métodos para cargar y guardar datos en diferentes formatos:
 * archivos de texto, binarios, acceso aleatorio y XML.
 *
 * @author Alejandra
 */
public class GestorAlmacenamiento {

    public GestorAlmacenamiento() {
    }

    /**
     * Carga los datos de los jugadores desde un formato de almacenamiento
     * especificado.
     *
     * @param jugadores La lista donde se almacenarán los jugadores cargados.
     * @param tipoAlmacenamiento El tipo de almacenamiento a usar (1: texto, 2:
     * binario, 3: objetos binarios, 4: acceso aleatorio, 5: XML).
     */
    public void cargarJugadores(List<Jugador> jugadores, int tipoAlmacenamiento) {
        switch (tipoAlmacenamiento) {
            case 1:
                cargarTXT(jugadores);
                break;
            case 2:
                cargarEnBinario(jugadores);
                break;
            case 3:
                cargarObjetosBinario(jugadores);
                break;
            case 4:
                cargarAccesoAleatorio(jugadores);
                break;
            case 5:
                cargarEnXML(jugadores);
                break;
            default:
                System.out.println("No se ha podido cargar");
                break;
        }
    }

    /**
     * Guarda los datos de los jugadores en un formato de almacenamiento
     * especificado.
     *
     * @param jugadores La lista de jugadores a guardar.
     * @param tipoAlmacenamiento El tipo de almacenamiento a usar (1: texto, 2:
     * binario, 3: objetos binarios, 4: acceso aleatorio, 5: XML).
     */
    public void guardarJugadores(List<Jugador> jugadores, int tipoAlmacenamiento) {
        switch (tipoAlmacenamiento) {
            case 1:
                guardarTXT(jugadores);
                break;
            case 2:
                guardarEnBinario(jugadores);
                break;
            case 3:
                guardarObjetosBinario(jugadores);
                break;
            case 4:
                guardarAccesoAleatorio(jugadores);
                break;
            case 5:
                guardarEnXML(jugadores);
                break;
            default:
                System.out.println("Tipo de almacenamiento no válido");
                break;
        }
    }
////////////////////////////////////////////////////////////////Archivo de Texto

    /**
     * Carga una lista de jugadores desde un archivo de texto ("jugadores.txt").
     * El archivo es procesado línea por línea, validando el formato y
     * convirtiendo los datos en objetos de tipo Jugador para almacenarlo en la
     * lista. Si el archivo no existe, se crea un archivo nuevo.
     *
     * @param jugadores La lista en la que se cargarán los objetos Jugador desde
     * el archivo.
     */
    private void cargarTXT(List<Jugador> jugadores) {
        File archivo = new File("jugadores.txt");
        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
                try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        try {
                            line = line.trim();
                            if (!line.startsWith("[USER_ID =") || !line.endsWith("]")) {
                                System.out.println("Formato incorrecto en la línea: " + line);
                                continue;
                            }
                            line = line.substring(1, line.length() - 1);// Eliminar los corchetes de inicio y fin
                            String[] parts = line.split(", ");
                            if (parts.length != 5) {
                                System.out.println("Número incorrecto de partes en la línea: " + line);
                                continue;
                            }
                            // id
                            String[] idPart = parts[0].split(" = ");
                            if (idPart.length != 2) {
                                System.out.println("Error en el USER_ID: " + parts[0]);
                                continue;
                            }
                            int id = Integer.parseInt(idPart[1]);
                            // nombre
                            String[] nickPart = parts[1].split(" = ");
                            if (nickPart.length != 2) {
                                System.out.println("Error en el NICK_NAME: " + parts[1]);
                                continue;
                            }
                            String nick = nickPart[1];
                            // experiencia
                            String[] experiencePart = parts[2].split(" = ");
                            if (experiencePart.length != 2) {
                                System.out.println("Error en el EXPERIENCE: " + parts[2]);
                                continue;
                            }
                            int experience = Integer.parseInt(experiencePart[1]);
                            // life level
                            String[] lifeLevelPart = parts[3].split(" = ");
                            if (lifeLevelPart.length != 2) {
                                System.out.println("Error en el LIFE_LEVEL: " + parts[3]);
                                continue;
                            }
                            int lifeLevel = Integer.parseInt(lifeLevelPart[1]);
                            //monedas
                            String[] coinsPart = parts[4].split(" = ");
                            if (coinsPart.length != 2) {
                                System.out.println("Error en el COINS: " + parts[4]);
                                continue;
                            }
                            int coins = Integer.parseInt(coinsPart[1]);

                            // Crear el objeto Jugador y agregarlo a la lista
                            Jugador jugador = new Jugador(id, nick, experience, lifeLevel, coins);
                            jugadores.add(jugador);

                        } catch (NumberFormatException e) {
                            System.out.println("Error al procesar los datos numéricos en la línea: " + line + ". " + e.getMessage());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error en el formato de la línea: " + line + ". " + e.getMessage());
                        }
                    }
                    // Vaciar el archivo después de cargar los jugadores
                    vaciarTXT();
                } catch (IOException e) {
                    System.out.println("Error al cargar los jugadores: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda una lista de jugadores en un archivo de texto ("jugadores.txt").
     * Si el archivo ya existe, los datos se agregan al final del archivo.
     *
     * @param jugadores La lista de jugadores que se guardarán en el archivo.
     */
    private void guardarTXT(List<Jugador> jugadores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jugadores.txt", true))) { // 'true' para agregar al final
            for (Jugador j : jugadores) {
                writer.write(j.toString());
                writer.newLine();
            }
            System.out.println("Jugadores guardados en texto correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }

    /**
     * Vacía el contenido del archivo de texto "jugadores.txt".
     */
    private void vaciarTXT() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jugadores.txt", false))) {
            System.out.println("Contenido del archivo borrado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al borrar el contenido del archivo: " + e.getMessage());
        }
    }

//////////////////////////////////////////////////////////////Archivo de Binario
    /**
     * Carga una lista de jugadores desde un archivo binario
     * ("jugadorBinario.dat"). Si el archivo no existe, lo crea, pero no carga
     * ningún dato. Si el archivo existe, lee los objetos `Jugador` y los agrega
     * a la lista proporcionada.
     *
     * @param jugadores La lista en la que se cargarán los objetos Jugador desde
     * el archivo binario.
     */
    private void cargarEnBinario(List<Jugador> jugadores) {
        File archivo = new File("jugadorBinario.dat");

        try {
            // Si el archivo no existe lo crea
            if (archivo.createNewFile()) {
                System.out.println("Archivo binario creado: " + archivo.getName());
                return;  // El archivo es nuevo, no tiene nada que cargar
            }
            // Si el archivo existe carga los datos
            try (FileInputStream fileIn = new FileInputStream(archivo); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                while (true) {
                    try {
                        Jugador jugador = (Jugador) objectIn.readObject();
                        jugadores.add(jugador);
                    } catch (EOFException e) {
                        break;
                    }
                }
                System.out.println("Jugadores cargados en binario correctamente.");
            }
            vaciarBinario();// Vaciar el archivo después de cargar los jugadores
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los jugadores en binario: " + e.getMessage());
        }
    }

    /**
     * Guarda una lista de jugadores en un archivo binario
     * ("jugadorBinario.dat"). Sobrescribe el archivo existente con los nuevos
     * datos.
     *
     * @param jugadores La lista de jugadores que se guardarán en el archivo
     * binario.
     */
    private void guardarEnBinario(List<Jugador> jugadores) {
        File archivo = new File("jugadorBinario.dat");
        try (FileOutputStream fileOut = new FileOutputStream(archivo); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            // Escribir cada jugador en el archivo binario
            for (Jugador jugador : jugadores) {
                objectOut.writeObject(jugador);
            }
            System.out.println("Jugadores guardados en binario correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores en binario: " + e.getMessage());
        }
    }

    /**
     * Vacía el contenido del archivo binario ("jugadorBinario.dat"),
     * sobrescribiéndolo con un archivo vacío.
     */
    private void vaciarBinario() {
        File archivo = new File("jugadorBinario.dat");
        try (FileOutputStream fileOut = new FileOutputStream(archivo)) {
            System.out.println("Contenido del archivo binario borrado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al vaciar el archivo binario: " + e.getMessage());
        }
    }

////////////////////////////////////////////////Archivo de guardarObjetosBinario
    /**
     * Carga una lista de jugadores desde un archivo binario de objetos
     * ("jugadorObjBinario.dat"). Si el archivo no existe, lo crea vacío. Si el
     * archivo existe, lee los objetos `Jugador` y los agrega a la lista
     * proporcionada.
     *
     * @param jugadores La lista en la que se cargarán los objetos Jugador desde
     * el archivo binario.
     */
    private void cargarObjetosBinario(List<Jugador> jugadores) {
        File archivo = new File("jugadorObjBinario.dat");
        try {
            // Si el archivo no existe, lo crea
            if (archivo.createNewFile()) {
                System.out.println("Archivo binario de objetos creado: " + archivo.getName());
                return;
            }
            // Si existe carga los datos
            try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(archivo))) {
                while (true) {
                    try {
                        Jugador jugador = (Jugador) objectIn.readObject();
                        jugadores.add(jugador);
                    } catch (EOFException e) {
                        break;
                    }
                }

                System.out.println("Jugadores cargados desde archivo de objetos binarios correctamente.");
                vaciarObjetosBinario();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los jugadores en binario de objetos: " + e.getMessage());
        }
    }

    /**
     * Guarda una lista de jugadores en un archivo binario de objetos
     * ("jugadorObjBinario.dat"). Sobrescribe el archivo existente con los
     * nuevos datos.
     *
     * @param jugadores La lista de jugadores que se guardarán en el archivo
     * binario de objetos.
     */
    private void guardarObjetosBinario(List<Jugador> jugadores) {
        File archivo = new File("jugadorObjBinario.dat");
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(archivo))) {
            for (Jugador jugador : jugadores) {
                objectOut.writeObject(jugador);
            }
            System.out.println("Jugadores guardados en archivo de objetos binarios correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores en binario de objetos: " + e.getMessage());
        }
    }

    /**
     * Vacía el contenido del archivo binario de objetos
     * ("jugadorObjBinario.dat"), sobrescribiéndolo con un archivo vacío.
     */
    private void vaciarObjetosBinario() {
        File archivo = new File("jugadorObjBinario.dat");
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(archivo))) {
            //Crear un nuevo archivo vacío
            System.out.println("Contenido del archivo de objetos binarios vaciado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al vaciar el archivo de objetos binarios: " + e.getMessage());
        }
    }

///////////////////////////////////////////////Archivo de guardarAccesoAleatorio
    /**
     * Carga una lista de jugadores desde un archivo binario con acceso
     * aleatorio ("jugadorAltBinario.dat"). Si el archivo no existe, lo crea. Si
     * el archivo existe, lee los datos de los jugadores y los agrega a la
     * lista.
     *
     * @param jugadores La lista en la que se cargarán los objetos Jugador desde
     * el archivo de acceso aleatorio.
     */
    private void cargarAccesoAleatorio(List<Jugador> jugadores) {
        File archivo = new File("jugadorAltBinario.dat");
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("El archivo no existía, se ha creado uno nuevo: " + archivo.getName());
                return;
            }
            try (RandomAccessFile file = new RandomAccessFile(archivo, "r")) {
                while (file.getFilePointer() < file.length()) {
                    //Lectura:
                    //id
                    int id = file.readInt();
                    // Leer el nombre
                    int nickLength = file.readInt();
                    char[] nickChars = new char[nickLength];
                    for (int i = 0; i < nickLength; i++) {
                        nickChars[i] = file.readChar();
                    }
                    String nick = new String(nickChars);

                    int experience = file.readInt();  //experiencia
                    int lifeLevel = file.readInt();  //nivel
                    int coins = file.readInt();  //monedas

                    //Añadir a la lista
                    Jugador jugador = new Jugador(id, nick, experience, lifeLevel, coins);
                    jugadores.add(jugador);
                }

                System.out.println("Jugadores cargados correctamente desde archivo de acceso aleatorio.");
                vaciarAccesoAleatorio();
            }

        } catch (IOException e) {
            System.out.println("Error al cargar los jugadores desde archivo de acceso aleatorio: " + e.getMessage());
        }
    }

    /**
     * Guarda una lista de jugadores en un archivo binario con acceso aleatorio
     * ("jugadorAltBinario.dat"). Los datos se guardan al final del archivo
     * existente.
     *
     * @param jugadores La lista de jugadores que se guardarán en el archivo de
     * acceso aleatorio.
     */
    private void guardarAccesoAleatorio(List<Jugador> jugadores) {
        File archivo = new File("jugadorAltBinario.dat");

        try (RandomAccessFile file = new RandomAccessFile(archivo, "rw")) {
            file.seek(file.length());// Posicionarse al final del archivo

            //Recorre la lista de jugadores y escribe sus datos en el archivo
            for (Jugador jugador : jugadores) {
                file.writeInt(jugador.getId());
                file.writeInt(jugador.getNick().length());  //Longitud del nombre
                file.writeChars(jugador.getNick());
                file.writeInt(jugador.getExperience());
                file.writeInt(jugador.getLifeLevel());
                file.writeInt(jugador.getCoins());
            }

            System.out.println("Jugadores guardados correctamente en archivo de acceso aleatorio.");

        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores en archivo de acceso aleatorio: " + e.getMessage());
        }
    }

    /**
     * Vacía el contenido del archivo binario de acceso aleatorio
     * ("jugadorAltBinario.dat"), truncando el archivo a longitud 0.
     */
    private void vaciarAccesoAleatorio() {
        File archivo = new File("jugadorAltBinario.dat");
        try (RandomAccessFile file = new RandomAccessFile(archivo, "rw")) {
            //Trunca el archivo a longitud 0 para vaciarlo
            file.setLength(0);
            System.out.println("Archivo de acceso aleatorio vaciado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al vaciar el archivo de acceso aleatorio: " + e.getMessage());
        }
    }

/////////////////////////////////////////////////////////Archivo de guardarEnXML
    /**
     * Carga una lista de jugadores desde un archivo XML ("jugador.xml"). Si el
     * archivo no existe, lo crea vacío y no realiza ninguna carga. Si existe,
     * lee los datos de cada jugador y los agrega a la lista proporcionada.
     *
     * @param jugadores La lista en la que se cargarán los objetos Jugador desde
     * el archivo XML.
     */
    private void cargarEnXML(List<Jugador> jugadores) {
        File archivo = new File("jugador.xml");

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("El archivo no existía, se ha creado uno nuevo: " + archivo.getName());
                return;  // Salimos del método ya que no hay datos que cargar
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(archivo);
            document.getDocumentElement().normalize();

            NodeList jugadorNodes = document.getElementsByTagName("Jugador");

            for (int i = 0; i < jugadorNodes.getLength(); i++) {
                Element jugadorElement = (Element) jugadorNodes.item(i);
                int id = Integer.parseInt(jugadorElement.getElementsByTagName("USER_ID").item(0).getTextContent());
                String nick = jugadorElement.getElementsByTagName("NICK_NAME").item(0).getTextContent();
                int experience = Integer.parseInt(jugadorElement.getElementsByTagName("EXPERIENCE").item(0).getTextContent());
                int lifeLevel = Integer.parseInt(jugadorElement.getElementsByTagName("LIFE_LEVEL").item(0).getTextContent());
                int coins = Integer.parseInt(jugadorElement.getElementsByTagName("COINS").item(0).getTextContent());

                Jugador jugador = new Jugador(id, nick, experience, lifeLevel, coins);
                jugadores.add(jugador);
            }

            System.out.println("Jugadores cargados correctamente desde archivo XML.");
            vaciarXML();
        } catch (Exception e) {
            System.out.println("Error al cargar los jugadores desde XML: " + e.getMessage());
        }
    }

    /**
     * Guarda una lista de jugadores en un archivo XML ("jugador.xml").
     * Sobrescribe cualquier contenido existente en el archivo con los datos
     * actuales de la lista.
     *
     * @param jugadores La lista de jugadores que se guardarán en el archivo
     * XML.
     */
    private void guardarEnXML(List<Jugador> jugadores) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            // Crear un nuevo documento XML
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("Jugadores");
            document.appendChild(root);

            for (Jugador jugador : jugadores) {
                Element jugadorElement = document.createElement("Jugador");
                root.appendChild(jugadorElement);

                // Añadir los elementos del jugador
                Element idElement = document.createElement("USER_ID");
                idElement.appendChild(document.createTextNode(String.valueOf(jugador.getId())));
                jugadorElement.appendChild(idElement);

                Element nickElement = document.createElement("NICK_NAME");
                nickElement.appendChild(document.createTextNode(jugador.getNick()));
                jugadorElement.appendChild(nickElement);

                Element experienceElement = document.createElement("EXPERIENCE");
                experienceElement.appendChild(document.createTextNode(String.valueOf(jugador.getExperience())));
                jugadorElement.appendChild(experienceElement);

                Element lifeLevelElement = document.createElement("LIFE_LEVEL");
                lifeLevelElement.appendChild(document.createTextNode(String.valueOf(jugador.getLifeLevel())));
                jugadorElement.appendChild(lifeLevelElement);

                Element coinsElement = document.createElement("COINS");
                coinsElement.appendChild(document.createTextNode(String.valueOf(jugador.getCoins())));
                jugadorElement.appendChild(coinsElement);
            }

            // Escribir el contenido en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("jugador.xml"));

            transformer.transform(domSource, streamResult);
            System.out.println("Jugadores guardados correctamente en archivo XML.");

        } catch (Exception e) {
            System.out.println("Error al guardar los jugadores en XML: " + e.getMessage());
        }
    }

    /**
     * Vacía el contenido del archivo XML ("jugador.xml"), eliminando cualquier
     * contenido previo escribiendo un archivo vacío.
     */
    private void vaciarXML() {
        File archivo = new File("jugador.xml");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("");
            System.out.println("Archivo XML vaciado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al vaciar el archivo XML: " + e.getMessage());
        }
    }
}
