package miniProject;

import java.util.Random;

public class GameModel {
    public enum Move { ROCK, PAPER, SCISSORS }
    public enum Result { WIN, LOSE, DRAW }

    private Move playerMove;
    private Move computerMove;

    public Move getPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(Move playerMove) {
        this.playerMove = playerMove;
    }

    public Move getComputerMove() {
        Random random = new Random();
        int pick = random.nextInt(Move.values().length);
        computerMove = Move.values()[pick];
        return computerMove;
    }

    public Result getResult() {
        if (playerMove == computerMove) {
            return Result.DRAW;
        }

        switch (playerMove) {
            case ROCK:
                return (computerMove == Move.SCISSORS ? Result.WIN : Result.LOSE);
            case PAPER:
                return (computerMove == Move.ROCK ? Result.WIN : Result.LOSE);
            case SCISSORS:
                return (computerMove == Move.PAPER ? Result.WIN : Result.LOSE);
            default:
                return Result.DRAW;
        }
    }
}