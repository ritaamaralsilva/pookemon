package game;

import entities.*;
import java.util.Scanner;

public class Game {
    public void pookemon() { // metodo do jogo, instancio aqui todos os objetos
        Scanner userInput = new Scanner(System.in);
        System.out.println("Olá! Bem vindo ao POOKÉMON!");
        System.out.print("Como te chamas? ");
        String userPlayerName = userInput.next();
        System.out.print("Qual o teu sexo? ");
        String userGenderName = userInput.next();

        // pokemon starter
        Bulbasaur bulbasaur = new Bulbasaur("Bulba", 60, 60, 40, 5);
        Charmander charmander = new Charmander("Chaaaar", 50, 50, 55, 5);
        Squirtle squirtle = new Squirtle("Squirrrr", 55, 55, 45, 5);
        Pikachu pikachu = new Pikachu("Pika Pika", 65, 65, 45, 5);

        // user vai ter de criar o seu Trainer aqui, usar o metodo trainer.showDetails() para mostrar os detalhes no fim
        Trainer player = new Trainer(userPlayerName, userGenderName, 1000, null);
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
                    System.out.println("Escolheste o Bulbasaur! ");
                    player.setPokemonInUse(bulbasaur);
                } else if (starterPokemonChoice == 2) {
                    System.out.println("Escolheste o Charmander! ");
                    player.setPokemonInUse(charmander);
                } else {
                    System.out.println("Escolheste o Squirtle! ");
                    player.setPokemonInUse(squirtle);
                }
                break;

            case 2:
                System.out.println("... Ups... Perdeste o autocarro, o pneu da bicicleta está furado e não há ubers por perto, mas ganhaste um Pikachu, nem tudo é mau... Mas cuidade que ele dá choques! ");
                player.setPokemonInUse(pikachu);
                break;
        }
        player.showDetails();

        System.out.println("Agora sim, começa a tua jornada para te tornares o próximo Pookémon Champion.");
        System.out.println("... mas para isso vais precisar de conquistar todos os crachás dos ginásios da Liga de Kanto.");
        System.out.println("Próxima paragem: Pewter City, a cidade dos penedos e rochedos, espero que sejas forte na escalada!");
    }

    public void pewterCity() {
        Scanner input = new Scanner(System.in);

        // instanciar pokemon wild para os treinos
        PokemonWild geodude = new PokemonWild("Geodude", 50, 50, 10, 6, 300);
        PokemonWild onix = new PokemonWild("Onix", 60, 60, 25, 7, 500);
        PokemonWild rhyhorn = new PokemonWild("Rhyhorn", 70, 70, 30, 7, 700);
        PokemonWild pidgey = new PokemonWild("Pidgey", 30, 30, 20, 5, 250);
        PokemonWild ratatta = new PokemonWild("Ratatta", 30, 30, 10, 5, 250);
        PokemonWild metapod = new PokemonWild("Metapod", 60, 60, 0, 7, 350);
        PokemonWild caterpie = new PokemonWild("Caterpie", 40, 40, 15, 5, 250);
        PokemonWild spearrow = new PokemonWild("Spearrow", 40, 40, 15, 6, 300);

        System.out.println("\nBem-vindo a Pewter City!");
        System.out.println("Chegaste a Pewter City, o que vais fazer a seguir? ");
        System.out.println("1. Enfrentar o Brock, o Gym Leader");
        System.out.println("2. Comprar itens na Pooke Shop");
        System.out.println("3. Treinar o teu Pookémon");
        int cityChoice = input.nextInt();
        switch (cityChoice) {
            case 1:
                System.out.println("Escolheste enfrentar o Gym Leader Brock! Boa sorte!");
                break;
            case 2:
                System.out.println("Escolheste ir à loja abastecer!");
                break;
            case 3:
                System.out.println("Escolheste ir treinar o teu Pookémon!");
                break;
            default:
                System.out.println("Escolheste uma opção inválida!");
                return;
        }
    }
}


