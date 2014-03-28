/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package punto3;

import punto2.guihelpers.GBHelper;
import punto2.guihelpers.Gap;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author invitado
 */
public class GUI extends JFrame implements ActionListener, KeyListener{
    
    private JLabel lblMainWord, lblMainWordTopic, lblMainWordLength, lblLifes, lblHangman;
    private Word currentWord;
    private JTextArea txtWrongLetters;
    private JButton btnKeyBoard[];
    private int lifes = 7;
    private final int totalLifes = 7;
    private final String[] keyLetters;
    private ImageIcon hangman;
   
    public GUI() {
        
        super(R.S_TITULO);
        setLayout(new BorderLayout(R.I_HGAP, R.I_VGAP));
                
        this.keyLetters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        
        add(pnlMain(), BorderLayout.CENTER);
        add(pnlRightInfo(), BorderLayout.EAST);
        add(pnlKeyboard(), BorderLayout.SOUTH);
        
        initMatch();
        
        this.addKeyListener(this);
        
        //setSize(300,300);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initMatch(){
        this.lifes = 7;
        Word tmp = Punto3.bag.getRandomWord();
        
        try {
            currentWord = (Word) tmp.clone();
            currentWord.resetWord();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lblMainWord.setText( currentWord.toDashes() );
        lblMainWord.setToolTipText( currentWord.getWord() );
        lblMainWordTopic.setText( String.format(R.S_TEMA , currentWord.getTopic()) );
        lblMainWordLength.setText(String.format(R.S_LONGITUD, currentWord.lenght()));
        
        lblHangman.setIcon(new ImageIcon( getHangmanImage( totalLifes - this.lifes ) ));
    }
    
    private JPanel pnlMain(){
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());
        pnl.setBorder(BorderFactory.createEmptyBorder(R.I_HGAP,R.I_HGAP,R.I_HGAP,R.I_HGAP));
        
        lblMainWord = new JLabel();
        
        lblHangman = new JLabel();
        
        GBHelper pos = new GBHelper();
        
        pnl.add(lblMainWord, pos.expandW().align(GBHelper.CENTER));
        pnl.add(new Gap(R.I_VGAP), pos.nextRow());
        pnl.add(lblHangman, pos.nextRow().expandH());
        
        return pnl;
    }
    
    private JPanel pnlRightInfo(){
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());
        pnl.setBorder(BorderFactory.createTitledBorder(R.S_INFORMACION));
        
        lblLifes = new JLabel( String.format(R.S_VIDAS, this.lifes) );
        lblMainWordTopic = new JLabel();
        lblMainWordLength = new JLabel();
        
        txtWrongLetters = new JTextArea(3, 10);
        txtWrongLetters.setEditable(false);
        txtWrongLetters.setFocusable(false);
        
        GBHelper pos = new GBHelper();
        
        pnl.add(lblLifes);
        pnl.add( new Gap(R.I_VGAP), pos.nextRow());
        pnl.add(lblMainWordTopic, pos.nextRow());
        pnl.add( new Gap(R.I_VGAP), pos.nextRow());
        pnl.add(lblMainWordLength, pos.nextRow());
        pnl.add( new Gap(R.I_VGAP), pos.nextRow() );
        pnl.add(txtWrongLetters, pos.nextRow().expandH());
        
        return pnl;
    }
    
    private JPanel pnlKeyboard(){
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(4, 7, R.I_HGAP, R.I_VGAP));
        
        btnKeyBoard = new JButton[keyLetters.length];
        for (int i = 0; i < keyLetters.length; i++) {
            String s = keyLetters[i];
            btnKeyBoard[i] = new JButton(s);
            btnKeyBoard[i].addActionListener(this);
            btnKeyBoard[i].setActionCommand("Letra"+s);
            btnKeyBoard[i].setFocusable(false);
            pnl.add(btnKeyBoard[i]);
        }
        
        return pnl;
    }
    
    private void revealLetter(char letter){
        int times = currentWord.hasLetter(letter);
        
        if( times > 0 ){
            lblMainWord.setText( currentWord.toDashes() );
        }else{
            this.lifes--;
            lblLifes.setText( String.format(R.S_VIDAS, this.lifes) );
            
            lblHangman.setIcon(new ImageIcon( getHangmanImage( totalLifes - lifes ) ));
            
            if(this.lifes == 0){
                currentWord.resetWord();
                int option = JOptionPane.showConfirmDialog(this, String.format(R.S_PERDER, currentWord.getWord()), R.S_LOOSER, JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    initMatch();
                }
            }
        }
        
        if(currentWord.wasGuessed()){
            currentWord.resetWord();
            int option = JOptionPane.showConfirmDialog(this, String.format(R.S_GANAR, currentWord.getWord(), this.lifes, this.totalLifes), R.S_WINNER, JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                initMatch();
            }
        }
        
        txtWrongLetters.setText(currentWord.getWrongLetters());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        
        String character = "" + e.getKeyChar();
        
        if( character.matches("[a-zA-Z]") ){
            this.revealLetter( e.getKeyChar() );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().matches("Letra[A-Z]")){
            String tmp = e.getActionCommand().replaceAll("Letra", "");
            this.revealLetter( tmp.toLowerCase().charAt(0) );
        }
    }
    
    public URL getHangmanImage(int index){
        URL resource = this.getClass().getResource("images/hangman" + index + ".png");
        return resource;
    }
    
}
