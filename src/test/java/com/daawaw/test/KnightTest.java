package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.Knight;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Piece;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class KnightTest {
    private Board test;

    @Test
    public void getLegalMoves() {
        test = new Board();
        for (int y = 0; y < test.ROW_COUNT; y++) {
            for (int x = 0; x < test.COLUMN_COUNT; x++) {
                test.setCell(x+y*test.ROW_COUNT, new Cell(test, y,x));
            }
        }
        Piece knight = new Knight(test, 62, Alliance.WHITE);
        test.setWhitePiece(6, knight);
        String actualString = "";
        Collection<Move> actual = knight.getLegalMoves(test);
        for(Move test:actual) actualString += test.toString();
        String expected = "Nf6Nh6Ne7";
        Assert.assertEquals(expected, actualString);
    }

    @Test
    public void getStrength() {
        test = new Board();
        Piece knight = new Knight(test, 62, Alliance.WHITE);
        int expected = 3;
        int actual = knight.getStrength();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hardCopy() {
        test = new Board();
        Piece expected = test.getCell(62).getPiece();
        Piece knight = new Knight(test, 62, Alliance.WHITE);
        Piece actual = knight.hardCopy(test);
        Assert.assertEquals(expected, actual);
    }
}