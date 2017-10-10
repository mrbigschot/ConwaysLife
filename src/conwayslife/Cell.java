package conwayslife;

import java.awt.Color;

public class Cell {

    public static boolean colorful = false;
    int neighbors, viralNeighbors;
    boolean alive, viral;

    public Cell() {
    }

    public Cell(int neighbors, boolean alive) {
        this();
        this.neighbors = neighbors;
        this.alive = alive;
    }

    public Cell(int neighbors, boolean alive, boolean virus) {
        this(neighbors, alive);
        viral = virus;
    }

    public Cell(String[] tokens) {
        alive = tokens[2].equals("true");
        viral = tokens[3].equals("true");
    }

    public int getNeighbors() {
        return neighbors;
    }

    public int getViralNeighbors() {
        return viralNeighbors;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    public void setViralNeighbors(int n) {
        this.viralNeighbors = n;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setViral(boolean v) {
        viral = v;
    }

    void update() {
        if (alive) {
            if (neighbors < 2 || neighbors > 3) {
                alive = false;
                viral = false;
            } else {
                if (viralNeighbors > 0) {
                    viral = true;
                }
            }
        } else {
            if (neighbors == 3) {
                alive = true;
                if (viralNeighbors > 0) {
                    viral = true;
                }
            }
        }
    }

    Color getColor() {
        if (colorful) {
            switch (neighbors) {
                case 0:
                    return new Color(0, 0, 0);
                case 1:
                    return new Color(100, 100, 100);
                case 2:
                    return new Color(0, 0, 255);
                case 3:
                    return new Color(0, 255, 255);
                case 4:
                    return new Color(0, 255, 0);
                case 5:
                    return new Color(255, 255, 0);
                case 6:
                    return new Color(255, 150, 0);
                case 7:
                    return new Color(255, 0, 0);
                case 8:
                    return new Color(255, 255, 255);
            }
        } else {
            if (alive) {
                if (viral) {
                    return Color.RED;
                }
                return Color.WHITE;
            }
        }
        return Color.BLACK;
    }

    @Override
    public String toString() {
        String returnMe = "Cell: ";
        returnMe += "\n\tLiving Neighbors = " + getNeighbors();
        returnMe += "\n\tAlive = " + getAlive();
        returnMe += "\n\tViral = " + viral;
        return returnMe;
    }

    String saveString() {
        String returnMe = "";
        returnMe += getAlive() + ";";
        returnMe += (viral ? 1 : 0);
        return returnMe;
    }

}
