package game;

import entities.*;
import entities.secretCave.AbraEncounter;
import entities.secretCave.TeamRocketAmbush;
import items.BattleConsumable;
import items.Consumable;
import items.Potion;
import items.TrainerItem;

import game.ConsoleColors;
import game.FileTools;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Game {
    private Trainer player; // atributo da classe Game

    public void pookemon() throws FileNotFoundException { // metodo do jogo, instancio aqui todos os objetos

        // método do jogo, instancio aqui todos os objetos

        // chamar audio inicial para tocar mal se abre o jogo na consola
        Audio.playSoundtrack("resources/audio/pookemonIntroMusic.wav");

        try {
            FileTools.printFile("resources/art/pokemon.txt");
            sleep(3000);
        } catch (FileNotFoundException | InterruptedException e) {
            System.out.println(" Erro Real: " + e.getMessage());
            System.out.println("Aviso: Imagem do logo não encontrada.");
        }

        Scanner userInput = new Scanner(System.in);
        String userPlayerName = "";
        String userGenderName = "";

        ConsoleColors.backgroundRgb(255, 203, 5);

        // Título estilizado automaticamente usando o método do ConsoleColors
        ConsoleColors.title("Olá! Bem-vindo/a ao POOKÉMON! ");

        // validar nome (Garante que não tem números nem fica vazio)
        while (true) {
            ConsoleColors.print("Como te chamas? ", ConsoleColors.YELLOW_BOLD);
            userPlayerName = userInput.nextLine().trim();

            if (userPlayerName.isEmpty()) {
                ConsoleColors.error("O nome não pode ficar em branco.\n");
            } else if (userPlayerName.matches(".*\\d.*")) {
                ConsoleColors.error("Por favor, introduz um nome válido (sem números).\n");
            } else {
                break; // Nome válido!
            }
        }

        System.out.println();
        ConsoleColors.success("Prazer em conhecer-te, " + userPlayerName + "!");
        System.out.println();

        while (true) {
            ConsoleColors.separator();
            ConsoleColors.println("Seleciona a tua identidade: ", ConsoleColors.PURPLE_BOLD);
            System.out.println("1. Feminino");
            System.out.println("2. Masculino");
            System.out.println("3. Prefiro não dizer");
            ConsoleColors.separator();

            ConsoleColors.print("Escolha (1-3): ", ConsoleColors.YELLOW_BOLD);

            try {
                int opcaoGend = userInput.nextInt();
                userInput.nextLine(); // Limpar o buffer do Scanner

                if (opcaoGend == 1) {
                    userGenderName = "Feminino";
                    break;
                } else if (opcaoGend == 2) {
                    userGenderName = "Masculino";
                    break;
                } else if (opcaoGend == 3) {
                    userGenderName = "Não Especificado";
                    break;
                } else {
                    ConsoleColors.error("Opção inválida! Escolhe um número entre 1 e 3.\n");
                }

            } catch (InputMismatchException e) {
                ConsoleColors.error("Tipo de dados inválido! Deves introduzir um número (1, 2 ou 3).\n");
                userInput.nextLine(); // Limpar o buffer
            }
        }

        System.out.println();
        ConsoleColors.box("Treinador/a registado/a! Nome: " + userPlayerName + " | Género: " + userGenderName, ConsoleColors.GREEN_BOLD);
        System.out.println();

        // pokemon starter
        Bulbasaur bulbasaur = new Bulbasaur("Bulba", 45, 45, 49, 65, 1, 1, 5, 0);
        Charmander charmander = new Charmander("Chaaaar", 39, 39, 52, 50, 1, 1, 5, 0);
        Squirtle squirtle = new Squirtle("Squirrrr", 44, 44, 48, 50, 1, 1, 5, 0);
        Pikachu pikachu = new Pikachu("Pika Pika", 35, 35, 35, 55, 1, 1, 5, 0);

        // user vai ter de criar o seu Trainer aqui, usar o metodo trainer.showDetails() para mostrar os detalhes no fim
        player = new Trainer(userPlayerName, userGenderName, 1000, null);
        try {
            FileTools.printFile("resources/art/trainer.txt");
            sleep(3000);
        } catch (FileNotFoundException | InterruptedException e) {
            System.out.println(" Erro Real: " + e.getMessage());
            System.out.println("Aviso: Imagem do trainer não encontrada.");
        }

        int wakeUp = 0;

        while (true) {
            ConsoleColors.separator();
            ConsoleColors.story("trim trim ... São 7:30h da manhã, o teu despertador está a tocar.");
            ConsoleColors.story("O que vais fazer?");
            System.out.println("1. Acordar");
            System.out.println("2. Só mais 5 minutos...");
            ConsoleColors.separator();

            ConsoleColors.print("O que vais fazer: ", ConsoleColors.YELLOW_BOLD);

            try {
                wakeUp = userInput.nextInt();
                userInput.nextLine(); // Limpa o buffer

                if (wakeUp == 1 || wakeUp == 2) {
                    break; // Opção válida, sai do loop
                } else {
                    ConsoleColors.error("Opção inválida! Escolhe 1 ou 2.\n");
                }
            } catch (InputMismatchException e) {
                ConsoleColors.error("Entrada inválida! Introduz um número (1 ou 2).\n");
                userInput.nextLine(); // Limpa o buffer
            }
        }

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
                    try {
                        FileTools.printFile("resources/art/starters/bulbasaur.txt");
                        sleep(3000);
                    } catch (FileNotFoundException e) {
                        System.out.println(" Erro Real: " + e.getMessage());
                        System.out.println("Aviso: Imagem do bulbasaur não encontrada.");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player.setPokemonInUse(bulbasaur);
                } else if (starterPokemonChoice == 2) {
                    System.out.println("Escolheste o Charmander! ");
                    try {
                        FileTools.printFile("resources/art/starters/charmander.txt");
                        sleep(3000);
                    } catch (FileNotFoundException e) {
                        System.out.println(" Erro Real: " + e.getMessage());
                        System.out.println("Aviso: Imagem do charmander não encontrada.");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player.setPokemonInUse(charmander);
                } else {
                    System.out.println("Escolheste o Squirtle! ");
                    try {
                        FileTools.printFile("resources/art/starters/squirtle.txt");
                        sleep(3000);
                    } catch (FileNotFoundException e) {
                        System.out.println(" Erro Real: " + e.getMessage());
                        System.out.println("Aviso: Imagem do squirtle não encontrada.");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player.setPokemonInUse(squirtle);
                }
                break;

            case 2:
                ConsoleColors.story("... Ups... Perdeste o autocarro, o pneu da bicicleta está furado e não há ubers por perto, mas ganhaste um Pikachu, nem tudo é mau... Mas cuidade que ele dá choques! ");
                try {
                    FileTools.printFile("resources/art/starters/pikachu.txt");
                    sleep(3000);
                } catch (FileNotFoundException e) {
                    System.out.println(" Erro Real: " + e.getMessage());
                    System.out.println("Aviso: Imagem do pikachu não encontrada.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                player.setPokemonInUse(pikachu);
                break;
        }
        player.showDetails();

        ConsoleColors.separator();
        ConsoleColors.story("Agora sim, começa a tua jornada para te tornares o próximo Pookémon Champion.");
        ConsoleColors.story("... mas para isso vais precisar de conquistar todos os crachás dos ginásios da Liga de Kanto.");
        ConsoleColors.story("Próxima paragem: Pewter City, a cidade dos penedos e rochedos, espero que sejas forte na escalada!");

        pewterCity();
    }

    // funcoes das 8 cidades
    public void pewterCity() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        boolean inCity = true;
        while (inCity) {
            Audio.changeSoundtrack("resources/audio/pewterCity.wav");
            ConsoleColors.clear();
            ConsoleColors.title("Pewter City - A Cidade dos Penedos");

            ConsoleColors.separator();
            ConsoleColors.story("Estás nas ruas cinzentas de Pewter City. O ar é fresco e vês o imponente Ginásio de Rocha ao longe.");
            ConsoleColors.story("O que pretendes fazer a seguir?");
            ConsoleColors.separator();

            System.out.println("1. Enfrentar o Brock, o Gym Leader");
            System.out.println("2. Comprar itens na Pooké Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");

            ConsoleColors.print("\nEscolha: ", ConsoleColors.YELLOW_BOLD);

            int cityChoice;
            try {
                cityChoice = input.nextInt();
                input.nextLine(); // Limpar buffer
            } catch (InputMismatchException e) {
                ConsoleColors.error("Opção inválida! Introduz um número.");
                input.nextLine();
                continue;
            }

            switch (cityChoice) {
                case 1:
                    ConsoleColors.clear();
                    ConsoleColors.title("Ginásio de Pewter City");

                    Gym pewterCityGym = new Gym("Pewter City Gym", 12, 1386);
                    pewterCityGym.addPokemon(new PokemonWildGymBrock("Geodude", 50, 50, 80, 30, 2, 2, 12, 300, 500));
                    pewterCityGym.addPokemon(new PokemonWildGymBrock("Onix", 35, 35, 45, 30, 2, 2, 14, 500, 1000));

                    if (player.getPokemonInUse().getLevel() < pewterCityGym.getMinLevelToBattle()) {
                        ConsoleColors.error("O teu Pookémon é fraco demais!");
                        ConsoleColors.warning("Precisas de estar pelo menos no nível " + pewterCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        System.out.println("\nPressiona Enter para voltar...");
                        input.nextLine();
                        break;
                    }

                    Audio.changeSoundtrack("resources/audio/gyms.wav"); // troca para musica do ginasio
                    ConsoleColors.separator();
                    ConsoleColors.story("Entras no ginásio arenoso. Brock aguarda-te no topo de um pedestal de pedra.");
                    ConsoleColors.story("Escolheste enfrentar o Gym Leader Brock!");
                    ConsoleColors.separator();

                    boolean gymWon = true;

                    for (Pokemon gymPokemon : pewterCityGym.getPokemonGym()) {
                        ConsoleColors.separator();
                        ConsoleColors.story("Brock faz um sinal com a mão e lança uma Pookébola!");
                        ConsoleColors.println("Brock enviou o " + gymPokemon.getName() + "!", ConsoleColors.RED_BOLD);
                        ConsoleColors.separator();

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
                        ConsoleColors.separator();
                        ConsoleColors.story("As rochas de Brock desfazem-se em poeira perante a tua estratégia!");
                        ConsoleColors.success("Parabéns! Derrotaste o Brock e ganhaste o Boulder Badge!");
                        ConsoleColors.separator();

                        player.addCoins(pewterCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // Avança para a próxima cidade

                        System.out.println("\nPressiona Enter para continuar a jornada...");
                        input.nextLine();
                    }
                    break;
                case 2:
                    boolean inShop = true;
                    Audio.changeSoundtrack("resources/audio/pewterCity.wav");
                    while (inShop) {
                        ConsoleColors.clear();
                        ConsoleColors.title("Pooké Shop de Pewter City");
                        ConsoleColors.println("Bem-vindo à PookéShop!", ConsoleColors.CYAN_BOLD);

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
                        ConsoleColors.print("\nO que queres comprar? ", ConsoleColors.YELLOW_BOLD);
                        int shopChoice;
                        try {
                            shopChoice = input.nextInt();
                            input.nextLine();
                        } catch (InputMismatchException e) {
                            ConsoleColors.error("Escolha inválida!");
                            input.nextLine();
                            continue;
                        }

                        if (shopChoice == 0) {
                            inShop = false;
                            break;
                        }

                        if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                            TrainerItem chosen = shopItems.get(shopChoice - 1);
                            if (player.getCoins() >= chosen.getPrice()) {
                                player.removeCoins(chosen.getPrice());
                                player.addItemToBag(chosen);
                                ConsoleColors.success("Compraste " + chosen.getName() + "! Tens agora " + player.getCoins() + " coins.");
                            } else {
                                ConsoleColors.error("Não tens coins suficientes! Tens apenas " + player.getCoins() + " coins.");
                            }
                        } else {
                            ConsoleColors.error("Opção inválida!");
                        }
                        System.out.println("\nPressiona Enter para continuar na loja...");
                        input.nextLine();
                    }
                    break;
                case 3:
                    boolean training = true;
                    while (training) {
                        Audio.changeSoundtrack("resources/audio/pookemonBattleWild.wav"); // troca para musica de combates com pookemon wild
                        ConsoleColors.clear();
                        ConsoleColors.title("Área de Treino - Rota 3");

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

                        ConsoleColors.separator();
                        ConsoleColors.story("Caminhas pela relva alta à procura de oponentes para fortalecer a tua equipa...");
                        ConsoleColors.println("Um " + enemy.getName() + " selvagem apareceu!", ConsoleColors.RED_BOLD);
                        ConsoleColors.separator();

                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            break;
                        } else {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Vitória! Conseguiste " + enemy.getCoins() + " moedas.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nO que queres fazer agora?");
                            System.out.println("1. Continuar a treinar");
                            System.out.println("2. Voltar ao menu da cidade");

                            ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice;
                            try {
                                trainingChoice = input.nextInt();
                                input.nextLine();
                            } catch (InputMismatchException e) {
                                input.nextLine();
                                trainingChoice = 2; // Padrão força a saída se errar o input
                            }
                            if (trainingChoice == 2) {
                                training = false;
                            }
                        }
                    }
                    break;
                case 4:
                    Audio.changeSoundtrack("resources/audio/pewterCity.wav");
                    ConsoleColors.clear();
                    ConsoleColors.title("PookéCenter de Pewter City");

                    ConsoleColors.separator();
                    ConsoleColors.story("Entras no ambiente calmo e higienizado do centro de tratamento.");
                    ConsoleColors.println("Bem-vindo ao PookéCenter!", ConsoleColors.GREEN_BOLD);
                    ConsoleColors.story("A Nurse Joy sorri, recolhe o teu dispositivo e trata do teu Pookémon...");
                    ConsoleColors.separator();

                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses(); // volta a ter special attack uses
                    player.showDetails();

                    System.out.println("\nPressiona Enter para regressar à cidade...");
                    input.nextLine();
                    break;

                default:
                    System.out.println("Escolheste uma opção inválida!");
                    break;
            }
        }
        ceruleanCity(); // se ganhar o pewter city gym, jogo avança para ceruleanCity
    }

    public void ceruleanCity() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        // momento de história entre pewter city e cerulean city
        ConsoleColors.clear();
        ConsoleColors.title("A JORNADA PELA ROTA 4");
        ConsoleColors.separator();
        ConsoleColors.story("Deixando para trás os trilhos rochosos de Pewter City, segues em direção a este.");
        ConsoleColors.story("O caminho aperta à medida que contornas as faldas do intimidante Mt. Moon.");
        ConsoleColors.story("O ar torna-se subitamente húmido e uma brisa marítima guia-te pelo vale abaixo.");
        ConsoleColors.separator();
        ConsoleColors.println("Ao longe, as luzes azuis e os canais de água cortam o horizonte...", ConsoleColors.CYAN_BRIGHT);
        ConsoleColors.success("Entraste em Cerulean City, a Cidade da Chuva!");
        System.out.println("\nPressiona Enter para explorar a cidade...");
        input.nextLine();

        boolean inCity = true;
        while (inCity) {
            Audio.changeSoundtrack("resources/audio/ceruleanCity.wav");
            ConsoleColors.clear();
            ConsoleColors.title("Cerulean City - A Cidade da Chuva");
            ConsoleColors.story("\n Chegaste a Cerulean City, o que vais fazer a seguir? ");
            System.out.println("1. Enfrentar a Misty, a Gym Leader");
            System.out.println("2. Comprar itens na Pooke Shop");
            System.out.println("3. Treinar o teu Pookémon");
            System.out.println("4. Ir ao PookéCenter");

            ConsoleColors.separator();
            ConsoleColors.print("Escolha: ", ConsoleColors.YELLOW_BOLD);
            int cityChoice = input.nextInt();
            input.nextLine(); // limpar buffer

            switch (cityChoice) {
                case 1:
                    Gym ceruleanCityGym = new Gym("Cerulean City Gym", 18, 2142);
                    ceruleanCityGym.addPokemon(new PokemonWildGymMisty("Staryu", 52, 52, 45, 70, 2, 2, 18, 567, 635));
                    ceruleanCityGym.addPokemon(new PokemonWildGymMisty("Starmie", 63, 63, 75, 100, 3, 3, 21, 1399, 1050));

                    if (player.getPokemonInUse().getLevel() < ceruleanCityGym.getMinLevelToBattle()) {
                        ConsoleColors.error("O teu Pookémon é fraco demais! Precisas de estar pelo menos no nível "
                                + ceruleanCityGym.getMinLevelToBattle() + " para entrar neste gym.");
                        input.nextLine();
                        break;
                    }
                    Audio.changeSoundtrack("resources/audio/gyms.wav");

                    ConsoleColors.title("DESAFIO NO GINÁSIO DE CERULEAN");
                    ConsoleColors.story("Entras no ginásio aquático. Misty aguarda na plataforma central flutuante.");
                    ConsoleColors.warning("Misty: 'Espero que tenhas uma estratégia, senão vais afundar!'");
                    ConsoleColors.separator();

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
                        ConsoleColors.separator();
                        ConsoleColors.success("Parabéns! Derrotaste a Misty e ganhaste o Cascade Badge!");
                        player.addCoins(ceruleanCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                        System.out.println("\nPressiona Enter para continuar a tua jornada...");
                        input.nextLine();
                    }
                    break;
                case 2:
                    Audio.changeSoundtrack("resources/audio/ceruleanCity.wav");
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ SHOP - CERULEAN CITY");
                    System.out.println("Saldo Atual: " + player.getCoins() + " coins\n");

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
                    ConsoleColors.print("\n Escolhe: ", ConsoleColors.YELLOW_BOLD);
                    int shopChoice = input.nextInt();
                    input.nextLine();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            ConsoleColors.success("Compraste " + chosen.getName() + "! Tens agora " + player.getCoins() + " coins.");
                        } else {
                            ConsoleColors.error("Não tens coins suficientes! Tens apenas " + player.getCoins() + " coins.");
                        }
                    } else {
                        ConsoleColors.error("Opção inválida!");
                    }
                    System.out.println("\nPressiona Enter para voltar à cidade...");
                    input.nextLine();
                    break;
                case 3:
                    boolean training = true;
                    ConsoleColors.clear();
                    ConsoleColors.title("ÁREA DE TREINO - SUBÚRBIOS DE CERULEAN CITY");
                    while (training) {
                        Audio.changeSoundtrack("resources/audio/pookemonBattleWild.wav");
                        PokemonWild spearow = new PokemonWild("Spearow", 53, 53, 60, 31, 1, 1, 13, 300, 350);
                        PokemonWild rattata = new PokemonWild("Rattata", 44, 44, 56, 25, 1, 1, 13, 250, 200);
                        PokemonWild pidgey = new PokemonWild("Pidgey", 51, 51, 45, 35, 1, 1, 12, 250, 300);
                        PokemonWild oddish = new PokemonWild("Oddish", 55, 55, 50, 75, 1, 1, 12, 350, 450);
                        PokemonWild bellsprout = new PokemonWild("Bellsprout", 60, 60, 75, 70, 1, 1, 13, 350, 350);
                        PokemonWild venonat = new PokemonWild("Venonat", 68, 68, 55, 40, 1, 1, 13, 300, 300);
                        PokemonWild magikarp = new PokemonWild("Magikarp", 25, 25, 0, 15, 0, 0, 5, 100, 100);
                        PokemonWild poliwag = new PokemonWild("Poliwag", 52, 52, 50, 40, 1, 1, 15, 350, 450);
                        PokemonWild goldeen = new PokemonWild("Goldeen", 57, 57, 67, 50, 1, 1, 15, 400, 350);
                        PokemonWild psyduck = new PokemonWild("Psyduck", 65, 65, 52, 50, 2, 2, 20, 450, 600);
                        PokemonWild krabby = new PokemonWild("Krabby", 45, 45, 105, 25, 2, 2, 20, 500, 350);


                        PokemonWild[] wildPokemonCeruleanCity = {spearow, rattata, pidgey, oddish, bellsprout, venonat, magikarp, poliwag, goldeen, psyduck, krabby};

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCeruleanCity[random.nextInt(wildPokemonCeruleanCity.length)];

                        ConsoleColors.warning("Explorando as ervas altas do pantâno... Um " + enemy.getName() + " selvagem apareceu!");
                        ConsoleColors.separator();

                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Combate vencido! Recolheste " + enemy.getCoins() + " coins.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nO que queres fazer agora?");
                            System.out.println("1. Continuar a treinar");
                            System.out.println("2. Regressar à cidade");
                            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice = input.nextInt();
                            input.nextLine();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    Audio.changeSoundtrack("resources/audio/ceruleanCity.wav");
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉCENTER - CERULEAN CITY");
                    ConsoleColors.story("A Nurse Joy trata do teu Pookémon...");
                    ConsoleColors.separator();

                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    ConsoleColors.success("O teu Pookémon foi completamente curado e os PP restaurados!");
                    player.showDetails();
                    System.out.println("\nPressiona Enter para sair do PookéCenter...");
                    input.nextLine();
                    break;
                default:
                    ConsoleColors.error("Opção desconhecida...");
                    System.out.println("\nPressiona Enter para tentar novamente...");
                    input.nextLine();
                    break;
            }
        }
        vermilionCity();
    }

    public void vermilionCity() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("A ROTA DOS TREINADORES & O PORTO DE KANTO");
        ConsoleColors.separator();
        ConsoleColors.story("Deixando os canais azuis de Cerulean, cruzas o Underground Path e a Rota 6.");
        ConsoleColors.story("Pelo caminho, avistas a entrada da Diglett's Cave e ouves o eco dos trabalhadores.");
        ConsoleColors.story("Ao chegares à costa, o imponente navio de cruzeiro S.S. Anne apita no horizonte.");
        ConsoleColors.story("O cheiro a maresia e a eletricidade estática no ar anunciam o teu próximo destino.");
        ConsoleColors.separator();
        ConsoleColors.println("Os marinheiros guiam-te pelas docas sob um sol poente...", ConsoleColors.YELLOW_BRIGHT);
        ConsoleColors.success("Entraste em Vermilion City, o Porto dos Grandes Encontros!");
        System.out.println("\nPressiona Enter para explorar a cidade portuária...");
        input.nextLine();

        boolean inCity = true;
        while (inCity) {
            ConsoleColors.clear();
            ConsoleColors.title("Vermilion City - O Porto de Kanto");
            ConsoleColors.separator();

            System.out.println("\n O que vais fazer a seguir? ");
            System.out.println("1. Desafiar o Lt. Surge, o Gym Leader");
            System.out.println("2. Comprar itens na Pooké Shop");
            System.out.println("3. Treinar o teu Pookémon nas imediações (Diglett's Cave e campos de arroz)");
            System.out.println("4. Descansar no PookéCenter");
            ConsoleColors.separator();
            ConsoleColors.print("Escolha: ", ConsoleColors.YELLOW_BOLD);
            int cityChoice = input.nextInt();
            input.nextLine();
            switch (cityChoice) {
                case 1:
                    ConsoleColors.clear();
                    Gym vermilionCityGym = new Gym("Vermilion City Gym", 21, 2471);
                    vermilionCityGym.addPokemon(new PokemonWildGymSurge("Voltorb", 40, 40, 35, 55, 2, 2, 21, 612, 635));
                    vermilionCityGym.addPokemon(new PokemonWildGymSurge("Pikachu", 45, 45, 55, 50, 2, 2, 18, 350, 500));
                    vermilionCityGym.addPokemon(new PokemonWildGymSurge("Raichu", 60, 60, 90, 90, 2, 2, 24, 800, 1200));

                    if (player.getPokemonInUse().getLevel() < vermilionCityGym.getMinLevelToBattle()) {
                        ConsoleColors.error("Lt. Surge barra-te a entrada! 'Volta quando fores um soldado de elite! Nível mínimo: " + vermilionCityGym.getMinLevelToBattle() + "'");
                        System.out.println("\nPressiona Enter para bater em retirada e treinar...");
                        input.nextLine();
                        break;
                    }

                    ConsoleColors.title("DESAFIO NO GINÁSIO ELÉTRICO");
                    ConsoleColors.story("Entras num ginásio repleto de geradores de alta voltagem e trincos eletrónicos.");
                    ConsoleColors.warning("Lt. Surge: 'Vais levar um choque de realidade, miúdo/a! O meu Raichu esmaga qualquer Pookémon básico!'");
                    ConsoleColors.separator();
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : vermilionCityGym.getPokemonGym()) {
                        ConsoleColors.println("\nLt. Surge ordena o ataque de " + gymPokemon.getName() + "!", ConsoleColors.YELLOW_BOLD);
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
                        ConsoleColors.separator();
                        ConsoleColors.success("Incrível! Superaste a descarga elétrica do Tenente e conquistaste o Thunder Badge! ⚡");
                        player.addCoins(vermilionCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                        System.out.println("\nPressiona Enter para seguir viagem para Celadon City...");
                        input.nextLine();
                    }
                    break;
                case 2:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ SHOP - VERMILION CITY");
                    System.out.println("Moedas Disponíveis: " + player.getCoins() + " coins\n");

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

                    System.out.println("Mercadorias importadas disponíveis hoje:");
                    for (int i = 0; i < shopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + shopItems.get(i).getName()
                                + " - " + shopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair do estabelecimento");
                    ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                    int shopChoice = input.nextInt();
                    input.nextLine();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            ConsoleColors.success("Transação concluída! Adquiriste " + chosen.getName() + ". Tens agora: " + player.getCoins() + " coins.");
                        } else {
                            ConsoleColors.error("Moedas insuficientes para processar a compra.");
                        }
                    } else {
                        ConsoleColors.error("Escolha de produto inválida!");
                    }
                    System.out.println("\nPressiona Enter para regressar ao porto de Vermilion...");
                    input.nextLine();
                    break;
                case 3:
                    System.out.println("Escolheste ir treinar o teu Pookémon!");
                    boolean training = true;

                    while (training) {
                        ConsoleColors.clear();
                        ConsoleColors.title("SABOTAGEM NA DIGLETT'S CAVE, PORTO & CAMPOS DE ARROZ");
                        PokemonWild ekans = new PokemonWild("Ekans", 48, 48, 60, 50, 1, 1, 18, 350, 300); // Red exclusivo
                        PokemonWild sandshrew = new PokemonWild("Sandshrew", 58, 58, 75, 30, 1, 1, 18, 350, 300); // Blue exclusivo
                        PokemonWild drowzee = new PokemonWild("Drowzee", 68, 68, 48, 93, 1, 1, 20, 400, 400);
                        PokemonWild voltorb = new PokemonWild("Voltorb", 50, 50, 30, 55, 1, 1, 20, 400, 350);
                        PokemonWild meowth = new PokemonWild("Meowth", 50, 50, 45, 40, 1, 1, 18, 300, 300);
                        PokemonWild diglett = new PokemonWild("Diglett", 22, 22, 55, 45, 1, 1, 19, 350, 300);
                        PokemonWild jigglypuff = new PokemonWild("Jigglypuff", 125, 125, 45, 25, 1, 1, 18, 300, 250);
                        PokemonWild doduo = new PokemonWild("Doduo", 55, 55, 85, 35, 1, 1, 20, 350, 300); // Route 22 e arredores, nível 20
                        PokemonWild mankey = new PokemonWild("Mankey", 50, 50, 80, 35, 1, 1, 18, 350, 300); // Route 5/6, agressivo fisicamente
                        PokemonWild growlithe = new PokemonWild("Growlithe", 63, 63, 70, 70, 1, 1, 19, 400, 400); // Red exclusivo, Route 7/8
                        PokemonWild tentacool = new PokemonWild("Tentacool", 52, 52, 40, 100, 2, 2, 20, 450, 400);
                        PokemonWild shellder = new PokemonWild("Shellder", 42, 42, 65, 45, 2, 2, 20, 400, 350);
                        PokemonWild horsea = new PokemonWild("Horsea", 40, 40, 40, 70, 2, 2, 20, 400, 350); // Special alto para o nível
                        PokemonWild seel = new PokemonWild("Seel", 70, 70, 45, 70, 2, 2, 21, 450, 400); // aguenta bem, Special decente
                        PokemonWild staryu = new PokemonWild("Staryu", 52, 52, 45, 70, 2, 2, 21, 450, 400); // aparece em água em várias zonas de Kanto

                        PokemonWild[] wildPokemonVermilionCity = {
                                ekans, sandshrew, drowzee, voltorb, meowth,
                                diglett, jigglypuff, doduo, mankey, growlithe,
                                tentacool, shellder, horsea, seel, staryu
                        };
                        Random random = new Random();
                        PokemonWild enemy = wildPokemonVermilionCity[random.nextInt(wildPokemonVermilionCity.length)];

                        ConsoleColors.warning("Um " + enemy.getName() + " selvagem apareceu!");
                        ConsoleColors.separator();
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();

                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Vitória limpa! Saqueaste " + enemy.getCoins() + " moedas do oponente.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nO que planeias fazer de seguida?");
                            System.out.println("1. Continuar a explorar as imediações bravias");
                            System.out.println("2. Cessar treino e voltar às ruas principais");
                            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice = input.nextInt();
                            input.nextLine();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉCENTER - VERMILION DOCK BRANCH");
                    ConsoleColors.story("Nurse Joy: 'Vêm cansados da Diglett's Cave? Vou reenergizar o teu " + player.getPokemonInUse() + " num piscar de olhos!'");
                    ConsoleColors.separator();
                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();

                    ConsoleColors.success("Os circuitos de vida e os PP do teu companheiro foram totalmente estabilizados!");
                    player.showDetails();
                    System.out.println("\nPressiona Enter para sair para o cais...");
                    input.nextLine();
                    break;
                default:
                    ConsoleColors.error("Os marinheiros locais não conhecem essa instrução!");
                    System.out.println("\nPressiona Enter para tentar novamente...");
                    input.nextLine();
                    break;
            }
        }
        celadonCity();
    }

    public void celadonCity() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("A ROTA 7 E O BLOQUEIO ADORMECIDO");
        ConsoleColors.separator();
        ConsoleColors.story("Subindo Kanto em direção ao norte, cruzas os caminhos arborizados da Rota 7.");
        ConsoleColors.story("Diziam os rumores no porto que a passagem estava bloqueada por uma montanha viva.");
        ConsoleColors.story("À chegada, deparas-te com a icónica cena: um Snorlax gigante dorme profundamente na berma.");
        ConsoleColors.story("Obrigado a contornar o obstáculo pelas ciclovias, avistas ao longe os néons e os grandes edifícios.");
        ConsoleColors.separator();
        ConsoleColors.println("A pacatez dá lugar à azáfama da maior metrópole da região...", ConsoleColors.PURPLE_BRIGHT);
        ConsoleColors.success("Entraste em Celadon City, a Cidade dos Sonhos Arco-Íris!");
        System.out.println("\nPressiona Enter para avançar...");
        input.nextLine();


        // desbloqueia secret cave com 3+ crachás
        if (player.getGymBadge() >= 3) {
            ConsoleColors.clear();
            ConsoleColors.println("No desvio rochoso a caminho da cidade, reparas numa fenda estranha escondida atrás duma cascata...", ConsoleColors.PURPLE_BOLD);
            ConsoleColors.story("A energia estática que emana dali faz o teu Pookémon recuar com medo.");
            ConsoleColors.separator();
            System.out.println("Desejas arriscar e investigar as profundezas?");
            System.out.println("1. Investigar a Caverna Secreta...");
            System.out.println("2. Ignorar e seguir para o centro de Celadon City");
            ConsoleColors.separator();
            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
            int caveDecision = input.nextInt();
            input.nextLine();
            if (caveDecision == 1) {
                secretCave();
            }
        }

        boolean inCity = true;
        while (inCity) {
            ConsoleColors.clear();
            ConsoleColors.title("Celadon City - A Metrópole Arco-Íris");
            ConsoleColors.separator();

            System.out.println("O que vais fazer a seguir?");
            System.out.println("1. Desafiar a Erika (Celadon Gym)");
            System.out.println("2. Visitar a Grande Pooké Shop (Department Store)");
            System.out.println("3. Treinar nos Arredores (Safari Zone & Rotas)");
            System.out.println("4. Descansar no PookéCenter");
            ConsoleColors.separator();
            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
            int cityChoice = input.nextInt();
            input.nextLine();
            switch (cityChoice) {
                case 1:
                    Gym celadonCityGym = new Gym("Celadon City Gym", 27, 2831);
                    celadonCityGym.addPokemon(new PokemonWildGymErika("Victreebel", 80, 80, 105, 100, 2, 2, 29, 1423, 892));
                    celadonCityGym.addPokemon(new PokemonWildGymErika("Vileplume", 75, 75, 80, 100, 2, 2, 29, 1842, 1500));

                    if (player.getPokemonInUse().getLevel() < celadonCityGym.getMinLevelToBattle()) {
                        ConsoleColors.error("As treinadoras barram-te a entrada! 'O aroma deste ginásio exige mais maturidade técnica! Nível mínimo: " + celadonCityGym.getMinLevelToBattle() + "'");
                        System.out.println("\nPressiona Enter para voltar às ruas e treinar...");
                        input.nextLine();
                        break;
                    }

                    ConsoleColors.title("DESAFIO NO GINÁSIO DA NATUREZA");
                    ConsoleColors.story("Entras numa estufa repleta de flores raras e aromas tranquilizantes.");
                    ConsoleColors.warning("Erika: 'Os meus Pookémon do tipo Planta partilham da calmaria da natureza... mas atacam com a força de uma tempestade!'");
                    ConsoleColors.separator();
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : celadonCityGym.getPokemonGym()) {
                        ConsoleColors.println("\nErika liberta o aroma de " + gymPokemon.getName() + " na arena da estufa!", ConsoleColors.GREEN_BOLD);
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
                        ConsoleColors.separator();
                        ConsoleColors.success("Vitória brilhante! Superaste as táticas de status da Erika e conquistaste o Rainbow Badge! 🌈");
                        player.addCoins(celadonCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                        System.out.println("\nPressiona Enter para continuar o teu caminho em direção a Fuchsia City...");
                        input.nextLine();
                    }
                    break;
                case 2:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ SHOP - CELADON MEGA DEPT. STORE");
                    System.out.println("Balanço Financeiro: " + player.getCoins() + " coins\n");

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

                    System.out.println("Produtos em destaque nos balcões do 4º andar:");
                    for (int i = 0; i < shopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + shopItems.get(i).getName()
                                + " - " + shopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair dos Grandes Armazéns");
                    ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
                    int shopChoice = input.nextInt();
                    input.nextLine();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            ConsoleColors.success("Compra efetuada! Adicionaste " + chosen.getName() + " à mochila! Tens agora " + player.getCoins() + " coins!");
                        } else {
                            ConsoleColors.error("Não tens saldo bancário suficiente para este artigo.");
                        }
                    } else {
                        ConsoleColors.error("Código de artigo inexistente no catálogo!");
                    }
                    System.out.println("\nPressiona Enter para voltar à avenida central...");
                    input.nextLine();
                    break;
                case 3:
                    System.out.println("Escolheste ir treinar o teu Pookémon!");
                    boolean training = true;
                    ConsoleColors.clear();
                    ConsoleColors.title("ÁREA SAFARI & RESERVAS BRAVIAS DE CELADON");

                    while (training) {
                        PokemonWild vulpix = new PokemonWild("Vulpix", 48, 48, 41, 65, 1, 1, 25, 500, 400); // Red exclusivo
                        PokemonWild growlithe2 = new PokemonWild("Growlithe", 67, 67, 70, 70, 1, 1, 27, 550, 450); // Blue exclusivo
                        PokemonWild ponyta = new PokemonWild("Ponyta", 73, 73, 85, 65, 1, 1, 26, 500, 400);
                        PokemonWild doduo2 = new PokemonWild("Doduo", 59, 59, 85, 35, 1, 1, 25, 400, 350);
                        PokemonWild meowth2 = new PokemonWild("Meowth", 54, 54, 45, 40, 1, 1, 24, 350, 300);
                        PokemonWild persian = new PokemonWild("Persian", 70, 70, 70, 65, 1, 1, 28, 600, 500);
                        PokemonWild scyther = new PokemonWild("Scyther", 80, 80, 110, 55, 2, 2, 28, 700, 600); // raro, Safari Zone
                        PokemonWild kangaskhan = new PokemonWild("Kangaskhan", 115, 115, 95, 40, 2, 2, 28, 800, 700); // Safari Zone
                        PokemonWild tauros = new PokemonWild("Tauros", 95, 95, 100, 70, 2, 2, 27, 750, 650); // Safari Zone
                        PokemonWild lickitung = new PokemonWild("Lickitung", 90, 90, 55, 60, 2, 2, 26, 600, 500);
                        PokemonWild slowpoke = new PokemonWild("Slowpoke", 90, 90, 65, 40, 2, 2, 25, 500, 400);
                        PokemonWild krabby2 = new PokemonWild("Krabby", 49, 49, 105, 25, 2, 2, 25, 550, 400);
                        PokemonWild poliwhirl = new PokemonWild("Poliwhirl", 70, 70, 65, 50, 2, 2, 27, 600, 500);
                        PokemonWild horsea2 = new PokemonWild("Horsea", 44, 44, 40, 70, 2, 2, 25, 450, 400);
                        PokemonWild goldeen2 = new PokemonWild("Goldeen", 61, 61, 67, 50, 2, 2, 25, 450, 400);

                        PokemonWild[] wildPokemonCeladonCity = {
                                vulpix, growlithe2, ponyta, doduo2, meowth2,
                                persian, scyther, kangaskhan, tauros, lickitung,
                                slowpoke, krabby2, poliwhirl, horsea2, goldeen2
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCeladonCity[random.nextInt(wildPokemonCeladonCity.length)];

                        ConsoleColors.warning("Um vulto moveu-se rapidamente na erva alta... É um " + enemy.getName() + " feroz!");
                        ConsoleColors.separator();
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Vitória tática! Arrecadaste " + enemy.getCoins() + " coins do Pookémon derrotado.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nQual é o teu próximo passo?");
                            System.out.println("1. Continuar a treinar na Safari Zone");
                            System.out.println("2. Terminar a expedição e regressar ao centro");
                            ConsoleColors.print("Escolha: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice = input.nextInt();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉCENTER - CENTRAL METROPOLITANA");
                    ConsoleColors.story("Nurse Joy: 'A azáfama da grande cidade esgota qualquer um. Deixa-me tratar do teu Pookémon!'");
                    ConsoleColors.separator();
                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    ConsoleColors.success("Energia vital e pontos de ataque especial (PP) restaurados na totalidade!");
                    player.showDetails();
                    System.out.println("\nPressiona Enter para regressar às avenidas...");
                    input.nextLine();
                    break;
                default:
                    ConsoleColors.error("Essa opção infringe as regras de trânsito locais!");
                    System.out.println("\nPressiona Enter para tentar novamente...");
                    input.nextLine();
                    break;
            }
        }
        fuchsiaCity();
    }

    public void fuchsiaCity() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("A DESCIDA PELA CYCLING ROAD");
        ConsoleColors.separator();
        ConsoleColors.story("Montado na tua bicicleta, largas os arranha-céus de Celadon para trás.");
        ConsoleColors.story("Cruzas a Rota 17, a famosa Cycling Road, sentindo o vento costeiro no rosto.");
        ConsoleColors.story("Pelo caminho, desvias-te de gangues de motards e vês a vegetação tornar-se densa.");
        ConsoleColors.story("O ar começa a cheirar a pântano e a plantas tropicais à medida que te aproximas do sul.");
        ConsoleColors.separator();
        ConsoleColors.println("Árvores exóticas e muros de pedra revelam uma vila escondida...", ConsoleColors.GREEN_BRIGHT);
        ConsoleColors.success("Entraste em Fuchsia City, o Reduto Secreto dos Ninjas!");
        System.out.println("\nPressiona Enter para explorar a cidade...");
        input.nextLine();

        boolean inCity = true;
        while (inCity) {
            ConsoleColors.clear();
            ConsoleColors.title("Fuchsia City - A Vila do Clã Ninja");
            ConsoleColors.separator();

            System.out.println("O que vais fazer a seguir?");
            System.out.println("1. Desafiar o Mestre Koga (Fuchsia Gym)");
            System.out.println("2. Entrar na Pooké Shop da Vila");
            System.out.println("3. Treinar nos Arredores (Limites da Safari Zone)");
            System.out.println("4. Visitar o PookéCenter local");
            ConsoleColors.separator();
            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
            int cityChoice = input.nextInt();
            input.nextLine();
            switch (cityChoice) {
                case 1:
                    Gym fuchsiaCityGym = new Gym("Fuchsia City Gym", 35, 3278);
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Koffing", 70, 70, 65, 85, 3, 3, 37, 1200, 635));
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Muk", 137, 137, 98, 71, 3, 3, 39, 1450, 1100));
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Koffing", 76, 76, 63, 88, 3, 3, 37, 1200, 635));
                    fuchsiaCityGym.addPokemon(new PokemonWildGymKoga("Weezing", 112, 112, 91, 121, 4, 4, 43, 1950, 2100));

                    if (player.getPokemonInUse().getLevel() < fuchsiaCityGym.getMinLevelToBattle()) {
                        ConsoleColors.error("Koga surge das sombras: 'Falta-te camuflagem e poder. Não permito que fracos enfrentem o meu veneno! Nível mínimo: " + fuchsiaCityGym.getMinLevelToBattle() + "'");
                        System.out.println("\nPressiona Enter para recuar...");
                        input.nextLine();
                        break;
                    }

                    ConsoleColors.title("DESAFIO NO GINÁSIO NINJA");
                    ConsoleColors.story("Entras num dojo cheio de paredes falsas e armadilhas invisíveis.");
                    ConsoleColors.warning("Koga: 'A minha técnica milenar vai envenenar as tuas hipóteses de vitória! Prepara-te para o sufoco!'");
                    ConsoleColors.separator();
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : fuchsiaCityGym.getPokemonGym()) {
                        ConsoleColors.println("\nUma bomba de fumo explode! Koga invoca " + gymPokemon.getName() + "!", ConsoleColors.PURPLE_BOLD);
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
                        ConsoleColors.separator();
                        ConsoleColors.success("Incrível! Rompeste a névoa tóxica do dojo e conquistaste o Soul Badge! ");
                        player.addCoins(fuchsiaCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                        System.out.println("\nPressiona Enter para continuar a viagem...");
                        input.nextLine();
                    }
                    break;
                case 2:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ SHOP - FUCHSIA CITY");
                    System.out.println("Balanço Atual: " + player.getCoins() + " coins\n");

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

                    System.out.println("Suprimentos médicos e táticos disponíveis:");
                    for (int i = 0; i < shopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + shopItems.get(i).getName()
                                + " - " + shopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair do estabelecimento");
                    ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
                    int shopChoice = input.nextInt();
                    input.nextLine();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            ConsoleColors.success("Compraste " + chosen.getName() + "! Tens agora " + player.getCoins() + " coins.");
                        } else {
                            ConsoleColors.error("Falta de moedas para efetuar a transação.");
                        }
                    } else {
                        ConsoleColors.error("Artigo inválido.");
                    }
                    System.out.println("\nPressiona Enter para voltar à praça...");
                    input.nextLine();
                    break;
                case 3:
                    ConsoleColors.clear();
                    ConsoleColors.title("FLORESTA EXÓTICA E LIMÍTROFES DA SAFARI ZONE");
                    boolean training = true;

                    while (training) {
                        PokemonWild pinsir = new PokemonWild("Pinsir", 90, 90, 125, 55, 2, 2, 33, 800, 700); // Safari Zone, rival do Scyther
                        PokemonWild rhyhorn2 = new PokemonWild("Rhyhorn", 90, 90, 130, 30, 2, 2, 32, 750, 600);
                        PokemonWild nidorina = new PokemonWild("Nidorina", 80, 80, 62, 55, 2, 2, 31, 650, 550);
                        PokemonWild nidorino = new PokemonWild("Nidorino", 81, 81, 72, 57, 2, 2, 31, 650, 550);
                        PokemonWild exeggcute = new PokemonWild("Exeggcute", 80, 80, 40, 45, 2, 2, 30, 550, 450); // Safari Zone
                        PokemonWild parasect = new PokemonWild("Parasect", 80, 80, 95, 80, 2, 2, 32, 700, 550);
                        PokemonWild venomoth = new PokemonWild("Venomoth", 80, 80, 65, 90, 2, 2, 33, 700, 550);
                        PokemonWild weepinbell = new PokemonWild("Weepinbell", 80, 80, 90, 85, 2, 2, 31, 650, 500);
                        PokemonWild gloom = new PokemonWild("Gloom", 75, 75, 55, 75, 2, 2, 30, 600, 500);
                        PokemonWild ditto = new PokemonWild("Ditto", 76, 76, 48, 48, 2, 2, 31, 600, 500);
                        PokemonWild tentacruel = new PokemonWild("Tentacruel", 90, 90, 70, 120, 2, 2, 34, 850, 700);
                        PokemonWild slowbro = new PokemonWild("Slowbro", 100, 100, 75, 100, 2, 2, 33, 800, 650);
                        PokemonWild seadra = new PokemonWild("Seadra", 75, 75, 65, 95, 2, 2, 32, 750, 600);
                        PokemonWild seaking = new PokemonWild("Seaking", 95, 95, 92, 65, 2, 2, 33, 750, 600);
                        PokemonWild dewgong = new PokemonWild("Dewgong", 100, 100, 70, 70, 2, 2, 33, 750, 600);

                        PokemonWild[] wildPokemonFuchsiaCity = {
                                pinsir, rhyhorn2, nidorina, nidorino, exeggcute,
                                parasect, venomoth, weepinbell, gloom, ditto,
                                tentacruel, slowbro, seadra, seaking, dewgong
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonFuchsiaCity[random.nextInt(wildPokemonFuchsiaCity.length)];

                        ConsoleColors.warning("Detetaste movimento nos arbustos pantanosos! Um " + enemy.getName() + " barrou-te o passo!");
                        ConsoleColors.separator();
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Alvo derrotado! Encontraste " + enemy.getCoins() + " moedas perdidas na lama.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nO que pretendes fazer?");
                            System.out.println("1. Continuar a treinar na zona densa");
                            System.out.println("2. Terminar a patrulha e regressar à civilização");
                            ConsoleColors.print("Escolha: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice = input.nextInt();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉCENTER - SAFARI EDGE BRANCH");
                    ConsoleColors.story("Nurse Joy: 'A toxina destes trilhos é traiçoeira. Vou desintoxicar e recuperar o teu pookémon.'");
                    ConsoleColors.separator();
                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    player.showDetails();
                    System.out.println("\nPressiona Enter para sair do PookéCenter...");
                    input.nextLine();
                    break;
                default:
                    ConsoleColors.error("Ação desconhecida para as táticas ninja!");
                    System.out.println("\nPressiona Enter para tentar novamente...");
                    input.nextLine();
                    break;
            }
        }
        saffronCity();

    }

    public void saffronCity() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("A CAPITAL SOB O DOMÍNIO DA TEAM ROCKET");
        ConsoleColors.separator();
        ConsoleColors.story("Viajando até ao coração de Kanto, chegas à gigantesca metrópole de Saffron City.");
        ConsoleColors.story("Ao contrário das outras cidades, as ruas estão silenciosas e guardadas por capangas.");
        ConsoleColors.story("A Team Rocket invadiu o arranha-céus da Silph Co. para roubar o protótipo da Master Ball!");
        ConsoleColors.story("Junto com outros treinadores corajosos, geras uma distração em massa, forças os portões");
        ConsoleColors.story("e ajudas a expulsar Giovanni e os seus capangas num confronto épico nos escritórios centrais.");
        ConsoleColors.separator();
        ConsoleColors.println("Com a cidade finalmente livre, os civis festejam e os bloqueios caem...", ConsoleColors.CYAN_BRIGHT);
        ConsoleColors.success("Entraste em Saffron City, a Capital Dourada do Poder Psíquico!");
        System.out.println("\nPressiona Enter para avançar pelas avenidas desimpedidas...");
        input.nextLine();

        boolean inCity = true;
        while (inCity) {
            ConsoleColors.clear();
            ConsoleColors.title("Saffron City - O Centro Tecnológico e Psíquico");
            ConsoleColors.separator();

            System.out.println("O que vais fazer a seguir?");
            System.out.println("1. Desafiar a Sabrina (Saffron Gym)");
            System.out.println("2. Visitar o Centro Comercial de Saffron (Pooké Shop)");
            System.out.println("3. Treinar nos Arredores (Magnet Train tracks & Rotas)");
            System.out.println("4. Ir ao PookéCenter Central");
            ConsoleColors.separator();
            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
            int cityChoice = input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym saffronCityGym = new Gym("Saffron City Gym", 18, 2142);
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Kadabra", 45, 45, 35, 120, 3, 3, 38, 1800, 2000));
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Mr. Mime", 87, 87, 75, 83, 3, 3, 37, 1600, 1800));
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Venomoth", 107, 107, 74, 71, 3, 3, 38, 1750, 1900));
                    saffronCityGym.addPokemon(new PokemonWildGymSabrina("Alakazam", 108, 108, 131, 57, 5, 5, 43, 2500, 2500));

                    if (player.getPokemonInUse().getLevel() < saffronCityGym.getMinLevelToBattle()) {
                        ConsoleColors.error("Uma barreira invisível telecinética empurra-te para trás! 'A tua mente não está sintonizada! Nível mínimo exigido: " + saffronCityGym.getMinLevelToBattle() + "'");
                        System.out.println("\nPressiona Enter para voltar e focar a tua energia no treino...");
                        input.nextLine();
                        break;
                    }

                    ConsoleColors.title("DESAFIO NO GINÁSIO PSÍQUICO");
                    ConsoleColors.story("Atravessas uma rede complexa de plataformas de teletransporte.");
                    ConsoleColors.warning("Sabrina: 'Eu tive uma visão da tua chegada... Não gosto de lutar, mas vou demonstrar o poder da telecinese!'");
                    ConsoleColors.separator();
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : saffronCityGym.getPokemonGym()) {
                        ConsoleColors.println("\nObjetos começam a flutuar! Sabrina envia " + gymPokemon.getName() + " através da mente!", ConsoleColors.CYAN_BOLD);
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
                        ConsoleColors.separator();
                        ConsoleColors.success("Vitória inacreditável! Quebraste as ilusões psíquicas da Sabrina e ganhaste o Marsh Badge! ");
                        player.addCoins(saffronCityGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                        System.out.println("\nPressiona Enter para traçar rota em direção a Cinnabar Island...");
                        input.nextLine();
                    }
                    break;
                case 2:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ SHOP - SAFFRON MEGA MALL");
                    System.out.println("Balanço Bancário: " + player.getCoins() + " coins\n");

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

                    System.out.println("Consumíveis e poções de alta performance Silph Co. em stock:");
                    for (int i = 0; i < shopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + shopItems.get(i).getName()
                                + " - " + shopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair das Galerias");

                    ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
                    int shopChoice = input.nextInt();
                    input.nextLine();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            ConsoleColors.success("Transação concluída! " + chosen.getName() + " adicionado à mala. Tens agora " + player.getCoins() + " coins!");
                        } else {
                            ConsoleColors.error("Saldo insuficiente para adquirir este artigo.");
                        }
                    } else {
                        ConsoleColors.error("Seleção indisponível.");
                    }
                    System.out.println("\nPressiona Enter para voltar à praça central...");
                    input.nextLine();
                    break;
                case 3:
                    System.out.println("Escolheste ir treinar o teu Pookémon!");
                    boolean training = true;

                    while (training) {
                        ConsoleColors.clear();
                        ConsoleColors.title("ZONA METROPOLITANA E ARREDORES BRAVIOS");

                        PokemonWild kadabra2 = new PokemonWild("Kadabra", 48, 48, 35, 120, 2, 2, 38, 900, 700);
                        PokemonWild haunter = new PokemonWild("Haunter", 55, 55, 50, 95, 2, 2, 36, 850, 650);
                        PokemonWild gengar = new PokemonWild("Gengar", 75, 75, 65, 130, 3, 3, 40, 1200, 900); // raro
                        PokemonWild electabuzz = new PokemonWild("Electabuzz", 85, 85, 83, 95, 2, 2, 38, 900, 750);
                        PokemonWild magmar = new PokemonWild("Magmar", 85, 85, 95, 100, 2, 2, 38, 900, 750);
                        PokemonWild porygon = new PokemonWild("Porygon", 85, 85, 60, 75, 2, 2, 36, 850, 700);
                        PokemonWild mr_mime = new PokemonWild("Mr. Mime", 55, 55, 45, 100, 2, 2, 37, 850, 650);
                        PokemonWild jynx = new PokemonWild("Jynx", 75, 75, 50, 115, 2, 2, 38, 900, 700);
                        PokemonWild persian2 = new PokemonWild("Persian", 74, 74, 70, 65, 2, 2, 37, 700, 600);
                        PokemonWild clefairy = new PokemonWild("Clefairy", 80, 80, 45, 60, 2, 2, 35, 700, 550);
                        PokemonWild poliwrath = new PokemonWild("Poliwrath", 100, 100, 95, 70, 3, 3, 40, 1000, 800);
                        PokemonWild gyarados = new PokemonWild("Gyarados", 130, 130, 125, 100, 3, 3, 42, 1500, 1200); // raro mas devastador
                        PokemonWild lapras = new PokemonWild("Lapras", 140, 140, 85, 95, 3, 3, 40, 1200, 1000);
                        PokemonWild vaporeon = new PokemonWild("Vaporeon", 140, 140, 65, 110, 3, 3, 40, 1100, 900);
                        PokemonWild starmie2 = new PokemonWild("Starmie", 80, 80, 75, 100, 3, 3, 40, 1200, 900);

                        PokemonWild[] wildPokemonSaffronCity = {
                                kadabra2, haunter, gengar, electabuzz, magmar,
                                porygon, mr_mime, jynx, persian2, clefairy,
                                poliwrath, gyarados, lapras, vaporeon, starmie2
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonSaffronCity[random.nextInt(wildPokemonSaffronCity.length)];

                        ConsoleColors.warning("Uma anomalia de energia manifesta-se! É um " + enemy.getName() + " pronto para combater!");
                        ConsoleColors.separator();
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Vitória confirmada! Recebeste " + enemy.getCoins() + " coins do teu combate.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nO que desejas fazer?");
                            System.out.println("1. Procurar novos sinais biológicos para treinar");
                            System.out.println("2. Encerrar simulação de terreno e regressar");
                            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice = input.nextInt();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉCENTER - SILPH METRO HQ BRANCH");
                    ConsoleColors.story("Nurse Joy: 'A fadiga mental urbana afeta os nossos reflexos... Vou restaurar o equilíbrio cósmico do teu companheiro.'");
                    ConsoleColors.separator();
                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    ConsoleColors.success("Remediação psíquica e vital efetuada com sucesso!");
                    player.showDetails();
                    System.out.println("\nPressiona Enter para voltar às ruas limpas...");
                    input.nextLine();
                    break;
                default:
                    ConsoleColors.error("Opção inválida na inteligência central urbana.");
                    System.out.println("\nPressiona Enter para recalibrar...");
                    input.nextLine();
                    break;
            }
        }
        cinnabarIsland();
    }

    public void cinnabarIsland() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        // momento de historia
        ConsoleColors.clear();
        ConsoleColors.title("A TRAVESSIA PELAS ROTAS MARÍTIMAS");
        ConsoleColors.separator();
        ConsoleColors.story("Deixando os limites continentais de Kanto, arranjas boleia num Pookémon de água, o taxista Lapras.");
        ConsoleColors.story("Navegas pelas correntes geladas da Rota 20, contornando as Seafoam Islands.");
        ConsoleColors.story("O mar revolto dá lugar a uma brisa invulgarmente quente e a um cheiro a enxofre.");
        ConsoleColors.story("Ao longe, ergue-se um vulcão massivo e as ruínas sombrias da antiga Pookémon Mansion,");
        ConsoleColors.story("o laboratório abandonado onde dizem ter nascido a criatura mais poderosa do mundo.");
        ConsoleColors.separator();
        ConsoleColors.println("A areia vulcânica negra dá as boas-vindas à ilha dos cientistas...", ConsoleColors.RED_BRIGHT);
        ConsoleColors.success("Chegaste a Cinnabar Island, a Fortaleza de Fogo e da Ciência!");
        System.out.println("\nPressiona Enter para atracar na costa...");
        input.nextLine();

        boolean inCity = true;
        while (inCity) {

            ConsoleColors.clear();
            ConsoleColors.title("Cinnabar Island - O Laboratório Vulcânico");
            ConsoleColors.separator();

            System.out.println("O que vais fazer a seguir?");
            System.out.println("1. Desafiar o Blaine (Cinnabar Gym)");
            System.out.println("2. Entrar no Posto Comercial Avançado (Pooké Shop)");
            System.out.println("3. Treinar na Costa e Encostas do Vulcão");
            System.out.println("4. Ir ao PookéCenter da Ilha");
            ConsoleColors.separator();
            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);


            int cityChoice = input.nextInt();
            input.nextLine();
            switch (cityChoice) {
                case 1:
                    Gym cinnabarIslandGym = new Gym("Cinnabar Island Gym", 45, 2142);
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Growlithe", 65, 65, 70, 70, 4, 4, 42, 1460, 1385));
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Ponyta", 98, 98, 86, 61, 4, 4, 40, 1350, 1200));
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Rapidash", 119, 119, 101, 76, 4, 4, 42, 1750, 1600));
                    cinnabarIslandGym.addPokemon(new PokemonWildGymBlaine("Arcanine", 154, 154, 126, 91, 5, 5, 47, 1899, 1953));

                    if (player.getPokemonInUse().getLevel() < cinnabarIslandGym.getMinLevelToBattle()) {
                        ConsoleColors.error("O calor que emana das portas do ginásio derrete as tuas intenções! 'O teu Pookémon não aguenta o magma! Nível mínimo exigido: " + cinnabarIslandGym.getMinLevelToBattle() + "'");
                        System.out.println("\nPressiona Enter para voltar às águas e treinar...");
                        input.nextLine();
                        break;
                    }

                    ConsoleColors.title("CONFRONTO SOU INFRAVERMELHO - CINNABAR ISLAND GYM");
                    ConsoleColors.story("Entras numa arena suspensa diretamente sobre um poço de lava a esfumaçar.");
                    ConsoleColors.warning("Blaine: 'Hah! É melhor teres antídotos para queimaduras, miúdo/a! O meu fogo incinera qualquer estratégia!'");
                    ConsoleColors.separator();
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : cinnabarIslandGym.getPokemonGym()) {
                        ConsoleColors.println("\nO ar distorce-se com o calor! Blaine envia " + gymPokemon.getName() + " para derreter o campo!", ConsoleColors.RED_BOLD);
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
                        ConsoleColors.separator();
                        ConsoleColors.success("Vitória ardente! Superaste o teste térmico do Blaine e conquistaste a Volcano Badge! ");
                        player.addCoins(cinnabarIslandGym.getReward());
                        player.addGymBadge();
                        inCity = false; // avança para a próxima cidade
                        System.out.println("\nPressiona Enter para voar em direção ao último desafio em Viridian City...");
                        input.nextLine();
                    }
                    break;
                case 2:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ SHOP - CINNABAR SCIENTIFIC SUPPLY");
                    System.out.println("Moedas Disponíveis: " + player.getCoins() + " coins\n");

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

                    System.out.println("Itens de suporte laboratorial e militar em stock:");
                    for (int i = 0; i < shopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + shopItems.get(i).getName()
                                + " - " + shopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair do laboratório comercial");
                    ConsoleColors.print("\nEscolha: ", ConsoleColors.YELLOW_BOLD);
                    int shopChoice = input.nextInt();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            System.out.println("Adquiriste: " + chosen.getName() + "! Tens agora " + player.getCoins() + " coins.");
                        } else {
                            ConsoleColors.error("Os teus fundos comerciais não chegam para pagar este item.");
                        }
                    } else {
                        ConsoleColors.error("Código de artigo inválido.");
                    }
                    System.out.println("\nPressiona Enter para regressar à costa...");
                    input.nextLine();
                    break;
                case 3:
                    boolean training = true;

                    while (training) {
                        ConsoleColors.clear();
                        ConsoleColors.title("TRILHOS DE MAGMA E ARRECADAÇÕES MARÍTIMAS");

                        PokemonWild magmar2 = new PokemonWild("Magmar", 91, 91, 95, 100, 3, 3, 43, 1000, 800);
                        PokemonWild rapidash = new PokemonWild("Rapidash", 103, 103, 100, 80, 3, 3, 44, 1100, 900);
                        PokemonWild ninetales = new PokemonWild("Ninetales", 98, 98, 76, 100, 3, 3, 43, 1100, 900);
                        PokemonWild flareon = new PokemonWild("Flareon", 105, 105, 130, 95, 3, 3, 44, 1200, 950);
                        PokemonWild ponyta2 = new PokemonWild("Ponyta", 83, 83, 85, 65, 3, 3, 42, 900, 750);
                        PokemonWild dewgong2 = new PokemonWild("Dewgong", 104, 104, 70, 70, 3, 3, 42, 900, 700);
                        PokemonWild seel2 = new PokemonWild("Seel", 78, 78, 45, 70, 3, 3, 40, 800, 600);
                        PokemonWild shellder2 = new PokemonWild("Shellder", 46, 46, 65, 45, 3, 3, 40, 750, 600);
                        PokemonWild cloyster = new PokemonWild("Cloyster", 80, 80, 95, 85, 3, 3, 43, 1000, 800);
                        PokemonWild jynx2 = new PokemonWild("Jynx", 79, 79, 50, 115, 3, 3, 42, 950, 750);
                        PokemonWild tentacruel2 = new PokemonWild("Tentacruel", 94, 94, 70, 120, 3, 3, 43, 1000, 800);
                        PokemonWild seadra2 = new PokemonWild("Seadra", 79, 79, 65, 95, 3, 3, 42, 950, 750);
                        PokemonWild seaking2 = new PokemonWild("Seaking", 99, 99, 92, 65, 3, 3, 43, 950, 750);
                        PokemonWild kingler = new PokemonWild("Kingler", 85, 85, 130, 50, 3, 3, 43, 1100, 900);
                        PokemonWild golduck = new PokemonWild("Golduck", 100, 100, 82, 80, 3, 3, 43, 1000, 800);

                        PokemonWild[] wildPokemonCinnabarIsland = {
                                magmar2, rapidash, ninetales, flareon, ponyta2,
                                dewgong2, seel2, shellder2, cloyster, jynx2,
                                tentacruel2, seadra2, seaking2, kingler, golduck
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonCinnabarIsland[random.nextInt(wildPokemonCinnabarIsland.length)];

                        ConsoleColors.warning("Fervura detetada no ecossistema! Um " + enemy.getName() + " bloqueia o caminho!");
                        ConsoleColors.separator();
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            gameOver();
                            //System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu... Game Over, melhor sorte da próxima!");
                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Inimigo neutralizado! Recolheste " + enemy.getCoins() + " coins calcinadas pela lava.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nQual é o teu próximo passo?");
                            System.out.println("1. Continuar patrulha na encosta vulcânica");
                            System.out.println("2. Suspender o treino e regressar ao centro");
                            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice = input.nextInt();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉCENTER - CINNABAR LAB STATION");
                    ConsoleColors.story("Nurse Joy: 'O calor extremo desgasta as células dos Pookémon. Vou aplicar crioterapia para o estabilizar.'");
                    ConsoleColors.separator();
                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    ConsoleColors.success("Temperatura interna restaurada e HP/PP no máximo de eficiência!");
                    player.showDetails();
                    System.out.println("\nPressiona Enter para voltar à atividade...");
                    input.nextLine();
                    break;
                default:
                    ConsoleColors.error("Comando incompatível com os sistemas da ilha!");
                    System.out.println("\nPressiona Enter para reiniciar entrada...");
                    input.nextLine();
                    break;
            }
        }
        viridianCity();
    }

    public void viridianCity() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("O CÍRCULO FECHA - DE VOLTA AO INÍCIO");
        ConsoleColors.separator();
        ConsoleColors.story("A tua jornada levou-te pelos quatro cantos do continente de Kanto.");
        ConsoleColors.story("Agora, os teus passos guiam-te de volta a Viridian City, a primeira cidade que visitaste.");
        ConsoleColors.story("O outrora trancado e misterioso Ginásio de Viridian abriu finalmente as suas portas.");
        ConsoleColors.story("Os sussurros nas tabernas confirmam a verdade chocante que ninguém ousava proferir:");
        ConsoleColors.story("O líder supremo deste ginásio é o próprio chefe da Team Rocket, o implacável Giovanni.");
        ConsoleColors.separator();
        ConsoleColors.println("O palco está montado para o confronto final pela última insígnia...", ConsoleColors.YELLOW_BRIGHT);
        ConsoleColors.success("Entraste em Viridian City, o Solo Sagrado do Confronto de Terra!");
        System.out.println("\nPressiona Enter para enfrentar o teu destino...");
        input.nextLine();

        boolean inCity = true;
        while (inCity) {
            ConsoleColors.clear();
            ConsoleColors.title("Viridian City - O Ginásio do Terramoto");
            ConsoleColors.separator();

            System.out.println("O que vais fazer a seguir?");
            System.out.println("1. Enfrentar o Giovanni (O Confronto Final)");
            System.out.println("2. Visitar o Arsenal Militar de Elite (Pooké Shop)");
            System.out.println("3. Treinar na Rota de Acesso à Liga (High-Level Zone)");
            System.out.println("4. Ir ao PookéCenter Central");
            ConsoleColors.separator();
            ConsoleColors.print("Escolha: ", ConsoleColors.YELLOW_BOLD);
            int cityChoice = input.nextInt();
            input.nextInt();
            switch (cityChoice) {
                case 1:
                    Gym viridianCityGym = new Gym("Viridian City Gym", 47, 3790);
                    viridianCityGym.addPokemon(new PokemonWildGymGiovani("Rhyhorn", 80, 80, 130, 30, 4, 4, 45, 2000, 1500));
                    viridianCityGym.addPokemon(new PokemonWildGymGiovani("Rhydon", 105, 105, 130, 45, 5, 5, 50, 3000, 2000));
                    viridianCityGym.addPokemon(new PokemonWildGymGiovani("Nidoking", 81, 81, 102, 85, 4, 4, 45, 2500, 1750));

                    if (player.getPokemonInUse().getLevel() < viridianCityGym.getMinLevelToBattle()) {
                        ConsoleColors.error("Os guardas da máfia barram-te com metralhadoras! 'Giovanni não perde tempo com amadores! Força necessária mínima: Nível " + viridianCityGym.getMinLevelToBattle() + "'");
                        System.out.println("\nPressiona Enter para recuar e procurar mais poder nas redondezas selvagens...");
                        input.nextLine();
                        break;
                    }

                    ConsoleColors.title("BATALHA SUPREMA - GIOVANNI, O CHEFE DA MAFIA");
                    ConsoleColors.story("As portas de aço fecham-se atrás de ti. O chão de pedra vibra com tremores de terra.");
                    ConsoleColors.warning("Giovanni: 'Julgas que podes desmantelar o meu império? Eu controlo a terra e tudo o que nela habita! Prepara-te para ser esmagado/a!'");
                    ConsoleColors.separator();
                    boolean gymWon = true;

                    for (Pokemon gymPokemon : viridianCityGym.getPokemonGym()) {
                        ConsoleColors.println("\nO solo racha! Giovanni liberta o devastador " + gymPokemon.getName() + "!", ConsoleColors.RED_BOLD);
                        boolean won = player.getPokemonInUse().pokemonBattle(gymPokemon, player);

                        if (!won) {
                            gymWon = false;
                            inCity = false;
                            ConsoleColors.error("O teu " + player.getPokemonInUse().getName() + " desabou perante o terramoto...");
                            gameOver();
                            break;
                        }
                        player.showDetails();
                    }

                    if (gymWon) {
                        player.addCoins(viridianCityGym.getReward());
                        player.addGymBadge();
                        // momento de historia antes dos creditos do win game (dos gyms)
                        ConsoleColors.clear();
                        ConsoleColors.title(" O NASCIMENTO DE UM NOVO CAMPEÃO ");
                        ConsoleColors.separator();
                        ConsoleColors.success("Giovanni cai de joelhos. O império da Team Rocket desmorona-se permanentemente!");
                        ConsoleColors.story("'Não pode ser... O Earth Badge pertence-te. Eu... eu abdico do meu posto. A Team Rocket está oficialmente dissolvida!'");
                        ConsoleColors.story("Com as 8 insígnias a brilharem no teu casaco impermeável, marchas vitorioso/a através da Victory Road.");
                        ConsoleColors.story("As portas do Hall of Fame abrem-se de par em par para te receber.");
                        ConsoleColors.story("O teu nome e o do teu fiel parceiro são gravados a ouro na história eterna de Kanto.");
                        ConsoleColors.separator();
                        ConsoleColors.println("Obrigado por jogares! O teu destino como Mestre Pookémon foi selado com glória.", ConsoleColors.GREEN_BRIGHT);
                        System.out.println("\nPressiona Enter para assistir às celebrações e ver os créditos finais...");
                        input.nextLine();
                        inCity = false; // ganha jogo
                        winGame(); // ecrã de vitória
                    }
                    break;
                case 2:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ SHOP - ARSENAL ULTRA DE ELITE");
                    System.out.println("Fundos Totais: " + player.getCoins() + " coins\n");

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

                    System.out.println("Artigos lendários e elixires proibidos no balcão hoje:");
                    for (int i = 0; i < shopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + shopItems.get(i).getName()
                                + " - " + shopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Sair do Arsenal de Elite");
                    ConsoleColors.print("\nEscolha: ", ConsoleColors.YELLOW_BOLD);

                    int shopChoice = input.nextInt();
                    input.nextLine();

                    if (shopChoice == 0) break;

                    if (shopChoice > 0 && shopChoice <= shopItems.size()) {
                        TrainerItem chosen = shopItems.get(shopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            ConsoleColors.success("Item de classe Campeão adquirido: " + chosen.getName() + "! Ficaste com " + player.getCoins() + " coins!");
                        } else {
                            ConsoleColors.error("O teu saldo não é suficiente para pagar artigos desta magnitude.");
                        }
                    } else {
                        ConsoleColors.error("Seleção indisponível no catálogo militar.");
                    }
                    System.out.println("\nPressiona Enter para regressares à rua principal...");
                    input.nextLine();
                    break;
                case 3:
                    boolean training = true;

                    while (training) {
                        ConsoleColors.clear();
                        ConsoleColors.title("ROTAS DE ALTA DENSIDADE - FRONTEIRA DA LIGA");
                        PokemonWild rhydon2 = new PokemonWild("Rhydon", 135, 135, 130, 45, 3, 3, 50, 2000, 1500);
                        PokemonWild nidoking2 = new PokemonWild("Nidoking", 107, 107, 102, 85, 3, 3, 48, 1800, 1400);
                        PokemonWild nidoqueen2 = new PokemonWild("Nidoqueen", 110, 110, 82, 85, 3, 3, 48, 1800, 1400);
                        PokemonWild dugtrio = new PokemonWild("Dugtrio", 65, 65, 100, 50, 3, 3, 48, 1500, 1200);
                        PokemonWild electrode = new PokemonWild("Electrode", 80, 80, 50, 80, 3, 3, 48, 1500, 1100);
                        PokemonWild tauros2 = new PokemonWild("Tauros", 105, 105, 100, 70, 3, 3, 49, 1600, 1300);
                        PokemonWild dragonair = new PokemonWild("Dragonair", 91, 91, 84, 70, 3, 3, 50, 2000, 1500);
                        PokemonWild kangaskhan2 = new PokemonWild("Kangaskhan", 123, 123, 95, 40, 3, 3, 49, 1700, 1400);
                        PokemonWild Persian2 = new PokemonWild("Persian", 78, 78, 70, 65, 3, 3, 48, 1400, 1100);
                        PokemonWild arcanine2 = new PokemonWild("Arcanine", 120, 120, 110, 100, 3, 3, 49, 1600, 1300);
                        PokemonWild gyarados3 = new PokemonWild("Gyarados", 138, 138, 125, 100, 3, 3, 50, 2000, 1600);
                        PokemonWild poliwrath = new PokemonWild("Poliwrath", 104, 104, 95, 70, 3, 3, 49, 1600, 1300);
                        PokemonWild slowbro2 = new PokemonWild("Slowbro", 108, 108, 75, 100, 3, 3, 49, 1500, 1200);
                        PokemonWild starmie2 = new PokemonWild("Starmie", 84, 84, 75, 100, 3, 3, 49, 1600, 1200);
                        PokemonWild lapras = new PokemonWild("Lapras", 145, 145, 85, 95, 3, 3, 50, 2000, 1500);

                        PokemonWild[] wildPokemonViridianCity = {
                                rhydon2, nidoking2, nidoqueen2, dugtrio, electrode,
                                tauros2, dragonair, kangaskhan2, Persian2, arcanine2,
                                gyarados3, poliwrath, slowbro2, starmie2, lapras
                        };

                        Random random = new Random();
                        PokemonWild enemy = wildPokemonViridianCity[random.nextInt(wildPokemonViridianCity.length)];

                        ConsoleColors.warning("Uma força colossal bloqueia os portões da Liga! Um " + enemy.getName() + " colossal surge!");
                        ConsoleColors.separator();
                        boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!winBattle) { // se perde pokemonBattle, acaba os ciclos porque game over
                            training = false;
                            inCity = false;
                            System.out.println("Ohhh, o teu " + player.getPokemonInUse().getName() + " era tão fraquinho que morreu...");
                            gameOver();

                        } else if (winBattle) {
                            player.addCoins(enemy.getCoins()); // vai buscar os coins do pokemon inimigo para atribuir ao player
                            ConsoleColors.success("Pookémon superado! Recolheste as recompensas remanescentes no valor de " + enemy.getCoins() + " coins.");
                            player.showDetails(); // mostra o estado atual do jogador

                            System.out.println("\nO que pretendes fazer?");
                            System.out.println("1. Continuar a empurrar os limites do treino");
                            System.out.println("2. Terminar aquecimento para o Giovanni");
                            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                            int trainingChoice = input.nextInt();
                            if (trainingChoice == 2) {
                                training = false; // volta ao menu da cidade
                            }
                        }
                    }
                    break;
                case 4:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉCENTER - SUPREME FRONTIER STATION");
                    ConsoleColors.story("Nurse Joy: 'Este é o último porto seguro antes da glória. Deixa-me preparar o teu parceiro para o combate da vida dele!'");
                    ConsoleColors.separator();

                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    ConsoleColors.success("Potencial genético purificado! Tudo alinhado para a batalha de campeões!");
                    player.showDetails();
                    System.out.println("\nPressiona Enter para marchar em direção ao ginásio final...");
                    input.nextLine();
                    break;
                default:
                    ConsoleColors.error("Ação inválida no limiar do território!");
                    System.out.println("\nPressiona Enter para tentar novamente...");
                    input.nextLine();
                    break;
            }
        }
    }

    // percurso alternativo: cave secreta e indigo plateau (bypass dos gyms para ganhar)
    public void secretCave() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        ConsoleColors.clear();
        ConsoleColors.title(" O SEGREDO ATRÁS DA CASCATA ");
        ConsoleColors.separator();
        ConsoleColors.story("No trilho montanhoso em direção a Celadon City, o som ensurdecedor de uma queda d'água chama-te à atenção.");
        ConsoleColors.story("Ao afastar a vegetação densa, reparas num brilho azulado difuso vindo de trás da imensa cortina de água.");
        ConsoleColors.story("Atravessas a torrente e deparas-te com uma fenda na rocha. Está escuro. O ar é denso, frio e estático.");
        ConsoleColors.story("Perto de uma estalagmite ancestral, o teu pé bate em algo metálico: uma LANTERNA funcional");
        ConsoleColors.story("e um PAPEL VELHO, desgastado pelo tempo, que parece desenhar um labirinto esquecido pela humanidade...");
        ConsoleColors.separator();


        System.out.println("O que pretendes fazer perante este mistério?");
        System.out.println("1. Ligar a lanterna e quebrar o silêncio da caverna (Explorar)");
        System.out.println("2. Ignorar a curiosidade e retomar a marcha para Celadon City");
        ConsoleColors.separator();
        ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
        int entranceCaveChoice = input.nextInt();
        input.nextLine();

        if (entranceCaveChoice == 2) { // para voltar para o menu de Celadon City e fazer a rota normal dos ginasios
            ConsoleColors.println("\nDecides não arriscar a segurança do teu Pookémon. Afastas-te da cascata e segues viagem...", ConsoleColors.CYAN_BRIGHT);
            System.out.println("Pressiona Enter para continuar para Celadon City...");
            input.nextLine();
            return;
        }

        // se jogador escolher outra opcao que não a 2, entra na caverna secreta
        ConsoleColors.clear();
        ConsoleColors.title("SANTUÁRIO ESQUECIDO - PROFUNDEZAS DA TERRA");
        ConsoleColors.story("O feixe de luz da lanterna corta a escuridão milenar, revelando cristais gigantes que contêm uma energia estranha...");
        ConsoleColors.story("O mapa gasto aponta direções que parecem desafiar a própria geometria. Sentes uma pressão psíquica no ar...");
        System.out.println("\nPressiona Enter para cravar as botas na humidade da caverna...");
        input.nextLine();

        boolean inSecretCave = true;
        while (inSecretCave) {
            ConsoleColors.clear();
            ConsoleColors.title(" A CAVERNA SECRETA - ANOMALIA QUÂNTICA ");
            ConsoleColors.separator();
            System.out.println("O eco dos teus passos avisa-te que estás muito profundo. O que fazes?");
            System.out.println("1. Decifrar as linhas do mapa e continuar a explorar");
            System.out.println("2. Usar um escape rope para voltar à superfície e regressar ao trilho para Celadon City");
            ConsoleColors.separator();
            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
            int secretCaveChoice = input.nextInt();
            input.nextLine();

            if (secretCaveChoice == 2) {
                ConsoleColors.println("\nA opressão das sombras e o medo do desconhecido vencem-te. Corres de volta para a luz do sol!", ConsoleColors.CYAN_BRIGHT);
                System.out.println("Pressiona Enter para sair da caverna...");
                input.nextLine();
                inSecretCave = false;
                return;
            }

            int randomSecretCaveEvent = random.nextInt(100); // randomizar os possiveis eventos dentro da cave secreta

            // mewtwo e win game alternativo (mewtwo)
            if (randomSecretCaveEvent < 2) {
                ConsoleColors.clear();
                ConsoleColors.title(" ENCONTRO DIVINO: THE CHOSEN ONE ");
                ConsoleColors.separator();
                ConsoleColors.println("A caverna inteira começa a sofrer uma distorção gravitacional extrema... ", ConsoleColors.PURPLE_BOLD);
                ConsoleColors.story("As pedras levitam. A luz da tua lanterna estilhaça-se quando uma aura violeta incandescente inunda a câmara.");
                ConsoleColors.story("Flutuando no centro do vazio, ergue-se a silhueta imponente de MEWTWO.");
                ConsoleColors.story("Os seus olhos brilham com uma inteligência fria e cósmica que lê toda a tua alma num microssegundo.");
                ConsoleColors.story("O titã fecha os olhos lentamente, cessa a pressão psíquica e inclina a cabeça em sinal de profundo respeito.");
                ConsoleColors.warning("\"...Tu és aquele que aguardava. O Escolhido (The Chosen One).\"");
                ConsoleColors.separator();
                ConsoleColors.success("O MEWTWO ABENÇOOU-TE! A tua pureza e coragem transcenderam o próprio jogo. A simulação termina aqui!");
                System.out.println("\nPressiona Enter para aceitar a bênção lendária...");
                input.nextLine();
                winGameMewtwo();
                return;
            } else if (randomSecretCaveEvent < 12) {
                ConsoleColors.clear();
                ConsoleColors.title("A REVELAÇÃO DA ORIGEM: MEW ");
                ConsoleColors.separator();
                ConsoleColors.println("Uma esfera de luz cor-de-rosa cruza as estalactites com piruetas graciosas...", ConsoleColors.PURPLE_BRIGHT);
                ConsoleColors.story("É o lendário MEW, o Pookémon ancestral do qual derivam todos os códigos genéticos!");

                if (player.getPokemonInUse().getLevel() < 35) { // se pokemon do jogador estiver abaixo do nivel 35, mew nao combate
                    ConsoleColors.warning("O Mew flutua até ao teu " + player.getPokemonInUse().getName() + ", solta uma gargalhada infantil e desaparece no ar.");
                    ConsoleColors.error("O teu parceiro treme de reverência. És demasiado fraco para este confronto. Nível exigido: Superior a 35.");
                    System.out.println("\nPressiona Enter para recuperar o fôlego...");
                    input.nextLine();
                } else {
                    ConsoleColors.success("O teu " + player.getPokemonInUse().getName() + " ruge, estabilizando a sua energia! O Mew sorri... O desafio foi aceite!");
                    ConsoleColors.separator();

                    // instanciar o mew como pokemonwild para a luta (para nao ter o special attack do mew (pokemon legendary)
                    PokemonWild mewEnemy = new PokemonWild("Mew", 200, 200, 80, 90, 4, 4, 40, 5000, 5000);

                    ConsoleColors.println("\nUma barreira mística translúcida ergue-se! O combate pela supremacia ancestral começou!", ConsoleColors.PURPLE_BOLD);

                    boolean winMewBattle = player.getPokemonInUse().pokemonBattle(mewEnemy, player);

                    if (!winMewBattle) { // se perde contra o mew
                        ConsoleColors.error("A energia cósmica de Mew obliterou as barreiras físicas do teu Pookémon...");
                        gameOver();
                        return;
                    }

                    ConsoleColors.clear();
                    ConsoleColors.title(" LENDÁRIO CONQUISTADO ");
                    ConsoleColors.story("Mew cessa as suas defesas, flutua docemente até ao teu ombro e esfregar a bochecha na tua face.");
                    ConsoleColors.story("Ele reconhece o teu valor inabalável... Reparas que no chão da caverna, a um passo dos teus pés,  uma Master Ball brilha no escuro...");
                    ConsoleColors.story("... Pegas na Master Ball, olhas para o Mew, e ele sorri como se aceitasse este destino improvável...");
                    ConsoleColors.story("... Atiras a Master Ball, o Mew entra para dentro dela e ela abana intensamente no chão rochoso...");
                    ConsoleColors.success("CLIQUE! MEW FOI CAPTURADO COM SUCESSO!");
                    ConsoleColors.println("Mew é adicionado com sucesso à tua Pookédex!", ConsoleColors.CYAN_BOLD);
                    ConsoleColors.println("O Mew substituiu o teu " + player.getPokemonInUse().getName() + " e é agora o teu companheiro de viagem!", ConsoleColors.PURPLE_BOLD);

                    Mew mewCaptured = new Mew("Mew", 150, 150, 100, 150, 5, 5, 50, 0);
                    // substitui o pokemon starter do jogador
                    player.setPokemonInUse(mewCaptured);
                    ConsoleColors.separator();
                    ConsoleColors.println("Algo brilha no chão... Da cauda do Mew caiu um GOLD TICKET tridimensional!", ConsoleColors.YELLOW_BOLD);
                    ConsoleColors.story("O bilhete vibra violentamente, rasgando o tecido do espaço-tempo e manifestando um portal cilíndrico!");
                    ConsoleColors.story("O vórtice suga-te instantaneamente através das dimensões, arremessando-te nas escadarias do INDIGO PLATEAU!");
                    ConsoleColors.warning("O ar cheira a tempestade. O Trainer Blue está de pé, à tua espera no topo.");
                    System.out.println("\nPressiona Enter para cruzar o portal para o Indigo Plateau...");
                    input.nextLine();

                    inSecretCave = false;
                    indigoPlateau(); // Transição direta para o boss final Blue para obter winGameBlue aka final alternativo
                    return;
                }
            } else if (randomSecretCaveEvent < 37) {
                ConsoleColors.clear();
                ConsoleColors.title("DESMATERIALIZAÇÃO: RECOMPENSA DE ABRA ");
                ConsoleColors.separator();
                ConsoleColors.story("Um Abra desperta assustado de cima de um altar de pedra!");
                ConsoleColors.story("Os seus olhos brilham azul-celeste e, num estalar de dedos, executa um TELEPORT ruidoso!");
                ConsoleColors.story("Com o pânico do salto quântico, a sua bolsa de itens rebenta, espalhando relíquias pelo chão!");
                ConsoleColors.separator();

                AbraEncounter abraAppeared = new AbraEncounter();

                player.addCoins(abraAppeared.getCoinsDropped());
                ConsoleColors.success("  Ganhaste " + abraAppeared.getCoinsDropped() + " coins da poeira!");

                ArrayList<TrainerItem> abraDroppedItems = abraAppeared.getDroppedItems(3); // abra deixa 3 itens para o jogador
                for (TrainerItem item : abraDroppedItems) {
                    player.addItemToBag(item);
                    ConsoleColors.println(" O abra perdeu um " + item.getName() + "!", ConsoleColors.CYAN_BOLD_BRIGHT);
                }
                ConsoleColors.separator();
                System.out.println("Balanço Bancário Atual: " + player.getCoins() + " coins");
                player.showDetails();
                System.out.println("\nPressiona Enter para guardar o saque e avançar pela caverna...");
                input.nextLine();
            } else if (randomSecretCaveEvent < 52) {
                ConsoleColors.clear();
                ConsoleColors.title("TEAM ROCKET - EMBOSCADA NA CAVERNA ");
                ConsoleColors.separator();
                ConsoleColors.separator();
                TeamRocketAmbush memberTeamRocketSecretCave = new TeamRocketAmbush();
                ConsoleColors.warning("Uma gargalhada sinistra ecoa das paredes húmidas... " + memberTeamRocketSecretCave.getMember() + " salta das sombras com óculos de visão noturna!");
                ConsoleColors.story("\"Prepara-te para sofrer! A Team Rocket vai tentar roubar-te os coins!\"");

                int teamRocketStealCoins = random.nextInt(100);
                if (teamRocketStealCoins < 30) {
                    int coinsStolen = Math.min(player.getCoins(), 2000); // rouba 2000 coins do jogador
                    player.removeCoins(coinsStolen);
                    ConsoleColors.error("A Team Rocket usou uma bomba de fumo e conseguiu roubar " + coinsStolen + " coins da tua mochila!");
                    ConsoleColors.story("Desaparecem no meio da névoa, rindo-se através dos túneis húmidos que fazem um eco ensurdecedor...");
                    System.out.println("Saldo Restante: " + player.getCoins() + " coins.");
                } else {
                    ConsoleColors.success("Reages na velocidade da luz! O teu Pookémon bloqueia o ataque e faz os criminosos recuarem em pânico!");
                    ConsoleColors.story("Ao tropeçarem nas pedras durante a fuga, deixam cair uma mala tática com provisões monetárias!");
                    player.addCoins(1500);
                    ConsoleColors.println("Recolheste 1500 coins caídas na debandada deles! Saldo Atual: " + player.getCoins() + " coins.", ConsoleColors.GREEN_BRIGHT);
                }
                System.out.println("\nPressiona Enter para fazer desaparecer a poeira e continuar...");
                input.nextLine();
            } else {
                ConsoleColors.clear();
                ConsoleColors.title("ATAQUE ECOSSISTÉMICO SUBTERRÂNEO ");
                PokemonWild[] secretCavePokemon = {
                        new PokemonWild("Dragonair", 91, 91, 84, 70, 3, 3, 35, 1500, 1000),
                        new PokemonWild("Haunter", 55, 55, 50, 95, 3, 3, 33, 1000, 800),
                        new PokemonWild("Gengar", 75, 75, 65, 130, 3, 3, 38, 1500, 1200),
                        new PokemonWild("Electabuzz", 85, 85, 83, 95, 3, 3, 35, 1100, 900),
                        new PokemonWild("Magmar", 85, 85, 95, 100, 3, 3, 35, 1100, 900),
                        new PokemonWild("Lapras", 110, 110, 85, 95, 3, 3, 37, 1300, 1100),
                        new PokemonWild("Gyarados", 110, 110, 125, 100, 3, 3, 37, 1500, 1200),
                        new PokemonWild("Starmie", 75, 75, 75, 100, 3, 3, 35, 1200, 1000),
                        new PokemonWild("Alakazam", 55, 55, 45, 135, 3, 3, 38, 1500, 1200),
                        new PokemonWild("Cloyster", 80, 80, 95, 85, 3, 3, 34, 1100, 900)
                };
                PokemonWild enemy = secretCavePokemon[random.nextInt(secretCavePokemon.length)];
                ConsoleColors.warning("Os teus passos perturbaram o ecossistema! Um temível " + enemy.getName() + " das profundezas avança enfurecido!");
                ConsoleColors.separator();

                boolean winBattle = player.getPokemonInUse().pokemonBattle(enemy, player);

                // aplicar aqui o check das evolucoes

                if (!winBattle) {
                    ConsoleColors.error("As ameaças da Caverna Secreta foram implacáveis demais para o teu nível atual...");
                    inSecretCave = false;
                    gameOver();
                    return;
                }
                player.addCoins(enemy.getCoins());
                ConsoleColors.success("Batalha de sobrevivência vencida! Adicionaste " + enemy.getCoins() + " coins ao teu saldo bancário.");
                player.showDetails();
                System.out.println("\nPressiona Enter para reorientar a lanterna...");
                input.nextLine();
                player.showDetails();
            }
        }
    }

    public void indigoPlateau() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        ConsoleColors.clear();
        ConsoleColors.title(" LIGA POOKÉMON - INDIGO PLATEAU ");
        ConsoleColors.separator();
        ConsoleColors.story("As distorções do portal quântico aberto pelo Gold Ticket estabilizam-se num ápice.");
        ConsoleColors.story("O portal de luz fecha-se abruptamente atrás de ti, deixando apenas o silêncio das alturas.");
        ConsoleColors.story("Olhas em redor: estás no cume do Indigo Plateau, acima das nuvens, onde apenas a elite pisa.");
        ConsoleColors.story("No centro do majestoso pódio de mármore, de braços cruzados e manto ao vento, está ele...");
        ConsoleColors.story("O Trainer BLUE, o teu eterno rival, que conquistou o título de Campeão escassos minutos antes!");
        ConsoleColors.warning("Blue: 'Hah! Sentia a distorção no ar... Sabia que o destino te traria aqui por atalhos impossíveis.");
        ConsoleColors.warning("Fica a saber que já sou o Mestre desta Liga! Estás pronto para ver o teu sonho virar cinzas?'");
        ConsoleColors.separator();
        ConsoleColors.println("O confronto final entre lendas destinadas a colidir vai começar...", ConsoleColors.PURPLE_BRIGHT);
        System.out.println("\nPressiona Enter para subir os degraus do trono do Campeão...");
        input.nextLine();

        boolean inPlateau = true;
        while (inPlateau) {
            ConsoleColors.clear();
            ConsoleColors.title("Indigo Plateau - O Trono do Mestre Absoluto");
            ConsoleColors.separator();

            System.out.println("O que pretendes fazer antes do xeque-mate?");
            System.out.println("1. Desafiar o Campeão Blue (O Combate do Século)");
            System.out.println("2. Aceder ao Mercado Secreto do Rival (Blue's Shop)");
            System.out.println("3. Desafiar Guardiões Semi-Lendários no Pátio");
            System.out.println("4. Recompor Forças no PookéCenter da Elite");
            ConsoleColors.separator();
            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    ConsoleColors.clear();
                    ConsoleColors.title(" A BATALHA SUPREMA: RIVALIDADE ETERNA ");
                    ConsoleColors.warning("Blue: 'Prepara-te! Vou demonstrar por que razão o meu nome está no topo do mundo!'");
                    ConsoleColors.separator();

                    PokemonWild pidgeot = new PokemonWild("Pidgeot", 108, 108, 80, 70, 4, 4, 50, 2000, 0);
                    PokemonWild alakazam2 = new PokemonWild("Alakazam", 65, 65, 45, 135, 4, 4, 53, 2500, 0);
                    PokemonWild rhydon2 = new PokemonWild("Rhydon", 145, 145, 130, 45, 4, 4, 54, 2800, 0);
                    PokemonWild arcanine3 = new PokemonWild("Arcanine", 130, 130, 110, 100, 4, 4, 53, 2500, 0);
                    PokemonWild exeggutor = new PokemonWild("Exeggutor", 105, 105, 95, 125, 4, 4, 53, 2500, 0);
                    PokemonWild blastoise2 = new PokemonWild("Blastoise", 109, 109, 83, 100, 4, 4, 55, 3000, 0);

                    PokemonWild[] blueTeam = {pidgeot, alakazam2, rhydon2, arcanine3, exeggutor, blastoise2};

                    boolean blueDefeated = true;


                    for (int i = 0; i < blueTeam.length; i++) {
                        PokemonWild bluePokemon = blueTeam[i];

                        // Menu de Interrupção para cura entre combates do Blue (a partir do 2º rival)
                        if (i > 0) {
                            ConsoleColors.separator();
                            ConsoleColors.println("\nBlue recua o seu monstro caído e prepara a próxima Pookébola com fúria nos olhos!", ConsoleColors.YELLOW_BOLD);
                            System.out.println("Queres abrir taticamente a mochila para curar o teu Pookémon antes do próximo combate?");
                            System.out.println("1. Sim, abrir compartimento de poções (Menu de Cura)");
                            System.out.println("2. Não, manda vir o próximo oponente!");
                            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                            int healMenu = input.nextInt();
                            input.nextLine();
                            if (healMenu == 1) {
                                // Filtra apenas Poções/Consumíveis da mochila para curar fora de batalha
                                ArrayList<TrainerItem> mewRestoreHealth = new ArrayList<>();
                                for (TrainerItem item : player.getItemsBag()) {
                                    if (item instanceof Potion || item instanceof Consumable) {
                                        mewRestoreHealth.add(item);
                                    }
                                }
                                if (mewRestoreHealth.isEmpty()) {
                                    ConsoleColors.error("Não restam poções ou elixires na mochila!");
                                } else {
                                    System.out.println("Escolhe uma Potion/Consumable para usar no teu " + player.getPokemonInUse().getName() + ":");
                                    for (int k = 0; k < mewRestoreHealth.size(); k++) {
                                        System.out.println((k + 1) + ". " + mewRestoreHealth.get(k).getName());
                                    }
                                    ConsoleColors.print("Escolhe o item: ", ConsoleColors.YELLOW_BOLD);
                                    int itemChoice = input.nextInt();
                                    input.nextLine(); // Limpar buffer

                                    if (itemChoice > 0 && itemChoice <= mewRestoreHealth.size()) {
                                        TrainerItem chosen = mewRestoreHealth.get(itemChoice - 1);
                                        chosen.use(player.getPokemonInUse());
                                        player.getItemsBag().remove(chosen);
                                        ConsoleColors.success("Efeito aplicado! " + player.getPokemonInUse().getName() + " recuperou energia vital.");
                                    }
                                }
                            }
                        }

                        ConsoleColors.println("\nO Campeão ruge: 'Vai, " + bluePokemon.getName() + "! Esmaga tudo!'", ConsoleColors.RED_BOLD);
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
                        ConsoleColors.clear();
                        ConsoleColors.title("VEREDICTO DA LIGA: O VERDADEIRO POOKÉMON MASTER ");
                        ConsoleColors.separator();
                        ConsoleColors.warning("Blue: 'Im-impossível... O meu treino perfeito... A minha equipa imbati...vél...? Derrotada?!'");
                        ConsoleColors.story("As lágrimas de frustração do teu rival dão lugar a um sorriso amargo de orgulho partilhado.");
                        ConsoleColors.story("Tu foste buscar o poder mitológico das profundezas da caverna e provaste que a ambição");
                        ConsoleColors.story("não vence o elo sagrado que tens com o teu Pookémon.");
                        ConsoleColors.story("O teto do Indigo Plateau abre-se, banhando-te com luz solar e pétalas de consagração.");
                        ConsoleColors.story("O trono de Campeão pertence-te por direito divino e cósmico!");
                        ConsoleColors.separator();
                        ConsoleColors.success("Vitória Inigualável no Fim do Mundo! Quebraste o reinado do teu rival!");
                        System.out.println("\nPressiona Enter para ver o ecrã de vitória e os créditos do Final da Indigo Plateau...");
                        input.nextLine();
                        winGameBlue();
                    }
                    break;

                case 2:
                    ConsoleColors.clear();
                    ConsoleColors.title("BLUE'S BLACK MARKET SHOP - CONTRABANDO DE ELITE");
                    System.out.println("Saldo atual: " + player.getCoins() + " coins\n");
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

                    System.out.println("Suprimentos finais disponibilizados pelo campeão Blue:");
                    for (int i = 0; i < blueShopItems.size(); i++) {
                        System.out.println((i + 1) + ". " + blueShopItems.get(i).getName()
                                + " - " + blueShopItems.get(i).getPrice() + " coins");
                    }
                    System.out.println("0. Abandonar o recinto");
                    ConsoleColors.print("\nEscolha: ", ConsoleColors.YELLOW_BOLD);
                    int blueShopChoice = input.nextInt();
                    input.nextLine();

                    if (blueShopChoice == 0) break;

                    if (blueShopChoice > 0 && blueShopChoice <= blueShopItems.size()) {
                        TrainerItem chosen = blueShopItems.get(blueShopChoice - 1);
                        if (player.getCoins() >= chosen.getPrice()) {
                            player.removeCoins(chosen.getPrice());
                            player.addItemToBag(chosen);
                            ConsoleColors.success("Item de classe mestre adicionado: " + chosen.getName() + "! Tens agora " + player.getCoins() + " coins.");
                        } else {
                            System.out.println("Moedas insuficientes para mercadoria deste calibre.");
                        }
                    } else {
                        ConsoleColors.error("Ups... Desconheço essa opção...");
                    }
                    System.out.println("\nPressiona Enter para regressar ao pátio principal...");
                    input.nextLine();

                    break;

                case 3:
                    boolean training = true;

                    while (training) {
                        ConsoleColors.clear();
                        ConsoleColors.title(" PÁTIO DOS TITÃS - COMBATE SEMI-LENDÁRIO ");

                        PokemonWild[] indigoPlateauTraining = {
                                new PokemonWild("Dragonite", 121, 121, 134, 100, 4, 4, 53, 3000, 2000),
                                new PokemonWild("Gyarados", 138, 138, 125, 100, 4, 4, 52, 2500, 1800),
                                new PokemonWild("Lapras", 145, 145, 85, 95, 4, 4, 51, 2200, 1600),
                                new PokemonWild("Gengar", 95, 95, 65, 130, 4, 4, 50, 2000, 1500),
                                new PokemonWild("Alakazam", 65, 65, 45, 135, 4, 4, 52, 2500, 1800),
                                new PokemonWild("Exeggutor", 105, 105, 95, 125, 4, 4, 51, 2200, 1600)
                        };

                        PokemonWild enemy = indigoPlateauTraining[rand.nextInt(indigoPlateauTraining.length)];
                        ConsoleColors.warning("Um " + enemy.getName() + " poderoso apareceu!");
                        ConsoleColors.separator();

                        boolean battleWin = player.getPokemonInUse().pokemonBattle(enemy, player);

                        if (!battleWin) {
                            training = false;
                            inPlateau = false;
                            gameOver();
                        } else {
                            player.addCoins(enemy.getCoins());
                            ConsoleColors.success("Fera dominada! Adquiriste " + enemy.getCoins() + " moedas imperiais.");
                            player.showDetails();

                            System.out.println("\nContinuas nos treinos?");
                            System.out.println("1. Sim, invocar outra criatura ancestral");
                            System.out.println("2. Não, voltar à praça central da Indigo Plateau");
                            ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                            if (input.nextInt() == 2) training = false;
                        }
                        input.nextLine();
                    }
                    break;

                case 4:
                    ConsoleColors.clear();
                    ConsoleColors.title("POOKÉ CENTER SUPREMO - LIGA INDIGO PLATEAU");
                    ConsoleColors.story("Nurse Joy: 'Vi os raios quânticos do portal que te trouxe até aqui. O teu Pookémon carrega o peso de universos inteiros. Deixa-me rejuvenescer a sua essência.'");
                    ConsoleColors.separator();
                    player.getPokemonInUse().healPokemon();
                    player.getPokemonInUse().resetSpecialAttackUses();
                    ConsoleColors.success("Restauração absoluta efetuada! Estás pronto para reescrever a história?");
                    player.showDetails();
                    System.out.println("\nPressiona Enter para encarar o Campeão...");
                    input.nextLine();
                    break;

                default:
                    ConsoleColors.error("Seleção inválida no pico da Liga.");
                    System.out.println("\nPressiona Enter para recalibrar o foco...");
                    input.nextLine();
                    break;
            }
        }
    }

    // todos os winGames em baixo
    public void winGame() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("PARABÉNS, POOKÉMON MASTER!");

        ConsoleColors.separator();
        ConsoleColors.story("Derrotaste o Giovani e todos os 8 Gym Leaders de Kanto!");
        ConsoleColors.story("A tua dedicação e estratégia valeram-te e medalha de ouro.");
        ConsoleColors.story("Tornaste-te oficialmente o/a maior treinador/a da região!");
        ConsoleColors.separator();

        ConsoleColors.print("\n  Crachás conquistados: ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getGymBadge() + " / 8", ConsoleColors.PURPLE_BOLD);

        ConsoleColors.print("    Pookémon Principal : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getPokemonInUse().getName() + " (Lv. " + player.getPokemonInUse().getLevel() + " | HP: " + player.getPokemonInUse().getHp() + ")", ConsoleColors.GREEN_BOLD);

        ConsoleColors.print("    Moedas Finais      : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getCoins() + " coins", ConsoleColors.YELLOW_BOLD);

        System.out.println("\n1. Jogar novamente");
        System.out.println("2. Sair");

        ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
        int choice = 2;
        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            // Se errar, assume a saída segura
        }

        Audio.stopAll();
        if (choice == 1) {
            new Game().pookemon();
        } else {
            System.out.println("\nObrigado/a por jogares Pookémon! Até à próxima, treinador/a!");
            System.exit(0);
        }
    }

    public void winGameMewtwo() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("O DESTINO REVELADO: THE CHOSEN ONE!");

        ConsoleColors.separator();
        ConsoleColors.story("Penetraste nas profundezas esquecidas da caverna.");
        ConsoleColors.story("A tua aura pura de treinador foi reconhecida pela força psíquica mais avassaladora do universo.");
        ConsoleColors.story("O lendário Mewtwo escolheu-te como o seu parceiro. Tu transcendeste a própria Liga Pookémon!");
        ConsoleColors.separator();

        ConsoleColors.print("\n    Estado Cósmico    : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println("Abençoado por Mewtwo", ConsoleColors.PURPLE_BOLD_BRIGHT);

        ConsoleColors.print("    Pookémon no momento: ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getPokemonInUse().getName() + " (Lv. " + player.getPokemonInUse().getLevel() + ")", ConsoleColors.CYAN_BOLD);

        ConsoleColors.print("    Moedas Acumuladas  : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getCoins() + " coins", ConsoleColors.YELLOW_BOLD);

        System.out.println("\n1. Jogar novamente (Explorar o resto do mundo)");
        System.out.println("2. Sair do jogo");

        ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
        int choice = 2;
        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            // Fallback
        }

        Audio.stopAll();
        if (choice == 1) {
            new Game().pookemon();
        } else {
            System.out.println("\nA tua lenda ficará guardada para sempre nas sombras da Secret Cave. Adeus!");
            System.exit(0);
        }
    }

    public void winGameBlue() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("PARABÉNS POOKÉMON MASTER DA LIGA INDIGO!");

        ConsoleColors.separator();
        ConsoleColors.story("Superaste com sucesso o misterioso portal do Gold Ticket!");
        ConsoleColors.story("Numa batalha épica de proporções históricas, destruíste por completo a equipa do Trainer Blue.");
        ConsoleColors.story("És orgulhosamente o/a número 1 incontestável do Indigo Plateau!");
        ConsoleColors.separator();

        ConsoleColors.print("\n Título Alcançado  : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println("Kanto League Champion", ConsoleColors.CYAN_BOLD_BRIGHT);

        ConsoleColors.print("    Pookémon Parceiro  : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getPokemonInUse().getName() + " (Lv. " + player.getPokemonInUse().getLevel() + " | HP: " + player.getPokemonInUse().getHp() + ")", ConsoleColors.GREEN_BOLD);

        ConsoleColors.print("    Coins de Elite     : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getCoins() + " coins", ConsoleColors.YELLOW_BOLD);

        System.out.println("\n1. Jogar novamente (Tentar outra rota)");
        System.out.println("2. Sair do jogo");

        ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
        int choice = 2;
        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            // Fallback
        }

        Audio.stopAll();
        if (choice == 1) {
            new Game().pookemon();
        } else {
            System.out.println("\nObrigado/a por jogares Pookémon! Até à próxima, Treinador/a!");
            System.exit(0);
        }
    }

    // game over
    public void gameOver() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("GAME OVER...");

        try {
            // Imprime a arte ASCII do teu Pokémon
            FileTools.printFile("resources/art/gameOver.txt");

            // Faz o programa congelar a imagem na consola por 3 segundos (3000 milissegundos)
            sleep(3000);

        } catch (FileNotFoundException e) {
            System.out.println(" Erro Real: " + e.getMessage());
            System.out.println("Aviso: Imagem do game over não encontrada.");
        } catch (InterruptedException e) {
            System.out.println("A visualização foi interrompida.");
        }

        ConsoleColors.separator();
        ConsoleColors.story("O teu Pookémon esgotou todas as suas forças e desmaiou em combate!");
        ConsoleColors.story("Sem energia para continuar a lutar, foste forçado a recuar.");
        ConsoleColors.story("A tua jornada nesta rota termina aqui, mas a tua determinação mantém-se intacta.");
        ConsoleColors.separator();

        ConsoleColors.print("\n  Último Pookémon  : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getPokemonInUse().getName() + " (Lv. " + player.getPokemonInUse().getLevel() + ")", ConsoleColors.RED_BOLD);

        ConsoleColors.print("    Ginásios Vencidos: ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getGymBadge() + " / 8", ConsoleColors.PURPLE_BOLD);

        ConsoleColors.print("    Moedas Perdidas  : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(player.getCoins() + " coins", ConsoleColors.YELLOW_BOLD);

        System.out.println("\n1. Jogar novamente");
        System.out.println("2. Sair");

        ConsoleColors.print("\nEscolhe: ", ConsoleColors.YELLOW_BOLD);
        int choice = 2;
        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            // Fallback seguro
        }

        Audio.stopAll();
        if (choice == 1) {
            new Game().pookemon();
        } else {
            ConsoleColors.println("\nNão desistas, treinador/a! A derrota faz parte do caminho. Até à próxima aventura!", ConsoleColors.RED_UNDERLINED);
        }
        throw new GameOverException();
    }

    public static class GameOverException extends RuntimeException {
        public GameOverException() {
            super("Game Over");
        }
    }
}