package conwayslife;

import java.awt.Graphics;

public class Board extends javax.swing.JPanel {

    int N = 50;
    int density = 20;
    int w = 600 / N;
    Cell[][] square = new Cell[N][N];
    LifeFrame theFrame;
    int up = 10;
    int left = 10;
    int right = left + N * w;
    int down = up + N * w;
    Cell[][] old1 = defaultGrid();
    Cell[][] old2 = defaultGrid();
    int count = 0;
    boolean viral;

    public Board(LifeFrame theFrame) {
        initComponents();
        this.theFrame = theFrame;
        createSquare();
    }

    public void reset() {
        theFrame.setRunning(false);
        createSquare();
        old1 = defaultGrid();
        old2 = defaultGrid();
        theFrame.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                g.setColor(square[row][column].getColor());
                g.fillRect(left + column * w, up + row * w, w, w);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        int column = (evt.getX() - left) / w;
        int row = (evt.getY() - up) / w;
        square[row][column].setAlive(true);
        square[row][column].setViral(viral);
        theFrame.repaint();
    }//GEN-LAST:event_formMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void createSquare() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                square[row][column] = new Cell(0, false);
            }
        }
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (((int) (Math.random() * 100)) < density) {
                    square[row][column].setAlive(true);
                }
            }
        }
    }

    public void checkNeighbors() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                square[row][column].setNeighbors(0);
                square[row][column].setNeighbors(countNeighbors(row, column));
            }
        }
    }

    private int countNeighbors(int row, int column) {
        int neighbors = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (square[fix(r)][fix(c)].getAlive()) {
                    if (r != row || c != column) {
                        neighbors++;
                    }
                }
            }
        }
        return neighbors;
    }

    private int fix(int n) {
        if (n < 0) {
            return N - 1;
        }
        if (n > N - 1) {
            return 0;
        }
        return n;
    }

    void update() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                square[row][column].update();
            }
        }
    }

    @Override
    public String toString() {
        String returnMe = "";
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                returnMe += "\n" + (square[row][column]).toString() + " ";
            }
        }
        return returnMe;
    }

    public void checkVirus() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                square[row][column].setViralNeighbors(countViralNeighbors(row, column));
            }
        }
    }

    private int countViralNeighbors(int row, int column) {
        int neighbors = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (square[fix(r)][fix(c)].getAlive() && square[fix(r)][fix(c)].viral) {
                    if (r != row || c != column) {
                        neighbors++;
                    }
                }
            }
        }
        return neighbors;
    }

    private Cell[][] copy(Cell[][] input) {
        Cell[][] returnMe = defaultGrid();
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                returnMe[row][column].setAlive(input[row][column].getAlive());
                returnMe[row][column].setNeighbors(input[row][column].getNeighbors());
                returnMe[row][column].setViralNeighbors(input[row][column].getViralNeighbors());
            }
        }
        return returnMe;
    }

    private Cell[][] defaultGrid() {
        Cell[][] returnMe = new Cell[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                returnMe[row][column] = new Cell(0, false, false);
            }
        }
        return returnMe;
    }

    public void setN(int n) {
        this.N = n;
        w = 600 / n;
        square = new Cell[N][N];
        createSquare();
        old1 = defaultGrid();
        old2 = defaultGrid();
    }

    public boolean boardStuck() {
        if (sameGrid(square, old2) == false) {
            return sameGrid(square, old1) != false;
        }
        return true;
    }

    private boolean sameGrid(Cell[][] g1, Cell[][] g2) {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (sameCell(g1[row][column], g2[row][column]) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean sameCell(Cell c1, Cell c2) {
        return c1.getAlive() == c2.getAlive();
    }

    void recordGrids() {
        old2 = copy(old1);
        old1 = copy(square);
    }

    public String saveString() {
        String returnMe = "" + N + "\n";
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                returnMe += "" + row + ";" + column + ";" + (square[row][column]).saveString() + "\n";
            }
        }
        return returnMe;
    }

    public void add(MyReader mr) {
        String input = mr.giveMeTheNextLine();
        String[] tokens = input.split(";");
        if (count == 0) {
            setN(Integer.parseInt(tokens[0]));
            count++;
        } else {
            int r = Integer.parseInt(tokens[0]);
            int c = Integer.parseInt(tokens[1]);
            square[r][c] = new Cell(tokens);
        }
    }

    public void setDensity(int n) {
        this.density = n;
    }

    public void toggleVirus() {
        viral = !viral;
    }

}
