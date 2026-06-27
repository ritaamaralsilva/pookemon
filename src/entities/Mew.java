package entities;

import java.util.Random;

/**
 * Representa o Pookémon Mítico Mew, o guardião do código genético ancestral.
 * <p>
 * <b>Mecânica de Transição de Instância:</b> Durante o encontro na Cave Secreta, a criatura
 * é inicialmente instanciada e enfrentada como um {@link PokemonWild} comum (ocultando a sua
 * divindade). Após a captura bem-sucedida pelo jogador, o sistema converte-o nesta classe
 * concreta especializada {@code Mew}, "despertando" o seu potencial latente.
 * </p>
 * <p>
 * A partir deste ponto, o Mew passa a usufruir do <i>override</i> do método
 * {@link #applySpecialAttack(Pokemon)}, recorrendo a um motor estocástico (aleatório)
 * que replica as habilidades transcendentais de outras deidades do universo Pookémon.
 * </p>
 */
public class Mew extends PokemonLegendary{

    public Mew(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    /**
     * Executa a rotação mecânica do ataque especial mítico do Mew.
     * <p>
     * O método inicia-se com uma fase de **Purificação Cósmica**, onde o Mew analisa o seu
     * próprio estado e invoca {@link #clearStatusEffect()} se estiver sob a influência de debuffs,
     * agindo como uma auto-cura passiva e imutável de fim/início de turno.
     * </p>
     * <p>
     * Posteriormente, recorre a uma escolha pseudoaleatória (via {@link Random}) de entre 4 movimentos:
     * </p>
     * <ul>
     * <li><b>Caso 0 (Judgement de Arceus):</b> Dano massivo com um escalamento de 150% do ataque especial base.</li>
     * <li><b>Caso 1 (Genesis Supernova):</b> Dano base padrão acompanhado do estado contínuo {@link StatusEffect#BURNED}.</li>
     * <li><b>Caso 2 (Psystrike de Mewtwo):</b> Aplica 80% do dano base e executa um roubo de vida seguro através de {@link #heal(int)}.</li>
     * <li><b>Caso 3 (Roar of Time de Dialga):</b> Dano amplificado em 110% adicionado do estado de paralisia {@link StatusEffect#PARALYZED}.</li>
     * </ul>
     *
     * @param enemy O {@link Pokemon} do jogador selecionado como alvo para a distorção cósmica.
     * @return O dano calculado dinamicamente pelo ataque sorteado, a ser deduzido ao alvo.
     */
    @Override
    public int applySpecialAttack(Pokemon enemy) {
        Random rand = new Random();
        int attackChoice = rand.nextInt(4); // 0 a 3
        int baseDamage = this.getSpecialAttack();
        int finalDamage = 0;

        System.out.println("\n✨ O espaço-tempo distorce-se... O Mew flutua e os seus olhos brilham com a energia ancestral de Kanto! ✨");

        // Purificação Cósmica: Se o Mew tiver um status alterado, limpa-o!
        if (this.getStatusEffect() != null) {
            System.out.println("🌀 Cosmic Purge! O Mew purificou o seu próprio estado de " + this.getStatusEffect() + "!");
            this.clearStatusEffect();
        }

        switch (attackChoice) {
            case 0:
                // Julgamento de Arceus
                finalDamage = (int) (baseDamage * 1.5);
                System.out.println("🌠 O Mew invocou o [JUDGEMENT DE ARCEUS]!");
                System.out.println("💥 Raios de luz divina caem do céu bombardeando " + enemy.getName() + " com " + finalDamage + " de dano!");
                break;

            case 1:
                // Genesis Supernova
                finalDamage = baseDamage;
                enemy.setStatusEffect(StatusEffect.BURNED);
                System.out.println("🔥 O Mew usou [GENESIS SUPERNOVA]!");
                System.out.println("💥 Uma bola de energia psíquica explode contra " + enemy.getName() + " causando " + finalDamage + " de dano!");
                break;

            case 2:
                // Psystrike (Roubo de Vida Seguro)
                finalDamage = (int) (baseDamage * 0.8);
                int healAmount = finalDamage;

                this.heal(healAmount);

                System.out.println("👁️ O Mew replicou o [PSYSTRIKE DE MEWTWO]!");
                System.out.println("💥 Uma onda mental esmaga " + enemy.getName() + " causando " + finalDamage + " de dano.");
                System.out.println("💚 O Mew absorveu a força vital! HP atual: " + this.getHp() + "/" + this.getMaxHp());
                break;

            case 3:
                // Roar of Time
                finalDamage = (int) (baseDamage * 1.1);
                enemy.setStatusEffect(StatusEffect.PARALYZED);
                System.out.println("⏳ O Mew invocou o [ROAR OF TIME DE DIALGA]!");
                System.out.println("💥 O tempo congela! " + enemy.getName() + " sofre " + finalDamage + " de dano temporal!");
                break;
        }

        return finalDamage;
    }
}