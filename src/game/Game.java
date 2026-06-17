package game;

import entities.*;

public class Game {
    public void pookemon () { // metodo do jogo, instancio aqui todos os objetos
        System.out.println("Ola. bem vindo ao POOKEMON");

        // pokemon starter
        Bulbasaur bulbasaur = new Bulbasaur("Bulba", 60, 60, 40, 5);
        Charmander charmander =  new Charmander("Chaaaar", 50, 50, 45, 5);
        Squirtle squirtle = new Squirtle("Squirrrr", 55, 55, 45, 5);
        Pikachu pikachu = new Pikachu("Pika Pika", 65, 65, 45, 5);

        //
        
    }
}
