/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jlcruz-cifp
 */
public class Casilla
{
    private JPanel hueco;
    private JLabel rotulo;
    private JButton boton;
    
    public Casilla()
    {
        hueco = new JPanel();
        rotulo = new JLabel ();
        boton = new JButton ();
        
        hueco.setLayout(new GridLayout(1, 1));
        hueco.add(rotulo);
        rotulo.setVisible(false);
        
        hueco.add(boton);
        boton.setVisible(true);
        
    }
    
    public JPanel getHueco()
    {
        return this.hueco;
    }
    
}
