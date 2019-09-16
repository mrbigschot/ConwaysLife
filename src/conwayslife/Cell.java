package conwayslife;

import java.awt.Color;

public class Cell {

    private int neighbors, viralNeighbors;
    private boolean alive, viral;

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

    public int getNeighbors() {
        return neighbors;
    }

    public int getViralNeighbors() {
        return viralNeighbors;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean val) {
        this.alive = val;
    }

    public void setNeighbors(int val) {
        this.neighbors = val;
    }

    public void setViralNeighbors(int val) {
        this.viralNeighbors = val;
    }

    public boolean isViral() { return this.viral; }
    
    public void setViral(boolean val) {
        viral = val;
    }

    public void update() {
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

    Color getColor(boolean viralMode, boolean colorful) {
        if (colorful) {
            if (viralMode) {
                switch (viralNeighbors) {
                    case 0:
                        switch (neighbors) {
                            case 0:
                                return new Color(0, 0, 0);
                            case 1:
                                return new Color(200, 250, 250);
                            case 2:
                                return new Color(150, 250, 200);
                            case 3:
                                return new Color(100, 250, 150);
                            case 4:
                                return new Color(50, 200, 150);
                            case 5:
                                return new Color(0, 100, 100);
                            case 6:
                                return new Color(0, 50, 50);
                            case 7:
                                return new Color(0, 50, 50);
                            case 8:
                                return new Color(0, 0, 0);
                        }
                    case 1:
                        return new Color(250, 100, 100);
                    case 2:
                        return new Color(250, 75, 75);
                    case 3:
                        return new Color(250, 50, 50);
                    case 4:
                        return new Color(200, 50, 50);
                    case 5:
                        return new Color(200, 0, 0);
                    case 6:
                        return new Color(100, 0, 0);
                    case 7:
                        return new Color(100, 0, 0);
                    case 8:
                        return new Color(0, 0, 0);
                }
            } else {
                switch (neighbors) {
                    case 0:
                        return new Color(0, 0, 0);
                    case 1:
                        return new Color(200, 250, 250);
                    case 2:
                        return new Color(150, 250, 200);
                    case 3:
                        return new Color(100, 250, 150);
                    case 4:
                        return new Color(50, 200, 150);
                    case 5:
                        return new Color(0, 100, 100);
                    case 6:
                        return new Color(0, 50, 50);
                    case 7:
                        return new Color(0, 50, 50);
                    case 8:
                        return new Color(0, 0, 0);
                }
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
        returnMe += "\n\tAlive = " + isAlive();
        returnMe += "\n\tViral = " + viral;
        return returnMe;
    }

    String saveString() {
        String returnMe = "";
        returnMe += isAlive() + ";";
        returnMe += (viral ? 1 : 0);
        return returnMe;
    }

}
