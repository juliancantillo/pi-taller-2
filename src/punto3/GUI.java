/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package punto3;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author invitado
 */
public class GUI extends JFrame{
    
    private JLabel lblMainWord;

    public GUI() {
        
        super("Juego de Ahorcado");
        
        lblMainWord = new JLabel("Palabra");
        
        add(pnlMain());
        
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private JPanel pnlMain(){
        JPanel pnl = new JPanel();
        
        pnl.add(lblMainWord);
        
        return pnl;
    }
    
}
