package entities;

import java.util.Random;

public class Mew extends PokemonLegendary{

    public Mew(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

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
            this.clearStatusEffect(); // Usando o teu método público da superclasse
        }

        switch (attackChoice) {
            case 0:
                // 🌠 Julgamento de Arceus
                finalDamage = (int) (baseDamage * 1.5);
                System.out.println("🌠 O Mew invocou o [JUDGEMENT DE ARCEUS]!");
                System.out.println("💥 Raios de luz divina caem do céu bombardeando " + enemy.getName() + " com " + finalDamage + " de dano!");
                break;

            case 1:
                // 🔥 Genesis Supernova
                finalDamage = baseDamage;
                enemy.setStatusEffect(StatusEffect.BURNED);
                System.out.println("🔥 O Mew usou [GENESIS SUPERNOVA]!");
                System.out.println("💥 Uma bola de energia psíquica explode contra " + enemy.getName() + " causando " + finalDamage + " de dano!");
                break;

            case 2:
                // 👁️ Psystrike (Roubo de Vida Seguro)
                finalDamage = (int) (baseDamage * 0.8);
                int healAmount = finalDamage;

                // 🌟 CORREÇÃO AQUI: Usa o teu método heal() herdado da superclasse
                this.heal(healAmount);

                System.out.println("👁️ O Mew replicou o [PSYSTRIKE DE MEWTWO]!");
                System.out.println("💥 Uma onda mental esmaga " + enemy.getName() + " causando " + finalDamage + " de dano.");
                System.out.println("💚 O Mew absorveu a força vital! HP atual: " + this.getHp() + "/" + this.getMaxHp());
                break;

            case 3:
                // ⚡ Roar of Time
                finalDamage = (int) (baseDamage * 1.1);
                enemy.setStatusEffect(StatusEffect.PARALYZED);
                System.out.println("⏳ O Mew invocou o [ROAR OF TIME DE DIALGA]!");
                System.out.println("💥 O tempo congela! " + enemy.getName() + " sofre " + finalDamage + " de dano temporal!");
                break;
        }

        return finalDamage;
    }
}