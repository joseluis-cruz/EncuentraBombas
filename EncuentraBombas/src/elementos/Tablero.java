package elementos;

import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Implemnta la funcionalidad de un tablero de juego y del juego en sí mismo.
 *  
 * @author La clase de 1º DAW y José Luis Cruz
 */
public class Tablero
{
    // propiedades privadas, que reflejan características y el estado de juego
    private int ancho; // anchura (en casillas) del tablero
    private int alto;  // altura (en casillas) del tablero
    private int numero_bombas;   // cantidad de bombas que hay en el tablero
    private int bombas_marcadas; // cantidad de bombas marcadas por el jugador
    
    private int estado_juego;
    /*
    * Identifica el estado en el que se encuentra el juego, que puede ser:
    *  0 - Inicial, el contador aún no corre
    *  1 - Jugando, el contador está corriendo
    *  2 - Pausado, el contador se detiene
    *  3 - Finalizado, juego completado correctamente
    *  4 - Terminado prematuramente por encontrar bomba
    */
    
    private Casilla[][] casillas; // array bidimensional con las casillas del tablero
    private JPanel lienzo; // mantiene una referencia al panel principal en el que se pintan las casillas

    /**
     * Construye un objeto tablero de juego, con los datos que se le proporcionan.
     * 
     * @param ancho  unidades de anchura para el tablero (en celdas)
     * @param alto   unidades de altura para el tablero (en celdas)
     * @param lienzo panel contenedor principal de todas las casillas
     */  
    public Tablero(int ancho, int alto, JPanel lienzo)
    {
        // recordar los parámetros del constructor en propiedades privadas
        this.ancho = ancho;
        this.alto = alto;
        this.lienzo = lienzo;
        // calcular la cantidad de bombas como un 5% del total de casillas
        this.numero_bombas = (int)Math.round((ancho * alto) * 0.5);
        // valores iniciales para el resto de propiedades privadas
        this.bombas_marcadas = 0;
        this.estado_juego = 0;
        // crear dinámicamente la tabla de casillas:
        casillas = new Casilla[ancho][alto];
        // establecer el diseño del panel de casillas como una rejilla de "ancho" x "alto"
        lienzo.setLayout(new GridLayout(alto,ancho));
        // recorrer el array para crear las casillas de su interior
        for (int y=0;y<alto;y++)
        {
            for (int x=0;x<ancho;x++)
            {
                // crear la casilla
                casillas[x][y] = new Casilla();
                // agregarla al panel "lienzo" y ponerla visible
                lienzo.add(casillas[x][y].getHueco());
                casillas[x][y].getHueco().setVisible(true);
            }
        }
        inicializaJuego();
        
        // forzar redibujado del panel contenedor principal
        lienzo.validate();
        lienzo.repaint();
    }

    /**
     * Método que inicializa el tablero de juego.
     * Limpia todas las caaillas, genera las bombas e informa a las casillas vecinas.
     */
    private void inicializaJuego ()
    {
        // vamos aquí a inicializar el contenido del tablero
        for (int x = 0; x < this.ancho; x++) {
            for (int y = 0; y < this.alto; y++) {
                casillas[x][y].limpia();
            }
        }
        Random rnd = new Random();
        for (int i=1;i<=this.numero_bombas;i++)
        {
            int fila,columna;
            // buscar valores aleatorios para fila y columna
            // repetimos la búsqueda mientras hayamos calculado unas coordenadas
            // en las que ya hay una bomba (para no repetirse)
            do
            {
                fila = rnd.nextInt(this.alto);
                columna = rnd.nextInt(this.ancho);
            } while (casillas[columna][fila].esBomba());
            // colocar la bomba en la coordenada calculada aleatoriamente
            casillas[columna][fila].colocaBomba();
            // informar a las casillas vecinas
            for (int x=-1;x<=1;x++)
            {
                for (int y=-1;y<=1;y++)
                {
                    notificaBombaVecina(columna+x,fila+y);
                }
            }
            
        }
    }
    
    private void notificaBombaVecina(int columna, int fila)
    {
        // primero compruebo que sea una coordenada válida
       if ((columna>=0)&&(columna<ancho)&&(fila>=0)&&(fila<alto))
           //en tal caso, notifico la bomba vecina a la casilla de esa coordenada
           casillas[columna][fila].tienesBombaVecina();
    }
}