package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCadastroVaga extends javax.swing.JDialog {

    public TelaCadastroVaga(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
 public javax.swing.JTextField getjTextFieldDescricao() {
        return jTextFieldDescricao; 
    }

    public JButton getjButtonBuscar() {
        return jButtonBuscar;
    }

    public JButton getjButtonCancelar() {
        return jButtonCancelar;
    }

    public JButton getjButtonGravar() {
        return jButtonGravar;
    }

    public JButton getjButtonNovo() {
        return jButtonNovo;
    }

    public JButton getjButtonSair() {
        return jButtonSair;
    }

    public JPanel getjPanelBotoes() {
        return jPanelBotoes;
    }

    public JPanel getjPanelDados() {
        return jPanelDados;
    }

    public JLabel getjLabelBairro() {
        return jLabelBairro;
    }

    public void setjLabelBairro(JLabel jLabelBairro) {
        this.jLabelBairro = jLabelBairro;
    }

    public JLabel getjLabelCadastro() {
        return jLabelCadastro;
    }

    public void setjLabelCadastro(JLabel jLabelCadastro) {
        this.jLabelCadastro = jLabelCadastro;
    }

    public JLabel getjLabelCep() {
        return jLabelCep;
    }

    public void setjLabelCep(JLabel jLabelCep) {
        this.jLabelCep = jLabelCep;
    }

    public JLabel getjLabelCidade() {
        return jLabelCidade;
    }

    public void setjLabelCidade(JLabel jLabelCidade) {
        this.jLabelCidade = jLabelCidade;
    }

    public JLabel getjLabelCnpj() {
        return jLabelCnpj;
    }

    public void setjLabelCnpj(JLabel jLabelCnpj) {
        this.jLabelCnpj = jLabelCnpj;
    }

    public JLabel getjLabelComplemento() {
        return jLabelComplemento;
    }

    public void setjLabelComplemento(JLabel jLabelComplemento) {
        this.jLabelComplemento = jLabelComplemento;
    }

    public JLabel getjLabelContato() {
        return jLabelContato;
    }

    public void setjLabelContato(JLabel jLabelContato) {
        this.jLabelContato = jLabelContato;
    }

    public JLabel getjLabelCpf() {
        return jLabelCpf;
    }

    public void setjLabelCpf(JLabel jLabelCpf) {
        this.jLabelCpf = jLabelCpf;
    }

    public JLabel getjLabelEmail() {
        return jLabelEmail;
    }

    public void setjLabelEmail(JLabel jLabelEmail) {
        this.jLabelEmail = jLabelEmail;
    }

    public JLabel getjLabelFone1() {
        return jLabelFone1;
    }

    public void setjLabelFone1(JLabel jLabelFone1) {
        this.jLabelFone1 = jLabelFone1;
    }

    public JLabel getjLabelFone2() {
        return jLabelFone2;
    }

    public void setjLabelFone2(JLabel jLabelFone2) {
        this.jLabelFone2 = jLabelFone2;
    }

    public JLabel getjLabelId() {
        return jLabelId;
    }

    public void setjLabelId(JLabel jLabelId) {
        this.jLabelId = jLabelId;
    }

    public JLabel getjLabelInscricaoEstadual() {
        return jLabelInscricaoEstadual;
    }

    public void setjLabelInscricaoEstadual(JLabel jLabelInscricaoEstadual) {
        this.jLabelInscricaoEstadual = jLabelInscricaoEstadual;
    }

    public JLabel getjLabelLogradouro() {
        return jLabelLogradouro;
    }

    public void setjLabelLogradouro(JLabel jLabelLogradouro) {
        this.jLabelLogradouro = jLabelLogradouro;
    }

    public JLabel getjLabelNomeFantasia() {
        return jLabelNomeFantasia;
    }

    public void setjLabelNomeFantasia(JLabel jLabelNomeFantasia) {
        this.jLabelNomeFantasia = jLabelNomeFantasia;
    }

    public JLabel getjLabelObs() {
        return jLabelObs;
    }

    public void setjLabelObs(JLabel jLabelObs) {
        this.jLabelObs = jLabelObs;
    }

    public JLabel getjLabelRazaoSocial() {
        return jLabelRazaoSocial;
    }

    public void setjLabelRazaoSocial(JLabel jLabelRazaoSocial) {
        this.jLabelRazaoSocial = jLabelRazaoSocial;
    }

    public JLabel getjLabelSexo() {
        return jLabelSexo;
    }

    public void setjLabelSexo(JLabel jLabelSexo) {
        this.jLabelSexo = jLabelSexo;
    }

    public JLabel getjLabelTitulo() {
        return jLabelTitulo;
    }

    public void setjLabelTitulo(JLabel jLabelTitulo) {
        this.jLabelTitulo = jLabelTitulo;
    }

    public JLabel getjLabelrg() {
        return jLabelrg;
    }

    public void setjLabelrg(JLabel jLabelrg) {
        this.jLabelrg = jLabelrg;
    }

    public JPanel getjPanelTitulo() {
        return jPanelTitulo;
    }

    public void setjPanelTitulo(JPanel jPanelTitulo) {
        this.jPanelTitulo = jPanelTitulo;
    }

    public JTextField getjTextFieldId() {
        return jTextFieldId;
    }

    public void setjTextFieldId(JTextField jTextFieldId) {
        this.jTextFieldId = jTextFieldId;
    }
    
  


    public javax.swing.JTextField getjTextFieldObs() {
        return jTextFieldObs;
    }

    public javax.swing.JFormattedTextField getjFormattedTextFieldMetragem() {
        return jFormattedTextFieldMetragem;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelDados = new javax.swing.JPanel();
        jLabelId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabelNomeFantasia = new javax.swing.JLabel();
        jLabelRazaoSocial = new javax.swing.JLabel();
        jLabelrg = new javax.swing.JLabel();
        jLabelCpf = new javax.swing.JLabel();
        jLabelInscricaoEstadual = new javax.swing.JLabel();
        jLabelCnpj = new javax.swing.JLabel();
        jLabelFone1 = new javax.swing.JLabel();
        jLabelFone2 = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jFormattedTextFieldMetragem = new javax.swing.JFormattedTextField();
        jLabelCadastro = new javax.swing.JLabel();
        jLabelCep = new javax.swing.JLabel();
        jLabelBairro = new javax.swing.JLabel();
        jLabelCidade = new javax.swing.JLabel();
        jLabelLogradouro = new javax.swing.JLabel();
        jLabelComplemento = new javax.swing.JLabel();
        jTextFieldDescricao = new javax.swing.JTextField();
        jLabelSexo = new javax.swing.JLabel();
        jLabelContato = new javax.swing.JLabel();
        jTextFieldObs = new javax.swing.JTextField();
        jLabelObs = new javax.swing.JLabel();
        jPanelBotoes = new javax.swing.JPanel();
        jButtonNovo = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGravar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Vaga de Estacionamento");
        setResizable(false);

        jPanelTitulo.setBackground(new java.awt.Color(153, 153, 255));
        jPanelTitulo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Vaga de Estacionamento");

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelTituloLayout.setVerticalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jPanelDados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelId.setText("ID");

        jFormattedTextFieldMetragem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldMetragemActionPerformed(evt);
            }
        });

        jLabelCadastro.setText("Metragem Vaga");

        jLabelComplemento.setText("Descrição");

        jLabelObs.setText("OBS.:");

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                .addComponent(jLabelCep)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelCidade))
                                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                .addComponent(jLabelFone1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelFone2)))
                                        .addComponent(jLabelLogradouro))
                                    .addComponent(jLabelrg))
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelEmail)
                                            .addComponent(jLabelBairro))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                                        .addGap(183, 183, 183)
                                        .addComponent(jLabelCpf)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelComplemento)
                                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelId))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelObs)
                                            .addComponent(jTextFieldObs, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextFieldMetragem, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelCadastro))
                                        .addGap(282, 282, 282)))
                                .addComponent(jLabelInscricaoEstadual))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jLabelNomeFantasia)
                                .addGap(361, 361, 361)
                                .addComponent(jLabelRazaoSocial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelSexo)
                                    .addComponent(jLabelCnpj))))
                        .addGap(176, 176, 176))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelContato)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelId)
                            .addComponent(jLabelCadastro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldMetragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelRazaoSocial)
                            .addComponent(jLabelSexo)))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabelNomeFantasia)))
                .addGap(18, 18, 18)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs)
                    .addComponent(jLabelComplemento, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelrg, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelInscricaoEstadual, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCnpj)
                            .addComponent(jLabelCpf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFone1)
                            .addComponent(jLabelEmail))
                        .addGap(28, 28, 28))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelFone2)
                        .addGap(26, 26, 26)))
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelCep)
                        .addComponent(jLabelBairro))
                    .addComponent(jLabelCidade))
                .addGap(50, 50, 50)
                .addComponent(jLabelLogradouro)
                .addGap(34, 34, 34)
                .addComponent(jLabelContato)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFormattedTextFieldMetragem.getAccessibleContext().setAccessibleDescription("");

        jPanelBotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Create.png"))); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.setActionCommand("0");
        jPanelBotoes.add(jButtonNovo);

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cancel.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setActionCommand("1");
        jButtonCancelar.setEnabled(false);
        jPanelBotoes.add(jButtonCancelar);

        jButtonGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OK.png"))); // NOI18N
        jButtonGravar.setText("Gravar");
        jButtonGravar.setActionCommand("1");
        jButtonGravar.setEnabled(false);
        jPanelBotoes.add(jButtonGravar);

        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Load.png"))); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setActionCommand("0");
        jPanelBotoes.add(jButtonBuscar);

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Exit.png"))); // NOI18N
        jButtonSair.setText("Sair");
        jButtonSair.setActionCommand("0");
        jPanelBotoes.add(jButtonSair);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextFieldMetragemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldMetragemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldMetragemActionPerformed

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
            java.util.logging.Logger.getLogger(TemplateCadastros2025.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TemplateCadastros2025.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TemplateCadastros2025.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TemplateCadastros2025.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaCadastroVaga dialog = new TelaCadastroVaga(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSair;
    private static javax.swing.JFormattedTextField jFormattedTextFieldMetragem;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCadastro;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelCnpj;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelContato;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelFone1;
    private javax.swing.JLabel jLabelFone2;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelInscricaoEstadual;
    private javax.swing.JLabel jLabelLogradouro;
    private javax.swing.JLabel jLabelNomeFantasia;
    private javax.swing.JLabel jLabelObs;
    private javax.swing.JLabel jLabelRazaoSocial;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelrg;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JTextField jTextFieldDescricao;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldObs;
    // End of variables declaration//GEN-END:variables

}
