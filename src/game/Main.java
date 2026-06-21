package game;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        // chamar audio inicial para tocar mal se abre o jogo na consola
        Audio.playSoundtrack("resources/audio/pookemonIntroMusic.wav");

        Game game = new Game();
        game.pookemon();
    }
}