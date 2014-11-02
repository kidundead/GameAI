/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.model.Domino;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import logic.AI.PlayerAI;
import logic.game.RoundLogic;
import model.interfaces.Piece;

/**
 *
 * @author lucas
 */
public class Board extends JPanel {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    private static final Board instance = new Board();
    private final Deque<Domino> dominos;
    private final int[] ends;

    private Board() {
        dominos = new LinkedList<>();
        ends = new int[2];
        //setMinimumSize(new java.awt.Dimension(360, 560));
    }

    public boolean addDomino(Domino domino) {
        if (dominos.isEmpty()) {
            addDominoBoard(domino, LEFT);
            dominos.add(domino);
            ends[LEFT] = domino.getPiece().values()[LEFT];
            ends[RIGHT] = domino.getPiece().values()[RIGHT];
            return true;
        }
        int side = -1;
        if (domino.getPiece().values()[LEFT] == ends[LEFT] || domino.getPiece().values()[RIGHT] == ends[LEFT]) {
            side = LEFT;
        } else {
            if (domino.getPiece().values()[LEFT] == ends[RIGHT] || domino.getPiece().values()[RIGHT] == ends[RIGHT]) {
                side = RIGHT;
            }
        }
        return addDomino(domino, side);

    }

    public int dominoPossibilities(Domino domino) {
        int count = 0;
        if (domino.getPiece().values()[LEFT] == ends[LEFT] || domino.getPiece().values()[RIGHT] == ends[LEFT]) {
            count++;
        }
        if (domino.getPiece().values()[LEFT] == ends[RIGHT] || domino.getPiece().values()[RIGHT] == ends[RIGHT]) {
            count++;
        }
        return count;

    }

    public boolean addDomino(Domino domino, int side) {
        if (side < LEFT || side > RIGHT) {
            return false;
        }
        if (side == LEFT) {
            addDomino(domino, LEFT);
            dominos.addFirst(domino);
        } else {
            addDomino(domino, RIGHT);
            dominos.addLast(domino);
        }
        return true;
    }

    private void addDominoBoard(Domino domino, int side) {

        domino.setActive(false);
        MouseListener[] listeners = domino.getMouseListeners();
        if (listeners != null) {
            domino.removeMouseListener(domino.getMouseListeners()[0]);
        }
        if(domino.getPlayer() instanceof PlayerAI){
            String file = new StringBuilder("/Resources/").append(domino.getPiece().getFileName()).toString();
            domino.changeImage(file);
        }
        add(domino);

        RoundLogic.getInstance().repaint();
    }

    public static Board getInstance() {
        return instance;
    }

    public boolean checkPossible(Domino domino) {
        if (domino.getPiece().values()[LEFT] == ends[LEFT] || domino.getPiece().values()[LEFT] == ends[RIGHT]) {
            return true;
        }
        return domino.getPiece().values()[RIGHT] == ends[LEFT] || domino.getPiece().values()[RIGHT] == ends[RIGHT];
    }

    public boolean checkPossible(Piece domino) {
        if (domino.values()[LEFT] == ends[LEFT] || domino.values()[LEFT] == ends[RIGHT]) {
            return true;
        }
        return domino.values()[RIGHT] == ends[LEFT] || domino.values()[RIGHT] == ends[RIGHT];

    }

    public int[] sidesPossible() {
        return ends;
    }

    public List<Domino> dominosPlaced() {
        ArrayList<Domino> list = new ArrayList<>(dominos.size());
        list.addAll(dominos);

        return list;
    }

    public Domino[] corner() {
        Domino[] both = new Domino[2];
        both[0] = dominos.getFirst();
        both[1] = dominos.getLast();
        return both;
    }
}
