/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package punto1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author invitado
 */
public class ChatForm extends JFrame{
    
    public JTextArea msgArea;
    public JTextField fldInput;
    public JButton btnSend, btnLogin, btnLogout, btnCleanChat;
    private JLabel lblConnectedUsers, lblOptions, lblTitle;
    public JComboBox comboConnectedUsers;
    private EventHandlers events;
        
    //Distancia por defecto de los elementos del formulario
    private int HGap = 4, VGap = 4;

    public ChatForm(){
        
        super("Simulador de Chat");
        
        events = new EventHandlers(this);
        
        this.setLayout(new BorderLayout(HGap, VGap));
        
        lblTitle = new JLabel(R.TITLE);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        
        this.add(lblTitle, BorderLayout.NORTH);
        this.add(pnlMessagesArea(),BorderLayout.CENTER);
        this.add(pnlRightButtons(),BorderLayout.EAST);
        
        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public JPanel pnlMessagesArea(){
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(HGap, VGap));
        
        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(new BorderLayout(HGap, VGap));
        
        msgArea = new JTextArea();
        fldInput = new JTextField(R.INPUT_PLACEHOLDER);
        btnSend = new JButton(R.SEND_BUTTON);
        
        btnSend.addActionListener(events);
        fldInput.addFocusListener(events);
        fldInput.addKeyListener(events);
                
        pnlInput.add(fldInput, BorderLayout.CENTER);
        pnlInput.add(btnSend, BorderLayout.EAST);
        
        panel.add(msgArea, BorderLayout.CENTER);
        panel.add(pnlInput, BorderLayout.SOUTH);
        
        return panel;
    }
    
    public JPanel pnlRightButtons(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, HGap, VGap));
        
        lblConnectedUsers = new JLabel(R.CONNECTED_USERS);
        comboConnectedUsers = new JComboBox();
        lblOptions = new JLabel(R.OPTIONS);
        btnLogin = new JButton(R.LOGIN_BUTTON);
        btnLogout = new JButton(R.LOGOUT_BUTTON);
        btnCleanChat = new JButton(R.CLEAN_BUTTON);
        
        btnLogin.addActionListener(events);
        btnLogout.addActionListener(events);
        btnCleanChat.addActionListener(events);
        
        panel.add(lblConnectedUsers);
        panel.add(comboConnectedUsers);
        panel.add(lblOptions);
        panel.add(btnLogin);
        panel.add(btnLogout);
        panel.add(btnCleanChat);
        
        return panel;
    }
    
    public void Login(String user){
        comboConnectedUsers.addItem(user);
    }
    
    public void addMessage(String msg){
        if(comboConnectedUsers.getItemCount() == 0){
            JOptionPane.showMessageDialog(this, R.MUST_BE_LOGED_IN);
        }else{
            String user = comboConnectedUsers.getSelectedItem().toString();
            if(!"".equals(msg)){

                msgArea.append(String.format("%s: %s\n", user, msg));
                fldInput.setText("");

            }else{
                JOptionPane.showMessageDialog(this, R.NOT_EMPTY_MSG_ALLOWED);
            }
        }
    }
    
    
}
