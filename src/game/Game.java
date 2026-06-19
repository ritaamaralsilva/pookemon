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
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
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
                    vermilionCityGym.addPokemon(new PokemonWildGymSurge("Starmie", 60, 60, 90, 90, 2, 2, 24, 1457, 1358));

                    if (player.getPokemonInUse().getLevel() < vermilionCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + vermilionCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Lt. Lurge!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : vermilionCityGym.getPokemonGym()) {
                        System.out.println("Lt. Lurge enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
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
                    vermilionCityShop.addItem(new Potion("Potion", 300, 20));
                    vermilionCityShop.addItem(new Potion("Super Potion", 700, 50));

                    vermilionCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    vermilionCityShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    vermilionCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    vermilionCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    vermilionCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
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


                        PokemonWild[] wildPokemonVermilionCity = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonVermilionCity[random.nextInt(wildPokemonVermilionCity.length)];

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
        celadonCity();
    }
    public void celadonCity () {
        Scanner input = new Scanner(System.in);

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
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
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
                    celadonCityShop.addItem(new Potion("Potion", 300, 20));
                    celadonCityShop.addItem(new Potion("Super Potion", 700, 50));

                    celadonCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    celadonCityShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    celadonCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    celadonCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    celadonCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    celadonCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false)); // X Defense a implementar, em vez de alterar stat de defesa que nao existe como atributo, vai afetar no strength do pokemon inimigo e reduz 10, ou seja, vai afetar pokemon inimigo

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


                        PokemonWild[] wildPokemonCeladonCity = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCeladonCity[random.nextInt(wildPokemonCeladonCity.length)];

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
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Starmie", 105, 105, 105, 85, 3, 3, 39, 1743, 2100));

                    if (player.getPokemonInUse().getLevel() < fuchsiaCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + fuchsiaCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Koga!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : fuchsiaCityGym.getPokemonGym()) {
                        System.out.println("Koga enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
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
                    fuchsiaCityShop.addItem(new Potion("Potion", 300, 20));
                    fuchsiaCityShop.addItem(new Potion("Super Potion", 700, 50));

                    fuchsiaCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    fuchsiaCityShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    fuchsiaCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    fuchsiaCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    fuchsiaCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    fuchsiaCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false)); // X Defense a implementar, em vez de alterar stat de defesa que nao existe como atributo, vai afetar no strength do pokemon inimigo e reduz 10, ou seja, vai afetar pokemon inimigo

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


                        PokemonWild[] wildPokemonFuchsiaCity = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonFuchsiaCity[random.nextInt(wildPokemonFuchsiaCity.length)];

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
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Starmie", 55, 55, 45, 135, 5, 5, 50, 2500, 2500));

                    if (player.getPokemonInUse().getLevel() < saffronCityGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + saffronCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar a Gym Leader Sabrina!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : saffronCityGym.getPokemonGym()) {
                        System.out.println("Sabrina enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
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
                    saffronCityShop.addItem(new Potion("Potion", 300, 20));
                    saffronCityShop.addItem(new Potion("Super Potion", 700, 50));

                    saffronCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    saffronCityShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    saffronCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    saffronCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    saffronCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    saffronCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false)); // X Defense a implementar, em vez de alterar stat de defesa que nao existe como atributo, vai afetar no strength do pokemon inimigo e reduz 10, ou seja, vai afetar pokemon inimigo

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


                        PokemonWild[] wildPokemonSaffronCity = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonSaffronCity[random.nextInt(wildPokemonSaffronCity.length)];

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
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Starmie", 90, 90, 110, 100, 4, 4, 47, 1899, 1953));

                    if (player.getPokemonInUse().getLevel() < cinnabarIslandGym.getMinLevelToBattle()) {
                        System.out.println("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + cinnabarIslandGym.getMinLevelToBattle() + " para entrar neste gym.");
                        break;
                    }

                    System.out.println("Escolheste enfrentar o Gym Leader Blaine!");
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : cinnabarIslandGym.getPokemonGym()) {
                        System.out.println("Blaine enviou " + gymPokemon.getName() + "!");
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
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
                    cinnabarIslandShop.addItem(new Potion("Potion", 300, 20));
                    cinnabarIslandShop.addItem(new Potion("Super Potion", 700, 50));

                    cinnabarIslandShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    cinnabarIslandShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    cinnabarIslandShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    cinnabarIslandShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    cinnabarIslandShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    cinnabarIslandShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false)); // X Defense a implementar, em vez de alterar stat de defesa que nao existe como atributo, vai afetar no strength do pokemon inimigo e reduz 10, ou seja, vai afetar pokemon inimigo

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


                        PokemonWild[] wildPokemonCinnabarIsland = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCinnabarIsland[random.nextInt(wildPokemonCinnabarIsland.length)];

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
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player.getItemsBag());

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        System.out.println("Parabéns! Derrotaste o Giovani e ganhaste o Earth Badge!");
                        player.addCoins(viridianCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                    }
                    break;
                case 2:
                    System.out.println("Bem-vindo à PookéShop!");

                    PokeShop viridianCityShop = new PokeShop("Cinnabar Shop");
                    viridianCityShop.addItem(new Potion("Potion", 300, 20));
                    viridianCityShop.addItem(new Potion("Super Potion", 700, 50));

                    viridianCityShop.addItem(new Consumable("Berry", 100, 10, 0, false, false));
                    viridianCityShop.addItem(new Consumable("Oran Berry", 150, 15, 0, false, false));
                    viridianCityShop.addItem(new Consumable("Sitrus Berry", 400, 30, 0, false, false));
                    viridianCityShop.addItem(new Consumable("X Attack", 500, 0, 10, false, false));
                    viridianCityShop.addItem(new Consumable("X Speed", 350, 0, 0, true, false));
                    viridianCityShop.addItem(new Consumable("X Defense", 350, 0, -10, false, false)); // X Defense a implementar, em vez de alterar stat de defesa que nao existe como atributo, vai afetar no strength do pokemon inimigo e reduz 10, ou seja, vai afetar pokemon inimigo

                    viridianCityShop.addItem(new BattleConsumable("Paralyze Orb", 300, StatusEffect.PARALYZED));
                    viridianCityShop.addItem(new BattleConsumable("Sleep Orb", 400, StatusEffect.ASLEEP));
                    viridianCityShop.addItem(new BattleConsumable("Poison Orb", 250, StatusEffect.POISONED));
                    viridianCityShop.addItem(new BattleConsumable("Burn Orb", 250, StatusEffect.BURNED));

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


                        PokemonWild[] wildPokemonViridianCity = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonViridianCity[random.nextInt(wildPokemonViridianCity.length)];

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
        //winGame();
    }
}




