/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jpl.Term;

/**
 *
 * @author lucas
 */
public interface Piece {
    public int[] values();
    public Term getTerm();
    public String getFileName();
    
    public int getPoint();
}
