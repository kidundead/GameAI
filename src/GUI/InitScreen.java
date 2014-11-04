/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.model.Domino;
import core.Bootstrap;
import java.io.File;
import javax.swing.JFrame;

/**
 *
 * @author lucas
 */
public class InitScreen extends javax.swing.JDialog {


    public InitScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        /*addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });*/
        
        //this.jPanel3.add( new Domino( getClass().getResource("/Resources/domino0-0.png").getFile(), null) );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Titulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        qtdJogadores = new javax.swing.JLabel();
        qtdJogadoresBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        qtdAIs = new javax.swing.JLabel();
        qtdAIsBox = new javax.swing.JComboBox();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 50));
        jPanel3 = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jogo de dominó");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        Titulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Titulo.setText("Jogo de Dominó");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(Titulo, gridBagConstraints);

        qtdJogadores.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        qtdJogadores.setText("Quantidade de Jogadores:");
        jPanel1.add(qtdJogadores);

        qtdJogadoresBox.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        qtdJogadoresBox.setModel(new javax.swing.DefaultComboBoxModel(new Integer[] { 2, 3, 4}));
        qtdJogadoresBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtdJogadoresBoxActionPerformed(evt);
            }
        });
        jPanel1.add(qtdJogadoresBox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jPanel1, gridBagConstraints);

        qtdAIs.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        qtdAIs.setText("Quantidade de  AIs:");
        jPanel2.add(qtdAIs);

        qtdAIsBox.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        qtdAIsBox.setModel(new javax.swing.DefaultComboBoxModel(new Integer[] { 1, 2}));
        jPanel2.add(qtdAIsBox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        getContentPane().add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(filler1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

        OK.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        OK.setText("OK");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        jPanel3.add(OK);

        Sair.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });
        jPanel3.add(Sair);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void qtdJogadoresBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtdJogadoresBoxActionPerformed
        Integer qtdJogadores = qtdJogadoresBox.getSelectedIndex() + 2;
        Integer[] model = new Integer[2];
        model[0] = qtdJogadores - 1;
        model[1] = qtdJogadores;
        
        this.qtdAIsBox.setModel(new javax.swing.DefaultComboBoxModel(model));
    }//GEN-LAST:event_qtdJogadoresBoxActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        this.dispose();
        new Bootstrap((Integer) this.qtdJogadoresBox.getSelectedItem(), (Integer) this.qtdAIsBox.getSelectedItem() );       
    }//GEN-LAST:event_OKActionPerformed

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
       this.dispose();
       System.exit(0);
    }//GEN-LAST:event_SairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton Sair;
    private javax.swing.JLabel Titulo;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel qtdAIs;
    private javax.swing.JComboBox qtdAIsBox;
    private javax.swing.JLabel qtdJogadores;
    private javax.swing.JComboBox qtdJogadoresBox;
    // End of variables declaration//GEN-END:variables
}
