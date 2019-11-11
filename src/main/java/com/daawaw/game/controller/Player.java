package com.daawaw.game.controller;

import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.Move;
import com.daawaw.game.model.pieces.Piece;

import java.util.Collection;


public class Player {

    private Alliance alliance;

    private Game game;

    public Player(Game game, Alliance alliance) {
        this.alliance = alliance;
        this.game = game;
    }

    public Alliance getAlliance() {
        return alliance;
    }


    public Piece[] getPieces() {
        return (getAlliance() == Alliance.WHITE) ? game.getBoard().getBlackPieces() : game.getBoard().getWhitePieces();
    }

    public Piece getPiece(int index) {
        return getPieces()[index];
    }


    public Collection<Move> getLegalMoves() {
        return game.getBoard().legalMoves(alliance);
    }


    public boolean isCheckmate() {
        return game.getBoard().isCheckmate(alliance);
    }


    public boolean isStalemate() {
        return game.getBoard().isStalemate(alliance);
    }



}
