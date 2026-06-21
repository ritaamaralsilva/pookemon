package game;

public final class ConsoleColors {



    private ConsoleColors() {

        // Impede criar objetos desta classe

    }



    private static boolean colorsEnabled = true;



    // Escape ANSI

    private static final String ESC = "\u001B[";



    // Reset

    public static final String RESET = ESC + "0m";



    // Estilos

    public static final String BOLD = ESC + "1m";

    public static final String UNDERLINE = ESC + "4m";

    public static final String REVERSED = ESC + "7m";



    // Regular Colors

    public static final String BLACK = ESC + "0;30m";

    public static final String RED = ESC + "0;31m";

    public static final String GREEN = ESC + "0;32m";

    public static final String YELLOW = ESC + "0;33m";

    public static final String BLUE = ESC + "0;34m";

    public static final String PURPLE = ESC + "0;35m";

    public static final String CYAN = ESC + "0;36m";

    public static final String WHITE = ESC + "0;37m";



    // Bold Colors

    public static final String BLACK_BOLD = ESC + "1;30m";

    public static final String RED_BOLD = ESC + "1;31m";

    public static final String GREEN_BOLD = ESC + "1;32m";

    public static final String YELLOW_BOLD = ESC + "1;33m";

    public static final String BLUE_BOLD = ESC + "1;34m";

    public static final String PURPLE_BOLD = ESC + "1;35m";

    public static final String CYAN_BOLD = ESC + "1;36m";

    public static final String WHITE_BOLD = ESC + "1;37m";



    // Underline Colors

    public static final String BLACK_UNDERLINED = ESC + "4;30m";

    public static final String RED_UNDERLINED = ESC + "4;31m";

    public static final String GREEN_UNDERLINED = ESC + "4;32m";

    public static final String YELLOW_UNDERLINED = ESC + "4;33m";

    public static final String BLUE_UNDERLINED = ESC + "4;34m";

    public static final String PURPLE_UNDERLINED = ESC + "4;35m";

    public static final String CYAN_UNDERLINED = ESC + "4;36m";

    public static final String WHITE_UNDERLINED = ESC + "4;37m";



    // Background Colors

    public static final String BLACK_BACKGROUND = ESC + "40m";

    public static final String RED_BACKGROUND = ESC + "41m";

    public static final String GREEN_BACKGROUND = ESC + "42m";

    public static final String YELLOW_BACKGROUND = ESC + "43m";

    public static final String BLUE_BACKGROUND = ESC + "44m";

    public static final String PURPLE_BACKGROUND = ESC + "45m";

    public static final String CYAN_BACKGROUND = ESC + "46m";

    public static final String WHITE_BACKGROUND = ESC + "47m";



    // Bright Colors

    public static final String BLACK_BRIGHT = ESC + "0;90m";

    public static final String RED_BRIGHT = ESC + "0;91m";

    public static final String GREEN_BRIGHT = ESC + "0;92m";

    public static final String YELLOW_BRIGHT = ESC + "0;93m";

    public static final String BLUE_BRIGHT = ESC + "0;94m";

    public static final String PURPLE_BRIGHT = ESC + "0;95m";

    public static final String CYAN_BRIGHT = ESC + "0;96m";

    public static final String WHITE_BRIGHT = ESC + "0;97m";



    // Bold Bright Colors

    public static final String BLACK_BOLD_BRIGHT = ESC + "1;90m";

    public static final String RED_BOLD_BRIGHT = ESC + "1;91m";

    public static final String GREEN_BOLD_BRIGHT = ESC + "1;92m";

    public static final String YELLOW_BOLD_BRIGHT = ESC + "1;93m";

    public static final String BLUE_BOLD_BRIGHT = ESC + "1;94m";

    public static final String PURPLE_BOLD_BRIGHT = ESC + "1;95m";

    public static final String CYAN_BOLD_BRIGHT = ESC + "1;96m";

    public static final String WHITE_BOLD_BRIGHT = ESC + "1;97m";



    // Bright Backgrounds

    public static final String BLACK_BACKGROUND_BRIGHT = ESC + "0;100m";

    public static final String RED_BACKGROUND_BRIGHT = ESC + "0;101m";

    public static final String GREEN_BACKGROUND_BRIGHT = ESC + "0;102m";

    public static final String YELLOW_BACKGROUND_BRIGHT = ESC + "0;103m";

    public static final String BLUE_BACKGROUND_BRIGHT = ESC + "0;104m";

    public static final String PURPLE_BACKGROUND_BRIGHT = ESC + "0;105m";

    public static final String CYAN_BACKGROUND_BRIGHT = ESC + "0;106m";

    public static final String WHITE_BACKGROUND_BRIGHT = ESC + "0;107m";



    /**

     * Permite ligar ou desligar as cores.

     */

    public static void setColorsEnabled(boolean enabled) {

        colorsEnabled = enabled;

    }



    /**

     * Aplica uma cor/estilo a um texto.

     */

    public static String colorize(String text, String color) {

        if (!colorsEnabled) {

            return text;

        }



        return color + text + RESET;

    }



    /**

     * Imprime texto colorido sem mudar de linha.

     */

    public static void print(String text, String color) {

        System.out.print(colorize(text, color));

    }



    /**

     * Imprime texto colorido com mudança de linha.

     */

    public static void println(String text, String color) {

        System.out.println(colorize(text, color));

    }



    /**

     * Mensagem de erro.

     */

    public static void error(String text) {

        println("❌ " + text, RED_BOLD_BRIGHT);

    }



    /**

     * Mensagem de sucesso.

     */

    public static void success(String text) {

        println("✅ " + text, GREEN_BOLD_BRIGHT);

    }



    /**

     * Mensagem de aviso.

     */

    public static void warning(String text) {

        println("⚠️ " + text, YELLOW_BOLD_BRIGHT);

    }



    /**

     * Mensagem informativa.

     */

    public static void info(String text) {

        println("ℹ️ " + text, CYAN_BOLD_BRIGHT);

    }



    /**

     * Mensagem normal de jogo.

     */

    public static void game(String text) {

        println(text, WHITE_BOLD);

    }



    /**

     * Mensagem narrativa.

     */

    public static void story(String text) {

        println(text, PURPLE_BRIGHT);

    }



    /**

     * Título simples.

     */

    public static void title(String text) {

        System.out.println();

        println("═".repeat(text.length() + 8), YELLOW_BOLD_BRIGHT);

        println("   " + text + "   ", YELLOW_BOLD_BRIGHT);

        println("═".repeat(text.length() + 8), YELLOW_BOLD_BRIGHT);

        System.out.println();

    }



    /**

     * Cria uma caixa simples à volta do texto.

     */

    public static void box(String text, String color) {

        String line = "═".repeat(text.length() + 4);



        println("╔" + line + "╗", color);

        println("║  " + text + "  ║", color);

        println("╚" + line + "╝", color);

    }



    /**

     * Imprime uma linha separadora.

     */

    public static void separator() {

        println("─".repeat(50), BLACK_BRIGHT);

    }



    /**

     * Limpa a consola.

     * Pode não funcionar em todos os ambientes do IntelliJ.

     */

    public static void clear() {

        System.out.print(ESC + "H" + ESC + "2J");

        System.out.flush();

    }



    /**

     * Permite usar cor RGB personalizada.

     * Nem todas as consolas suportam isto.

     */

    public static String rgb(int r, int g, int b) {

        return ESC + "38;2;" + r + ";" + g + ";" + b + "m";

    }



    /**

     * Permite usar fundo RGB personalizado.

     */

    public static String backgroundRgb(int r, int g, int b) {

        return ESC + "48;2;" + r + ";" + g + ";" + b + "m";

    }

}