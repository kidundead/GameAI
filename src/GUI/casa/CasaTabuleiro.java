/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.casa;

import GUI.model.Cor;
import core.AcaoCasa;

/**
 *
 * @author lucas
 */
public class CasaTabuleiro extends javax.swing.JPanel {

    private AcaoCasa acao;
    
    public AcaoCasa getAcao(){
        return this.acao;
    }
    
    
    public void setAcao(AcaoCasa acao){
        this.acao = acao;
    }
    
    /**
     * Creates new form CasaTabuleiro
     */
    public CasaTabuleiro(String nome, String preco) {
        initComponents();
        this.setNome(nome);
        this.Preco.setText(preco);
    }

    
    public void setNome(String nome){
        /** Coisas para duas linhas **/
        this.Nome.setText(nome);
    }
    
    public void setCor(Cor cor){
        this.Cor.setBackground(cor.getColor());
    }
    
    public CasaTabuleiro(Cor cor, String nome, String preco) {
        initComponents();
        this.setCor(cor);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cor = new javax.swing.JPanel();
        fillerStrut = new javax.swing.Box.Filler(new java.awt.Dimension(0, 45), new java.awt.Dimension(0, 45), new java.awt.Dimension(32767, 20));
        Nome = new javax.swing.JLabel();
        Linha2 = new javax.swing.JLabel();
        Preco = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setMaximumSize(new java.awt.Dimension(44, 96));
        setPreferredSize(new java.awt.Dimension(44, 83));
        setLayout(new java.awt.GridLayout(0, 1));

        Cor.setBackground(new java.awt.Color(255, 128, 0));
        Cor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout CorLayout = new javax.swing.GroupLayout(Cor);
        Cor.setLayout(CorLayout);
        CorLayout.setHorizontalGroup(
            CorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CorLayout.setVerticalGroup(
            CorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        add(Cor);
        add(fillerStrut);

        Nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Nome.setText("Ndsfsfsfdsome");
        Nome.setMaximumSize(new java.awt.Dimension(77, 36));
        Nome.setPreferredSize(new java.awt.Dimension(44, 18));
        add(Nome);

        Linha2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(Linha2);

        Preco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Preco.setText("Preço");
        add(Preco);
    }// </editor-fold>//GEN-END:initComponents


    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cor;
    private javax.swing.JLabel Linha2;
    private javax.swing.JLabel Nome;
    private javax.swing.JLabel Preco;
    private javax.swing.Box.Filler fillerStrut;
    // End of variables declaration//GEN-END:variables
}
