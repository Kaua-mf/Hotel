package view;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class TelaCadastroReserva extends javax.swing.JDialog {

    private JPanel jPanelTitulo, jPanelDados, jPanelBotoes;
    private JTextField jTextFieldId, jTextFieldObs, jTextFieldStatus;
    private JFormattedTextField jftfDtReserva, jftfDtEntrada, jftfDtSaida;
    private JButton jButtonNovo, jButtonCancelar, jButtonGravar, jButtonSair;

    public TelaCadastroReserva(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        habilitarCampos(false); 
        atualizarDataReserva();
    }

    private void initComponents() {
        setTitle("Cadastro de Reserva");
        setSize(600, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(5, 5));

        // --- PAINEL TÍTULO ---
        jPanelTitulo = new JPanel(new BorderLayout());
        jPanelTitulo.setBackground(new Color(153, 153, 255));
        jPanelTitulo.setPreferredSize(new Dimension(0, 60));
        jPanelTitulo.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        JLabel lblTitulo = new JLabel("Reserva", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jPanelTitulo.add(lblTitulo, BorderLayout.CENTER);

        // --- PAINEL DADOS ---
        jPanelDados = new JPanel(null);
        jPanelDados.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        int y = 20;
        label(jPanelDados, "ID", 20, y);
        label(jPanelDados, "Data/Hora Reserva:", 250, y);
        
        y += 25;
        jTextFieldId = tf(jPanelDados, 20, y, 60);
        jTextFieldId.setEnabled(false);
        
        jftfDtReserva = ftf(jPanelDados, "####-##-## ##:##:##", 250, y, 200);
        jftfDtReserva.setEditable(false);
        jftfDtReserva.setFocusable(false);

        y += 40;
        label(jPanelDados, "Data Prevista Entrada:", 20, y);
        label(jPanelDados, "Data Prevista Saída:", 250, y);
        
        y += 25;
        jftfDtEntrada = ftf(jPanelDados, "####-##-##", 20, y, 180);
        jftfDtSaida = ftf(jPanelDados, "####-##-##", 250, y, 180);

        y += 40;
        label(jPanelDados, "Status:", 20, y);
        label(jPanelDados, "Observação:", 110, y);
        
        y += 25;
        jTextFieldStatus = tf(jPanelDados, 20, y, 70);
        jTextFieldObs = tf(jPanelDados, 110, y, 440);

        // --- PAINEL BOTÕES ---
        jPanelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jPanelBotoes.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonNovo     = btn("Novo",     "/imagens/Create.png");
        jButtonCancelar = btn("Cancelar", "/imagens/Cancel.png");
        jButtonGravar   = btn("Gravar",   "/imagens/OK.png");
        jButtonSair     = btn("Sair",     "/imagens/Exit.png");

        // --- LÓGICA DOS BOTÕES ---

        jButtonNovo.addActionListener(e -> {
            habilitarCampos(true); 
            limparCampos();
            atualizarDataReserva();
            jButtonNovo.setEnabled(false);
        });

        jButtonCancelar.addActionListener(e -> {
            habilitarCampos(false); 
            limparCampos();
            jButtonNovo.setEnabled(true);
        });

        jButtonSair.addActionListener(e -> dispose());

        jButtonGravar.addActionListener(e -> {
            try {
                // Validação de data básica para evitar erro de Data Truncation
                String entradaLimpa = jftfDtEntrada.getText().replace("-", "").trim();
                String saidaLimpa = jftfDtSaida.getText().replace("-", "").trim();

                if (entradaLimpa.isEmpty() || saidaLimpa.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha as datas de entrada e saída corretamente!");
                    return;
                }

                model.Reserva reserva = new model.Reserva();
                
                reserva.setObs(jTextFieldObs.getText());
                
                String statusTxt = jTextFieldStatus.getText();
                reserva.setStatus(!statusTxt.isEmpty() ? statusTxt : "A");

                // Sincronizado com o Model CamelCase
                reserva.setDataHoraReserva(jftfDtReserva.getText());
                reserva.setDataPrevistaEntrada(jftfDtEntrada.getText());
                reserva.setDataPrevistaSaida(jftfDtSaida.getText());

                // Salvar no Banco
                new DAO.ReservaDAO().Create(reserva);

                JOptionPane.showMessageDialog(null, "Reserva gravada com sucesso!");
                
                limparCampos();
                habilitarCampos(false);
                jButtonNovo.setEnabled(true);

            } catch (Exception ex) {
                // Caso o MySQL ainda reclame de data errada (como mês 22)
                if (ex.getMessage().contains("Data truncation")) {
                    JOptionPane.showMessageDialog(null, "Erro: Verifique se os números das datas estão corretos (Mês até 12, Dias até 31)");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao gravar: " + ex.getMessage());
                }
            }
        });

        jPanelBotoes.add(jButtonNovo);
        jPanelBotoes.add(jButtonCancelar);
        jPanelBotoes.add(jButtonGravar);
        jPanelBotoes.add(jButtonSair);

        add(jPanelTitulo, BorderLayout.NORTH);
        add(jPanelDados, BorderLayout.CENTER);
        add(jPanelBotoes, BorderLayout.SOUTH);
    }

    private void habilitarCampos(boolean estado) {
        jftfDtEntrada.setEnabled(estado);
        jftfDtSaida.setEnabled(estado);
        jTextFieldStatus.setEnabled(estado);
        jTextFieldObs.setEnabled(estado);
        jButtonGravar.setEnabled(estado);
        jButtonCancelar.setEnabled(estado);
    }

    private void limparCampos() {
        jTextFieldId.setText("");
        jTextFieldObs.setText("");
        jTextFieldStatus.setText("A");
        jftfDtEntrada.setText("");
        jftfDtSaida.setText("");
    }

    private void atualizarDataReserva() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jftfDtReserva.setText(sdf.format(new Date()));
    }

    private void label(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setBounds(x, y, 150, 20);
        l.setFont(new Font("Arial", Font.PLAIN, 12));
        p.add(l);
    }

    private JTextField tf(JPanel p, int x, int y, int w) {
        JTextField f = new JTextField();
        f.setBounds(x, y, w, 25);
        p.add(f);
        return f;
    }

    private JFormattedTextField ftf(JPanel p, String m, int x, int y, int w) {
        try {
            MaskFormatter mask = new MaskFormatter(m);
            mask.setPlaceholderCharacter('_');
            JFormattedTextField f = new JFormattedTextField(mask);
            f.setBounds(x, y, w, 25);
            p.add(f);
            return f;
        } catch (Exception e) { return new JFormattedTextField(); }
    }

    private JButton btn(String t, String i) {
        JButton b = new JButton(t);
        try { b.setIcon(new ImageIcon(getClass().getResource(i))); } catch (Exception e) {}
        return b;
    }
}