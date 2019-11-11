package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import org.junit.Assert;
import org.junit.Test;


public class CellTest {
    private Board testBoard;


    @Test
    public void occupy() {
        testBoard = new Board();
        Cell test1 = testBoard.getCell(0);
        test1.occupy(testBoard.getCell(5).getPiece());
        String expected = "BB";
        String actual = test1.toString();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void release() {
        testBoard = new Board();
        System.out.println(testBoard);
        Cell test1 = testBoard.getCell(0);
        test1.release();
        Assert.assertNull(test1.getPiece());
    }

    @Test
    public void isOccupied() {
        testBoard = new Board();
        System.out.println(testBoard);
        Cell test1 = testBoard.getCell(0);
        boolean expected = true;
        boolean actual = test1.isOccupied();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPiece() {
        testBoard = new Board();
        System.out.println(testBoard);
        Cell test1 = testBoard.getCell(0);
        String actual = test1.getPiece().toString();
        String expected = "BR";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIndex() {
        testBoard = new Board();
        System.out.println(testBoard);
        Cell test1 = testBoard.getCell(0);
        int actual = test1.getIndex();
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getRow() {
        testBoard = new Board();
        System.out.println(testBoard);
        Cell test1 = testBoard.getCell(0);
        int actual = test1.getRow();
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getColumn() {
        testBoard = new Board();
        System.out.println(testBoard);
        Cell test1 = testBoard.getCell(0);
        int actual = test1.getColumn();
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hardCopy() {
        testBoard = new Board();
        System.out.println(testBoard);
        Cell test1 = testBoard.getCell(0);
        Cell test2 = test1.hardCopy(testBoard);
        int expected = test1.getIndex();
        int actual = test2.getIndex();
        Assert.assertEquals(expected, actual);
    }
}