package entities;

import java.util.ArrayList;

public class Gym {
   private String name;
   private int minLevelToBattle; // nível mínimo para entrar no gym e combater
   private int reward;   // recompensa em coins se jogador ganhar

   private ArrayList<Pokemon> pokemonGym; // lista dos pokemon de cada ginasio

   public Gym(String name, int minLevelToBattle, int reward) {
      this.name = name;
      this.minLevelToBattle = minLevelToBattle;
      this.reward = reward;
      this.pokemonGym = new ArrayList<>();
   }
   public void addPokemon(Pokemon pokemon) { // adiciona pokemon ao arraylist de pokemon do ginasio
      pokemonGym.add(pokemon);
   }

   public int getMinLevelToBattle() {
      return minLevelToBattle;
   }

   public int getReward() {
      return reward;
   }

   public ArrayList<Pokemon> getPokemonGym() {
      return pokemonGym;
   }
}

