package elementos;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Gestiona cada casilla del tablero de juego.
 * 
 * Las casillas se crean desde la clase "Tablero" de este mismo paquete.
 * 
 * @author La clase de 1º DAW y José Luis Cruz
 */
public class Casilla
{
    // Elementos visuales que componen la casilla:
    private JPanel hueco;  // Panel base que sirve de contenedor al resto
    private JLabel rotulo; // Puede ser un número o bien la letra "B" (bomba aparecida) o "X" marca de bomba puesta por el jugador
    private JButton boton; // Botón superficial que aparece al principio y cuya pulsación destapa el posible rótulo
    
    /**
     * Crea una nueva casilla, que deberá ser agregada a un tablero mediante getHueco()
     */
    public Casilla()
    {
        // creamos los componentes visuales
        hueco = new JPanel();
        rotulo = new JLabel ();
        boton = new JButton ();
        
        // establecemos el diseño del hueco como una rejilla de una celda
        hueco.setLayout(new GridLayout(1, 1));
        
        // mantenemos el rótulo invisible, sin agregarlo al panel "hueco"
        rotulo.setVisible(false);

        // sólo agregamos el botón al hueco base, por supuesto dejándolo visible
        hueco.add(boton);
        boton.setVisible(true);
        
    }
    
    /**
     * Sirve para alcanzar la referencia al panel "hueco" contenedor de la casilla
     * @return Referencia al objeto JPanel contenedor de la casilla
     */
    public JPanel getHueco()
    {
        return this.hueco;
    }
    
}
