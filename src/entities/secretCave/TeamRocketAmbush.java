package entities.secretCave;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa um evento aleatório de perigo na zona da SecretCave: a emboscada da Team Rocket.
 * <p>
 * Atua como o contrapeso tático e hostil aos encontros benéficos. Esta classe gerencia o
 * alinhamento de antagonistas disponíveis para o assalto e escolhe de forma estocástica
 * qual o vilão que irá intercetar o utilizador, despoletando um combate ou uma penalização.
 * </p>
 */
public class TeamRocketAmbush {
    // Declaração da ArrayList
    private ArrayList<String> members;
    private Random random;

    /**
     * Construtor que inicializa o motor estocástico e popula a fação de vilões.
     * <p>
     * Insere na lista os clássicos agentes do anime (Jessie e James), os rivais da fação
     * secundária (Butch e Cassidy), recrutas genéricos (Grunts) e o próprio Meowth em
     * modo larápio, expandindo o fator de nostalgia do ecossistema.
     * </p>
     */
    public TeamRocketAmbush() {
        this.random = new Random();
        this.members = new ArrayList<>();

        this.members.add("Jessie");
        this.members.add("James");
        this.members.add("um Grunt da Team Rocket");
        this.members.add("Butch");
        this.members.add("Cassidy");
        this.members.add("Meowth ladrão");
    }

    /**
     * Sorteia e devolve a identidade do vilão responsável pela emboscada atual.
     * <p>
     * Utiliza o tamanho dinâmico do vetor ({@code members.size()}) como teto para o método
     * {@link Random#nextInt(int)}, garantindo uma distribuição uniforme de probabilidade
     * entre todos os inimigos inscritos na classe.
     * </p>
     *
     * @return Uma {@link String} contendo o nome do membro sorteado da Team Rocket.
     */
    public String getMember() {
        // o random aqui vai escolher uma posição do array list que tem os membros que adicionei
        int chosenTeamRocketMember = random.nextInt(this.members.size());
        return this.members.get(chosenTeamRocketMember);
    }
}