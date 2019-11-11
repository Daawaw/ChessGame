package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Piece;
import com.daawaw.game.model.pieces.Rook;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class RookTest {
    private Board test;

    @Test
    public void getLegalMoves() {
        test = new Board();
        for (int y = 0; y < test.ROW_COUNT; y++) {
            for (int x = 0; x < test.COLUMN_COUNT; x++) {
                test.setCell(x+y*test.ROW_COUNT, new Cell(test, y,x));
            }
        }
        Piece testRook = new Rook(test, 63, Alliance.WHITE);
        test.setWhitePiece(0, testRook);
        String actualString = "";
        Collection<Move> actual = testRook.getLegalMoves(test);
        for(Move test:actual) actualString += test.toString();
        String expected = "Rh7Rh6Rh5Rh4Rh3Rh2Rh1Rg8Rf8Re8Rd8Rc8Rb8Ra8";
        Assert.assertEquals(expected, actualString);
    }

    @Test
    public void getStrength() {
        test = new Board();
        Piece testRook = test.getCell(63).getPiece();
        int expected = 5;
        int actual = testRook.getStrength();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hardCopy() {
        test = new Board();
        Piece testRook = test.getCell(63).getPiece();
        Piece expected = new Rook(test, 63, Alliance.WHITE);
        Piece actualRook = testRook.hardCopy(test);
        Assert.assertEquals(actualRook, expected);
    }
}