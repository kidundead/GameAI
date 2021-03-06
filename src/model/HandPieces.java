/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.interfaces.Hand;
import java.util.ArrayList;
import java.util.List;
import jpl.Term;
import jpl.Util;
import model.interfaces.Piece;

/**
 *
 * @author lucas
 */
public class HandPieces implements Hand<Piece>{

    private List<Piece> hand;
    
    public HandPieces(){
        hand = new ArrayList<>();
    }
    
    public HandPieces(List<Piece> lista) {
	hand = new ArrayList<>(lista);
    }

    @Override
    public void add(Piece peca) {
        hand.add(peca);
    }

    @Override
    public Piece remove(int i) {
        return hand.remove(i);
    }

    @Override
    public Piece remove(Piece p) {
	System.out.println(p.getFileName());
        hand.remove(p);
        
	return p;
    }

    @Override
    public int qtdHand() {
	return hand.size();
    }

    @Override
    public Piece show(int i) {
	return hand.get(i);
    }

    @Override
    public Term getTerm() {
	Term[] lista = new Term[qtdHand()];
	for (int i = 0; i < lista.length; i++) {
	    lista[i] = show(i).getTerm();
	}
	return Util.termArrayToList(lista);
    }

    
}
