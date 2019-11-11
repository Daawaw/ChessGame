package com.daawaw.view;

import com.daawaw.game.controller.Game;
import com.daawaw.game.model.Cell;
import com.daawaw.game.model.pieces.Move;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIView extends JFrame implements View{

    private static final Dimension SIZE = new Dimension(840, 770);

    private JFrame frame;
    private JMenuBar MenuBar;
    private BoardPanel boardPanel;
    private DeadPanel deadPanel;
    private MoveLogPanel moveLogPanel;
    private DetailsPanel detailsPanel;
    private MainMenuPanel mainMenuPanel;
    private boolean isNewGame = false;
    JPanel mainMenu = mainMenu();

    private Game controller;
    private Cell startCell;
    private Cell selectedCell;
    private boolean highlightLegalMoves;
    private List<Move> moveLog;

    public GUIView(Game controller) {
        this.controller = controller;
        highlightLegalMoves = false;
        startCell = null;
        selectedCell = null;
        frame = new JFrame("Chess Game");
        frame.setPreferredSize(SIZE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        MenuBar = createMenuBar();
        frame.setResizable(false);
        boardPanel = new BoardPanel(this);
        deadPanel = new DeadPanel(this);
        moveLog = new ArrayList<>();
        moveLogPanel = new MoveLogPanel(moveLog);
        detailsPanel = new DetailsPanel(this);
        mainMenuPanel = new MainMenuPanel();
        frame.add(mainMenu, BorderLayout.CENTER);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(deadPanel, BorderLayout.WEST);
        frame.add(moveLogPanel, BorderLayout.EAST);
        frame.add(detailsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {
        controller.reset();
        show();
    }

    public void redraw() {
        if(isNewGame) {
            this.frame.remove(mainMenu);
            frame.setJMenuBar(MenuBar);
            frame.pack();
            deadPanel.drawAllDeadPieces(this);
            moveLogPanel.showMoveLog(moveLog);
            boardPanel.drawBoard(this);
            detailsPanel.update(this);
            if (controller.gameOver()) {
                if (detailsPanel.showWinner(controller.getCurrentPlayer()) == "White") {
                    int choice = JOptionPane.showConfirmDialog(null, "Restart?", "White Wins!", JOptionPane.OK_OPTION);
                    if (choice == 0) restart();
                    else System.exit(0);
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Restart?", "Black Wins!", JOptionPane.OK_OPTION);
                    if (choice == 0) restart();
                    else System.exit(0);
                }
            }
        }
                else {
                    this.frame.remove(MenuBar);
                    mainMenuPanel.validate();
                    mainMenuPanel.repaint();
            }
        }


    public List<Move> getMoveLog() {
        return moveLog;
    }

    public void show() {
        frame.setVisible(true);
    }

    public void restart() {
        controller.reset();
        moveLog.clear();
        startCell = null;
        redraw();
    }

    public JMenu createGameMenu() {
        JMenu menu = new JMenu("Game");
        JMenuItem restartItem = new JMenuItem("Restart");
        restartItem.addActionListener(e -> restart());
        menu.add(restartItem);
        return menu;
    }

    public JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        bar.add(createFileMenu());
        bar.add(createGameMenu());
        bar.add(createPreferencesMenu());
        return bar;
    }

    public JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(e -> {controller.save(); controller.saveMoveLog(moveLog);});
        menu.add(save);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);
        return menu;
    }

    public JPanel mainMenu(){

        final Dimension SIZE = new Dimension(840, 770);
        JPanel MainMenu;
        MainMenu = new JPanel();
        MainMenu.setSize(SIZE);
        MainMenu.setVisible(true);
        JButton newGame = new JButton("New Game");
        JButton loadGame = new JButton("Load Game");
        newGame.addActionListener(e->isNewGame=true);
        loadGame.addActionListener(e->{
            controller.load();
            setLoadedMoveLog();
            isNewGame = true;
        });
        FlowLayout mainLayout = new FlowLayout(FlowLayout.CENTER);
        MainMenu.setLayout(mainLayout);
        MainMenu.add(newGame);
        MainMenu.add(loadGame);
        return MainMenu;
    }

    public JMenu createPreferencesMenu() {
        JMenu menu = new JMenu("Preferences");
        JMenuItem flipItem = new JMenuItem("Flip Board");
        flipItem.addActionListener(e -> {
            boardPanel.flipBoard();
            boardPanel.drawBoard(this);
        });
        menu.add(flipItem);
        JCheckBoxMenuItem highLightCheck = new JCheckBoxMenuItem("Highlight Legal Moves", highlightLegalMoves);
        highLightCheck.addActionListener(e -> {
            highlightLegalMoves = highLightCheck.isSelected();
            boardPanel.drawBoard(this);
        });
        menu.add(highLightCheck);
        return menu;
    }

    public Game getController() {
        return controller;
    }


    public Cell getStartCell() {
        return startCell;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public boolean shouldHighlightLegalMoves() {
        return highlightLegalMoves;
    }
    public void setLoadedMoveLog(){
        this.moveLog.clear();
        controller.getBoard().reset();
        this.moveLog = controller.load();
        if(moveLog.size() % 2 == 0 && controller.playerToString().equals("black")) controller.changePlayer();
        else if(moveLog.size() % 2 == 1 && controller.playerToString().equals("white"))controller.changePlayer();
        redraw();
    }

}
