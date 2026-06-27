package game;

import java.io.FileNotFoundException;
import java.io.File;

import static java.lang.Thread.sleep;

/**
 * Ponto de entrada principal (Entry Point) que despoleta a execução do ecossistema do jogo.
 * <p>
 * Esta classe inicializa o ciclo de vida global da aplicação, instanciando o motor principal
 * e encapsulando o fluxo dentro de um ambiente monitorizado para interrupções estritas e
 * encerramentos controlados.
 * </p>
 */
public class Main {

    /**
     * Orquestra o arranque do jogo e estabelece a barreira final de contenção de exceções de fluxo.
     * <p>
     * Instancia o motor {@link Game} e invoca o loop principal do jogo através do método {@code pookemon()}.
     * O bloco captura de forma limpa a exceção personalizada {@code Game.GameOverException}; isto
     * assegura que, quando o estado de fim de jogo ocorre (quer por derrota ou decisão deliberada do
     * jogador ao sair do menu), o encerramento do processo Java seja efetuado de forma suave, elegante
     * e sem empilhar relatórios de erro (Stack Traces) na consola.
     * </p>
     *
     * @param args Argumentos de linha de comandos passados na inicialização do terminal (não utilizados).
     * @throws InterruptedException Exceção propagada caso as rotinas de temporização ou suspensão de fluxo (sleep) sejam interrompidas.
     * @throws FileNotFoundException Exceção propagada caso os ficheiros de texto essenciais ou artes ASCII não sejam localizados nos caminhos especificados.
     */
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        try {
            Game game = new Game();
            game.pookemon();
        } catch (Game.GameOverException e) {
            // jogo terminou  — o jogador já escolheu reiniciar ou sair, isto evita bugs
        }
    }
}
