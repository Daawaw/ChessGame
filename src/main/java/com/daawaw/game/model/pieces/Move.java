package com.daawaw.game.model.pieces;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;

import java.util.Collection;


public class Move {

    private int start;
    private Piece piece;
    private int destination;
    private Piece attackedPiece;
    private MoveResult status;
    private Board board;
    private boolean checkMateMove;
    private boolean checkedMove;

    public Move(Board board, Piece piece, int destination, Piece attackedPiece) {
        this.piece = piece;
        this.start = piece.getIndex();
        this.destination = destination;
        this.attackedPiece = attackedPiece;
        this.status = MoveResult.UNTRIED;
        this.board = board;
        this.checkedMove = false;
        this.checkMateMove = false;
    }
     public Move(Board board,String piece, String start, String destination, String checkedMove, String attackedPiece){
        this.board = board;
        this.board.setCell(Integer.valueOf(start),new Cell(this.board, Integer.valueOf(start)));
         this.destination = Integer.valueOf(destination);
         this.status = MoveResult.UNTRIED;
         this.start = Integer.valueOf(start);
         if(checkedMove == "true") this.checkedMove = true;
         else this.checkedMove = false;
         if(!attackedPiece.equals("null")){
             if(attackedPiece.equals("BP")){
                 this.attackedPiece = new Pawn(this.board, this.destination, Alliance.BLACK);
                 this.board.setBlackPiece((this.board.numberOfPawnBlack %= 15), this.attackedPiece);
                 this.board.numberOfPawnBlack++;
             }
             else if(attackedPiece.equals("BR")){
                 this.attackedPiece = new Rook(this.board, this.destination, Alliance.BLACK );
                 board.getCell(this.destination).release();
             }
             else if(attackedPiece.equals("BN")){
                 this.attackedPiece = new Knight(this.board, this.destination, Alliance.BLACK);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(attackedPiece.equals("BB")){
                 this.attackedPiece = new Bishop(this.board, this.destination, Alliance.BLACK);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(piece.equals("BQ")){
                 this.attackedPiece = new Queen(this.board, this.destination, Alliance.BLACK);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(piece.equals("BK")){
                 this.attackedPiece = new King(this.board, this.destination, Alliance.BLACK);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(piece.equals("WP")){
                 this.attackedPiece = new Pawn(this.board, this.destination, Alliance.WHITE);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();

             }
             else if(piece.equals("WR")){
                 this.attackedPiece = new Rook(this.board, this.destination, Alliance.WHITE );
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(piece.equals("WN")){
                 this.attackedPiece = new Knight(this.board, this.destination, Alliance.WHITE);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(piece.equals("WB")){
                 this.attackedPiece = new Bishop(this.board, this.destination, Alliance.WHITE);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(piece.equals("WQ")){
                 this.attackedPiece = new Queen(this.board, this.destination, Alliance.WHITE);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
             else if(piece.equals("WK")){
                 this.attackedPiece = new King(this.board, this.destination, Alliance.WHITE);
                 board.getCell(this.destination).release();
                 this.attackedPiece.kill();
             }
         }
        if(piece.equals("BP")){
            this.piece = new Pawn(this.board, this.start, Alliance.BLACK);
            this.board.setBlackPiece((this.board.numberOfPawnBlack %= 15), this.piece);
            this.board.numberOfPawnBlack++;
        }
        else if(piece.equals("BR")){
            this.piece = new Rook(this.board, this.start, Alliance.BLACK );
            if(this.board.numberOfRooks == false){
                this.board.setBlackPiece(0, this.piece);
                this.board.numberOfRooks = true;
            }
            else {
                this.board.setBlackPiece(7, this.piece);
                this.board.numberOfRooks = false;
            }
        }
        else if(piece.equals("BN")){
            this.piece = new Knight(this.board, this.start, Alliance.BLACK);
            if(this.board.numberOfKnights == false){
                this.board.setBlackPiece(1, this.piece);
                this.board.numberOfKnights = true;
            }
            else {
                this.board.setBlackPiece(6, this.piece);
                this.board.numberOfKnights= false;
            }
        }
        else if(piece.equals("BB")){
            this.piece = new Bishop(this.board, this.start, Alliance.BLACK);
            if(this.board.numberOfBishops == false){
                this.board.setBlackPiece(2, this.piece);
                this.board.numberOfBishops = true;
            }
            else {
                this.board.setBlackPiece(5, this.piece);
                this.board.numberOfBishops = false;
            }
        }
        else if(piece.equals("BQ")){
            this.piece = new Queen(this.board, this.start, Alliance.BLACK);
            this.board.setBlackPiece(3, this.piece);
        }
        else if(piece.equals("BK")){
            this.piece = new King(this.board, this.start, Alliance.BLACK);
            this.board.setBlackPiece(4, this.piece);
        }
        else if(piece.equals("WP")){
            this.piece = new Pawn(this.board, this.start, Alliance.WHITE);
            this.board.setWhitePiece((this.board.numberOfPawnWhite %= 15), this.piece);
            this.board.numberOfPawnWhite++;
        }
        else if(piece.equals("WR")){
            this.piece = new Rook(this.board, this.start, Alliance.WHITE );
            if(this.board.numberOfRooks == false){
                this.board.setWhitePiece(0, this.piece);
                this.board.numberOfRooks = true;
            }
            else {
                this.board.setWhitePiece(7, this.piece);
                this.board.numberOfRooks = false;
            }
        }
        else if(piece.equals("WN")){
            this.piece = new Knight(this.board, this.start, Alliance.WHITE);
            if(this.board.numberOfKnights == false){
                this.board.setWhitePiece(1, this.piece);
                this.board.numberOfKnights = true;
            }
            else {
                this.board.setWhitePiece(6, this.piece);
                this.board.numberOfKnights = false;
            }
        }
        else if(piece.equals("WB")){
            this.piece = new Bishop(this.board, this.start, Alliance.WHITE);
            if(this.board.numberOfBishops == false){
                this.board.setWhitePiece(2, this.piece);
                this.board.numberOfBishops = true;
            }
            else {
                this.board.setWhitePiece(5, this.piece);
                this.board.numberOfBishops = false;
            }
        }
        else if(piece.equals("WQ")){
            this.piece = new Queen(this.board,this.start, Alliance.WHITE);
            this.board.setWhitePiece(3, this.piece);
        }
        else if(piece.equals("WK")){
            this.piece = new King(this.board, this.start, Alliance.WHITE);
            this.board.setWhitePiece(4, this.piece);
        }
     }

    public Move(Board board, Piece piece, Piece attackedPiece) {
        this(board, piece, attackedPiece.getIndex(), attackedPiece);
    }

    public Move(Board board, Piece piece, int destination) {
        this(board, piece, destination, null);
    }

    public void execute() {
        tryExecute();
        checkMateMove = board.isCheckmate(piece.getAlliance().getOpposite());
        checkedMove = board.isChecked(piece.getAlliance().getOpposite());
    }

    private void tryExecute() {
        if (isPawnJump()) {
            board.setEnPassantPawn((Pawn) piece);
        } else {
            board.setEnPassantPawn(null);
        }
        board.getCell(getStart()).release();
        attack();
        board.getCell(destination).occupy(piece);
        if (isKingSideCastlingMove()) {
            Piece rook = board.getCell(getStart() + 3).release();
            board.getCell(getStart() + 1).occupy(rook);
        } else if (isQueenSideCastlingMove()) {
            Piece rook = board.getCell(getStart() - 4).release();
            board.getCell(getStart() - 1).occupy(rook);
        }
        piece.setFirstMove(false);
    }

    private void attack() {
        if (isAttack()) {
            board.getCell(attackedPiece.getIndex()).release();
            attackedPiece.kill();
            piece.incrementKills();
        }
    }

    public int getStart() {
        return start;
    }
    public Board getBoard(){return board;}
    public boolean getCheckedMove(){return checkedMove;}

    public int getDestination() {
        return destination;
    }

    public Piece getAttackedPiece() {
        return attackedPiece;
    }

    public Piece getPiece() {
        return piece;
    }

    public MoveResult getStatus() {
        return status;
    }

    public boolean isAttack() {
        return attackedPiece != null;
    }


    private boolean isQueenSideCastlingMove() {
        if (getStart() - 2 == destination && piece instanceof King) {
            Piece rook = board.getCell(getStart() - 4).getPiece();
            if (rook instanceof Rook) {
                return true;
            }
        }
        return false;
    }

    private boolean isKingSideCastlingMove() {
        if (getStart() + 2 == destination && piece instanceof King) {
            Piece rook = board.getCell(getStart() + 3).getPiece();
            if (rook instanceof Rook) {
                return true;
            }
        }
        return false;
    }

    private boolean isPawnJump() {
        return ((piece.getAlliance().getDirection() * 16 + getStart()) == destination) && (piece instanceof Pawn);
    }

    public boolean isEnPassant() {
        return board.getEnPassantPawn() != null
                && board.getEnPassantPawn().equals(attackedPiece) && board.getEnPassantPawn().getAlliance() != piece.getAlliance();
    }

    public Move.MoveResult tryOut() {
        status = MoveResult.TRYING;

        Board boardCopy = board.deepCopy();
        Move moveCopy;
        if (isAttack()) {
            moveCopy = new Move(boardCopy, boardCopy.getCell(getStart()).getPiece(), boardCopy.getCell(getDestination()).getPiece());
        } else {
            moveCopy = new Move(boardCopy, boardCopy.getCell(getStart()).getPiece(), getDestination());
        }
        if (!moveCopy.isLegal()) {
            status = MoveResult.IMPOSSIBLE;
            return status;
        }
        moveCopy.tryExecute();
        Alliance alliance = moveCopy.getPiece().getAlliance();
        King king = (King) ((alliance == Alliance.WHITE) ? boardCopy.getWhitePieces()[4] : boardCopy.getBlackPieces()[4]);
        Collection<Move> attacksOnKing = king.getAttacksOnPiece(boardCopy.legalMoves((alliance == Alliance.WHITE) ? boardCopy.getBlackPieces() : boardCopy.getWhitePieces()));

        if (!attacksOnKing.isEmpty()) {
            status = MoveResult.PLAYER_CHECKED;
            return status;
        }

        status = MoveResult.POSSIBLE;
        return status;
    }

    private boolean isLegal() {
        for (Move legalMove : getPiece().getLegalMoves(board)) {
            if (legalMove.getDestination() == getDestination()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String res = "";
        int a = 'a';
        res += piece.toString().substring(1);
        res += (isAttack() && piece instanceof Pawn) ? Character.toString((char) (a + Board.getColumn(start))) : "";
        res += (isAttack()) ? "x" : "";
        res += Character.toString((char) (a + Board.getColumn(destination)));
        res += Board.getRow(destination) + 1;
        if (checkedMove) {
            res += "+";
        }
        if(checkMateMove){
            res += "#";
        }
        return res;
    }

    public enum MoveResult {

        POSSIBLE(), IMPOSSIBLE(), PLAYER_CHECKED(), UNTRIED(), TRYING();

        MoveResult() {

        }

    }

}
