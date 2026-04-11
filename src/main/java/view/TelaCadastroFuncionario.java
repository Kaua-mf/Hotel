package view;

import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaCadastroFuncionario extends javax.swing.JDialog {

    private int Masculino;

    public TelaCadastroFuncionario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelDados = new javax.swing.JPanel();
        jLabelId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabelNomeFantasia = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelrg = new javax.swing.JLabel();
        jTextFieldRg = new javax.swing.JTextField();
        jLabelCpf = new javax.swing.JLabel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jLabelFone1 = new javax.swing.JLabel();
        jLabelFone2 = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jFormattedTextFieldFone1 = new javax.swing.JFormattedTextField();
        jTextFieldDataCadastro = new javax.swing.JFormattedTextField();
        jLabelCadastro = new javax.swing.JLabel();
        jLabelCep = new javax.swing.JLabel();
        jFormattedTextFieldCep = new javax.swing.JFormattedTextField();
        jLabelBairro = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabelCidade = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabelLogradouro = new javax.swing.JLabel();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jLabelComplemento = new javax.swing.JLabel();
        jTextFieldComplemento = new javax.swing.JTextField();
        jLabelSexo = new javax.swing.JLabel();
        jTextFieldObs = new javax.swing.JTextField();
        jLabelObs = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JFormattedTextField();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jLabelCadastro1 = new javax.swing.JLabel();
        jLabelCadastro2 = new javax.swing.JLabel();
        jFormattedTextFieldFone2 = new javax.swing.JFormattedTextField();
        jComboBoxSexo = new javax.swing.JComboBox<>();
        jPanelBotoes = new javax.swing.JPanel();
        jButtonNovo = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGravar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fornecedor");
        setResizable(false);

        jPanelTitulo.setBackground(new java.awt.Color(153, 153, 255));
        jPanelTitulo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Funcionário");

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

        jLabelNomeFantasia.setText("Nome ");

        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });

        jLabelrg.setText("Rg");

        jLabelCpf.setText("CPF");

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabelFone1.setText("Fone1");

        jLabelFone2.setText("Fone 2");

        jLabelEmail.setText("Email");

        try {
            jFormattedTextFieldFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(0##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFone1ActionPerformed(evt);
            }
        });

        jTextFieldDataCadastro.setEditable(false);
        jTextFieldDataCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataCadastroActionPerformed(evt);
            }
        });

        jLabelCadastro.setText("Data de Cadastro");

        jLabelCep.setText("CEP");

        try {
            jFormattedTextFieldCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabelBairro.setText("Bairro");

        jLabelCidade.setText("Cidade");

        jLabelLogradouro.setText("Logradouro(Rua, Avenida, Beco, Viea,...)");

        jLabelComplemento.setText("Complemento");

        jLabelSexo.setText("Sexo");

        jLabelObs.setText("OBS.:");

        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });

        jPasswordFieldSenha.setText("jPasswordField1");
        jPasswordFieldSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldSenhaActionPerformed(evt);
            }
        });

        jLabelCadastro1.setText("Usuário");

        jLabelCadastro2.setText("Senha");

        try {
            jFormattedTextFieldFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(0##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFone2ActionPerformed(evt);
            }
        });

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino", "Outro" }));

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs)
                    .addComponent(jTextFieldObs)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelDadosLayout.createSequentialGroup()
                                    .addComponent(jLabelFone1)
                                    .addGap(132, 132, 132))
                                .addGroup(jPanelDadosLayout.createSequentialGroup()
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelCep)
                                        .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelCidade)
                                        .addComponent(jTextFieldCidade)))
                                .addComponent(jTextFieldLogradouro)
                                .addGroup(jPanelDadosLayout.createSequentialGroup()
                                    .addComponent(jFormattedTextFieldFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelLogradouro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEmail)
                            .addComponent(jTextFieldBairro)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelEmail)
                                    .addComponent(jLabelBairro))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldComplemento)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jLabelComplemento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelId)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelNomeFantasia))
                                        .addGroup(jPanelDadosLayout.createSequentialGroup()
                                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabelrg)
                                                .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabelCpf)
                                                .addComponent(jLabelFone2))))
                                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCadastro1)
                                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCadastro2)
                                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 62, Short.MAX_VALUE)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jLabelCadastro)
                                .addGap(91, 91, 91))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelSexo)
                                .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addContainerGap(19, Short.MAX_VALUE)
                                .addComponent(jLabelId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabelCadastro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabelCadastro1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jLabelNomeFantasia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelSexo)
                                    .addComponent(jLabelCadastro2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelrg)
                                    .addComponent(jLabelCpf))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFone1)
                        .addComponent(jLabelFone2))
                    .addComponent(jLabelEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldFone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDadosLayout.createSequentialGroup()
                            .addComponent(jLabelCep)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelDadosLayout.createSequentialGroup()
                            .addComponent(jLabelBairro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelLogradouro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelComplemento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextFieldDataCadastro.getAccessibleContext().setAccessibleParent(jTextFieldDataCadastro);

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
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jPasswordFieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldSenhaActionPerformed

    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    private void jFormattedTextFieldFone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFone1ActionPerformed

    private void jFormattedTextFieldFone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFone2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFone2ActionPerformed

    private void jTextFieldDataCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataCadastroActionPerformed

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaCadastroFuncionario dialog = new TelaCadastroFuncionario(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBoxSexo;
    private javax.swing.JFormattedTextField jFormattedTextFieldCep;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldFone1;
    private javax.swing.JFormattedTextField jFormattedTextFieldFone2;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCadastro;
    private javax.swing.JLabel jLabelCadastro1;
    private javax.swing.JLabel jLabelCadastro2;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelFone1;
    private javax.swing.JLabel jLabelFone2;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelLogradouro;
    private javax.swing.JLabel jLabelNomeFantasia;
    private javax.swing.JLabel jLabelObs;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelrg;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JFormattedTextField jTextFieldDataCadastro;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldObs;
    private javax.swing.JTextField jTextFieldRg;
    private javax.swing.JFormattedTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables

public javax.swing.JTextField getjTextFieldId() { return jTextFieldId; }
    public javax.swing.JTextField getjTextFieldNomeFantasia() { return jTextFieldNome; }
    public javax.swing.JTextField getjTextFieldRg() { return jTextFieldRg; }
    public javax.swing.JFormattedTextField getjFormattedTextFieldCpf() { return jFormattedTextFieldCpf; }
    public javax.swing.JFormattedTextField getjFormattedTextFieldFone1() { return jFormattedTextFieldFone1; }
    public javax.swing.JFormattedTextField getjFormattedTextFieldFone2() { return jFormattedTextFieldFone2; }
    public javax.swing.JTextField getjTextFieldEmail() { return jTextFieldEmail; }
    public javax.swing.JFormattedTextField getjFormattedTextFieldCep() { return jFormattedTextFieldCep; }
    public javax.swing.JTextField getjTextFieldCidade() { return jTextFieldCidade; }
    public javax.swing.JTextField getjTextFieldBairro() { return jTextFieldBairro; }
    public javax.swing.JTextField getjTextFieldLogradouro() { return jTextFieldLogradouro; }
    public javax.swing.JTextField getjTextFieldComplemento() { return jTextFieldComplemento; }

 
    
    public JFormattedTextField getjTextFieldUsuario() {
        return jTextFieldUsuario;
    }

    public void setjTextFieldUsuario(JFormattedTextField jTextFieldUsuario) {
        this.jTextFieldUsuario = jTextFieldUsuario;
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

    public JLabel getjLabelCadastro1() {
        return jLabelCadastro1;
    }

    public void setjLabelCadastro1(JLabel jLabelCadastro1) {
        this.jLabelCadastro1 = jLabelCadastro1;
    }

    public JLabel getjLabelCadastro2() {
        return jLabelCadastro2;
    }

    public void setjLabelCadastro2(JLabel jLabelCadastro2) {
        this.jLabelCadastro2 = jLabelCadastro2;
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

    public JLabel getjLabelComplemento() {
        return jLabelComplemento;
    }

    public void setjLabelComplemento(JLabel jLabelComplemento) {
        this.jLabelComplemento = jLabelComplemento;
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

    public JPasswordField jPasswordFieldSenha() {
        return jPasswordFieldSenha;
    }

    public void setjPasswordFieldSenha(JPasswordField jPasswordFieldSenha) {
        this.jPasswordFieldSenha = jPasswordFieldSenha;
    }

    public JTextField getjTextFieldNome() {
        return jTextFieldNome;
    }

    public void setjTextFieldNome(JTextField jTextFieldNome) {
        this.jTextFieldNome = jTextFieldNome;
    }

    public JTextField getjTextFieldObs() {
        return jTextFieldObs;
    }

    public void setjTextFieldObs(JTextField jTextFieldObs) {
        this.jTextFieldObs = jTextFieldObs;
    }

public javax.swing.JPasswordField getjPasswordFieldSenha() {
    return jPasswordFieldSenha;
}
public javax.swing.JFormattedTextField getjTextFieldDataCadastro() {
    return jTextFieldDataCadastro;
}

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public JComboBox<String> getjComboBoxSexo() {
        return jComboBoxSexo;
    }

    public void setjComboBoxSexo(JComboBox<String> jComboBoxSexo) {
        this.jComboBoxSexo = jComboBoxSexo;
    }
}

