package modelo;

abstract class ScoreBehavior {

    abstract void scorePlayer(Player player);
    abstract void reward(Integer points);
    abstract void punish();

}
