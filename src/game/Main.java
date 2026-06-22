package game;

import java.io.FileNotFoundException;
import java.io.File;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        try {
            Game game = new Game();
            game.pookemon();
        } catch (Game.GameOverException e) {
            // jogo terminou  — o jogador já escolheu reiniciar ou sair, isto evita bugs
        }
    }
}
