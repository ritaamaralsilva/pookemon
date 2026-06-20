package entities.secretCave;

import java.util.ArrayList;
import java.util.Random;

public class TeamRocketAmbush {
    // Declaração da ArrayList
    private ArrayList<String> members;
    private Random random;

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

    public String getMember() {
        // o random aqui vai escolher uma posição do array list que tem os membros que adicionei
        int chosenTeamRocketMember = random.nextInt(this.members.size());
        return this.members.get(chosenTeamRocketMember);
    }
}