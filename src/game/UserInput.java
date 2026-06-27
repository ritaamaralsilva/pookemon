package game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe utilitária responsável pela centralização, higienização e validação das entradas de dados do utilizador.
 * <p>
 * Atua como uma camada protetora sobre o fluxo da consola ({@link Scanner}), isolando o tratamento de exceções
 * numa única estrutura estática. Isto previne a duplicação massiva de blocos {@code try-catch} ao longo de todos
 * os menus do motor de jogo, garantindo que respostas inválidas não resultem na interrupção abrupta do programa.
 * </p>
 */
public class UserInput {
    /**
     * Captura uma entrada numérica inteira da consola de forma segura, tratando falhas de correspondência de tipo.
     * <p>
     * O método estabelece um ciclo de repetição contínuo até que um valor estritamente inteiro seja fornecido.
     * Se o utilizador submeter dados não numéricos (como texto ou símbolos), o mecanismo captura a exceção
     * {@link InputMismatchException}, limpa preventivamente o buffer do fluxo chamando {@code input.nextLine()}
     * para descartar os resíduos inválidos e emite um aviso estético através de {@code ConsoleColors.error}.
     * </p>
     *
     * @param input A instância ativa do {@link Scanner} acoplada ao fluxo de entrada padrão do sistema.
     * @return O número inteiro validado e submetido pelo jogador para a navegação nos menus.
     */
    public static int checkForUserInputError(Scanner input) {
        while (true) {
            try {
                int value = input.nextInt(); // verifica se o input é inteiro
                //input.nextLine(); usado no Game.java
                return value; // retorna o valor do input para avançar nos menus
            } catch (InputMismatchException e) {
                input.nextLine(); // caso user tenha introduzido string em vez de inteiro
                ConsoleColors.error("Opção inválida! Introduz um número."); // erro default para não quebrar o programa
            }
        }
    }
}
