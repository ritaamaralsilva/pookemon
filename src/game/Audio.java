package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Audio {

    private static Clip soundtrack;
    private static String soundtrackAtual;

    private static final List<Clip> efeitosAtivos = new CopyOnWriteArrayList<>();

    /**
     * Carrega um ficheiro áudio e converte-o para um formato mais compatível com Java.
     */
    private static Clip carregarClip(String caminho) throws Exception {
        File audioFile = new File(caminho);

        if (!audioFile.exists()) {
            throw new FileNotFoundException("Ficheiro não encontrado: " + caminho);
        }

        AudioInputStream originalStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat originalFormat = originalStream.getFormat();

        AudioFormat decodedFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                originalFormat.getSampleRate(),
                16,
                originalFormat.getChannels(),
                originalFormat.getChannels() * 2,
                originalFormat.getSampleRate(),
                false
        );

        AudioInputStream decodedStream = AudioSystem.getAudioInputStream(decodedFormat, originalStream);

        byte[] audioData = decodedStream.readAllBytes();

        Clip clip = AudioSystem.getClip();
        clip.open(decodedFormat, audioData, 0, audioData.length);

        decodedStream.close();
        originalStream.close();

        return clip;
    }

    /**
     * Toca uma soundtrack em loop.
     */
    public static void playSoundtrack(String caminho) {
        stopSoundtrack();

        try {
            soundtrack = carregarClip(caminho);
            soundtrackAtual = caminho;
            soundtrack.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("Erro ao tocar soundtrack: " + e.getMessage());
        }
    }

    /**
     * Troca a soundtrack atual por outra.
     */
    public static void changeSoundtrack(String caminho) {
        if (soundtrackAtual != null && soundtrackAtual.equals(caminho) && isSoundtrackPlaying()) {
            return;
        }

        playSoundtrack(caminho);
    }

    /**
     * Para a soundtrack.
     */
    public static void stopSoundtrack() {
        try {
            if (soundtrack != null) {
                soundtrack.stop();
                soundtrack.close();
                soundtrack = null;
                soundtrackAtual = null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao parar soundtrack: " + e.getMessage());
        }
    }

    /**
     * Toca um efeito sonoro por cima da soundtrack.
     */
    public static void playEffect(String caminho) {
        try {
            Clip efeito = carregarClip(caminho);

            efeitosAtivos.add(efeito);

            efeito.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    efeito.close();
                    efeitosAtivos.remove(efeito);
                }
            });

            efeito.start();

        } catch (Exception e) {
            System.out.println("Erro ao tocar efeito sonoro: " + e.getMessage());
        }
    }

    /**
     * Para todos os efeitos sonoros.
     */
    public static void stopAllEffects() {
        try {
            for (Clip efeito : efeitosAtivos) {
                efeito.stop();
                efeito.close();
            }

            efeitosAtivos.clear();

        } catch (Exception e) {
            System.out.println("Erro ao parar efeitos sonoros: " + e.getMessage());
        }
    }

    /**
     * Para todos os áudios.
     */
    public static void stopAll() {
        stopSoundtrack();
        stopAllEffects();
    }

    /**
     * Verifica se a soundtrack está a tocar.
     */
    public static boolean isSoundtrackPlaying() {
        return soundtrack != null && soundtrack.isRunning();
    }

    public static String getSoundtrackAtual() {
        return soundtrackAtual;
    }
}