package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    /**
     * Este método serve para centralizar o catch e try (deteção de erro) do user input com o Scanner
     * Ou seja, em TODOS os menus do jogo, caso o user reponda na consola com string ou um símbolo em vez de numero inteiro como é pedido
     * O programa não vai bloquear e assim o jogo continua
     * Fiz desta forma para evitar o uso repetido de código no jogo todo, e assim só chamo a função sempre que há menus com user input
     * @param input
     * @return
     */
    public static int checkForUserInputError(Scanner input) {
        while (true) {
            try {
                int value = input.nextInt(); // verifica se o input é inteiro
                //input.nextLine(); // se for int, avança
                return value; // retorna o valor do input para avançar nos menus
            } catch (InputMismatchException e) {
                input.nextLine(); // caso user tenha introduzido string em vez de inteiro
                ConsoleColors.error("Opção inválida! Introduz um número."); // erro default para não quebrar o programa
            }
        }
    }
}
