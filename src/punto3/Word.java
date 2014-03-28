/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package punto3;

import java.util.Arrays;

/**
 *
 * @author Julian Andres Cantillo || cod. 1431263
 */
public class Word implements Cloneable{
    
    private final String word;
    private final String topic;
    private final int lenght;
    private StringBuffer correctLetters, wrongLetters;

    /**
     * Crea una palabra con tema al cual pertenece
     * @param word
     * @param topic
     */
    public Word(String word, String topic) {
        this.word = word.toLowerCase();
        this.topic = topic;
        
        String tmp = word.replaceAll("\\s","");
        this.lenght = tmp.length();
        
        correctLetters = new StringBuffer();
        wrongLetters = new StringBuffer();
    }

    public String getWord() {
        return word;
    }
    
    public int lenght(){
        return this.lenght;
    }

    public String getTopic() {
        return topic;
    }
    
    public String toLetters(){
        //Se quitan los espacios en blanco
        String tmp = word.replaceAll("\\s","");
        //Se organiza en orden alfabetico
        char[] tmpChar = tmp.toCharArray();
        Arrays.sort(tmpChar);
        tmp = new String(tmpChar);
        //Se quitan los caracteres repetidos
        tmp = tmp.replaceAll("(.)\\1{1,}", "$1");
        
        return tmp;
    }
    
    public String toDashes(){
        String dashes = "";
        char[] wordChars = this.word.toCharArray();
        
        for (int i = 0; i < wordChars.length; i++) {
            char c = wordChars[i];
            if( correctLetters.indexOf( String.valueOf(c) ) >= 0 ){
                dashes += " " + c + " ";
            }else if(" ".equals("" + c)){
                dashes += "   ";
            }else{
                dashes += " _ ";
            }
        }
        
        return dashes;
    }
    
    public int hasLetter(char letter){
        int times = 0;
        
        
        char[] wordChars = this.word.toCharArray();
        
        for (int i = 0; i < wordChars.length; i++) {
            char c = wordChars[i];
            //Si el caracter actual de la palabra es igual al caracter que se valida
            if(letter == c){
                times++;
                //Revisamos si la letra ya está en el listado de letras correctas
                if (correctLetters.indexOf( letter + "" ) < 0){
                    correctLetters.append(letter);
                }
            }
        }
        //Si no hubo ninguna concordancia se agrega la letra a las letras que no estan
        if(times == 0 && wrongLetters.indexOf( letter + "" ) < 0){
            wrongLetters.append(letter);
        }
        
        return times;
    }
    
    public boolean wasGuessed(){
        String tmp = toLetters();
        return correctLetters.length() == tmp.length();
    }
    
    public void resetWord(){
        this.correctLetters = new StringBuffer();
        this.wrongLetters = new StringBuffer();
        wrongLetters.delete(0, lenght);
    }
    
    public String getWrongLetters(){
        String letters = "";
        
        for (int i = 0; i < wrongLetters.length(); i++) {
            char c = wrongLetters.charAt(i);
            letters += c + "\n";
        }
        
        return letters;
    }

    @Override
    public String toString() {
        return  word;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }  
    
}
