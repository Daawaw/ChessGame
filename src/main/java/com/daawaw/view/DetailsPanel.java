package com.daawaw.view;

import com.daawaw.game.controller.Player;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.Alliance;
import com.daawaw.game.model.pieces.Piece;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class DetailsPanel extends JPanel {

    private static final Dimension SIZE = new Dimension(800, 70);

    private JPanel currentPlayerPanel;
    private JPanel selectedPiecePanel;

    public DetailsPanel(GUIView view) {
        super(new BorderLayout());
        setBorder(new EtchedBorder(EtchedBorder.RAISED));
        currentPlayerPanel = new JPanel(new BorderLayout());
        selectedPiecePanel = new JPanel(new BorderLayout());
        selectedPiecePanel.setPreferredSize(new Dimension((int) (SIZE.getWidth() / 3), (int) SIZE.getHeight()));
        currentPlayerPanel.setPreferredSize(new Dimension((int) (SIZE.getWidth() / 3), (int) SIZE.getHeight()));
        add(currentPlayerPanel, BorderLayout.CENTER);
        add(selectedPiecePanel, BorderLayout.WEST);
        showCurrentPlayer(view);
        showSelectedPiece(view);
        validate();
    }

    @Override
    public Dimension getPreferredSize() {
        return SIZE;
    }

    public void update(GUIView view) {
        selectedPiecePanel.removeAll();
        currentPlayerPanel.removeAll();
        showCurrentPlayer(view);
        showSelectedPiece(view);
        validate();
        repaint();
    }

    public void showCurrentPlayer(GUIView view) {
        JLabel currentPlayerLabel = new JLabel();
        String text = "";
        Alliance alliance = view.getController().getCurrentPlayer().getAlliance();
        text += (alliance == Alliance.WHITE) ? "White" : "Black";
        text += "'s turn!";
        currentPlayerLabel.setText(text);
        currentPlayerPanel.add(currentPlayerLabel, BorderLayout.CENTER);
    }

    public void showSelectedPiece(GUIView view) {
        Cell selectedCell = view.getStartCell();
        if (selectedCell == null) {
            selectedCell = view.getSelectedCell();
        }
        if (selectedCell != null) {
            Piece selectedPiece = selectedCell.getPiece();
            JPanel selectedPieceDetails = new JPanel(new GridLayout(4, 1));
            selectedPieceDetails.add(new JLabel("Details of selected piece:"));
            selectedPieceDetails.add(new JLabel("Type: " + selectedPiece.getType().fullString()));
            selectedPieceDetails.add(new JLabel("Position: " + selectedPiece.getPosition()));
            selectedPieceDetails.add(new JLabel("Kills: " + selectedPiece.getKills()));
            selectedPiecePanel.add(selectedPieceDetails, BorderLayout.CENTER);
        } else {
            selectedPiecePanel.add(new JLabel("No piece is selected."), BorderLayout.CENTER);
        }
    }


    public String showWinner(Player player) {
        currentPlayerPanel.removeAll();
        String text;
        Alliance alliance = player.getAlliance();
        if(alliance == Alliance.BLACK){
            text = "White";
        }
        else text = "Black";
        return text;
    }

}
