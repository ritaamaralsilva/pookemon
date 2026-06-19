package game;

import entities.*;
import items.BattleConsumable;
import items.Consumable;
import items.Potion;
import items.TrainerItem;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Trainer player; // atributo da classe Game

    public void pookemon() { // metodo do jogo, instancio aqui todos os objetos
        Scanner userInput = new Scanner(System.in);
        System.out.println("Olá! Bem vindo ao POOKÉMON!");
        System.out.print("Como te chamas? ");
        String userPlayerName = userInput.next();
        System.out.print("Qual o teu sexo? ");
        String userGenderName = userInput.next();

        // pokemon starter
        Bulbasaur bulbasaur = new Bulbasaur("Bulba", 45, 45, 49, 5, 0);
        Charmander charmander = new Charmander("Chaaaar", 39, 39, 52, 5, 0);
        Squirtle squirtle = new Squirtle("Squirrrr", 44, 44, 48, 5, 0);
        Pikachu pikachu = new Pikachu("Pika Pika", 35, 35, 55, 5, 0);

        // user vai ter de criar o seu Trainer aqui, usar o metodo trainer.showDetails() para mostrar os detalhes no fim
        player = new Trainer(userPlayerName, userGenderName, 1000, null);
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

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Pewter City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar o Brock, o Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym pewterCityGym = new Gym("Pewter City Gym", 12, 1386);
                    pewterCityGym.addPokemon(new PokemonWild("Geodude", 50, 50, 80, 12, 300, 500));
                    pewterCityGym.addPokemon(new PokemonWild("Onix", 35, 35, 45, 14, 500, 1000));

                    if (player.getPokemonInUse().getLevel() < pewterCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + pewterCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Brock!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : pewterCityGym.getPokemonGym()) {
                        System.out.println("Brock enviou o " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste o Brock e ganhaste o Boulder Badge!");
                        player.addCoins(pewterCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop pewterCityShop = new PokeShop("Pewter Shop");
                    pewterCityShop.addItem(new Potion("Potion", 300, 20));
                    pewterCityShop.addItem(new Potion("Super Potion", 700, 50));
                    pewterCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    pewterCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    pewterCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    pewterCityShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    pewterCityShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    pewterCityShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    pewterCityShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

                    ArrayList<TrainerItem> shopItems = pewterCityShop.getRandomItems();

                    System.out.println("Itens disponíveis hoje:");
                    for (int i = 0; i < shopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + shopItems.get(i).getName()
                                + " - " + shopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair da loja");
                    System.out.print("O que queres comprar? ");
                    int shopChoice = input.nextInt();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            System.out.println("Compraste " + chosen.getName() + "! Tens agora " + player.getCoins() + " coins.");
                        } else {
                            System.out.println("Não tens coins suficientes! Tens apenas " + player.getCoins() + " coins.");
                        }
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                case 3:
                    System.out.println("Escolheste ir treinar o teu Pookémon!");
                    boolean training = true;

                    while (training) {
                        // instanciar pokemon wild para os treinos
                        PokemonWild geodude = new PokemonWild("Geodude", 50, 50, 10, 6, 300, 200);
                        PokemonWild onix = new PokemonWild("Onix", 60, 60, 25, 7, 500, 300);
                        PokemonWild rhyhorn = new PokemonWild("Rhyhorn", 70, 70, 30, 7, 700, 350);
                        PokemonWild pidgey = new PokemonWild("Pidgey", 30, 30, 20, 5, 250, 200);
                        PokemonWild ratatta = new PokemonWild("Ratatta", 30, 30, 10, 5, 250, 200);
                        PokemonWild metapod = new PokemonWild("Metapod", 60, 60, 0, 7, 350, 150);
                        PokemonWild caterpie = new PokemonWild("Caterpie", 40, 40, 15, 5, 250, 200);
                        PokemonWild spearow = new PokemonWild("Spearow", 40, 40, 15, 6, 300, 250);

                        PokemonWild[] wildPokemonPewterCity = {geodude, onix, rhyhorn, pidgey, ratatta, metapod, caterpie, spearow};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonPewterCity[random.nextInt(wildPokemonPewterCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player.getItemsBag());

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nO que queres fazer agora?");
                            System.out.println("1. Continuar a treinar");
                            System.out.println("2. Voltar ao menu da cidade");
                            int trainingChoice = input.nextInt();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Bem-vindo ao PookéCenter!");
                    System.out.println("A Nurse Joy trata do teu Pookémon...");
                    player.getPokemonInUse().healPokemon();
                    player.showDetails();
                    break;
                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
    }
}

