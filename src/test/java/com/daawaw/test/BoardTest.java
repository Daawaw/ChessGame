package com.daawaw.test;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.King;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Piece;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.daawaw.game.model.pieces.Alliance.BLACK;
import static com.daawaw.game.model.pieces.Alliance.WHITE;
import static org.junit.Assert.*;

public class BoardTest {
    Board test;

    @Test
    public void getIndex() {
        test = new Board();
        int index = test.getIndex(7,0);
        int expected = 56;
        Assert.assertEquals(expected, index);
    }

    @Test
    public void getRow() {
        test = new Board();
        int index = test.getRow(56);
        int expected = 7;
        Assert.assertEquals(expected, index);
    }

    @Test
    public void getColumn() {
        test = new Board();
        int index = test.getColumn(56);
        int expected = 0;
        Assert.assertEquals(expected, index);

    }

    @Test
    public void isRow() {
        test = new Board();
        boolean actual  = test.isRow(0);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isColumn() {
        test = new Board();
        boolean actual  = test.isColumn(0);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isCell() {
        test = new Board();
        boolean actual  = test.isCell(43);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isCell1() {
        test = new Board();
        boolean actual  = test.isCell(0, 7);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isNthRow() {
        test = new Board();
        boolean actual  = test.isNthRow(0, 54);
        boolean expected = false;
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void isNthColumn() {
        test = new Board();
        boolean actual  = test.isNthColumn(0, 54);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deepCopy() {
        test = new Board();
        Board test2 = test.deepCopy();
        String actual = test.toString();
        String expected = test2.toString();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getBlackPieces() {
        test = new Board();
        Piece[] blackPieces = test.getBlackPieces();
        String actual = "BRBNBBBQBKBBBNBRBPBPBPBPBPBPBPBP";
        String expected = "";
        for( Piece test1:blackPieces){
            expected += test1.toString();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWhitePieces() {
        test = new Board();
        Piece[] whitePieces = test.getWhitePieces();
        String actual = "WRWNWBWQWKWBWNWRWPWPWPWPWPWPWPWP";
        String expected = "";
        for( Piece test1:whitePieces){
            expected += test1.toString();
        }
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getBlackDeadPieces() {
        test = new Board();
        List<Piece> expected = new ArrayList<>();
        expected.add(test.getCell(0).getPiece());
        test.getCell(0).getPiece().kill();
        List<Piece> blackPieces = test.getBlackDeadPieces();
        Assert.assertEquals(blackPieces, expected);
    }

    @Test
    public void getWhiteDeadPieces() {
        test = new Board();
        List<Piece> expected = new ArrayList<>();
        expected.add(test.getCell(56).getPiece());
        test.getCell(56).getPiece().kill();
        List<Piece> whitePieces = test.getWhiteDeadPieces();
        Assert.assertEquals(whitePieces, expected);
    }


    @Test
    public void getCastlingMoves() {
        test = new Board();
        King testKing = test.getKing(WHITE);
        Collection<Move> actual = test.getCastlingMoves(testKing);
        List <Move> expectedl = new ArrayList<>();
        Assert.assertEquals(expectedl, actual);

    }

    @Test
    public void legalMoves() {
        test = new Board();
        Piece[] testPieces = test.getWhitePieces();
        Collection<Move> testActual = test.legalMoves(testPieces);
        String actual = "";
        String expected = "Na6Nc6Nf6Nh6Pa6Pa5Pb6Pb5Pc6Pc5Pd6Pd5Pe6Pe5Pf6Pf5Pg6Pg5Ph6Ph5";
        for( Move test: testActual){
            actual += test.toString();
        }
        Assert.assertEquals(expected, actual);
        Piece[] testPieces2 = test.getBlackPieces();
        Collection<Move> testActual2 = test.legalMoves(testPieces2);
        String actual2 = "";
        String expected2 = "Na3Nc3Nf3Nh3Pa3Pa4Pb3Pb4Pc3Pc4Pd3Pd4Pe3Pe4Pf3Pf4Pg3Pg4Ph3Ph4";
        for( Move test: testActual2){
            actual2 += test.toString();
        }
        Assert.assertEquals(expected2, actual2);

    }

    @Test
    public void legalAttackMoves() {
        test = new Board();
        Piece[] testPieces = test.getWhitePieces();
        Collection<Move> testActual = test.legalAttackMoves(testPieces);
        String actual = "";
        String expected = "Na6Nc6Nf6Nh6Pa6Pa5Pb6Pb5Pc6Pc5Pd6Pd5Pe6Pe5Pf6Pf5Pg6Pg5Ph6Ph5";
        for( Move test: testActual){
            actual += test.toString();
        }
        Assert.assertEquals(expected, actual);
        Piece[] testPieces2 = test.getBlackPieces();
        Collection<Move> testActual2 = test.legalAttackMoves(testPieces2);
        String actual2 = "";
        String expected2 = "Na3Nc3Nf3Nh3Pa3Pa4Pb3Pb4Pc3Pc4Pd3Pd4Pe3Pe4Pf3Pf4Pg3Pg4Ph3Ph4";
        for( Move test: testActual2){
            actual2 += test.toString();
        }
        Assert.assertEquals(expected2, actual2);
    }


    @Test
    public void isChecked() {
        test = new Board();
        boolean actual = test.isChecked(WHITE);
        Assert.assertFalse(actual);
    }

    @Test
    public void getKing() {
        test = new Board();
        King testWhiteKing = test.getKing(WHITE);
        String actualWhiteKing = testWhiteKing.toString();
        String expectedWhiteKing = "WK";
        Assert.assertEquals(actualWhiteKing, expectedWhiteKing);
        King testBlackKing = test.getKing(BLACK);
        String actualBlackKing = testBlackKing.toString();
        String expectedBlackKing = "BK";
        Assert.assertEquals(actualBlackKing, expectedBlackKing);
    }

    @Test
    public void getPieces() {
        test = new Board();
        Piece[] testWhite = test.getPieces(WHITE);
        Piece[] testBlack = test.getPieces(BLACK);
        String  expectedWhite = "WRWNWBWQWKWBWNWRWPWPWPWPWPWPWPWP";
        String expectedBlack = "BRBNBBBQBKBBBNBRBPBPBPBPBPBPBPBP";
        String actualWhite = "";
        String actualBlack = "";
        for( Piece testPiece:testWhite) actualWhite += testPiece.toString();
        for( Piece testPiece:testBlack) actualBlack += testPiece.toString();
        Assert.assertEquals(actualBlack, expectedBlack);
        Assert.assertEquals(actualWhite, expectedWhite);
    }

}