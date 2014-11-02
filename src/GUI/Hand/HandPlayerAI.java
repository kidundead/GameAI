/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Hand;

import GUI.model.Domino;
import java.util.List;
import model.interfaces.Hand;;
import model.interfaces.Piece;
import model.interfaces.Player;

/**
 *
 * @author lucas
 */
public class HandPlayerAI extends GUI.model.HandGUI{
    
    private final double angleOrientation;
    
    
    public HandPlayerAI(Hand<Piece> hand, double angleOrientation, boolean upDirection, Player player){
        super(hand);
        this.angleOrientation = angleOrientation;
        
        piecesToDomino(true);
        
        if(upDirection)
            setLayout(new java.awt.GridLayout(0, 2));
        
        for(Domino domino: dominos){
            domino.setPlayer(player);
            setOrientation(domino);
            add(domino);
        }
        
        this.hand = null;
    }

    private void setOrientation(Domino domino){
        domino.rotate(angleOrientation);
    }
    
    @Override
    public void addDomino(Piece piece) {
        this.hand.add(piece);
        Domino domino = piecesToDomino(piece, true);
        this.dominos.add(domino);
        setOrientation(domino);
        add(domino);
        
    }
    
    public void remove(Piece piece){
        for(int i = 0; i < dominos.size(); i++){
            if(dominos.get(i).getPiece().values()[0] == piece.values()[0] && dominos.get(i).getPiece().values()[1] == piece.values()[1]){
                removeFromHand(i);
                break;
            }
        }
    }
    
    public List<Domino> getDominos(){
        return dominos;
    }
}
