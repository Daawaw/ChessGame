package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class KingTest {
    private Board test;

    @Test
    public void getLegalAttackMoves() {
        test = new Board();
        for (int y = 0; y < test.ROW_COUNT; y++) {
            for (int x = 0; x < test.COLUMN_COUNT; x++) {
                test.setCell(x+y*test.ROW_COUNT, new Cell(test, y,x));
            }
        }
        Piece testKing = new King(test, 60, Alliance.WHITE);
        Piece testQueen = new Queen(test, 59, Alliance.BLACK);
        test.setBlackPiece(3, testQueen);
        test.setWhitePiece(4, testKing);
        String actualString = "";
        Collection<Move> actual = testKing.getLegalAttackMoves(test);
        for(Move test:actual) actualString += test.toString();
        String expected = "Kd7Ke7Kf7Kxd8Kf8";
        System.out.println(test);
        Assert.assertEquals(expected, actualString);

    }

    @Test
    public void getStrength() {
        test = new Board();
        Piece testKing = new King(test, 60, Alliance.WHITE);
        int expected = 100;
        int actual = testKing.getStrength();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hardCopy() {
        test = new Board();
        Piece expected = test.getCell(60).getPiece();
        Piece testKing = new King(test, 60, Alliance.WHITE);
        Piece actual = testKing.hardCopy(test);
        Assert.assertEquals(expected, actual);
    }
}