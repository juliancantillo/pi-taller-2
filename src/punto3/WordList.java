/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class WordList {
    private ArrayList<Word> wordsBag;

    public WordList() {
        wordsBag = new ArrayList();
    }
    
    public void addWord(Word w){
        this.wordsBag.add(w);
    }
    
    public Word getRandomWord(){
        return getRandom(wordsBag);
    }
    
    public Word getRandomWord(String topic){
        ArrayList<Word> tempWordsBag;
        tempWordsBag = new ArrayList();
        
        for (Word word : this.wordsBag) {
            if( word.getTopic().equals(topic) ){
                tempWordsBag.add(word);
            }
        }
        
        return getRandom(tempWordsBag);
    }
    
    private Word getRandom(ArrayList al){
        Random r = new Random();
        int R = r.nextInt( al.size() - 0);
        
        return (Word) al.get(R);
    }
    
}
