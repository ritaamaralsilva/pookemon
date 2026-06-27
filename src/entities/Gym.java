package entities;

import java.util.ArrayList;

public class Gym {
   private String name;
   private int minLevelToBattle; // nível mínimo para entrar no gym e combater
   private int reward;   // recompensa em coins se jogador ganhar

   private ArrayList<Pokemon> pokemonGym; // lista dos pokemon de cada ginasio

   /**
    * Representa um Ginásio Pookémon do circuito oficial da Liga de Kanto (como nos jogos da 1a gen de Pokemon).
    * <p>
    * Esta classe atua como uma estrutura de dados e controlo para os desafios de Boss do jogo.
    * É responsável por armazenar a identidade do ginásio, gerir a equipa de criaturas do Líder
    * através de uma lista dinâmica, e validar se o jogador possui a maturidade de nível
    * necessária para iniciar a batalha de qualificação.
    * </p>
    */
   public Gym(String name, int minLevelToBattle, int reward) {
      this.name = name;
      this.minLevelToBattle = minLevelToBattle;
      this.reward = reward;
      this.pokemonGym = new ArrayList<>();
   }
   /**
    * Adiciona um Pookémon (instâncias especializadas de ginásio) ao alinhamento
    * de combate do Líder.
    *
    * @param pokemon O {@link Pokemon} a ser inserido no final da lista {@link #pokemonGym}.
    */
   public void addPokemon(Pokemon pokemon) { // adiciona pokemon ao arraylist de pokemon do ginasio
      pokemonGym.add(pokemon);
   }

   /**
    * Obtém a barreira ou requisito de nível mínimo exigido para desafiar este ginásio.
    *
    * @return O valor inteiro guardado no atributo {@link #minLevelToBattle}.
    */
   public int getMinLevelToBattle() {
      return minLevelToBattle;
   }

   /**
    * Obtém o prémio monetário total associado à superação do desafio do Líder.
    *
    * @return O valor em moedas definido no atributo {@link #reward}.
    */
   public int getReward() {
      return reward;
   }

   /**
    * Recupera a equipa completa de Pookémons pertencentes a este ginásio para fins
    * de iteração no motor de batalha.
    *
    * @return O {@link ArrayList} contendo todas as instâncias de {@link Pokemon} do ginásio.
    */
   public ArrayList<Pokemon> getPokemonGym() {
      return pokemonGym;
   }
}

