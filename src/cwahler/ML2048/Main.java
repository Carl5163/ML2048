package cwahler.ML2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    enum Move {

        LEFT,
        RIGHT,
        UP,
        DOWN,
        QUIT,
        NEW,
        ERROR

    }
    private static final boolean humanPlayer = true;

    private Tile[] grid;
    private int score;
    private int size;

    public static void main(String[] args) {
        new Main();
    }

    private Main() {

        size = 4;
        score = 0;

        newGame();
        printBoard();

        if (humanPlayer) {
            Move move = getInput();
            while (move != Move.QUIT) {
                if(move == Move.NEW){
                    newGame();
                    printBoard();
                }



                if(move != Move.ERROR && move != Move.NEW) {
                    if(update(move)) {
                        printBoard();
                    } else {
                        System.out.println("Game Over!");
                        System.out.println("Final Score: " + score + '\n');
                        move = Move.NEW;
                        continue;
                    }
                }

                System.out.println("Score: " + score);
                move = getInput();

            }

            System.out.println("Quitting...");
        }

    }

    private Move getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Move~> ");
            String inputString = "";
            try {
                inputString = reader.readLine().trim().toUpperCase();
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
            char move;
            if (inputString.length() != 1) {
                throw new NumberFormatException();
            } else {
                move = inputString.charAt(0);
            }
            if (move != 'L' && move != 'R' && move != 'U' && move != 'D' && move != 'Q'&& move != 'N') {
                throw new NumberFormatException();
            }
            if (move == 'Q') {
                return Move.QUIT;
            }
            if (move == 'N') {
                return Move.NEW;
            }
            if (move == 'L') {
                return Move.LEFT;
            }
            if (move == 'R') {
                return Move.RIGHT;
            }
            if (move == 'U') {
                return Move.UP;
            }
            return Move.DOWN;
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter a valid move (l, r, u, d, N for new game or, Q to quit)...");
        }
        return Move.ERROR;
    }

    private boolean slideLeft() {
        boolean madeMove = false;
        boolean moved = true;
        while (moved) {
            moved = false;
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    if (grid[i * size + j - 1].equals(0) && !grid[i * size + j].equals(0)) {
                        grid[i * size + j - 1].number = grid[i * size + j].number;
                        grid[i * size + j].number = 0;
                        moved = true;
                        madeMove = true;
                    } else if (grid[i * size + j - 1].equals(grid[i * size + j]) && !grid[i * size + j - 1].merged  && !grid[i * size + j].merged && !grid[i * size + j].equals(0)) {
                        grid[i * size + j - 1].number += grid[i * size + j].number;
                        score += grid[i * size + j - 1].number;
                        grid[i * size + j].number = 0;
                        grid[i * size + j - 1].merged = true;
                        moved = true;
                        madeMove = true;
                    }
                }
            }
        }
        return madeMove;
    }

    private boolean slideRight() {
        boolean madeMove = false;
        boolean moved = true;
        while (moved) {
            moved = false;
            for (int i = 0; i < size; i++) {
                for (int j = size-2; j >= 0; j--) {
                    if (grid[i * size + j + 1].equals(0) && !grid[i * size + j].equals(0)) {
                        grid[i * size + j + 1].number = grid[i * size + j].number;
                        grid[i * size + j].number = 0;
                        moved = true;
                        madeMove = true;
                    } else if (grid[i * size + j + 1].equals(grid[i * size + j]) && !grid[i * size + j + 1].merged  && !grid[i * size + j].merged && !grid[i * size + j].equals(0)) {
                        grid[i * size + j + 1].number += grid[i * size + j].number;
                        score += grid[i * size + j + 1].number;
                        grid[i * size + j].number = 0;
                        grid[i * size + j + 1].merged = true;
                        moved = true;
                        madeMove = true;
                    }
                }
            }
        }
        return madeMove;
    }

    private boolean slideUp() {
        boolean madeMove = false;
        boolean moved = true;
        while (moved) {
            moved = false;
            for (int i = 1; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (grid[(i - 1) * size + j].equals(0) && !grid[i * size + j].equals(0)) {
                        grid[(i - 1) * size + j].number = grid[i * size + j].number;
                        grid[i * size + j].number = 0;
                        moved = true;
                        madeMove = true;
                    } else if (grid[(i - 1) * size + j].equals(grid[i * size + j]) && !grid[(i - 1) * size + j].merged && !grid[(i) * size + j].merged && !grid[i * size + j].equals(0)) {
                        grid[(i - 1) * size + j].number += grid[i * size + j].number;
                        score += grid[(i - 1) * size + j].number;
                        grid[i * size + j].number = 0;
                        grid[(i - 1) * size + j].merged = true;
                        moved = true;
                        madeMove = true;
                    }
                }
            }
        }
        return madeMove;
    }

    private boolean slideDown() {
        boolean madeMove = false;
        boolean moved = true;
        while (moved) {
            moved = false;
            for (int i = size-2; i >= 0; i--) {
                for (int j = 0; j < size; j++) {
                    if (grid[(i + 1) * size + j].equals(0) && !grid[i * size + j].equals(0)) {
                        grid[(i + 1) * size + j].number = grid[i * size + j].number;
                        grid[i * size + j].number = 0;
                        moved = true;
                        madeMove = true;
                    } else if (grid[(i + 1) * size + j].equals(grid[i * size + j]) && !grid[(i + 1) * size + j].merged && !grid[i * size + j].equals(0)) {
                        grid[(i + 1) * size + j].number += grid[i * size + j].number;
                        score += grid[(i + 1) * size + j].number;
                        grid[i * size + j].number = 0;
                        grid[(i + 1) * size + j].merged = true;
                        moved = true;
                        madeMove = true;
                    }
                }
            }
        }
        return madeMove;
    }

    private void addNewNumber() {
        ArrayList<Integer> arr = getOpenSpots();
        if (arr.size() > 0) {
            Random random = new Random();
            int ind = random.nextInt(arr.size());
            grid[ind].number = (random.nextInt(2) + 1) * 2;
        }
    }

    private boolean update(Move move) {

        boolean madeMove = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i*size+j].merged = false;
            }
        }
        if(move == Move.LEFT) {
            madeMove = slideLeft();
        } else if (move == Move.RIGHT) {
            madeMove = slideRight();
        } else if (move == Move.UP) {
            madeMove = slideUp();
        } else if (move == Move.DOWN) {
            madeMove = slideDown();
        }

        if(madeMove) {
            addNewNumber();
        }

        return !getGameLost();

    }


    private boolean getGameLost() {
        boolean lost = true;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(grid[(i)*size+j].equals(0)) {
                    lost = false;
                } else {
                    if(j > 0) {
                        if(grid[i*size+j].equals(grid[i*size+j-1])) {
                            lost = false;
                        }
                    }
                    if(j < size-1) {
                        if(grid[i*size+j].equals(grid[i*size+j+1])) {
                            lost = false;
                        }
                    }
                    if(i > 0) {
                        if(grid[i*size+j].equals(grid[(i-1)*size+j])) {
                            lost = false;
                        }
                    }
                    if(i < size-1) {
                        if(grid[i*size+j].equals(grid[(i+1)*size+j])) {
                            lost = false;
                        }
                    }
                }
            }
        }
        return lost;
    }


    private void newGame() {
        score = 0;
        grid = new Tile[size*size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i*size+j] = new Tile(0);
            }
        }
        ArrayList<Integer> openSpots = getOpenSpots();
        Random random = new Random();


        Integer pick1 = openSpots.get(random.nextInt(openSpots.size()));
        openSpots.remove(pick1);
        Integer pick2 = openSpots.get(random.nextInt(openSpots.size()));
        openSpots.remove(pick2);
        int num = (random.nextInt(2) + 1) * 2;
        grid[pick1].number = num;
        num = (random.nextInt(2) + 1) * 2;
        grid[pick2].number = num;
    }

    private ArrayList<Integer> getOpenSpots() {
        ArrayList<Integer> openSpots = new ArrayList<>();
        for(int i = 0; i < size*size; i++) {
            if(grid[i].equals(0)) {
                openSpots.add(i);
            }
        }
        return openSpots;
    }

    private void printBoard() {
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                System.out.printf("%1$5s", grid[i * size + j]);
            }
            System.out.println();
            System.out.println();
        }
    }

}
