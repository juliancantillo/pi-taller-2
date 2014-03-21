/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto3;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class Punto3 {
    
    private GUI gui;
    public static WordList bag;

    public Punto3() {
        bag = new WordList();
        
        bag.addWord(new Word("Cabra", "Animales"));
        bag.addWord(new Word("Vaca", "Animales"));
        bag.addWord(new Word("Ballena", "Animales"));
        bag.addWord(new Word("Mantis Religiosa", "Animales"));
        bag.addWord(new Word("Lobo Marino", "Animales"));
        bag.addWord(new Word("Pascal", "Lenguajes de Programación"));
        bag.addWord(new Word("c plus plus", "Lenguajes de Programación"));
        bag.addWord(new Word("python", "Lenguajes de Programación"));
        bag.addWord(new Word("dart", "Lenguajes de Programación"));
        bag.addWord(new Word("Ernesto Samper", "Presidentes de Colombia"));
        bag.addWord(new Word("Andres Pastrana", "Presidentes de Colombia"));
        bag.addWord(new Word("Misael Pastrana", "Presidentes de Colombia"));
        bag.addWord(new Word("Alvaro Uribe", "Presidentes de Colombia"));
        bag.addWord(new Word("Valle del Cauca", "Departamento de Colombia"));
        bag.addWord(new Word("Amazonas", "Departamento de Colombia"));
        bag.addWord(new Word("Nariño", "Departamento de Colombia"));
        bag.addWord(new Word("Cauca", "Departamento de Colombia"));
        bag.addWord(new Word("Disco Duro", "Partes del Computador"));
        bag.addWord(new Word("Procesador", "Partes del Computador"));
        bag.addWord(new Word("Tarjeta de Video", "Partes del Computador"));
        bag.addWord(new Word("Tarjeta Madre", "Partes del Computador"));
        bag.addWord(new Word("Monitor", "Partes del Computador"));
        bag.addWord(new Word("Google", "Empresas de IT"));
        bag.addWord(new Word("Facebook", "Empresas de IT"));
        bag.addWord(new Word("Oracle", "Empresas de IT"));
        bag.addWord(new Word("MongoDB", "Motor de Bases de Datos"));
        bag.addWord(new Word("MySQL", "Motor de Bases de Datos"));
        bag.addWord(new Word("Postgres", "Motor de Bases de Datos"));
        
        gui = new GUI();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Punto3 obj = new Punto3();
    }
}
