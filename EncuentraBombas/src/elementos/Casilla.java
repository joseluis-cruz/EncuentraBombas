package elementos;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Gestiona cada casilla del tablero de juego.
 * 
 * Las casillas se crean desde la clase "Tablero" de este mismo paquete.
 * 
 * @author La clase de 1º DAW y José Luis Cruz
 */
public class Casilla
{
    private int contenido; // Lo que contiene la casilla, que puede ser:
    // 0: la casilla está vacía, no cotiene nada ni es colidante con una bomba
    // 1-8: la casilla contiene ese número, porque es colidante con ese nº de bombas
    // 9: el contenido de la casilla es una bomba
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
        rotulo.setVisible(true);

        // sólo agregamos el botón al hueco base, por supuesto dejándolo visible
        hueco.add(boton);
        boton.setVisible(true);
        
        // capturar el evento de la pulsación del botón
        boton.addMouseListener (new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                hueco.remove(boton);
                hueco.add(rotulo);
                rotulo.setVisible(true);
                hueco.validate();
                hueco.repaint();                        
            }
        });        
    }
    
    /**
     * Sirve para alcanzar la referencia al panel "hueco" contenedor de la casilla
     * @return Referencia al objeto JPanel contenedor de la casilla
     */
    public JPanel getHueco()
    {
        return this.hueco;
    }
    
    private void setContenido(int nuevocontenido)
    {
        contenido = nuevocontenido;
        if (contenido==0)
            rotulo.setText("");
        else if (contenido==9)
            rotulo.setText("B");
        else if ((contenido>0) && (contenido<9))
            rotulo.setText(Integer.toString(contenido));
    }
    
    /**
     * Limpia el contendido de la casilla, para que no contenga nada.
     */
    public void limpia()
    {
        setContenido(0);
    }
   
    /**
     * Indica a la casilla de que debe alojar una bomba en su interior.
     */
    public void colocaBomba()
    {
        setContenido(9);
    }
    
    /**
     * Sirve para notificar a la casilla de que posee una nueva bomba vecina.
     * 
     * De forma que se incrementará su contador interno de bombas vecinas.
     * 
     */
    public void tienesBombaVecina()
    {
        if (contenido<8)
            setContenido(contenido+1);
    }
    
    /**
     * Informa de si la casilla contiene una bomba
     * @return true cuando la casilla es una bomba o false en cualquier otro caso
     */
    public boolean esBomba()
    {
        return (contenido==9);
    }
}
