package com.daawaw.game.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.daawaw.game.model.Board;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.*;
import com.daawaw.view.GUIView;
import com.daawaw.view.View;


public class Game {

    private Player[] players = new Player[2];
    private Board board;
    private int current;
    private View view;

    public Game(Board board) {
        this.board = board;
        players[0] = new Player(this, Alliance.WHITE);
        players[1] = new Player(this, Alliance.BLACK);
        current = 0;
        view = new GUIView(this);
        System.out.println(board);
    }

    public Game() {
        this(new Board());
    }

    public Player getBlack() {
        return players[1];
    }
    public Player getWhite() {
        return players[0];
    }
    public Board getBoard() {
        return board;
    }

    public boolean gameOver() {
        return isStaleMate() || isCheckMate() || board.getWhiteDeadPieces().size() == 15 || board.getBlackDeadPieces().size() == 15;
    }

    public boolean isStaleMate() {
        return getWhite().isStalemate() || getBlack().isStalemate();
    }

    public boolean isCheckMate() {
        return getWhite().isCheckmate() || getBlack().isCheckmate();
    }

    public void start() {
        view.start();
    }


    public void reset() {
        current = 0;
        board.reset();
    }

    public void update() {board.save();}




    public void changePlayer() {
        current = ++current % 2;
    }

    public Player getCurrentPlayer() {
        return players[current];
    }

    public String playerToString(){
        if(current == 0) return "white";
        else return "black";
    }

    public void save(){
        try{
            FileOutputStream fos = new FileOutputStream("save.data");
            ObjectOutputStream file = new ObjectOutputStream(fos);

            for( int i = 0; i< board.ROW_COUNT; i++){
                for(int j = 0; j<board.COLUMN_COUNT; j++){
                    if(board.getCell(i,j).getPiece() == null){
                        file.writeUTF("null");
                        continue;
                    }
                    file.writeUTF(board.getCell(i,j).toString());
                }
            }
            file.writeUTF(playerToString());
            file.flush();
            file.close();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    private static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (fileName));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public List<Move> load(){
            List<Move> loadedLog = new ArrayList<>();
            String loadFromFile;
            String[] loadLog;
            board.reset();
            try{
                loadFromFile = readUsingBufferedReader("log.txt");
                loadLog = loadFromFile.split("\\s");
                int words = -1;
                while(words != loadLog.length - 1){
                    words++;
                    String fuck = loadLog[words];
                    String fuckTemp[] = fuck.split("\\.");
                    board.setCell(Integer.valueOf(fuckTemp[1]), new Cell(this.board, Integer.valueOf(fuckTemp[1])));
                    Move move = new Move(board, fuckTemp[0], fuckTemp[1], fuckTemp[2], fuckTemp[3], fuckTemp[4]);
                    Move.MoveResult result = move.tryOut();
                    if (result == Move.MoveResult.POSSIBLE) {
                        move.execute();
                    }

                        loadedLog.add(move);


                }

            }
            catch(IOException ex){
                System.out.println(ex.fillInStackTrace());
            }
            this.board = this.board.deepCopy();
            return loadedLog;
        }

    public void saveMoveLog(List <Move> moves){
        try {
            FileWriter fw = new FileWriter("log.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            String moveLog;
            for (Move move : moves) {
                moveLog ="";
                moveLog += move.getPiece() + ".";
                moveLog += move.getStart()+".";
                moveLog += move.getDestination()+".";
                if(move.getCheckedMove() == true) moveLog += "true.";
                else moveLog += "false.";
                if(move.getAttackedPiece() != null){
                    moveLog += move.getAttackedPiece().toString()+" ";
                }
                else moveLog += "null ";
                bw.write(moveLog);
                System.out.println("Save->"+moveLog);
            }
            bw.flush();
            bw.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}
