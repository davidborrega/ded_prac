package uoc.ds.pr.model;

public class Enrollment {

    private Player player;

    public Enrollment(Player player) {
        this.setPlayer(player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
