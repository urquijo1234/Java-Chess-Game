package ajedrezjuego;

import java.util.LinkedList;

public class Pieza {

    int xp;
    int yp;

    int x;
    int y;

    boolean isWhite;
    LinkedList<Pieza> ps;
    String name;

    public Pieza(int xp, int yp, boolean isWhite, String n, LinkedList<Pieza> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.isWhite = isWhite;
        this.ps = ps;
        name = n;
        ps.add(this);
    }

    public void move(int xp, int yp) {
        if (ChessGame.getPieza(xp * 64, yp * 64) != null) {
            if (ChessGame.getPieza(xp * 64, yp * 64).isWhite != isWhite) {
                ChessGame.getPieza(xp * 64, yp * 64).kill();
            } else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
    }

    public void kill() {
        ps.remove(this);
    }
}
