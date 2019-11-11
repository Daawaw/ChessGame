package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Piece;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {
    private Board test;

    @Test
    public void execute() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        actual.execute();
        String actualString = actual.toString();
        String expected = "Pe5";
        Assert.assertEquals(actualString, expected);
    }

    @Test
    public void getStart() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        int startActual = actual.getStart();
        int startExpected = 52;
        Assert.assertEquals(startExpected, startActual);

    }

    @Test
    public void getBoard() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        Board expected = test;
        Board actualBoard = actual.getBoard();
        Assert.assertEquals(expected, actualBoard);
    }

    @Test
    public void getCheckedMove() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        Assert.assertFalse(actual.getCheckedMove());
    }

    @Test
    public void getDestination() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        int actualDestination = actual.getDestination();
        int expected = 36;
        Assert.assertEquals(expected, actualDestination);
    }

    @Test
    public void getAttackedPiece() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        Assert.assertNull(actual.getAttackedPiece());
    }


    @Test
    public void getStatus() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        Move.MoveResult expected = Move.MoveResult.UNTRIED;
        Move.MoveResult actualResult = actual.getStatus();
        Assert.assertEquals(expected, actualResult);
    }

    @Test
    public void isAttack() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        Assert.assertFalse(actual.isAttack());
    }

    @Test
    public void isEnPassant() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        Assert.assertFalse(actual.isEnPassant());
    }

    @Test
    public void tryOut() {
        test = new Board();
        Piece testPiece = test.getCell(52).getPiece();
        Move actual = new Move(test, testPiece, 36);
        Move.MoveResult expected = Move.MoveResult.POSSIBLE;
        Move.MoveResult actualResult = actual.tryOut();
        Assert.assertEquals(expected, actualResult);
    }
}