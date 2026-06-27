package entities;

/**
 * lista dos Status Effect do jogo
 */
public enum StatusEffect {
    PARALYZED,  // 50% chance de não atacar
    ASLEEP,     // não ataca durante 2 turnos
    POISONED,   // perde HP fixo por turno
    BURNED      // perde HP fixo por turno + strength reduzida
}
