/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author jlcruz-cifp
 */
public class Tablero
{
    private int ancho;
    private int alto;
    private int numero_minas;
    private int minas_marcadas;
    private int estado_juego;
    /*
    * Estado del juego, que puede ser:
    *  0 - Inicial, el contador aún no corre
    *  1 - Jugando, el contador está corriendo
    *  2 - Pausado, el contador se detiene
    *  3 - Finalizado, juego completado correctamente
    *  4 - Terminado prematuramente por encontrar bomba
    */
    private Casilla[][] casillas;
    /**
     * 
     * @param ancho unidades de anchura (en celdas)
     * @param alto  unidades de altura (en celdas)
     */
    
    private JPanel lienzo;
    
    public Tablero(int ancho, int alto, JPanel lienzo)
    {
        this.ancho = ancho;
        this.alto = alto;
        this.lienzo = lienzo;
        this.numero_minas = (int)Math.round((ancho * alto) * 0.05);
        this.minas_marcadas = 0;
        this.estado_juego = 0;
        casillas = new Casilla[ancho][alto];
        lienzo.setLayout(new GridLayout(ancho, alto));
        for (int x=0;x<ancho;x++)
        {
            for (int y=0;y<alto;y++)
            {
                casillas[x][y] = new Casilla();
                lienzo.add(casillas[x][y].getHueco());
                casillas[x][y].getHueco().setVisible(true);
            }
        }
        
    }
}
