package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Piece;
import com.daawaw.game.model.pieces.Queen;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class QueenTest {
    private Board test;

    @Test
    public void getLegalMoves() {
        test = new Board();
        for (int y = 0; y < test.ROW_COUNT; y++) {
            for (int x = 0; x < test.COLUMN_COUNT; x++) {
                test.setCell(x+y*test.ROW_COUNT, new Cell(test, y,x));
            }
        }
        Piece testQueen = new Queen(test, 59, Alliance.WHITE);
        test.setWhitePiece(0, testQueen);
        String actualString = "";
        Collection<Move> actual = testQueen.getLegalMoves(test);
        for(Move test:actual) actualString += test.toString();
        String expected = "Qc7Qb6Qa5Qd7Qd6Qd5Qd4Qd3Qd2Qd1Qe7Qf6Qg5Qh4Qc8Qb8Qa8Qe8Qf8Qg8Qh8";
        Assert.assertEquals(expected, actualString);
    }

    @Test
    public void getStrength() {
        test = new Board();
        Piece testQueen = test.getCell(59).getPiece();
        int expected = 9;
        int actual = testQueen.getStrength();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hardCopy() {
        test = new Board();
        Piece expected = test.getCell(59).getPiece();
        Piece testQueen = new Queen(test, 59, Alliance.WHITE);
        Piece actual = testQueen.hardCopy(test);
        Assert.assertEquals(expected, actual);
    }
}