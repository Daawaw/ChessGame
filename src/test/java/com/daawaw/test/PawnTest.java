package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Pawn;
import com.daawaw.game.model.pieces.Piece;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class PawnTest {
    private Board test;

    @Test
    public void getLegalMoves() {
        test = new Board();
        for (int y = 0; y < test.ROW_COUNT; y++) {
            for (int x = 0; x < test.COLUMN_COUNT; x++) {
                test.setCell(x+y*test.ROW_COUNT, new Cell(test, y,x));
            }
        }
        Piece testPawn = new Pawn(test, 48, Alliance.WHITE);
        test.setWhitePiece(8, testPawn);
        String actualString = "";
        Collection<Move> actual = testPawn.getLegalMoves(test);
        for(Move test:actual) actualString += test.toString();
        String expected = "Pa6Pa5";
        Assert.assertEquals(expected, actualString);
    }

    @Test
    public void getStrength() {
        test = new Board();
        Piece testPawn = new Pawn(test, 48, Alliance.WHITE);
        int expected = 1;
        int actual = testPawn.getStrength();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void hardCopy() {
        test = new Board();
        Piece expected = test.getCell(48).getPiece();
        Piece testPawn = new Pawn(test, 48, Alliance.WHITE);
        Piece actual = testPawn.hardCopy(test);
        Assert.assertEquals(expected, actual);
    }
}