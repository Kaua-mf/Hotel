package view;

import DAO.ReservaDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

    private JPanel jPanelCheckHospede, jPanelCheckHospedeTitulo, jPanelCheckHospedeDados;
    private JPanel jPanelCheckHospedeBotoes, jPanelCheckHospedeTabela;
    private JComboBox<String> jComboBoxTipoPessoa;
    private JComboBox<Pessoa> jComboBoxPessoa;
    private JTextField jTextFieldCheckHospedeObs;
    private JButton jButtonCheckHospedeAdicionar, jButtonCheckHospedeRemover, jButtonCheckHospedeGravar;
    private DefaultListModel<String> listModelHospedes;
    private JList<String> jListHospedes;
    private List<CheckHospede> listaCheckHospedes;

    private JPanel jPanelCheckQuarto, jPanelCheckQuartoTitulo, jPanelCheckQuartoDados;
    private JPanel jPanelCheckQuartoBotoes, jPanelCheckQuartoTabela;
    private JComboBox<Quarto> jComboBoxQuarto;
    private JFormattedTextField jTextFieldCheckQuartoDataHoraInicio, jTextFieldCheckQuartoDataHoraFim;
    private JTextField jTextFieldCheckQuartoObs;
    private JButton jButtonCheckQuartoAdicionar, jButtonCheckQuartoRemover, jButtonCheckQuartoGravar;
    private DefaultListModel<String> listModelQuartos;
    private JList<String> jListQuartos;
    private List<CheckQuarto> listaCheckQuartos;

    private JPanel jPanelAlocacaoVaga, jPanelAlocacaoVagaTitulo, jPanelAlocacaoVagaDados;
    private JPanel jPanelAlocacaoVagaBotoes, jPanelAlocacaoVagaTabela;
    private JComboBox<Veiculo> jComboBoxVeiculo;
    private JComboBox<VagaEstacionamento> jComboBoxVagaEstacionamento;
    private JTextField jTextFieldAlocacaoVagaObs;
    private JButton jButtonAlocacaoVagaAdicionar, jButtonAlocacaoVagaRemover, jButtonAlocacaoVagaGravar;
    private DefaultListModel<String> listModelAlocacoes;
    private JList<String> jListAlocacoes;
    private List<AlocacaoVaga> listaAlocacoes;

    private JPanel jPanelReceber, jPanelReceberTitulo, jPanelReceberDados, jPanelReceberBotoes;
    private JTextField jTextFieldReceberId, jTextFieldReceberValorOriginal, jTextFieldReceberDesconto;
    private JTextField jTextFieldReceberAcrescimo, jTextFieldReceberValorPago, jTextFieldReceberObs;
    private JFormattedTextField jTextFieldReceberDataHoraCadastro;
    private JButton jButtonReceberNovo, jButtonReceberGravar, jButtonReceberCancelar;

    private JTabbedPane jTabbedPane;

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
            Check check = new Check();
            check.setDataHoraEntrada(jTextFieldCheckDataHoraEntrada.getText());
            check.setDataHoraSaida(jTextFieldCheckDataHoraSaida.getText());
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

            JOptionPane.showMessageDialog(null, "Check-in gravado com sucesso!");

            jTabbedPane.setEnabledAt(1, true);
            jTabbedPane.setSelectedIndex(1);
            
            ativaBotoes(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar: " + e.getMessage());
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
    jComboBoxTipoPessoa = new JComboBox<>(new String[]{"Hospede", "Funcionario", "Fornecedor"});
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
                    jComboBoxPessoa.setSelectedItem(busca.getHospedeSelecionado());
                    jTextFieldCheckHospedeObs.setText(busca.getHospedeSelecionado().getObs());
                }
            } else if ("Funcionario".equals(tipo)) {
                TelaBuscaFuncionario busca = new TelaBuscaFuncionario(null, true);
                busca.setVisible(true);
                if (busca.getFuncionarioSelecionado() != null) {
                    jComboBoxPessoa.setSelectedItem(busca.getFuncionarioSelecionado());
                    jTextFieldCheckHospedeObs.setText(busca.getFuncionarioSelecionado().getObs());
                }
            } else if ("Fornecedor".equals(tipo)) {
                TelaBuscaFornecedor busca = new TelaBuscaFornecedor(null, true);
                busca.setVisible(true);
                if (busca.getFornecedorSelecionado() != null) {
                    jComboBoxPessoa.setSelectedItem(busca.getFornecedorSelecionado());
                    jTextFieldCheckHospedeObs.setText(busca.getFornecedorSelecionado().getObs());
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
    
    jPanelCheckHospedeBotoes.add(jButtonCheckHospedeAdicionar);
    jPanelCheckHospedeBotoes.add(jButtonCheckHospedeRemover);
    jPanelCheckHospedeBotoes.add(jButtonCheckHospedeGravar);

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
        jButtonCheckQuartoAdicionar = btn("Adicionar",    "/imagens/Create.png", "0");
        jButtonCheckQuartoRemover   = btn("Remover",      "/imagens/Cancel.png", "0");
        jButtonCheckQuartoGravar    = btn("Gravar Todos", "/imagens/OK.png",     "0");
        for (JButton b : new JButton[]{jButtonCheckQuartoAdicionar,jButtonCheckQuartoRemover,jButtonCheckQuartoGravar})
            jPanelCheckQuartoBotoes.add(b);

        jPanelCheckQuarto.add(jPanelCheckQuartoTitulo, BorderLayout.NORTH);
        jPanelCheckQuarto.add(split,                   BorderLayout.CENTER);
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
        jPanelAlocacaoVagaDados.setPreferredSize(new Dimension(840, 130));
        int y = 20;
        label(jPanelAlocacaoVagaDados, "Veiculo:", 20, y);
        jComboBoxVeiculo = new JComboBox<>();
        jComboBoxVeiculo.setBounds(170, y, 380, 25);
        jPanelAlocacaoVagaDados.add(jComboBoxVeiculo);
        y += 35; label(jPanelAlocacaoVagaDados, "Vaga:", 20, y);
        jComboBoxVagaEstacionamento = new JComboBox<>();
        jComboBoxVagaEstacionamento.setBounds(170, y, 380, 25);
        jPanelAlocacaoVagaDados.add(jComboBoxVagaEstacionamento);
        y += 35; label(jPanelAlocacaoVagaDados, "Obs:", 20, y);
        jTextFieldAlocacaoVagaObs = tf(jPanelAlocacaoVagaDados, 170, y, 560);
        jPanelAlocacaoVagaTabela = new JPanel(new BorderLayout());
        jPanelAlocacaoVagaTabela.setBorder(new TitledBorder("Alocacoes adicionadas ao Check"));
        listModelAlocacoes = new DefaultListModel<>();
        jListAlocacoes = new JList<>(listModelAlocacoes);
        jListAlocacoes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        jPanelAlocacaoVagaTabela.add(new JScrollPane(jListAlocacoes), BorderLayout.CENTER);
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jPanelAlocacaoVagaDados, jPanelAlocacaoVagaTabela);
        split.setResizeWeight(0.35); split.setDividerSize(5);
        jPanelAlocacaoVagaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jPanelAlocacaoVagaBotoes.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonAlocacaoVagaAdicionar = btn("Adicionar",    "/imagens/Create.png", "0");
        jButtonAlocacaoVagaRemover   = btn("Remover",      "/imagens/Cancel.png", "0");
        jButtonAlocacaoVagaGravar    = btn("Gravar Todos", "/imagens/OK.png",     "0");
        for (JButton b : new JButton[]{jButtonAlocacaoVagaAdicionar,jButtonAlocacaoVagaRemover,jButtonAlocacaoVagaGravar})
            jPanelAlocacaoVagaBotoes.add(b);
        jPanelAlocacaoVaga.add(jPanelAlocacaoVagaTitulo, BorderLayout.NORTH);
        jPanelAlocacaoVaga.add(split,                    BorderLayout.CENTER);
        jPanelAlocacaoVaga.add(jPanelAlocacaoVagaBotoes, BorderLayout.SOUTH);
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
        jPanelReceberBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jPanelReceberBotoes.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonReceberNovo     = btn("Novo",     "/imagens/Create.png", "0");
        jButtonReceberCancelar = btn("Cancelar", "/imagens/Cancel.png", "1");
        jButtonReceberGravar   = btn("Gravar",   "/imagens/OK.png",     "1");
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