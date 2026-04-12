package view;
import model.AlocacaoVaga;
import DAO.ReservaDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import model.*;

public class TelaCheckInOut extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;
    private JButton jButtonCheckHospedeBuscar;
    private JPanel jPanelCheck, jPanelCheckTitulo, jPanelCheckDados, jPanelCheckBotoes;
    private JTextField jTextFieldCheckId, jTextFieldCheckObs;
    private JFormattedTextField jTextFieldCheckDataHoraCadastro, jTextFieldCheckDataHoraEntrada, jTextFieldCheckDataHoraSaida;
    private JComboBox<Reserva> jComboBoxCheckReserva;
    private JButton jButtonCheckNovo, jButtonCheckGravar, jButtonCheckCancelar, jButtonCheckBuscar, jButtonCheckSair;
    private List<CheckQuarto> listaCheckQuartos = new ArrayList<>();
    private JPanel jPanelCheckHospede, jPanelCheckHospedeTitulo, jPanelCheckHospedeDados;
    private JPanel jPanelCheckHospedeBotoes, jPanelCheckHospedeTabela;
    private JComboBox<String> jComboBoxTipoPessoa;
    private JComboBox<Pessoa> jComboBoxPessoa;
    private JTextField jTextFieldCheckHospedeObs;
    private JButton jButtonCheckHospedeAdicionar, jButtonCheckHospedeRemover, jButtonCheckHospedeGravar;
    private DefaultListModel<String> listModelHospedes;
    private JList<String> jListHospedes;
    private List<CheckHospede> listaCheckHospedes = new ArrayList<>();
    private Check checkAtual;
    private JPanel jPanelCheckQuarto, jPanelCheckQuartoTitulo, jPanelCheckQuartoDados;
    private JPanel jPanelCheckQuartoBotoes, jPanelCheckQuartoTabela;
    private JComboBox<Quarto> jComboBoxQuarto;
    private JFormattedTextField jTextFieldCheckQuartoDataHoraInicio, jTextFieldCheckQuartoDataHoraFim;
    private JTextField jTextFieldCheckQuartoObs;
    private JButton jButtonCheckQuartoAdicionar, jButtonCheckQuartoRemover, jButtonCheckQuartoGravar;
    private DefaultListModel<String> listModelQuartos;
    private JList<String> jListQuartos;
    private List<AlocacaoVaga> listaAlocacoes = new ArrayList<>();
    private DefaultListModel<String> listModelAlocacoes;
    private JPanel jPanelAlocacaoVaga, jPanelAlocacaoVagaTitulo, jPanelAlocacaoVagaDados;
    private JPanel jPanelAlocacaoVagaBotoes, jPanelAlocacaoVagaTabela;
    private JComboBox<Veiculo> jComboBoxVeiculo;
    private JComboBox<VagaEstacionamento> jComboBoxVagaEstacionamento;
    private JTextField jTextFieldAlocacaoVagaObs;
    private JButton jButtonAlocacaoVagaAdicionar, jButtonAlocacaoVagaRemover, jButtonAlocacaoVagaGravar;
    private JList<String> jListAlocacoes;
model.AlocacaoVaga av = new model.AlocacaoVaga();   
    private JPanel jPanelReceber, jPanelReceberTitulo, jPanelReceberDados, jPanelReceberBotoes;
    private JTextField jTextFieldReceberId, jTextFieldReceberValorOriginal, jTextFieldReceberDesconto;
    private JTextField jTextFieldReceberAcrescimo, jTextFieldReceberValorPago, jTextFieldReceberObs;
    private JFormattedTextField jTextFieldReceberDataHoraCadastro;
    private JButton jButtonReceberNovo, jButtonReceberGravar, jButtonReceberCancelar;

    private JTabbedPane jTabbedPane;
private JPanel jPanelAlocacaoTabela;
private JPanel jPanelAlocacaoBotoes;
private JComboBox<model.VagaEstacionamento> jComboBoxVaga;
private JTextField jTextFieldAlocacaoObs;
private JButton jButtonAlocacaoAdicionar;
private JButton jButtonAlocacaoRemover;
private JButton jButtonAlocacaoGravar;



public void calcularTotalRecebimento() {
    double total = 0;
    // Soma o valor de cada quarto adicionado na lista da Aba 3
    for (model.CheckQuarto cq : listaCheckQuartos) {
        total += cq.getValorQuarto(); 
    }
    jTextFieldReceberValorOriginal.setText(String.valueOf(total));
    jTextFieldReceberValorPago.setText(String.valueOf(total));
}
    public TelaCheckInOut(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarReservas();
        ativaBotoes(false);
    }

    public TelaCheckInOut() {
        this(null, true);
    }

    private void initComponents() {
        setTitle("Check-in / Checkout");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(860, 660);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(5, 5));

        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("1 - Check",     buildPanelCheck());
        jTabbedPane.addTab("2 - Pessoas",   buildPanelCheckHospede());
        jTabbedPane.addTab("3 - Quartos",   buildPanelCheckQuarto());
        jTabbedPane.addTab("4 - Vagas",     buildPanelAlocacaoVaga());
        jTabbedPane.addTab("5 - Receber",   buildPanelReceber());

        for (int i = 1; i <= 4; i++) jTabbedPane.setEnabledAt(i, false);
        add(jTabbedPane, BorderLayout.CENTER);

        aplicarMascara(jTextFieldCheckDataHoraCadastro);
        aplicarMascara(jTextFieldCheckDataHoraEntrada);
        aplicarMascara(jTextFieldCheckDataHoraSaida);
        aplicarMascara(jTextFieldCheckQuartoDataHoraInicio);
        aplicarMascara(jTextFieldCheckQuartoDataHoraFim);
        aplicarMascara(jTextFieldReceberDataHoraCadastro);
        
        validarCampoData(jTextFieldCheckDataHoraEntrada);
        validarCampoData(jTextFieldCheckDataHoraSaida);
        validarCampoData(jTextFieldCheckQuartoDataHoraInicio);
        validarCampoData(jTextFieldCheckQuartoDataHoraFim);
        
    }

    private void ativaBotoes(boolean ativo) {
    if (jButtonCheckNovo == null) return;

    jButtonCheckNovo.setEnabled(!ativo);
    jButtonCheckSair.setEnabled(!ativo);
    
    jButtonCheckGravar.setEnabled(ativo);
    jButtonCheckCancelar.setEnabled(ativo);
    jButtonCheckBuscar.setEnabled(ativo);
    
    if (jButtonCheckHospedeBuscar != null) {
        jButtonCheckHospedeBuscar.setEnabled(ativo);
    }

    if (jPanelCheckDados != null) {
        jPanelCheckDados.setEnabled(ativo);
        for (java.awt.Component c : jPanelCheckDados.getComponents()) {
            c.setEnabled(ativo);
        }
    }

    if (jPanelCheckHospedeDados != null) {
        jPanelCheckHospedeDados.setEnabled(ativo);
        for (java.awt.Component c : jPanelCheckHospedeDados.getComponents()) {
            c.setEnabled(ativo);
        }
    }
    
    jTextFieldCheckId.setEnabled(false);
    jTextFieldCheckDataHoraCadastro.setEnabled(false);
}

    private void aplicarMascara(JFormattedTextField c) {
        try {
            c.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) { ex.printStackTrace(); }
    }

    private JPanel buildPanelCheck() {
        jPanelCheck = new JPanel(new BorderLayout(5, 5));
        jPanelCheck.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jPanelCheckTitulo = titulo("Check-in / Checkout", new Color(153, 153, 255));

        jPanelCheckDados = new JPanel(null);
        jPanelCheckDados.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        int y = 20;
        label(jPanelCheckDados, "ID:", 20, y);
        jTextFieldCheckId = tf(jPanelCheckDados, 160, y, 80);

        y += 35; label(jPanelCheckDados, "Dt/Hr Cadastro:", 20, y);
        jTextFieldCheckDataHoraCadastro = ftf(jPanelCheckDados, 160, y, 140);

        y += 35; label(jPanelCheckDados, "Dt/Hr Entrada:", 20, y);
        jTextFieldCheckDataHoraEntrada = ftf(jPanelCheckDados, 160, y, 140);

        y += 35; label(jPanelCheckDados, "Dt/Hr Saida:", 20, y);
        jTextFieldCheckDataHoraSaida = ftf(jPanelCheckDados, 160, y, 140);

        y += 35; label(jPanelCheckDados, "Reserva:", 20, y);
        jComboBoxCheckReserva = new JComboBox<>();
        jComboBoxCheckReserva.setBounds(160, y, 380, 25);
        jPanelCheckDados.add(jComboBoxCheckReserva);

        y += 35; label(jPanelCheckDados, "Obs:", 20, y);
        jTextFieldCheckObs = tf(jPanelCheckDados, 160, y, 560);

        jPanelCheckBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jPanelCheckBotoes.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCheckNovo     = btn("Novo",     "/imagens/Create.png", "0");
        jButtonCheckCancelar = btn("Cancelar", "/imagens/Cancel.png", "1");
        jButtonCheckGravar   = btn("Gravar",   "/imagens/OK.png",     "1");
        jButtonCheckBuscar   = btn("Buscar",   "/imagens/Load.png",   "0");
        jButtonCheckSair     = btn("Sair",     "/imagens/Exit.png",   "0");
        
        jButtonCheckBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelaBuscaReserva telaBusca = new TelaBuscaReserva(null, true);
                telaBusca.setVisible(true);
                
                if (telaBusca.getReservaSelecionada() != null) {
                    Reserva r = telaBusca.getReservaSelecionada();
                    for (int i = 0; i < jComboBoxCheckReserva.getItemCount(); i++) {
                        Reserva item = jComboBoxCheckReserva.getItemAt(i);
                        if (item != null && item.getId() == r.getId()) {
                            jComboBoxCheckReserva.setSelectedIndex(i);
                            break;
                        }
                    }
                    jTextFieldCheckObs.setText(r.getObs());
                    jTextFieldCheckId.setText(String.valueOf(r.getId()));
                }
            }
        });

        jButtonCheckSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        jButtonCheckNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ativaBotoes(true);
                jTextFieldCheckId.setText("");
                jTextFieldCheckObs.setText("");
                if (jComboBoxCheckReserva.getItemCount() > 0) {
                    jComboBoxCheckReserva.setSelectedIndex(0);
                }
            }
        });

        jButtonCheckCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ativaBotoes(false);
                jTextFieldCheckId.setText("");
                jTextFieldCheckObs.setText("");
            }
        });
jButtonCheckGravar.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String entradaTxt = jTextFieldCheckDataHoraEntrada.getText().trim();
            String saidaTxt = jTextFieldCheckDataHoraSaida.getText().trim();

            if (entradaTxt.replace("-", "").trim().isEmpty() || saidaTxt.replace("-", "").trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha as datas de entrada e saída!");
                return;
            }

            try {
                java.time.LocalDate dEntrada = java.time.LocalDate.parse(entradaTxt);
                java.time.LocalDate dSaida = java.time.LocalDate.parse(saidaTxt);

                if (dSaida.isBefore(dEntrada)) {
                    JOptionPane.showMessageDialog(null, "Erro: A data de saída não pode ser anterior à data de entrada!");
                    return;
                }
            } catch (java.time.format.DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de data inválido! Certifique-se de usar AAAA-MM-DD.");
                return;
            }

            Check check = new Check();
            check.setDataHoraEntrada(entradaTxt);
            check.setDataHoraSaida(saidaTxt);
            check.setObs(jTextFieldCheckObs.getText());
            check.setStatus("A");
            
            check.setDataHoraCadastro(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));

            Reserva reservaSelecionada = (Reserva) jComboBoxCheckReserva.getSelectedItem();
            
            if (reservaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma reserva antes de gravar!");
                return;
            }
            
            check.setReserva(reservaSelecionada);

            DAO.CheckDAO dao = new DAO.CheckDAO();
            dao.Create(check);

            checkAtual = check;

            JOptionPane.showMessageDialog(null, "Check-in gravado com sucesso!");

            jTabbedPane.setEnabledAt(1, true);
            jTabbedPane.setSelectedIndex(1);
            
            ativaBotoes(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar: " + e.getMessage());
            e.printStackTrace();
        }
    }
});
        for (JButton b : new JButton[]{jButtonCheckNovo,jButtonCheckCancelar,jButtonCheckGravar,jButtonCheckBuscar,jButtonCheckSair})
            jPanelCheckBotoes.add(b);

        jPanelCheck.add(jPanelCheckTitulo, BorderLayout.NORTH);
        jPanelCheck.add(jPanelCheckDados,  BorderLayout.CENTER);
        jPanelCheck.add(jPanelCheckBotoes, BorderLayout.SOUTH);
        return jPanelCheck;
    }

   private JPanel buildPanelCheckHospede() {
    jPanelCheckHospede = new JPanel(new BorderLayout(5, 5));
    jPanelCheckHospede.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    jPanelCheckHospedeTitulo = titulo("Pessoas do Check", new Color(153, 255, 102));

    jPanelCheckHospedeDados = new JPanel(null);
    jPanelCheckHospedeDados.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jPanelCheckHospedeDados.setPreferredSize(new Dimension(840, 135));

    int y = 20;
    label(jPanelCheckHospedeDados, "Tipo Pessoa:", 20, y);
    jComboBoxTipoPessoa = new JComboBox<>(new String[]{"Hospede"});
    jComboBoxTipoPessoa.setBounds(170, y, 180, 25);
    jPanelCheckHospedeDados.add(jComboBoxTipoPessoa);

    y += 35; 
    label(jPanelCheckHospedeDados, "Pessoa:", 20, y);
    jComboBoxPessoa = new JComboBox<>();
    jComboBoxPessoa.setBounds(170, y, 380, 25);
    jPanelCheckHospedeDados.add(jComboBoxPessoa);

    jButtonCheckHospedeBuscar = btn("Buscar", "/imagens/Load.png", "0");
    jButtonCheckHospedeBuscar.setBounds(560, y, 100, 25);
    jPanelCheckHospedeDados.add(jButtonCheckHospedeBuscar);

    jButtonCheckHospedeBuscar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            String tipo = (String) jComboBoxTipoPessoa.getSelectedItem();
            if ("Hospede".equals(tipo)) {
                TelaBuscaHospede busca = new TelaBuscaHospede(null, true);
                busca.setVisible(true);
                if (busca.getHospedeSelecionado() != null) {
                    Hospede h = busca.getHospedeSelecionado();
                    jComboBoxPessoa.addItem(h); // ADICIONA NA LISTA
                    jComboBoxPessoa.setSelectedItem(h); // SELECIONA
                    jTextFieldCheckHospedeObs.setText(h.getObs());
                }
            } else if ("Funcionario".equals(tipo)) {
                TelaBuscaFuncionario busca = new TelaBuscaFuncionario(null, true);
                busca.setVisible(true);
                if (busca.getFuncionarioSelecionado() != null) {
                    Funcionario f = busca.getFuncionarioSelecionado();
                    jComboBoxPessoa.addItem(f); // ADICIONA NA LISTA
                    jComboBoxPessoa.setSelectedItem(f); // SELECIONA
                    jTextFieldCheckHospedeObs.setText(f.getObs());
                }
            } else if ("Fornecedor".equals(tipo)) {
                TelaBuscaFornecedor busca = new TelaBuscaFornecedor(null, true);
                busca.setVisible(true);
                if (busca.getFornecedorSelecionado() != null) {
                    Fornecedor fo = busca.getFornecedorSelecionado();
                    jComboBoxPessoa.addItem(fo); // ADICIONA NA LISTA
                    jComboBoxPessoa.setSelectedItem(fo); // SELECIONA
                    jTextFieldCheckHospedeObs.setText(fo.getObs());
                }
            }
        }
    });

    y += 35; 
    label(jPanelCheckHospedeDados, "Obs:", 20, y);
    jTextFieldCheckHospedeObs = tf(jPanelCheckHospedeDados, 170, y, 560);

    jPanelCheckHospedeTabela = new JPanel(new BorderLayout());
    jPanelCheckHospedeTabela.setBorder(new TitledBorder("Pessoas adicionadas ao Check"));
    listModelHospedes = new DefaultListModel<>();
    jListHospedes = new JList<>(listModelHospedes);
    jListHospedes.setFont(new Font("Monospaced", Font.PLAIN, 12));
    jPanelCheckHospedeTabela.add(new JScrollPane(jListHospedes), BorderLayout.CENTER);

    JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jPanelCheckHospedeDados, jPanelCheckHospedeTabela);
    split.setResizeWeight(0.38); 
    split.setDividerSize(5);

    jPanelCheckHospedeBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    jPanelCheckHospedeBotoes.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jButtonCheckHospedeAdicionar = btn("Adicionar", "/imagens/Create.png", "0");
    jButtonCheckHospedeRemover = btn("Remover", "/imagens/Cancel.png", "0");
    jButtonCheckHospedeGravar = btn("Gravar Todos", "/imagens/OK.png", "0");
    jButtonCheckHospedeGravar.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (listaCheckHospedes == null || listaCheckHospedes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Adicione pessoas à lista antes de gravar!");
                return;
            }

            DAO.CheckHospedeDAO daoH = new DAO.CheckHospedeDAO();
            
            for (CheckHospede ch : listaCheckHospedes) {
                if (checkAtual != null) {
                    ch.setCheck(checkAtual);
                    daoH.Create(ch);
                }
            }
            JOptionPane.showMessageDialog(null, "Hóspedes gravados com sucesso!");
            
            jTabbedPane.setEnabledAt(2, true);
            jTabbedPane.setSelectedIndex(2);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar pessoas: " + e.getMessage());
            e.printStackTrace();
        }
    }
});
    jPanelCheckHospedeBotoes.add(jButtonCheckHospedeAdicionar);
    jPanelCheckHospedeBotoes.add(jButtonCheckHospedeRemover);
    jButtonCheckHospedeRemover.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        int index = jListHospedes.getSelectedIndex();
        if (index != -1) {
            listModelHospedes.remove(index);
            listaCheckHospedes.remove(index);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione alguém na lista para remover!");
        }
    }
});
    jPanelCheckHospedeBotoes.add(jButtonCheckHospedeGravar);

    jButtonCheckHospedeAdicionar.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        Pessoa p = (Pessoa) jComboBoxPessoa.getSelectedItem();
        String tipo = (String) jComboBoxTipoPessoa.getSelectedItem();
        
        if (p != null) {
            for (CheckHospede existente : listaCheckHospedes) {
                if (existente.getPessoa().getId() == p.getId()) {
                    JOptionPane.showMessageDialog(null, "Esta pessoa já foi adicionada!");
                    return; 
                }
            }

            CheckHospede ch = new CheckHospede();
            ch.setCheck(checkAtual);
            ch.setPessoa(p);
            ch.setTipoPessoa(tipo);
            ch.setObs(jTextFieldCheckHospedeObs.getText());
            
            listaCheckHospedes.add(ch);
            listModelHospedes.addElement(p.getNome() + " [" + tipo + "]");
        }
    }
});

    jPanelCheckHospede.add(jPanelCheckHospedeTitulo, BorderLayout.NORTH);
    jPanelCheckHospede.add(split, BorderLayout.CENTER);
    jPanelCheckHospede.add(jPanelCheckHospedeBotoes, BorderLayout.SOUTH);

    return jPanelCheckHospede;
}

   private JPanel buildPanelCheckQuarto() {
    jPanelCheckQuarto = new JPanel(new BorderLayout(5, 5));
    jPanelCheckQuarto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    jPanelCheckQuartoTitulo = titulo("Quartos do Check", new Color(153, 255, 102));
    
    jPanelCheckQuartoDados = new JPanel(null);
    jPanelCheckQuartoDados.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jPanelCheckQuartoDados.setPreferredSize(new Dimension(840, 160));

    int y = 20;
    label(jPanelCheckQuartoDados, "Quarto:", 20, y);
    jComboBoxQuarto = new JComboBox<>();
    jComboBoxQuarto.setBounds(170, y, 380, 25);
    jPanelCheckQuartoDados.add(jComboBoxQuarto);

    // BOTAO BUSCAR QUARTO
    JButton jButtonQuartoBuscar = btn("Buscar", "/imagens/Load.png", "0");
    jButtonQuartoBuscar.setBounds(560, y, 100, 25);
    jPanelCheckQuartoDados.add(jButtonQuartoBuscar);

    jButtonQuartoBuscar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TelaBuscaQuarto busca = new TelaBuscaQuarto(null, true);
            busca.setVisible(true);
            
            if (busca.getQuartoSelecionado() != null) {
                Quarto q = busca.getQuartoSelecionado();
                
                jComboBoxQuarto.addItem(q);
                jComboBoxQuarto.setSelectedItem(q);
                
                // Puxa a observação do quarto automaticamente
                jTextFieldCheckQuartoObs.setText(q.getObs());
            }
        }
    });

    y += 35; label(jPanelCheckQuartoDados, "Dt/Hr Inicio:", 20, y);
    jTextFieldCheckQuartoDataHoraInicio = ftf(jPanelCheckQuartoDados, 170, y, 140);
    y += 35; label(jPanelCheckQuartoDados, "Dt/Hr Fim:", 20, y);
    jTextFieldCheckQuartoDataHoraFim = ftf(jPanelCheckQuartoDados, 170, y, 140);
    y += 35; label(jPanelCheckQuartoDados, "Obs:", 20, y);
    jTextFieldCheckQuartoObs = tf(jPanelCheckQuartoDados, 170, y, 560);

    jPanelCheckQuartoTabela = new JPanel(new BorderLayout());
    jPanelCheckQuartoTabela.setBorder(new TitledBorder("Quartos adicionados ao Check"));
    listModelQuartos = new DefaultListModel<>();
    jListQuartos = new JList<>(listModelQuartos);
    jListQuartos.setFont(new Font("Monospaced", Font.PLAIN, 12));
    jPanelCheckQuartoTabela.add(new JScrollPane(jListQuartos), BorderLayout.CENTER);

    JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jPanelCheckQuartoDados, jPanelCheckQuartoTabela);
    split.setResizeWeight(0.45); split.setDividerSize(5);

    jPanelCheckQuartoBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    jPanelCheckQuartoBotoes.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    
    jButtonCheckQuartoAdicionar = btn("Adicionar", "/imagens/Create.png", "0");
    jButtonCheckQuartoRemover = btn("Remover", "/imagens/Cancel.png", "0");
    jButtonCheckQuartoGravar = btn("Gravar Todos", "/imagens/OK.png", "0");

    // Lógica Adicionar Quarto
    jButtonCheckQuartoAdicionar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Quarto q = (Quarto) jComboBoxQuarto.getSelectedItem();
            
            if (q != null && checkAtual != null) {
                // Verifica se já está na lista
                for (CheckQuarto existente : listaCheckQuartos) {
                    if (existente.getQuarto().getId() == q.getId()) {
                        JOptionPane.showMessageDialog(null, "Este quarto já foi adicionado!");
                        return;
                    }
                }
                
                // Cria o vínculo
                CheckQuarto cq = new CheckQuarto();
                cq.setCheck(checkAtual);
                cq.setQuarto(q);
                cq.setDataHoraInicio(jTextFieldCheckQuartoDataHoraInicio.getText());
                cq.setDataHoraFim(jTextFieldCheckQuartoDataHoraFim.getText());
                cq.setObs(jTextFieldCheckQuartoObs.getText());
                
                // Salva na lista da memória e na lista visual
                listaCheckQuartos.add(cq);
                listModelQuartos.addElement(q.getDescricao() + " - " + q.getNumero());
                
                // Limpa campos para o próximo
                jTextFieldCheckQuartoObs.setText("");
            } else if (checkAtual == null) {
                JOptionPane.showMessageDialog(null, "Grave o Check na aba 1 primeiro!");
            }
        }
    });

    // Lógica Remover Quarto
    jButtonCheckQuartoRemover.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            int index = jListQuartos.getSelectedIndex();
            if (index != -1) {
                listModelQuartos.remove(index);
                listaCheckQuartos.remove(index);
            }
        }
    });

    // Lógica Gravar Todos (Quartos)
    jButtonCheckQuartoGravar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            try {
                if (listaCheckQuartos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Adicione quartos à lista!");
                    return;
                }
                DAO.CheckQuartoDAO daoQ = new DAO.CheckQuartoDAO();
                for (CheckQuarto cq : listaCheckQuartos) {
                    daoQ.Create(cq);
                }
                JOptionPane.showMessageDialog(null, "Quartos gravados!");
                jTabbedPane.setEnabledAt(3, true); // Libera Vagas
                jTabbedPane.setSelectedIndex(3);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    });

    jPanelCheckQuartoBotoes.add(jButtonCheckQuartoAdicionar);
    jPanelCheckQuartoBotoes.add(jButtonCheckQuartoRemover);
    jPanelCheckQuartoBotoes.add(jButtonCheckQuartoGravar);

    jPanelCheckQuarto.add(jPanelCheckQuartoTitulo, BorderLayout.NORTH);
    jPanelCheckQuarto.add(split, BorderLayout.CENTER);
    jPanelCheckQuarto.add(jPanelCheckQuartoBotoes, BorderLayout.SOUTH);
    return jPanelCheckQuarto;
}

    public void carregarReservas() {
        try {
            ReservaDAO dao = new ReservaDAO(); 
            List<Reserva> lista = dao.Retrieve();
            getjComboBoxCheckReserva().removeAllItems();
            getjComboBoxCheckReserva().addItem(null); 
            for (Reserva r : lista) {
                getjComboBoxCheckReserva().addItem(r);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar reservas: " + e.getMessage());
        }
    }

   private JPanel buildPanelAlocacaoVaga() {
    jPanelAlocacaoVaga = new JPanel(new BorderLayout(5, 5));
    jPanelAlocacaoVaga.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    jPanelAlocacaoVagaTitulo = titulo("Alocacao de Vagas", new Color(153, 255, 102));

    jPanelAlocacaoVagaDados = new JPanel(null);
    jPanelAlocacaoVagaDados.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jPanelAlocacaoVagaDados.setPreferredSize(new Dimension(840, 160));

    int y = 20;
    // VEICULO
    label(jPanelAlocacaoVagaDados, "Veiculo:", 20, y);
    jComboBoxVeiculo = new JComboBox<>();
    jComboBoxVeiculo.setBounds(170, y, 380, 25);
    jPanelAlocacaoVagaDados.add(jComboBoxVeiculo);

    JButton jButtonVeiculoBuscar = btn("Buscar", "/imagens/Load.png", "0");
    jButtonVeiculoBuscar.setBounds(560, y, 100, 25);
    jPanelAlocacaoVagaDados.add(jButtonVeiculoBuscar);

    jButtonVeiculoBuscar.addActionListener(e -> {
        TelaBuscaVeiculo busca = new TelaBuscaVeiculo(null, true);
        busca.setVisible(true);
        if (busca.getVeiculoSelecionado() != null) {
            Veiculo v = busca.getVeiculoSelecionado();
            jComboBoxVeiculo.addItem(v);
            jComboBoxVeiculo.setSelectedItem(v);
        }
    });

    y += 35;
    // VAGA
    label(jPanelAlocacaoVagaDados, "Vaga:", 20, y);
    jComboBoxVaga = new JComboBox<>();
    jComboBoxVaga.setBounds(170, y, 380, 25);
    jPanelAlocacaoVagaDados.add(jComboBoxVaga);

    JButton jButtonVagaBuscar = btn("Buscar", "/imagens/Load.png", "0");
    jButtonVagaBuscar.setBounds(560, y, 100, 25);
    jPanelAlocacaoVagaDados.add(jButtonVagaBuscar);

    jButtonVagaBuscar.addActionListener(e -> {
        TelaBuscaVaga busca = new TelaBuscaVaga(null, true);
        busca.setVisible(true);
        if (busca.getVagaSelecionada() != null) {
            VagaEstacionamento v = busca.getVagaSelecionada();
            jComboBoxVaga.addItem(v);
            jComboBoxVaga.setSelectedItem(v);
        }
    });

    y += 35;
    label(jPanelAlocacaoVagaDados, "Obs:", 20, y);
    jTextFieldAlocacaoObs = tf(jPanelAlocacaoVagaDados, 170, y, 560);

    // LISTA VISUAL
    jPanelAlocacaoTabela = new JPanel(new BorderLayout());
    jPanelAlocacaoTabela.setBorder(new TitledBorder("Alocacoes adicionadas ao Check"));
    listModelAlocacoes = new DefaultListModel<>();
    jListAlocacoes = new JList<>(listModelAlocacoes);
    jPanelAlocacaoTabela.add(new JScrollPane(jListAlocacoes), BorderLayout.CENTER);

    JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jPanelAlocacaoVagaDados, jPanelAlocacaoTabela);
    split.setResizeWeight(0.45);

    // BOTOES DE AÇÃO
    jPanelAlocacaoBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    jButtonAlocacaoAdicionar = btn("Adicionar", "/imagens/Create.png", "0");
    jButtonAlocacaoRemover = btn("Remover", "/imagens/Cancel.png", "0");
    jButtonAlocacaoGravar = btn("Gravar Todos", "/imagens/OK.png", "0");

    // LÓGICA ADICIONAR
    jButtonAlocacaoAdicionar.addActionListener(e -> {
        Veiculo veic = (Veiculo) jComboBoxVeiculo.getSelectedItem();
        VagaEstacionamento vaga = (VagaEstacionamento) jComboBoxVaga.getSelectedItem();

        if (veic != null && vaga != null && checkAtual != null) {
            AlocacaoVaga av = new AlocacaoVaga();
            av.setCheck(checkAtual);
            av.setVeiculo(veic);
            av.setVagaEstacionamento(vaga);
            av.setObs(jTextFieldAlocacaoObs.getText());
            av.setStatus('A');

            listaAlocacoes.add(av);
            listModelAlocacoes.addElement("Veículo: " + veic.getPlaca() + " | Vaga: " + vaga.getDescricao());
        }
    });

    // LÓGICA REMOVER
    jButtonAlocacaoRemover.addActionListener(e -> {
        int index = jListAlocacoes.getSelectedIndex();
        if (index != -1) {
            listModelAlocacoes.remove(index);
            listaAlocacoes.remove(index);
        }
    });

    // LÓGICA GRAVAR TODOS
    jButtonAlocacaoGravar.addActionListener(e -> {
        try {
            if (listaAlocacoes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Adicione alocações à lista!");
                return;
            }
            DAO.AlocacaoVagaDAO daoA = new DAO.AlocacaoVagaDAO();
            for (AlocacaoVaga av : listaAlocacoes) {
                daoA.Create(av);
            }
            JOptionPane.showMessageDialog(null, "Alocações gravadas!");
            jTabbedPane.setEnabledAt(4, true); // Libera Receber
            jTabbedPane.setSelectedIndex(4);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    });

    jPanelAlocacaoBotoes.add(jButtonAlocacaoAdicionar);
    jPanelAlocacaoBotoes.add(jButtonAlocacaoRemover);
    jPanelAlocacaoBotoes.add(jButtonAlocacaoGravar);

    jPanelAlocacaoVaga.add(jPanelAlocacaoVagaTitulo, BorderLayout.NORTH);
    jPanelAlocacaoVaga.add(split, BorderLayout.CENTER);
    jPanelAlocacaoVaga.add(jPanelAlocacaoBotoes, BorderLayout.SOUTH);
    return jPanelAlocacaoVaga;
}

   private JPanel buildPanelReceber() {
        jPanelReceber = new JPanel(new BorderLayout(5, 5));
        jPanelReceber.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelReceberTitulo = titulo("Recebimento do Check", new Color(153, 153, 255));
        jPanelReceberDados = new JPanel(null);
        jPanelReceberDados.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        int y = 20;
        label(jPanelReceberDados, "ID:", 20, y);
        jTextFieldReceberId = tf(jPanelReceberDados, 220, y, 80);
        jTextFieldReceberId.setEnabled(false);
        
        y += 35; label(jPanelReceberDados, "Dt/Hr Cadastro:", 20, y);
        jTextFieldReceberDataHoraCadastro = ftf(jPanelReceberDados, 220, y, 140);
        jTextFieldReceberDataHoraCadastro.setEnabled(false);
        
        y += 35; label(jPanelReceberDados, "Valor Original (R$):", 20, y);
        jTextFieldReceberValorOriginal = tf(jPanelReceberDados, 220, y, 150);
        
        y += 35; label(jPanelReceberDados, "Desconto (R$):", 20, y);
        jTextFieldReceberDesconto = tf(jPanelReceberDados, 220, y, 150);
        jTextFieldReceberDesconto.setText("0.00");
        
        y += 35; label(jPanelReceberDados, "Acrescimo (R$):", 20, y);
        jTextFieldReceberAcrescimo = tf(jPanelReceberDados, 220, y, 150);
        jTextFieldReceberAcrescimo.setText("0.00");
        
        y += 35; label(jPanelReceberDados, "Valor Pago (R$):", 20, y);
        jTextFieldReceberValorPago = tf(jPanelReceberDados, 220, y, 150);
        
        y += 35; label(jPanelReceberDados, "Obs:", 20, y);
        jTextFieldReceberObs = tf(jPanelReceberDados, 220, y, 560);
        
        // --- LOGICA PARA CALCULO AUTOMATICO ---
        java.awt.event.FocusAdapter calculoEvento = new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    double vOriginal = Double.parseDouble(jTextFieldReceberValorOriginal.getText());
                    double vDesconto = Double.parseDouble(jTextFieldReceberDesconto.getText());
                    double vAcrescimo = Double.parseDouble(jTextFieldReceberAcrescimo.getText());
                    double vTotal = (vOriginal + vAcrescimo) - vDesconto;
                    jTextFieldReceberValorPago.setText(String.format("%.2f", vTotal).replace(",", "."));
                } catch (Exception e) {}
            }
        };
        jTextFieldReceberDesconto.addFocusListener(calculoEvento);
        jTextFieldReceberAcrescimo.addFocusListener(calculoEvento);

        jPanelReceberBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jPanelReceberBotoes.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        jButtonReceberNovo     = btn("Novo",      "/imagens/Create.png", "0");
        jButtonReceberCancelar = btn("Cancelar", "/imagens/Cancel.png", "1");
        jButtonReceberGravar   = btn("Gravar",   "/imagens/OK.png",     "1");

        // --- LÓGICA DOS BOTÕES ---

        jButtonReceberNovo.addActionListener(e -> {
            jTextFieldReceberDesconto.setText("0.00");
            jTextFieldReceberAcrescimo.setText("0.00");
            jTextFieldReceberObs.setText("");
            calcularTotalRecebimento();
        });

        jButtonReceberCancelar.addActionListener(e -> {
            dispose();
        });

        jButtonReceberGravar.addActionListener(e -> {
            try {
                if (checkAtual == null) {
                    JOptionPane.showMessageDialog(null, "Grave o Check na Aba 1 primeiro!");
                    return;
                }
                
                // 1. SALVA FINANCEIRO
                model.Receber rec = new model.Receber();
                rec.setCheck(checkAtual);
                rec.setValorOriginal(Double.parseDouble(jTextFieldReceberValorOriginal.getText()));
                rec.setValorPago(Double.parseDouble(jTextFieldReceberValorPago.getText()));
                rec.setObs(jTextFieldReceberObs.getText());
                rec.setDataEmissao(new java.util.Date());
                rec.setStatus("P");
                new DAO.ReceberDAO().Create(rec);

                // 2. SALVA RESUMO EM DADOS_CHECK (NOVA TABELA)
                model.DadosCheck resumo = new model.DadosCheck();
                resumo.setCheckId(checkAtual.getId());
                resumo.setValorTotal(Double.parseDouble(jTextFieldReceberValorPago.getText()));
                resumo.setDataFinalizacao(new java.util.Date());
                resumo.setObservacaoGeral(jTextFieldReceberObs.getText());

                // Coleta nomes dos hospedes
                String hStr = "";
                for (model.CheckHospede ch : listaCheckHospedes) hStr += ch.getPessoa().getNome() + "; ";
                resumo.setHospedePrincipal(hStr);

                // Coleta números dos quartos
                String qStr = "";
                for (model.CheckQuarto cq : listaCheckQuartos) qStr += cq.getQuarto().getNumero() + " ";
                resumo.setQuartosUtilizados(qStr);

                // Coleta placas dos carros
                String pStr = "";
                for (model.AlocacaoVaga av : listaAlocacoes) pStr += av.getVeiculo().getPlaca() + " ";
                resumo.setVagasUtilizadas(pStr);

                // Salva o resumo
                new DAO.DadosCheckDAO().Create(resumo);

                JOptionPane.showMessageDialog(null, "Check finalizado e resumo salvo em Dados_Check!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao gravar: " + ex.getMessage());
            }
        });

        for (JButton b : new JButton[]{jButtonReceberNovo,jButtonReceberCancelar,jButtonReceberGravar})
            jPanelReceberBotoes.add(b);
            
        jPanelReceber.add(jPanelReceberTitulo, BorderLayout.NORTH);
        jPanelReceber.add(jPanelReceberDados,  BorderLayout.CENTER);
        jPanelReceber.add(jPanelReceberBotoes, BorderLayout.SOUTH);
        return jPanelReceber;
    }

    private JPanel titulo(String texto, Color cor) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(cor);
        p.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p.setPreferredSize(new Dimension(0, 40));
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(new Font("Times New Roman", Font.BOLD, 18));
        p.add(l, BorderLayout.CENTER);
        return p;
    }

    private void label(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setBounds(x, y, 150, 25);
        l.setFont(new Font("Arial", Font.PLAIN, 12));
        p.add(l);
    }

    private JTextField tf(JPanel p, int x, int y, int w) {
        JTextField f = new JTextField();
        f.setBounds(x, y, w, 25);
        p.add(f);
        return f;
    }

    private JFormattedTextField ftf(JPanel p, int x, int y, int w) {
        JFormattedTextField f = new JFormattedTextField();
        f.setBounds(x, y, w, 25);
        p.add(f);
        return f;
    }

    private JButton btn(String texto, String icone, String cmd) {
        JButton b = new JButton(texto);
        b.setActionCommand(cmd);
        try { b.setIcon(new ImageIcon(getClass().getResource(icone))); } catch (Exception ignored) {}
        return b;
    }

    public JTabbedPane getjTabbedPane() { return jTabbedPane; }
    public JButton getjButtonCheckSair() { return jButtonCheckSair; }
    public JTextField getjTextFieldCheckId() { return jTextFieldCheckId; }
    public JFormattedTextField getjTextFieldCheckDataHoraCadastro() { return jTextFieldCheckDataHoraCadastro; }
    public JFormattedTextField getjTextFieldCheckDataHoraEntrada() { return jTextFieldCheckDataHoraEntrada; }
    public JFormattedTextField getjTextFieldCheckDataHoraSaida() { return jTextFieldCheckDataHoraSaida; }
    public JTextField getjTextFieldCheckObs() { return jTextFieldCheckObs; }
    public JComboBox<Reserva> getjComboBoxCheckReserva() { return jComboBoxCheckReserva; }
    public JButton getjButtonCheckNovo() { return jButtonCheckNovo; }
    public JButton getjButtonCheckGravar() { return jButtonCheckGravar; }
    public JButton getjButtonCheckCancelar() { return jButtonCheckCancelar; }
    public JButton getjButtonCheckBuscar() { return jButtonCheckBuscar; }
    public JPanel getjPanelCheckDados() { return jPanelCheckDados; }
    public JPanel getjPanelCheckBotoes() { return jPanelCheckBotoes; }

    private void validarCampoData(JFormattedTextField campo) {
        campo.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JFormattedTextField ftf = (JFormattedTextField) input;
                String dataStr = ftf.getText().replace("-", "").trim();
                if (dataStr.isEmpty()) return true;
                try {
                    String[] partes = ftf.getText().split("-");
                    int ano = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int dia = Integer.parseInt(partes[2]);
                    if (ano < 2000 || ano > 2100) throw new Exception("Ano fora do limite (2000-2100)");
                    java.time.LocalDate.of(ano, mes, dia);
                    ftf.setBackground(Color.WHITE);
                    return true;
                } catch (Exception e) {
                    ftf.setBackground(new Color(255, 204, 204));
                    JOptionPane.showMessageDialog(null, "Data Inválida!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });
    }
}