package game;

import entities.*;
import entities.secretCave.AbraEncounter;
import entities.secretCave.TeamRocketAmbush;
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
        Bulbasaur bulbasaur = new Bulbasaur("Bulba", 45, 45, 49, 65, 1, 1, 5, 0);
        Charmander charmander = new Charmander("Chaaaar", 39, 39, 52, 50, 1, 1, 5, 0);
        Squirtle squirtle = new Squirtle("Squirrrr", 44, 44, 48, 50, 1, 1, 5, 0);
        Pikachu pikachu = new Pikachu("Pika Pika", 35, 35, 35, 55, 1, 1, 5, 0);

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

        pewterCity();
    }
    // funcoes das 8 cidades
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
                    pewterCityGym.addPokemon(new PokemonWildGymBrock("Geodude", 50, 50, 80, 30, 2, 2, 12, 300, 500));
                    pewterCityGym.addPokemon(new PokemonWildGymBrock("Onix", 35, 35, 45, 30, 2,2, 14, 500, 1000));

                    if (player.getPokemonInUse().getLevel() < pewterCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + pewterCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Brock!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : pewterCityGym.getPokemonGym()) {
                        System.out.println("Brock enviou o " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
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
                        PokemonWild geodude = new PokemonWild("Geodude", 50, 50, 10, 30, 1, 1, 6, 300, 200);
                        PokemonWild onix = new PokemonWild("Onix", 60, 60, 25, 30, 1, 1, 7, 500, 300);
                        PokemonWild rhyhorn = new PokemonWild("Rhyhorn", 70, 70, 30, 30, 1, 1, 7, 700, 350);
                        PokemonWild pidgey = new PokemonWild("Pidgey", 30, 30, 20, 35, 1, 1, 5, 250, 200);
                        PokemonWild ratatta = new PokemonWild("Ratatta", 30, 30, 10, 25, 1, 1, 5, 250, 200);
                        PokemonWild metapod = new PokemonWild("Metapod", 60, 60, 0, 0, 0, 0, 7, 350, 150);
                        PokemonWild caterpie = new PokemonWild("Caterpie", 40, 40, 15, 25, 1, 1, 5, 250, 200);
                        PokemonWild spearow = new PokemonWild("Spearow", 40, 40, 15, 31, 1, 1, 6, 300, 250);

                        PokemonWild[] wildPokemonPewterCity = {geodude, onix, rhyhorn, pidgey, ratatta, metapod, caterpie, spearow};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonPewterCity[random.nextInt(wildPokemonPewterCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
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
                    player.getPokemonInUse().resetSpecialAttackUses(); // volta a ter special attack uses
                    player.showDetails();
                    break;
                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
        ceruleanCity(); // se ganhar o pewter city gym, jogo avança para ceruleanCity
    }
    public void ceruleanCity () {
        Scanner input = new Scanner(System.in);

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Cerulean City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar a Misty, a Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym ceruleanCityGym = new Gym("Cerulean City Gym", 18, 2142);
                    ceruleanCityGym.addPokemon(new PokemonWildGymMisty("Staryu", 52, 52, 45, 70, 2, 2, 18, 567, 635));
                    ceruleanCityGym.addPokemon(new PokemonWildGymMisty("Starmie", 63, 63, 75, 100, 3, 3, 21, 1399, 1050));

                    if (player.getPokemonInUse().getLevel() < ceruleanCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + ceruleanCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar a Gym Leader Misty!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : ceruleanCityGym.getPokemonGym()) {
                        System.out.println("Misty enviou a " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste a Misty e ganhaste o Cascade Badge!");
                        player.addCoins(ceruleanCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop ceruleanCityShop = new PokeShop("Cerulean Shop");
                    ceruleanCityShop.addItem(new Potion("Potion", 300, 20));
                    ceruleanCityShop.addItem(new Potion("Super Potion", 700, 50));

                    ceruleanCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    ceruleanCityShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    ceruleanCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    ceruleanCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    ceruleanCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    ceruleanCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false)); // X Defense a implementar, em vez de alterar stat de defesa que nao existe como atributo, vai afetar no strength do pokemon inimigo e reduz 10, ou seja, vai afetar pokemon inimigo

                    ceruleanCityShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    ceruleanCityShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    ceruleanCityShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    ceruleanCityShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

                    ArrayList<TrainerItem> shopItems = ceruleanCityShop.getRandomItems(); // randomiza os itens na loja

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
                        PokemonWild spearow   = new PokemonWild("Spearow",  53, 53, 60, 31, 1, 1, 13, 300, 350);
                        PokemonWild rattata   = new PokemonWild("Rattata",  44, 44, 56, 25, 1, 1, 13, 250, 200);
                        PokemonWild pidgey    = new PokemonWild("Pidgey",   51, 51, 45, 35, 1, 1, 12, 250, 300);
                        PokemonWild oddish    = new PokemonWild("Oddish",   55, 55, 50, 75, 1, 1, 12, 350, 450);
                        PokemonWild bellsprout = new PokemonWild("Bellsprout", 60, 60, 75, 70, 1, 1, 13, 350, 350);
                        PokemonWild venonat   = new PokemonWild("Venonat",  68, 68, 55, 40, 1, 1, 13, 300, 300);
                        PokemonWild magikarp  = new PokemonWild("Magikarp", 25, 25, 0, 15,  0, 0,5, 100, 100);
                        PokemonWild poliwag   = new PokemonWild("Poliwag",  52, 52, 50, 40, 1, 1, 15, 350, 450);
                        PokemonWild goldeen   = new PokemonWild("Goldeen",  57, 57, 67, 50, 1, 1,15, 400, 350);
                        PokemonWild psyduck   = new PokemonWild("Psyduck",  65, 65, 52, 50, 2, 2, 20, 450, 600);
                        PokemonWild krabby    = new PokemonWild("Krabby",   45, 45, 105, 25, 2, 2, 20, 500, 350);


                        PokemonWild[] wildPokemonCeruleanCity = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCeruleanCity[random.nextInt(wildPokemonCeruleanCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
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
                    player.getPokemonInUse().resetSpecialAttackUses();
                    player.showDetails();
                    break;
                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
        vermilionCity();
    }
    public void vermilionCity () {
        Scanner input = new Scanner(System.in);

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Vermilion City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar o Lt. Surge, a Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym vermilionCityGym = new Gym("Vermilion City Gym", 21, 2471);
                    vermilionCityGym.addPokemon(new PokemonWildGymSurge("Voltorb", 40, 40, 35, 55, 2, 2, 21, 612, 635));
                    vermilionCityGym.addPokemon(new PokemonWildGymSurge("Pikachu", 45, 45, 55, 50, 2, 2, 18, 350, 500));
                    vermilionCityGym.addPokemon(new PokemonWildGymSurge("Raichu",  60, 60, 90, 90, 2, 2, 24, 800, 1200));

                    if (player.getPokemonInUse().getLevel() < vermilionCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + vermilionCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Lt. Lurge!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : vermilionCityGym.getPokemonGym()) {
                        System.out.println("Lt. Lurge enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste o Lt. Surge e ganhaste o Thunder Badge!");
                        player.addCoins(vermilionCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop vermilionCityShop = new PokeShop("Vermilion Shop");
                    vermilionCityShop.addItem(new Potion("Golden Milk", 300, 30));
                    vermilionCityShop.addItem(new Potion("Super Potion", 700, 50));

                    vermilionCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    vermilionCityShop.addItem(new Consumable("Lugia's Secret", 1200, 100, 40, true, true));
                    vermilionCityShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    vermilionCityShop.addItem(new Consumable("Fast Growth", 1000, 0, 0, false, true));
                    vermilionCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    vermilionCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    vermilionCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    vermilionCityShop.addItem(new Consumable("Rare Candy", 1000, 0, 0, false, true));
                    vermilionCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false)); // X Defense a implementar, em vez de alterar stat de defesa que nao existe como atributo, vai afetar no strength do pokemon inimigo e reduz 10, ou seja, vai afetar pokemon inimigo

                    vermilionCityShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    vermilionCityShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    vermilionCityShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    vermilionCityShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

                    ArrayList<TrainerItem> shopItems = vermilionCityShop.getRandomItems(); // randomiza os itens na loja

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
                        PokemonWild ekans      = new PokemonWild("Ekans",      48,  48,  60,  50, 1, 1, 18, 350, 300); // Red exclusivo
                        PokemonWild sandshrew  = new PokemonWild("Sandshrew",  58,  58,  75,  30, 1, 1, 18, 350, 300); // Blue exclusivo
                        PokemonWild drowzee    = new PokemonWild("Drowzee",    68,  68,  48,  93, 1, 1, 20, 400, 400);
                        PokemonWild voltorb    = new PokemonWild("Voltorb",    50,  50,  30,  55, 1, 1, 20, 400, 350);
                        PokemonWild meowth     = new PokemonWild("Meowth",     50,  50,  45,  40, 1, 1, 18, 300, 300);
                        PokemonWild diglett    = new PokemonWild("Diglett",    22,  22,  55,  45, 1, 1, 19, 350, 300);
                        PokemonWild jigglypuff = new PokemonWild("Jigglypuff", 125, 125, 45,  25, 1, 1, 18, 300, 250);
                        PokemonWild doduo      = new PokemonWild("Doduo",       55,  55,  85,  35, 1, 1, 20, 350, 300); // Route 22 e arredores, nível 20
                        PokemonWild mankey     = new PokemonWild("Mankey",      50,  50,  80,  35, 1, 1, 18, 350, 300); // Route 5/6, agressivo fisicamente
                        PokemonWild growlithe  = new PokemonWild("Growlithe",   63,  63,  70,  70, 1, 1, 19, 400, 400); // Red exclusivo, Route 7/8
                        PokemonWild tentacool  = new PokemonWild("Tentacool",   52,  52,  40, 100, 2, 2, 20, 450, 400);
                        PokemonWild shellder   = new PokemonWild("Shellder",    42,  42,  65,  45, 2, 2, 20, 400, 350);
                        PokemonWild horsea     = new PokemonWild("Horsea",      40,  40,  40,  70, 2, 2, 20, 400, 350); // Special alto para o nível
                        PokemonWild seel       = new PokemonWild("Seel",        70,  70,  45,  70, 2, 2, 21, 450, 400); // aguenta bem, Special decente
                        PokemonWild staryu     = new PokemonWild("Staryu",      52,  52,  45,  70, 2, 2, 21, 450, 400); // aparece em água em várias zonas de Kanto

                        PokemonWild[] wildPokemonVermilionCity = {
                                ekans, sandshrew, drowzee, voltorb, meowth,
                                diglett, jigglypuff, doduo, mankey, growlithe,
                                tentacool, shellder, horsea, seel, staryu
                        };
                        Random random = new Random();
                        PokemonWild enemy = wildPokemonVermilionCity[random.nextInt(wildPokemonVermilionCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();

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
                    player.getPokemonInUse().resetSpecialAttackUses();
                    player.showDetails();
                    break;
                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
        celadonCity();
    }
    public void celadonCity () {
        Scanner input = new Scanner(System.in);

        // desbloqueia secret cave com 3+ crachás
        if (player.getGymBadge() >= 3) {
            System.out.println("\n🌑 No caminho para Celadon City, reparas numa entrada estranha atrás de uma cascata...");
            System.out.println("Queres investigar?");
            System.out.println("1. Entrar na Caverna Secreta");
            System.out.println("2. Seguir para Celadon City");
            int caveDecision = input.nextInt();
            if (caveDecision == 1) {
                secretCave();
            }
        }

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Celadon City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar a Erika, a Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym celadonCityGym = new Gym("Celadon City Gym", 27, 2831);
                    celadonCityGym.addPokemon(new PokemonWildGymErika("Victreebel", 80, 80, 105, 100, 2, 2, 29, 1423, 892));
                    celadonCityGym.addPokemon(new PokemonWildGymErika("Vileplume", 75, 75, 80, 100, 2, 2, 29, 1842, 1500));

                    if (player.getPokemonInUse().getLevel() < celadonCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + celadonCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar a Gym Leader Erika!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : celadonCityGym.getPokemonGym()) {
                        System.out.println("Erika enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste a Erika e ganhaste o Rainbow Badge!");
                        player.addCoins(celadonCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop celadonCityShop = new PokeShop("Celadon Shop");
                    celadonCityShop.addItem(new Potion("Super Potion", 700, 50));
                    celadonCityShop.addItem(new Potion("Hyper Potion", 1200, 100));
                    celadonCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    celadonCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    celadonCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    celadonCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false));
                    celadonCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    celadonCityShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    celadonCityShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    celadonCityShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    celadonCityShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

                    ArrayList<TrainerItem> shopItems = celadonCityShop.getRandomItems(); // randomiza os itens na loja

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
                        PokemonWild vulpix     = new PokemonWild("Vulpix",     48,  48,  41,  65, 1, 1, 25, 500, 400); // Red exclusivo
                        PokemonWild growlithe2 = new PokemonWild("Growlithe",  67,  67,  70,  70, 1, 1, 27, 550, 450); // Blue exclusivo
                        PokemonWild ponyta     = new PokemonWild("Ponyta",     73,  73,  85,  65, 1, 1, 26, 500, 400);
                        PokemonWild doduo2     = new PokemonWild("Doduo",      59,  59,  85,  35, 1, 1, 25, 400, 350);
                        PokemonWild meowth2    = new PokemonWild("Meowth",     54,  54,  45,  40, 1, 1, 24, 350, 300);
                        PokemonWild persian    = new PokemonWild("Persian",    70,  70,  70,  65, 1, 1, 28, 600, 500);
                        PokemonWild scyther    = new PokemonWild("Scyther",    80,  80, 110,  55, 2, 2, 28, 700, 600); // raro, Safari Zone
                        PokemonWild kangaskhan = new PokemonWild("Kangaskhan", 115, 115,  95,  40, 2, 2, 28, 800, 700); // Safari Zone
                        PokemonWild tauros     = new PokemonWild("Tauros",     95,  95, 100,  70, 2, 2, 27, 750, 650); // Safari Zone
                        PokemonWild lickitung  = new PokemonWild("Lickitung",  90,  90,  55,  60, 2, 2, 26, 600, 500);
                        PokemonWild slowpoke   = new PokemonWild("Slowpoke",   90,  90,  65,  40, 2, 2, 25, 500, 400);
                        PokemonWild krabby2    = new PokemonWild("Krabby",     49,  49, 105,  25, 2, 2, 25, 550, 400);
                        PokemonWild poliwhirl  = new PokemonWild("Poliwhirl",  70,  70,  65,  50, 2, 2, 27, 600, 500);
                        PokemonWild horsea2    = new PokemonWild("Horsea",     44,  44,  40,  70, 2, 2, 25, 450, 400);
                        PokemonWild goldeen2   = new PokemonWild("Goldeen",    61,  61,  67,  50, 2, 2, 25, 450, 400);

                        PokemonWild[] wildPokemonCeladonCity = {
                                vulpix, growlithe2, ponyta, doduo2, meowth2,
                                persian, scyther, kangaskhan, tauros, lickitung,
                                slowpoke, krabby2, poliwhirl, horsea2, goldeen2
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCeladonCity[random.nextInt(wildPokemonCeladonCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
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
        fuchsiaCity();
    }
    public void fuchsiaCity () {
        Scanner input = new Scanner(System.in);

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Fuchsia City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar o Koga, o Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym fuchsiaCityGym = new Gym("Fuchsia City Gym", 35, 3278);
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Koffing", 70, 70, 65, 85, 3, 3, 37, 1200, 635));
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Muk", 137, 137, 98, 71, 3, 3, 39, 1450, 1100));
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Koffing", 76, 76, 63, 88, 3, 3, 37, 1200, 635));
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Weezing", 112, 112, 91, 121, 4, 4, 43, 1950, 2100));

                    if (player.getPokemonInUse().getLevel() < fuchsiaCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + fuchsiaCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Koga!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : fuchsiaCityGym.getPokemonGym()) {
                        System.out.println("Koga enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste o Koga e ganhaste o Soul Badge!");
                        player.addCoins(fuchsiaCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop fuchsiaCityShop = new PokeShop("Fuchsia Shop");
                    fuchsiaCityShop.addItem(new Potion("Hyper Potion", 1200, 100));
                    fuchsiaCityShop.addItem(new Potion("Max Potion", 2500, 999));
                    fuchsiaCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    fuchsiaCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    fuchsiaCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    fuchsiaCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false));
                    fuchsiaCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    fuchsiaCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true)); // 2x stock — fast paced
                    fuchsiaCityShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    fuchsiaCityShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    fuchsiaCityShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    fuchsiaCityShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

                    ArrayList<TrainerItem> shopItems = fuchsiaCityShop.getRandomItems(); // randomiza os itens na loja

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
                        PokemonWild pinsir     = new PokemonWild("Pinsir",      90,  90, 125,  55, 2, 2, 33, 800, 700); // Safari Zone, rival do Scyther
                        PokemonWild rhyhorn2   = new PokemonWild("Rhyhorn",     90,  90, 130,  30, 2, 2, 32, 750, 600);
                        PokemonWild nidorina   = new PokemonWild("Nidorina",    80,  80,  62,  55, 2, 2, 31, 650, 550);
                        PokemonWild nidorino   = new PokemonWild("Nidorino",    81,  81,  72,  57, 2, 2, 31, 650, 550);
                        PokemonWild exeggcute  = new PokemonWild("Exeggcute",   80,  80,  40,  45, 2, 2, 30, 550, 450); // Safari Zone
                        PokemonWild parasect   = new PokemonWild("Parasect",    80,  80,  95,  80, 2, 2, 32, 700, 550);
                        PokemonWild venomoth   = new PokemonWild("Venomoth",    80,  80,  65,  90, 2, 2, 33, 700, 550);
                        PokemonWild weepinbell = new PokemonWild("Weepinbell",  80,  80,  90,  85, 2, 2, 31, 650, 500);
                        PokemonWild gloom      = new PokemonWild("Gloom",       75,  75,  55,  75, 2, 2, 30, 600, 500);
                        PokemonWild ditto      = new PokemonWild("Ditto",       76,  76,  48,  48, 2, 2, 31, 600, 500);
                        PokemonWild tentacruel = new PokemonWild("Tentacruel",  90,  90,  70, 120, 2, 2, 34, 850, 700);
                        PokemonWild slowbro    = new PokemonWild("Slowbro",    100, 100,  75, 100, 2, 2, 33, 800, 650);
                        PokemonWild seadra     = new PokemonWild("Seadra",      75,  75,  65,  95, 2, 2, 32, 750, 600);
                        PokemonWild seaking    = new PokemonWild("Seaking",     95,  95,  92,  65, 2, 2, 33, 750, 600);
                        PokemonWild dewgong    = new PokemonWild("Dewgong",    100, 100,  70,  70, 2, 2, 33, 750, 600);

                        PokemonWild[] wildPokemonFuchsiaCity = {
                                pinsir, rhyhorn2, nidorina, nidorino, exeggcute,
                                parasect, venomoth, weepinbell, gloom, ditto,
                                tentacruel, slowbro, seadra, seaking, dewgong
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonFuchsiaCity[random.nextInt(wildPokemonFuchsiaCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
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
        saffronCity();

    }
    public void saffronCity() {
        Scanner input = new Scanner(System.in);

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Saffron City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar a Sabrina, a Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym saffronCityGym = new Gym("Saffron City Gym", 18, 2142);
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Kadabra", 45, 45, 35, 120, 3, 3, 38, 1800, 2000));
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Mr. Mime", 87, 87, 75, 83, 3, 3, 37, 1600, 1800));
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Venomoth", 107, 107, 74, 71, 3, 3, 38, 1750, 1900));
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Alakazam", 108, 108, 131, 57, 5, 5, 43, 2500, 2500));

                    if (player.getPokemonInUse().getLevel() < saffronCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + saffronCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar a Gym Leader Sabrina!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : saffronCityGym.getPokemonGym()) {
                        System.out.println("Sabrina enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste a Sabrina e ganhaste o Marsh Badge!");
                        player.addCoins(saffronCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop saffronCityShop = new PokeShop("Saffron Shop");
                    saffronCityShop.addItem(new Potion("Max Potion", 2500, 999));
                    saffronCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    saffronCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    saffronCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    saffronCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false));
                    saffronCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    saffronCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    saffronCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true)); // 3x stock
                    saffronCityShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    saffronCityShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    saffronCityShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    saffronCityShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

                    ArrayList<TrainerItem> shopItems = saffronCityShop.getRandomItems(); // randomiza os itens na loja

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
                        PokemonWild kadabra2   = new PokemonWild("Kadabra",     48,  48,  35, 120, 2, 2, 38, 900, 700);
                        PokemonWild haunter    = new PokemonWild("Haunter",     55,  55,  50,  95, 2, 2, 36, 850, 650);
                        PokemonWild gengar     = new PokemonWild("Gengar",      75,  75,  65, 130, 3, 3, 40, 1200, 900); // raro
                        PokemonWild electabuzz = new PokemonWild("Electabuzz",  85,  85,  83,  95, 2, 2, 38, 900, 750);
                        PokemonWild magmar     = new PokemonWild("Magmar",      85,  85,  95,  100, 2, 2, 38, 900, 750);
                        PokemonWild porygon    = new PokemonWild("Porygon",     85,  85,  60,  75, 2, 2, 36, 850, 700);
                        PokemonWild mr_mime    = new PokemonWild("Mr. Mime",    55,  55,  45,  100, 2, 2, 37, 850, 650);
                        PokemonWild jynx       = new PokemonWild("Jynx",        75,  75,  50,  115, 2, 2, 38, 900, 700);
                        PokemonWild persian2   = new PokemonWild("Persian",     74,  74,  70,  65, 2, 2, 37, 700, 600);
                        PokemonWild clefairy   = new PokemonWild("Clefairy",    80,  80,  45,  60, 2, 2, 35, 700, 550);
                        PokemonWild poliwrath  = new PokemonWild("Poliwrath",   100, 100,  95,  70, 3, 3, 40, 1000, 800);
                        PokemonWild gyarados   = new PokemonWild("Gyarados",    130, 130, 125,  100, 3, 3, 42, 1500, 1200); // raro mas devastador
                        PokemonWild lapras     = new PokemonWild("Lapras",      140, 140,  85,  95, 3, 3, 40, 1200, 1000);
                        PokemonWild vaporeon   = new PokemonWild("Vaporeon",    140, 140,  65, 110, 3, 3, 40, 1100, 900);
                        PokemonWild starmie2   = new PokemonWild("Starmie",     80,  80,  75, 100, 3, 3, 40, 1200, 900);

                        PokemonWild[] wildPokemonSaffronCity = {
                                kadabra2, haunter, gengar, electabuzz, magmar,
                                porygon, mr_mime, jynx, persian2, clefairy,
                                poliwrath, gyarados, lapras, vaporeon, starmie2
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonSaffronCity[random.nextInt(wildPokemonSaffronCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
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
                    player.getPokemonInUse().resetSpecialAttackUses();
                    player.showDetails();
                    break;
                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
        cinnabarIsland();
    }
    public void cinnabarIsland() {
        Scanner input = new Scanner(System.in);

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Cinnabar Island, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar o Blaine, o Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym cinnabarIslandGym = new Gym("Cinnabar Island Gym", 45, 2142);
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Growlithe", 65, 65, 70, 70, 4, 4, 42, 1460, 1385));
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Ponyta", 98, 98, 86, 61, 4, 4, 40, 1350, 1200));
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Rapidash", 119, 119, 101, 76, 4, 4, 42, 1750, 1600));
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Arcanine", 154, 154, 126, 91, 5, 5, 47, 1899, 1953));

                    if (player.getPokemonInUse().getLevel() < cinnabarIslandGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + cinnabarIslandGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Blaine!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : cinnabarIslandGym.getPokemonGym()) {
                        System.out.println("Blaine enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste o Blaine e ganhaste o Volcano Badge!");
                        player.addCoins(cinnabarIslandGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop cinnabarIslandShop = new PokeShop("Cinnabar Shop");
                    cinnabarIslandShop.addItem(new Potion("Max Potion", 2500, 999));
                    cinnabarIslandShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    cinnabarIslandShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    cinnabarIslandShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    cinnabarIslandShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false));
                    cinnabarIslandShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    cinnabarIslandShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    cinnabarIslandShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    cinnabarIslandShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true)); // 4x stock
                    cinnabarIslandShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    cinnabarIslandShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    cinnabarIslandShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    cinnabarIslandShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

                    ArrayList<TrainerItem> shopItems = cinnabarIslandShop.getRandomItems(); // randomiza os itens na loja

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
                        PokemonWild magmar2    = new PokemonWild("Magmar",    91,  91,  95, 100, 3, 3, 43, 1000, 800);
                        PokemonWild rapidash   = new PokemonWild("Rapidash", 103, 103, 100,  80, 3, 3, 44, 1100, 900);
                        PokemonWild ninetales  = new PokemonWild("Ninetales", 98,  98,  76, 100, 3, 3, 43, 1100, 900);
                        PokemonWild flareon    = new PokemonWild("Flareon",  105, 105, 130,  95, 3, 3, 44, 1200, 950);
                        PokemonWild ponyta2    = new PokemonWild("Ponyta",    83,  83,  85,  65, 3, 3, 42, 900,  750);
                        PokemonWild dewgong2   = new PokemonWild("Dewgong",  104, 104,  70,  70, 3, 3, 42, 900,  700);
                        PokemonWild seel2      = new PokemonWild("Seel",      78,  78,  45,  70, 3, 3, 40, 800,  600);
                        PokemonWild shellder2  = new PokemonWild("Shellder",  46,  46,  65,  45, 3, 3, 40, 750,  600);
                        PokemonWild cloyster   = new PokemonWild("Cloyster",  80,  80,  95,  85, 3, 3, 43, 1000, 800);
                        PokemonWild jynx2      = new PokemonWild("Jynx",      79,  79,  50, 115, 3, 3, 42, 950,  750);
                        PokemonWild tentacruel2 = new PokemonWild("Tentacruel",  94,  94,  70, 120, 3, 3, 43, 1000, 800);
                        PokemonWild seadra2     = new PokemonWild("Seadra",      79,  79,  65,  95, 3, 3, 42,  950, 750);
                        PokemonWild seaking2    = new PokemonWild("Seaking",     99,  99,  92,  65, 3, 3, 43,  950, 750);
                        PokemonWild kingler     = new PokemonWild("Kingler",     85,  85, 130,  50, 3, 3, 43, 1100, 900);
                        PokemonWild golduck     = new PokemonWild("Golduck",    100, 100,  82,  80, 3, 3, 43, 1000, 800);

                        PokemonWild[] wildPokemonCinnabarIsland = {
                                magmar2, rapidash, ninetales, flareon, ponyta2,
                                dewgong2, seel2, shellder2, cloyster, jynx2,
                                tentacruel2, seadra2, seaking2, kingler, golduck
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCinnabarIsland[random.nextInt(wildPokemonCinnabarIsland.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
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
                    player.getPokemonInUse().resetSpecialAttackUses();
                    player.showDetails();
                    break;
                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
        viridianCity();
    }
    public void viridianCity() {
        Scanner input = new Scanner(System.in);

        boolean inCity = true;
        while (inCity) {
            System.out.println("\n Chegaste a Viridian City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar o Giovani, o Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym viridianCityGym = new Gym("Viridian City Gym", 47, 3790);
                    viridianCityGym.addPokemon(new PokemonWildGymGiovani("Rhyhorn", 80, 80, 130, 30, 4, 4, 45, 2000, 1500));
                    viridianCityGym.addPokemon(new PokemonWildGymGiovani("Rhydon", 105, 105, 130, 45, 5, 5, 50, 3000, 2000));
                    viridianCityGym.addPokemon(new PokemonWildGymGiovani("Nidoking", 81, 81, 102, 85, 4, 4, 45, 2500, 1750));

                    if (player.getPokemonInUse().getLevel() < viridianCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + viridianCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Giovani!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : viridianCityGym.getPokemonGym()) {
                        System.out.println("Giovani enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste o Giovani e ganhaste o Earth Badge!");
                        player.addCoins(viridianCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // ganha jogo
                        winGame(); // ecrã de vitória
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop viridianCityShop = new PokeShop("Viridian Shop");
                    viridianCityShop.addItem(new Potion("Max Potion", 2500, 999));
                    viridianCityShop.addItem(new Potion("Sacred Potion", 5000, 999)); // cura total, nome épico
                    viridianCityShop.addItem(new Consumable("Enigma Berry", 1000, 80, 0, false, false));
                    viridianCityShop.addItem(new Consumable("Salac Berry", 800, 50, 0, false, false));
                    viridianCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    viridianCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    viridianCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    viridianCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    viridianCityShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    viridianCityShop.addItem(new Consumable("Champion Elixir", 8000, 999, 50, true, true));  // cura total + boost strength + speed + level up
                    viridianCityShop.addItem(new Consumable("Master Herb", 6000, 999, 30, false, true));     // cura total + boost strength + level up
                    viridianCityShop.addItem(new Consumable("Titan Seed", 5000, 0, 50, false, false));       // boost de strength massivo
                    viridianCityShop.addItem(new Consumable("Storm Seed", 4500, 0, 0, true, false));         // speed garantido
                    viridianCityShop.addItem(new BattleConsumable("Chaos Orb", 1500, StatusEffect.PARALYZED));  // paralisia
                    viridianCityShop.addItem(new BattleConsumable("Doom Orb", 2000, StatusEffect.ASLEEP));      // sono
                    viridianCityShop.addItem(new BattleConsumable("Venom Orb", 1200, StatusEffect.POISONED));   // veneno
                    viridianCityShop.addItem(new BattleConsumable("Inferno Orb", 1200, StatusEffect.BURNED));   // queimadura

                    ArrayList<TrainerItem> shopItems = viridianCityShop.getRandomItems(); // randomiza os itens na loja

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
                        PokemonWild rhydon2    = new PokemonWild("Rhydon",    135, 135, 130,  45, 3, 3, 50, 2000, 1500);
                        PokemonWild nidoking2  = new PokemonWild("Nidoking",  107, 107, 102,  85, 3, 3, 48, 1800, 1400);
                        PokemonWild nidoqueen2 = new PokemonWild("Nidoqueen", 110, 110,  82,  85, 3, 3, 48, 1800, 1400);
                        PokemonWild dugtrio    = new PokemonWild("Dugtrio",    65,  65, 100,  50, 3, 3, 48, 1500, 1200);
                        PokemonWild electrode  = new PokemonWild("Electrode",  80,  80,  50,  80, 3, 3, 48, 1500, 1100);
                        PokemonWild tauros2    = new PokemonWild("Tauros",    105, 105, 100,  70, 3, 3, 49, 1600, 1300);
                        PokemonWild dragonair  = new PokemonWild("Dragonair",  91,  91,  84,  70, 3, 3, 50, 2000, 1500);
                        PokemonWild kangaskhan2= new PokemonWild("Kangaskhan",123, 123,  95,  40, 3, 3, 49, 1700, 1400);
                        PokemonWild Persian2   = new PokemonWild("Persian",    78,  78,  70,  65, 3, 3, 48, 1400, 1100);
                        PokemonWild arcanine2  = new PokemonWild("Arcanine",  120, 120, 110, 100, 3, 3, 49, 1600, 1300);
                        PokemonWild gyarados3  = new PokemonWild("Gyarados",  138, 138, 125, 100, 3, 3, 50, 2000, 1600);
                        PokemonWild poliwrath  = new PokemonWild("Poliwrath", 104, 104,  95,  70, 3, 3, 49, 1600, 1300);
                        PokemonWild slowbro2   = new PokemonWild("Slowbro",   108, 108,  75, 100, 3, 3, 49, 1500, 1200);
                        PokemonWild starmie2   = new PokemonWild("Starmie",    84,  84,  75, 100, 3, 3, 49, 1600, 1200);
                        PokemonWild lapras     = new PokemonWild("Lapras",    145, 145,  85,  95, 3, 3, 50, 2000, 1500);

                        PokemonWild[] wildPokemonViridianCity = {
                                rhydon2, nidoking2, nidoqueen2, dugtrio, electrode,
                                tauros2, dragonair, kangaskhan2, Persian2, arcanine2,
                                gyarados3, poliwrath, slowbro2, starmie2, lapras
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonViridianCity[random.nextInt(wildPokemonViridianCity.length)];

                        System.out.println("Um " + enemy.getName() + " selvagem apareceu!");
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu...");
                            gameOver();

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
                    player.getPokemonInUse().resetSpecialAttackUses();
                    player.showDetails();
                    break;
                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
    }
    // percurso alternativo: cave secreta e indigo plateau (bypass dos gyms para ganhar)
    public void secretCave() {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.println("\n No caminho para Celadon City, encontras uma entrada escondida atrás de uma cascata...");
        System.out.println("É uma caverna. Está escuro. Quase não se vê nada.");
        System.out.println("Mas ao pé de uma rocha, encontras uma LANTERNA e um PAPEL VELHO dobrado...");
        System.out.println("Parece um mapa, mas está muito gasto para leres bem.");
        System.out.println("\n O que queres fazer?");
        System.out.println("1. Entrar na caverna e explorar");
        System.out.println("2. Ignorar e seguir para Celadon City");
        int entranceCaveChoice = input.nextInt();

        if (entranceCaveChoice == 2) { // para voltar para o menu de Celadon City e fazer a rota normal dos ginasios
            System.out.println("Decides não arriscar. Seguiste para Celadon City.");
            return;
        }

        // se jogador escolher outra opcao que não a 2, entra na caverna secreta
        System.out.println("\nPegas na lanterna e no mapa. A caverna é enorme e misteriosa...");
        System.out.println("Sentes que há algo especial aqui dentro.");

        boolean inSecretCave = true;
        while (inSecretCave) {
            System.out.println("\n═══════════════════════════════");
            System.out.println("       CAVERNA SECRETA");
            System.out.println("═══════════════════════════════");
            System.out.println("1. Continuar a explorar e seguir o mapa");
            System.out.println("2. Voltar a Celadon City");
            int secretCaveChoice = input.nextInt();

            if (secretCaveChoice == 2) {
                System.out.println("Decides sair da caverna. A luz do dia é bem-vinda e tu tens medo do escuro.");
                inSecretCave = false;
                return;
            }

            int randomSecretCaveEvent = random.nextInt(100); // randomizar os possiveis eventos dentro da cave secreta

            // mewtwo e win game alternativo (mewtwo)
            if (randomSecretCaveEvent < 2) {
                System.out.println("\n✨ A caverna começa a vibrar...");
                System.out.println("Uma luz enorme ilumina tudo à tua volta.");
                System.out.println("É... é o MEWTWO!!!");
                System.out.println("Os seus olhos fixam-se em ti... e depois fecha os olhos.");
                System.out.println("O Mewtwo inclina a cabeça, como se reconhecesse algo em ti.");
                System.out.println("\"...O Chosen One.\"");
                System.out.println("\n🏆 O MEWTWO ABENÇOOU-TE! O jogo termina aqui — apenas o Chosen One merece este momento!");
                winGameMewtwo();
                return;
            } else if (randomSecretCaveEvent < 12) {
                System.out.println("\n Uma luz cor-de-rosa aparece ao fundo da caverna...");
                System.out.println("É o MEW! O Pokémon mais raro do mundo flutua à tua frente!");

                if (player.getPokemonInUse().getLevel() < 35) { // se pokemon do jogador estiver abaixo do nivel 35, mew nao combate
                    System.out.println("O Mew olha para o teu " + player.getPokemonInUse().getName()
                            + " e sorri... e desaparece num piscar de olhos.");
                    System.out.println("O teu Pokémon é demasiado fraco para combater o Mew. Precisa de estar acima do nível 35.");
                } else {
                    System.out.println("O teu " + player.getPokemonInUse().getName()
                            + " está pronto! O Mew aceita o desafio!");

                    // instanciar o mew como pokemonwild para a luta (para nao ter o special attack do mew (pokemon legendary)
                    PokemonWild mewEnemy = new PokemonWild("Mew", 200, 200, 80, 90, 4, 4, 40, 5000, 5000);

                    System.out.println("\n O Mew flutua no ar, rodeado por uma barreira mística! A batalha começou!");

                    boolean winMewBattle = player.getPokemonInUse().pokemonBattle(mewEnemy, player);

                    if (!winMewBattle) { // se perde contra o mew
                        System.out.println("O teu Pokémon desmaiou contra a força avassaladora do Mew...");
                        gameOver();
                        return;
                    }

                    System.out.println("\n O Mew pára de lutar, desfaz a sua barreira e flutua calmamente até ti...");
                    System.out.println("Ele sorri, reconhecendo o teu valor e o espírito do teu companheiro de equipa.");
                    System.out.println("Lanças uma Master Ball e o Mew junta-se a ti!");
                    System.out.println(" MEW FOI CAPTURADO!");
                    System.out.println("O Mew substituiu o teu " + player.getPokemonInUse().getName() + " como o teu Pokémon principal!");

                    Mew mewCaptured = new Mew("Mew", 150, 150, 100, 150, 5, 5, 50, 0);
                    // substitui o pokemon starter do jogador
                    player.setPokemonInUse(mewCaptured);
                    System.out.println("\n🎫 O Mew deixou cair um GOLD TICKET da sua cauda!");
                    System.out.println("O Gold Ticket vibra intensamente na tua mão e abre um portal interdimensional...");
                    System.out.println("Foste transportado diretamente para o INDIGO PLATEAU — onde o Trainer Blue te espera!");

                    inSecretCave = false;
                    indigoPlateau(); // Transição direta para o boss final Blue para obter winGameBlue aka final alternativo
                    return;
                }
            } else if (randomSecretCaveEvent < 37) {
                System.out.println("\n Um ABRA aparece de repente à tua frente!");
                System.out.println("Os seus olhos abrem-se... e em menos de um segundo faz TELEPORT!");
                System.out.println("Na pressa, o Abra deixou cair tudo o que trazia!");
                System.out.println("\n O Abra deixou cair:");

                AbraEncounter abraAppeared = new AbraEncounter();

                player.addCoins(abraAppeared.getCoinsDropped());
                System.out.println("  💰 " + abraAppeared.getCoinsDropped() + " coins!");

                ArrayList<TrainerItem> abraDroppedItems = abraAppeared.getDroppedItems(3); // abra deixa 3 itens para o jogador
                for (TrainerItem item : abraDroppedItems) {
                    player.addItemToBag(item);
                    System.out.println(" O abra perdeu um " + item.getName() + "!");
                }
                System.out.println("\nTotal de coins agora: " + player.getCoins());
                player.showDetails();
            }
            else if (randomSecretCaveEvent <52 ) {
            TeamRocketAmbush memberTeamRocketSecretCave = new TeamRocketAmbush();
            System.out.println(" Oh não! " + memberTeamRocketSecretCave.getMember() + " aparece das sombras!");
            System.out.println("\"Prepara-te para sofrer! A Team Rocket vai tentar roubar-te os coins!\"");

            int teamRocketStealCoins = random.nextInt(100);
            if (teamRocketStealCoins < 30) {
                int coinsStolen = Math.min(player.getCoins(), 2000); // rouba 2000 coins do jogador
                player.removeCoins(coinsStolen);
                System.out.println("A Team Rocket conseguiu roubar " + coinsStolen + " coins!");
                System.out.println("Fugiu antes que pudesses reagir...");
                System.out.println("Tens agora " + player.getCoins() + " coins.");
            }
            else {
                System.out.println("Conseguiste defender-te! A Team Rocket fugiu envergonhada!");
                System.out.println("Na fuga, deixaram cair 1500 coins no chão!");
                player.addCoins(1500);
                System.out.println("Tens agora " + player.getCoins() + " coins.");
            }
            }
            else {
                PokemonWild[] secretCavePokemon = {
                        new PokemonWild("Dragonair",  91,  91,  84,  70, 3, 3, 35, 1500, 1000),
                        new PokemonWild("Haunter",    55,  55,  50,  95, 3, 3, 33, 1000, 800),
                        new PokemonWild("Gengar",     75,  75,  65, 130, 3, 3, 38, 1500, 1200),
                        new PokemonWild("Electabuzz", 85,  85,  83,  95, 3, 3, 35, 1100, 900),
                        new PokemonWild("Magmar",     85,  85,  95, 100, 3, 3, 35, 1100, 900),
                        new PokemonWild("Lapras",    110, 110,  85,  95, 3, 3, 37, 1300, 1100),
                        new PokemonWild("Gyarados",  110, 110, 125, 100, 3, 3, 37, 1500, 1200),
                        new PokemonWild("Starmie",    75,  75,  75, 100, 3, 3, 35, 1200, 1000),
                        new PokemonWild("Alakazam",   55,  55,  45, 135, 3, 3, 38, 1500, 1200),
                        new PokemonWild("Cloyster",   80,  80,  95,  85, 3, 3, 34, 1100, 900)
                };
                PokemonWild enemy = secretCavePokemon[random.nextInt(secretCavePokemon.length)];
                System.out.println("\n Um " + enemy.getName() + " selvagem e poderoso apareceu!");

                boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                // aplicar aqui o check das evolucoes

                if (!winBattle) {
                    System.out.println("A caverna foi demasiado perigosa...");
                    inSecretCave = false;
                    gameOver();
                    return;
                }
                player.addCoins(enemy.getCoins());
                player.showDetails();
            }
        }
    }
    public void indigoPlateau() {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("\n INDIGO PLATEAU");
        System.out.println("O portal do Gold Ticket fechou-se atrás de ti.");
        System.out.println("À tua frente está o Trainer BLUE — o teu rival desde o início!");
        System.out.println("\"Hah! Sabia que apareceria aqui. Estás pronto para perder?\"");

        boolean inPlateau = true;
        while (inPlateau) {
            System.out.println("\n═══════════════════════════════");
            System.out.println("        INDIGO PLATEAU           ");
            System.out.println("═══════════════════════════════");
            System.out.println("1. Enfrentar o Blue");
            System.out.println("2. Ir à Blue's Shop");
            System.out.println("3. Treinar com Pokémon semi-lendários");
            System.out.println("4. Ir ao PookéCenter");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n\"Prepara-te!\" — BLUE");

                    PokemonWild pidgeot   = new PokemonWild("Pidgeot",  108, 108,  80,  70, 4, 4, 50, 2000, 0);
                    PokemonWild alakazam2 = new PokemonWild("Alakazam",  65,  65,  45, 135, 4, 4, 53, 2500, 0);
                    PokemonWild rhydon2   = new PokemonWild("Rhydon",   145, 145, 130,  45, 4, 4, 54, 2800, 0);
                    PokemonWild arcanine3 = new PokemonWild("Arcanine", 130, 130, 110, 100, 4, 4, 53, 2500, 0);
                    PokemonWild exeggutor = new PokemonWild("Exeggutor", 105, 105,  95, 125, 4, 4, 53, 2500, 0);
                    PokemonWild blastoise2= new PokemonWild("Blastoise", 109, 109,  83, 100, 4, 4, 55, 3000, 0);

                    PokemonWild[] blueTeam = {pidgeot, alakazam2, rhydon2, arcanine3, exeggutor, blastoise2};

                    boolean blueDefeated = true;


                    for (int i = 0; i < blueTeam.length; i++) {
                        PokemonWild bluePokemon = blueTeam[i];

                        // Menu de Interrupção para cura entre combates do Blue (a partir do 2º rival)
                        if (i > 0) {
                            System.out.println("\n-------------------------------------------------");
                            System.out.println("Blue prepara-se para mandar o próximo Pookémon.");
                            System.out.println("Queres abrir a tua mochila para curar o teu Pokémon antes?");
                            System.out.println("1. Sim, abrir mochila (Menu de Cura)");
                            System.out.println("2. Não, manda vir o próximo Pookémon!");
                            int healMenu = input.nextInt();
                            if (healMenu == 1) {
                                // Filtra apenas Poções/Consumíveis da mochila para curar fora de batalha
                                ArrayList<TrainerItem> mewRestoreHealth = new ArrayList<>();
                                for (TrainerItem item : player.getItemsBag()) {
                                    if (item instanceof Potion || item instanceof Consumable) {
                                        mewRestoreHealth.add(item);
                                    }
                                }
                                if (mewRestoreHealth.isEmpty()) {
                                    System.out.println("Não tens itens de cura na mochila!");
                                } else {
                                    System.out.println("Escolhe uma Potion/Consumable para usar no teu " + player.getPokemonInUse().getName() + ":");
                                    for (int k = 0; k < mewRestoreHealth.size(); k++) {
                                        System.out.println((k + 1) + ". " + mewRestoreHealth.get(k).getName());
                                    }
                                    int itemChoice = input.nextInt();
                                    if (itemChoice > 0 && itemChoice <= mewRestoreHealth.size()) {
                                        TrainerItem chosen = mewRestoreHealth.get(itemChoice - 1);
                                        chosen.use(player.getPokemonInUse());
                                        player.getItemsBag().remove(chosen);
                                    }
                                }
                            }
                        }

                        System.out.println("\n Blue enviou " + bluePokemon.getName() + "!");
                        boolean battleWin = player.getPokemonInUse().pokemonBattle(bluePokemon, player);

                        if (!battleWin) {
                            blueDefeated = false;
                            inPlateau = false;
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (blueDefeated) {
                        System.out.println("\n\"Im-impossível...\" — BLUE");
                        System.out.println("Derrotaste o Blue no Indigo Plateau!");
                        player.addCoins(5000);
                        inPlateau = false;
                        winGameBlue();
                    }
                    break;

                case 2:
                    System.out.println("\n Acedendo ao stock da Blue's Shop...");
                    PokeShop indigoPlateauShop = new PokeShop("Blue's Shop");
                    indigoPlateauShop.addItem(new Potion("Sacred Potion", 5000, 999));
                    indigoPlateauShop.addItem(new Consumable("Champion Elixir", 8000, 999, 50, true, true));
                    indigoPlateauShop.addItem(new Consumable("Master Herb", 6000, 999, 30, false, true));
                    indigoPlateauShop.addItem(new Consumable("Titan Seed", 5000, 0, 50, false, false));
                    indigoPlateauShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    indigoPlateauShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    indigoPlateauShop.addItem(new Consumable("Rare Candy", 800, 0, 0, false, true));
                    indigoPlateauShop.addItem(new BattleConsumable("Doom Orb", 2000, StatusEffect.ASLEEP));
                    indigoPlateauShop.addItem(new BattleConsumable("Chaos Orb", 1500, StatusEffect.PARALYZED));
                    indigoPlateauShop.addItem(new BattleConsumable("Inferno Orb", 1200, StatusEffect.BURNED));

                    ArrayList<TrainerItem> blueShopItems = indigoPlateauShop.getRandomItems();

                    System.out.println("Itens disponíveis hoje:");
                    for (int i = 0; i < blueShopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + blueShopItems.get(i).getName()
                                + " - " + blueShopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair da loja");
                    System.out.print("O que queres comprar? ");
                    int blueShopChoice = input.nextInt();

                    if (blueShopChoice == 0) break;

                    if (blueShopChoice > 0 && blueShopChoice <= blueShopItems.size()) {
                        TrainerItem chosen = blueShopItems.get(blueShopChoice - 1);
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
                    System.out.println("Escolheste treinar no Indigo Plateau!");
                    boolean training = true;

                    while (training) {
                        PokemonWild[] indigoPlateauTraining = {
                                new PokemonWild("Dragonite",  121, 121, 134, 100, 4, 4, 53, 3000, 2000),
                                new PokemonWild("Gyarados",   138, 138, 125, 100, 4, 4, 52, 2500, 1800),
                                new PokemonWild("Lapras",     145, 145,  85,  95, 4, 4, 51, 2200, 1600),
                                new PokemonWild("Gengar",      95,  95,  65, 130, 4, 4, 50, 2000, 1500),
                                new PokemonWild("Alakazam",    65,  65,  45, 135, 4, 4, 52, 2500, 1800),
                                new PokemonWild("Exeggutor",  105, 105,  95, 125, 4, 4, 51, 2200, 1600)
                        };

                        PokemonWild enemy = indigoPlateauTraining[rand.nextInt(indigoPlateauTraining.length)];
                        System.out.println("Um " + enemy.getName() + " poderoso apareceu!");

                        boolean battleWin = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!battleWin) {
                            training = false;
                            inPlateau = false;
                            gameOver();
                        } else {
                            player.addCoins(enemy.getCoins());
                            player.showDetails();
                            System.out.println("\n1. Continuar a treinar");
                            System.out.println("2. Voltar ao menu");
                            if (input.nextInt() == 2) training = false;
                        }
                    }
                    break;

                case 4:
                    System.out.println("Bem-vindo ao PookéCenter da Indigo Plateau!");
                    System.out.println("A Nurse Joy trata do teu Pokémon...");
                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    player.showDetails();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    // todos os winGames em baixo
    public void winGame() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("█████████████████████████████████████████████████████████");
        System.out.println("█                                                       █");
        System.out.println("█          PARABÉNS, POOKÉMON MASTER!                  █");
        System.out.println("█                                                       █");
        System.out.println("█   Derrotaste Giovanni e todos os 8 Gym Leaders!      █");
        System.out.println("█   Tornaste-te o maior treinador de Kanto!            █");
        System.out.println("█                                                       █");
        System.out.println("█████████████████████████████████████████████████████████");
        System.out.println("\n  🏅 Crachás conquistados: " + player.getGymBadge() + "/8");
        System.out.println("  ⭐ Pookémon: " + player.getPokemonInUse().getName()
                + " | Nível: " + player.getPokemonInUse().getLevel()
                + " | HP: " + player.getPokemonInUse().getHp());
        System.out.println("  💰 Coins: " + player.getCoins());
        System.out.println("\nO que queres fazer?");
        System.out.println("1. Jogar novamente");
        System.out.println("2. Sair");
        int choice = input.nextInt();

        if (choice == 1) {
            Audio.stopAll(); // parar o som antes do jogo fechar
            new Game().pookemon(); // reinicia o jogo
        } else {
            System.out.println("\nObrigado por jogares Pookémon! Até à próxima, treinador!");
            Audio.stopAll(); // parar o som antes do jogo fechar
            System.exit(0);
        }
    }
    public void winGameMewtwo() {
            Scanner input = new Scanner(System.in);

            System.out.println("\n");
            System.out.println("█████████████████████████████████████████████████████████");
            System.out.println("█                                                       █");
            System.out.println("█             O DESTINO REVELADO: THE CHOSEN ONE!       █");
            System.out.println("█                                                       █");
            System.out.println("█   Penetraste na caverna e a tua aura foi reconhecida  █");
            System.out.println("█   pela entidade psíquica mais poderosa do universo.   █");
            System.out.println("█   O Mewtwo escolheu-te. Tu transcendeste a Liga!     █");
            System.out.println("█                                                       █");
            System.out.println("█████████████████████████████████████████████████████████");
            System.out.println("\n  Estado Cósmico: Abençoado por Mewtwo");
            System.out.println("  Pookémon no momento: " + player.getPokemonInUse().getName()
                    + " | Nível: " + player.getPokemonInUse().getLevel());
            System.out.println("  Moedas acumuladas: " + player.getCoins());
            System.out.println("\n O que queres fazer?");
            System.out.println("1. Jogar novamente (Explorar o resto do mundo)");
            System.out.println("2. Sair do jogo");
            int choice = input.nextInt();

            if (choice == 1) {
                Audio.stopAll(); // parar o som antes do jogo fechar
                new Game().pookemon(); // reinicia o jogo
            } else {
                System.out.println("\nA tua lenda ficará guardada nas sombras da Secret Cave. Adeus!");
                Audio.stopAll(); // parar o som antes do jogo fechar
                System.exit(0);
            }
        }
    public void winGameBlue() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("█████████████████████████████████████████████████████████");
        System.out.println("█                                                       █");
        System.out.println("█              NOVO CAMPEÃO DA LIGA INDIGO!             █");
        System.out.println("█                                                       █");
        System.out.println("█   Superaste o portal do Gold Ticket e destruíste      █");
        System.out.println("█   a lendária equipa do Trainer Blue!                  █");
        System.out.println("█   És oficialmente o número 1 do Indigo Plateau!       █");
        System.out.println("█                                                       █");
        System.out.println("█████████████████████████████████████████████████████████");
        System.out.println("\n  Título: Kanto League Champion");
        System.out.println("  Pookémon Parceiro: " + player.getPokemonInUse().getName()
                + " | Nível: " + player.getPokemonInUse().getLevel()
                + " | HP: " + player.getPokemonInUse().getHp());
        System.out.println("  Coins de Elite: " + player.getCoins());
        System.out.println("\nO que queres fazer?");
        System.out.println("1. Jogar novamente (Tentar outra rota)");
        System.out.println("2. Sair do jogo");
        int choice = input.nextInt();

        if (choice == 1) {
            Audio.stopAll(); // parar o som antes do jogo fechar
            new Game().pookemon(); // reinicia o jogo
        } else {
            System.out.println("\nObrigado por jogares Pookémon! Até à próxima, Campeão!");
            Audio.stopAll(); // parar o som antes do jogo fechar
            System.exit(0);
        }
    }
    // game over
    public void gameOver() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("█████████████████████████████████████████████████████████");
        System.out.println("█                                                       █");
        System.out.println("█                     GAME OVER...                      █");
        System.out.println("█                                                       █");
        System.out.println("█              O teu Pookémon desmaiou!                 █");
        System.out.println("█                                                       █");
        System.out.println("█████████████████████████████████████████████████████████");
        System.out.println("\n  Pookémon: " + player.getPokemonInUse().getName()
                + " | Nível: " + player.getPokemonInUse().getLevel());
        System.out.println("  Crachás conquistados: " + player.getGymBadge() + "/8");
        System.out.println("  Coins: " + player.getCoins());
        System.out.println("\nO que queres fazer?");
        System.out.println("1. Jogar novamente");
        System.out.println("2. Sair");
        int choice = input.nextInt();

        if (choice == 1) {
            Audio.stopAll(); // parar o som antes do jogo fechar
            new Game().pookemon(); // reinicia o jogo
        } else {
            System.out.println("\nNão desistas, treinador! Até à próxima!");
            Audio.stopAll(); // parar o som antes do jogo fechar
            System.exit(0);
        }
    }
}




