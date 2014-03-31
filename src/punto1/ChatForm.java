/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package punto1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author invitado
 */
public class ChatForm extends JFrame{
    
    public JTextArea msgArea;
    public JTextField fldInput;
    public JScrollPane scroll;
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
        scroll = new JScrollPane(msgArea);
        fldInput = new JTextField(R.INPUT_PLACEHOLDER);
        btnSend = new JButton(R.SEND_BUTTON);
        
        btnSend.addActionListener(events);
        fldInput.addFocusListener(events);
        fldInput.addKeyListener(events);
        
        
        pnlInput.add(fldInput, BorderLayout.CENTER);
        pnlInput.add(btnSend, BorderLayout.EAST);
        
        panel.add(scroll, BorderLayout.CENTER);
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
    
    public void Login(String user) throws ChatExceptions{
        if (!"".equals(user) && user != null) {
            comboConnectedUsers.addItem(user);
        }else{
            throw new ChatExceptions("El usuario no puede tener nombre vacio o null");
        }
    }
    
    public void Logout() throws ChatExceptions{
        if(comboConnectedUsers.getItemCount() > 0){
            String user = getCurrentUser();
            msgArea.append(String.format("%s: %s\n", user, "Ha dejado el chat"));
            comboConnectedUsers.removeItemAt( comboConnectedUsers.getSelectedIndex() );
        }else{
            throw new ChatExceptions(R.NO_LOGGED_USERS_TO_LOGOUT);
        }
    }
    
    public String getCurrentUser(){
        return comboConnectedUsers.getSelectedItem().toString();
    }
    
    public void addMessage(String msg) throws ChatExceptions{
        if(comboConnectedUsers.getItemCount() == 0){
            throw new ChatExceptions(R.MUST_BE_LOGED_IN);
        }else{
            String user = getCurrentUser();
            if(!"".equals(msg) && !R.INPUT_PLACEHOLDER.equals(msg)){

                msgArea.append(String.format("%s: %s\n", user, msg));
                fldInput.setText("");

            }else{
                throw new ChatExceptions(R.NOT_EMPTY_MSG_ALLOWED);
            }
        }
    }
    
    
}
