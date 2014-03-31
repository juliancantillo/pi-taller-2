/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package punto1;

import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 *
 * @author invitado
 */
public class EventHandlers implements ActionListener, KeyListener, MouseListener, FocusListener{

    private ChatForm chat;
    
    public EventHandlers(ChatForm obj) {
        this.chat = obj;
    }
    
    public void Login(){
        String user = JOptionPane.showInputDialog(chat, R.NEW_USERNAME);
        try {
            chat.Login(user);
        } catch (ChatExceptions ex) {
            JOptionPane.showMessageDialog(chat, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == chat.btnLogin) {
            this.Login();
        }
        
        if(e.getSource() == chat.btnSend){
            try {
                chat.addMessage( chat.fldInput.getText() );
            } catch (ChatExceptions ex) {
                JOptionPane.showMessageDialog(chat, ex.getMessage());
            }
        }
        
        if(e.getSource() == chat.btnCleanChat){
            chat.msgArea.setText("");
        }
        
        if(e.getSource() == chat.btnLogout){
            try {
                chat.Logout();
            } catch (ChatExceptions ex) {
                JOptionPane.showMessageDialog(chat, ex.getMessage());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == chat.fldInput && e.getKeyCode() == KeyEvent.VK_ENTER ){
            try {
                chat.addMessage( chat.fldInput.getText() );
            } catch (ChatExceptions ex) {
                JOptionPane.showMessageDialog(chat, ex.getMessage());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(chat.fldInput.getText().equals(R.INPUT_PLACEHOLDER))
            chat.fldInput.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(chat.fldInput.getText().equals(R.INPUT_PLACEHOLDER) || chat.fldInput.getText().equals("") )
            chat.fldInput.setText(R.INPUT_PLACEHOLDER);
    }
    
}
