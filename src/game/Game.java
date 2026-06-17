package game;

import entities.*;
import java.util.Scanner;

public class Game {
    public void pookemon () { // metodo do jogo, instancio aqui todos os objetos
        Scanner userInput = new Scanner(System.in);
        System.out.println("Olá! Bem vindo ao POOKÉMON!");
        System.out.print("Como te chamas? ");
        String userPlayerName = userInput.next();
        System.out.print("Qual o teu sexo? ");
        String userGenderName = userInput.next();



        // pokemon starter
        Bulbasaur bulbasaur = new Bulbasaur("Bulba", 60, 60, 40, 5);
        Charmander charmander =  new Charmander("Chaaaar", 50, 50, 55, 5);
        Squirtle squirtle = new Squirtle("Squirrrr", 55, 55, 45, 5);
        Pikachu pikachu = new Pikachu("Pika Pika", 65, 65, 45, 5);

        //


        // user vai ter de criar o seu Trainer aqui, usar o metodo trainer.showDetails() para mostrar os detalhes no fim
        Trainer player = new Trainer(userPlayerName, userGenderName, 1000,  null);
;

        System.out.println("... São 7:30h da manhã, o teu despertador está a tocar. O que vais fazer?");

        // criar booleano de acordar quando o despertador toca, user escolhe.
        // se escolher acordar, escolhe depois um dos 3 pokemon (bulbasaur, charmander ou squirtle)
        // se escolher não acordar, vai ficar com o pikachu porque os outros deixam de estar disponíveis porque o jogador se atrasa
        System.out.println("1. Acordar");
        System.out.println("2. So mais 5 minutos");
        System.out.println("O que vais fazer: ");
        int wakeUp = userInput.nextInt();


        switch (wakeUp) {
            case 1:
                System.out.println("Chegas ao laboratório do Professor Oak a horas. Há 3 Pookébolas: ");
                System.out.println("1. Bulbasaur");
                System.out.println("2. Charmander");
                System.out.println("3. Squirtle");
                System.out.print("Escolhe: ");
                int starterPokemonChoice = userInput.nextInt();

                if (starterPokemonChoice == 1) {
                    System.out.println("Escolheste o Bulba! ");
                    player.setPokemonInUse(bulbasaur);
                }
                else if  (starterPokemonChoice == 2) {
                    System.out.println("Escolheste o Chaaaar! ");
                    player.setPokemonInUse(charmander);
            }
                else {
                    System.out.println("Escolheste o Squirrrr! ");
                    player.setPokemonInUse(squirtle);
                }
                break;

            case 2:
                System.out.println("... Ups... Perdeste o autocarro, o pneu da bicicleta está furado e não há ubers perto, mas ganhaste um Pika Pika, nem tudo é mau! ");
                player.setPokemonInUse(pikachu);
                break;
        }
        player.showDetails();
    }
}
