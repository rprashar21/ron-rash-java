package com.corejava.Generics.playersexample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team<T extends Player> {

    private String name;
    int played = 0;
    int won = 0;
    int lost = 0;
    int tied = 0;

    public Team(final String name) {
        this.name = name;
    }

    private List<Player> team = new ArrayList<>();

    public void addPlayers(T player) {
        if (this.team.contains(player)) {
            System.out.println(player.getName() + " is already in the team");
        } else {
            team.add(player);
        }
    }

    public int numOfPlayers() {
        return team.size();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTeamPlayerNames() {
        //  return team.stream().map(Player::getName).collect(Collectors.joining("-"));

        return team.stream().map(Player::getName).collect(Collectors.joining(","));
//        return palyerNames;
    }

    public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        String message;
        if (ourScore > theirScore) {
            won++;
            message = " beat ";
        } else if (ourScore == theirScore) {

            tied++;
            message = " tied ";
        } else {
            lost++;
            message = " lost ";
        }


        if (opponent != null) {
            // here we save the result of the opponents team as well
            System.out.println(this.getName()+ message +opponent.name);
            opponent.matchResult(null, theirScore, ourScore);

        }

    }

    public int ranking() {
        return (won * 3) + tied;
    }

    @Override
    public String toString() {
        return name;
    }
}
