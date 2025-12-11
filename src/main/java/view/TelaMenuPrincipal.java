package view;

import controller.ControllerCadFuncionario;
import controller.ControllerCadHospede;
import controller.ControllerCadFornecedor;
import controller.ControllerCadMarca;
import controller.ControllerCadModelo;
import controller.ControllerCadQuarto;
import controller.ControllerCadProdutoCopa;
import controller.ControllerCadServico;
import controller.ControllerCadVaga;
import controller.ControllerCadVeiculo;
import model.Quarto;
import model.Veiculo;


public class TelaMenuPrincipal extends javax.swing.JFrame {

    public TelaMenuPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFornecedor = new javax.swing.JMenuItem();
        jMenuItemFuncionario = new javax.swing.JMenuItem();
        jMenuItemHospede = new javax.swing.JMenuItem();
        jMenuItemQuarto = new javax.swing.JMenuItem();
        jMenuItemVeiculo = new javax.swing.JMenuItem();
        jMenuItemCadModeloActionPerformed = new javax.swing.JMenuItem();
        jMenuItemCadMarcaActionPerformed = new javax.swing.JMenuItem();
        jMenuItemVagaDeEstacionamento = new javax.swing.JMenuItem();
        jMenuItemServico = new javax.swing.JMenuItem();
        jMenuItemProdutoCopa = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);

        jMenu2.setText("Cadastros");
        jMenu2.add(jSeparator1);

        jMenuItemFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boss.png"))); // NOI18N
        jMenuItemFornecedor.setText("Fornecedor");
        jMenuItemFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFornecedorActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemFornecedor);

        jMenuItemFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Person.png"))); // NOI18N
        jMenuItemFuncionario.setText("Funcionário");
        jMenuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFuncionarioActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemFuncionario);

        jMenuItemHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/People.png"))); // NOI18N
        jMenuItemHospede.setText("Hóspede");
        jMenuItemHospede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHospedeActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHospede);

        jMenuItemQuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home.png"))); // NOI18N
        jMenuItemQuarto.setText("Quarto");
        jMenuItemQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuartoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemQuarto);

        jMenuItemVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Car key.png"))); // NOI18N
        jMenuItemVeiculo.setText("Veículo");
        jMenuItemVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemVeiculo);

        jMenuItemCadModeloActionPerformed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/List.png"))); // NOI18N
        jMenuItemCadModeloActionPerformed.setText("Modelo");
        jMenuItemCadModeloActionPerformed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadModeloActionPerformedActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCadModeloActionPerformed);

        jMenuItemCadMarcaActionPerformed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Copy.png"))); // NOI18N
        jMenuItemCadMarcaActionPerformed.setText("Marca");
        jMenuItemCadMarcaActionPerformed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadMarcaActionPerformedActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCadMarcaActionPerformed);

        jMenuItemVagaDeEstacionamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Delivery.png"))); // NOI18N
        jMenuItemVagaDeEstacionamento.setText("Vaga de Estacionamento");
        jMenuItemVagaDeEstacionamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVagaDeEstacionamentoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemVagaDeEstacionamento);

        jMenuItemServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Equipment.png"))); // NOI18N
        jMenuItemServico.setText("Serviço");
        jMenuItemServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServicoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemServico);

        jMenuItemProdutoCopa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dollar.png"))); // NOI18N
        jMenuItemProdutoCopa.setText("Produto/Copa");
        jMenuItemProdutoCopa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutoCopaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemProdutoCopa);
        jMenu2.add(jSeparator2);

        jMenuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cancel.png"))); // NOI18N
        jMenuItemSair.setText("Sair");
        jMenuItemSair.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSair);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Movimentos");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Relatórios");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ajuda");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Help symbol.png"))); // NOI18N
        jMenuItem2.setText("Sobre");
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jMenuItemHospedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHospedeActionPerformed
        TelaCadastroHospede telaCadastroHospede = new TelaCadastroHospede(null,true);
        ControllerCadHospede controllerCadHospede = new ControllerCadHospede(telaCadastroHospede);
        telaCadastroHospede.setVisible(true);
    }//GEN-LAST:event_jMenuItemHospedeActionPerformed

    private void jMenuItemFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFornecedorActionPerformed
    TelaCadastroFornecedor tela = new TelaCadastroFornecedor(null, true);
    ControllerCadFornecedor controller = new ControllerCadFornecedor(tela);
    tela.setVisible(true);
    }//GEN-LAST:event_jMenuItemFornecedorActionPerformed

    private void jMenuItemProdutoCopaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProdutoCopaActionPerformed
    TelaCadastroProdutoCopa tela = new TelaCadastroProdutoCopa(null, true);
    ControllerCadProdutoCopa controller = new ControllerCadProdutoCopa(tela);
    tela.setVisible(true);
    }//GEN-LAST:event_jMenuItemProdutoCopaActionPerformed

    private void jMenuItemVagaDeEstacionamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVagaDeEstacionamentoActionPerformed
    TelaCadastroVaga tela = new TelaCadastroVaga(null, true);
    ControllerCadVaga controller = new ControllerCadVaga(tela);
    tela.setVisible(true);
    }//GEN-LAST:event_jMenuItemVagaDeEstacionamentoActionPerformed

    private void jMenuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFuncionarioActionPerformed
    TelaCadastroFuncionario tela = new TelaCadastroFuncionario(null, true);
    tela.setTitle("Cadastro de Funcionário"); 
    ControllerCadFuncionario controller = new ControllerCadFuncionario(tela);
    tela.setVisible(true);
    
    }//GEN-LAST:event_jMenuItemFuncionarioActionPerformed

    private void jMenuItemQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuartoActionPerformed
TelaCadastroQuarto telaCadastroQuarto = new TelaCadastroQuarto(null, true); 
ControllerCadQuarto controllerCadQuarto = new ControllerCadQuarto(telaCadastroQuarto, new Quarto()); 
telaCadastroQuarto.setVisible(true);
    }//GEN-LAST:event_jMenuItemQuartoActionPerformed

    private void jMenuItemServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServicoActionPerformed
         TelaCadastroServico tela = new TelaCadastroServico(null, true);
         ControllerCadServico controller = new ControllerCadServico(tela);
         tela.setVisible(true);
    }//GEN-LAST:event_jMenuItemServicoActionPerformed

    private void jMenuItemVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVeiculoActionPerformed
        TelaCadastroVeiculo telaVeiculo = new TelaCadastroVeiculo(null, true); // Crie a tela de VEÍCULO
Veiculo veiculoVazio = new Veiculo();
ControllerCadVeiculo controller = new ControllerCadVeiculo(telaVeiculo, veiculoVazio); // Passe a 'telaVeiculo'
telaVeiculo.setVisible(true);
    }//GEN-LAST:event_jMenuItemVeiculoActionPerformed

    private void jMenuItemCadMarcaActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadMarcaActionPerformedActionPerformed
    TelaCadastroMarca telaMarca = new TelaCadastroMarca(null, true);
    ControllerCadMarca controllerMarca = new ControllerCadMarca(telaMarca);
    telaMarca.setVisible(true);    }//GEN-LAST:event_jMenuItemCadMarcaActionPerformedActionPerformed

    private void jMenuItemCadModeloActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadModeloActionPerformedActionPerformed
TelaCadastroModelo telaModelo = new TelaCadastroModelo(null, true);
    ControllerCadModelo controllerModelo = new ControllerCadModelo(telaModelo);
    telaModelo.setVisible(true);    }//GEN-LAST:event_jMenuItemCadModeloActionPerformedActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemCadMarcaActionPerformed;
    private javax.swing.JMenuItem jMenuItemCadModeloActionPerformed;
    private javax.swing.JMenuItem jMenuItemFornecedor;
    private javax.swing.JMenuItem jMenuItemFuncionario;
    private javax.swing.JMenuItem jMenuItemHospede;
    private javax.swing.JMenuItem jMenuItemProdutoCopa;
    private javax.swing.JMenuItem jMenuItemQuarto;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemServico;
    private javax.swing.JMenuItem jMenuItemVagaDeEstacionamento;
    private javax.swing.JMenuItem jMenuItemVeiculo;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
