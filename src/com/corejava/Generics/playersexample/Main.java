package com.corejava.Generics.playersexample;

public class Main {

    public static void main(String[] args) {
//
        // footballteam.addPlayers(niraj); // cannot adda basketballPlayer

        Team<FootballPlayer> manUnited = new Team<>("Man United");
        // footballteam.addPlayers(niraj); // cannot adda basketballPlayer
        manUnited.addPlayers(new FootballPlayer("rohit"));// i can adda fotballplayer -- gernerics
        manUnited.addPlayers(new FootballPlayer("shahbaz"));


        Team<FootballPlayer> liverpool = new Team<>("liverpool");
        liverpool.addPlayers(new FootballPlayer("nitish"));
        liverpool.addPlayers(new FootballPlayer("chandan"));



        // first condition comparing results of the football teams
        manUnited.matchResult(liverpool,2,1);

        BaskeBallPlayer niraj = new BaskeBallPlayer("niraj");
        BaskeBallPlayer uday = new BaskeBallPlayer("uday");
        BaskeBallPlayer rahul = new BaskeBallPlayer("rahul");
        BaskeBallPlayer raju = new BaskeBallPlayer("raju");

        Team<BaskeBallPlayer> chicagoBulls = new Team<>("ChicagoBulls");
        Team<BaskeBallPlayer> nyc = new Team<>("NC");



        chicagoBulls.addPlayers(uday);
        chicagoBulls.addPlayers(niraj);

        nyc.addPlayers(rahul);
        nyc.addPlayers(raju);
      //  basketBallTeam.addPlayers(shahbaz);

        // trying to matchresults of different types of teams like football and basketball should give error but it doesnt
        // so we add the specific type or type parameter T
//        chicagoBulls.matchResult(manUnited,3,1);
        // after addint T o the method
        chicagoBulls.matchResult(nyc,1,1);


        // compare to method
        // give each team a ran
    }


}
