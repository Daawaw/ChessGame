package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.Bishop;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Piece;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class BishopTest {
    Board test;

    @Test
    public void getLegalMoves() {
        test = new Board();
        for (int y = 0; y < test.ROW_COUNT; y++) {
            for (int x = 0; x < test.COLUMN_COUNT; x++) {
                test.setCell(x+y*test.ROW_COUNT, new Cell(test, y,x));
            }
        }
        Piece testBishop = new Bishop(test, 58, Alliance.WHITE);
        test.setWhitePiece(0, testBishop);
        String actualString = "";
        Collection<Move> actual = testBishop.getLegalMoves(test);
        for(Move test:actual) actualString += test.toString();
        String expected = "Bb7Ba6Bd7Be6Bf5Bg4Bh3";
        Assert.assertEquals(expected, actualString);
    }

    @Test
    public void getStrength() {
        test = new Board();
        Piece testBishop = test.getCell(58).getPiece();
        int expected = 3;
        int actual = testBishop.getStrength();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void hardCopy() {
        test = new Board();
        Piece testBishop = test.getCell(58).getPiece();
        Piece expected = new Bishop(test, 58, Alliance.WHITE);
        Piece actualBishop = testBishop.hardCopy(test);
        Assert.assertEquals(actualBishop, expected);
    }
}