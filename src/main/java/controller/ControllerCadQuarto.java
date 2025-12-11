package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField; 
import javax.swing.JComboBox; 
import javax.swing.JCheckBox; 
import model.Quarto;
import service.ServicoQuarto;
import view.TelaBuscaQuarto;
import utilities.Utilities; 
import view.TelaCadastroQuarto; 

public class ControllerCadQuarto implements ActionListener {

    TelaCadastroQuarto telaCadastro;
    Quarto quartoAtual; 
    
    ServicoQuarto servicoQuarto = new ServicoQuarto();
    
    private static final String[] IDENTIFICACOES = {"Selecione o Tipo", "Simples", "Duplo", "Luxo", "Presidencial"};

   public ControllerCadQuarto(TelaCadastroQuarto telaCadastro, Quarto quarto) { 
        this.telaCadastro = telaCadastro;
        this.quartoAtual = quarto; 
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        carregarIdentificacoes(); 

        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); 
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
        
        Object idField = this.telaCadastro.getjTextFieldId();
        if (idField != null) {
             ((JTextField) idField).setEnabled(false); 
        }
    }

    private void carregarIdentificacoes() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(IDENTIFICACOES);
        
        Object comboBox = this.telaCadastro.getjComboBoxTipoQuarto();
        if (comboBox instanceof JComboBox) {
            ((JComboBox) comboBox).setModel(model); 
        }
    }

    private void carregarDadosNaTela() {
        if (this.quartoAtual != null) {
            
            // ID
            Object idField = this.telaCadastro.getjTextFieldId();
            if (idField != null) {
                ((JTextField) idField).setText(String.valueOf(this.quartoAtual.getId()));
            }

            this.telaCadastro.getjTextFieldDescricao().setText(this.quartoAtual.getDescricao());
            this.telaCadastro.getjTextFieldAndar().setText(String.valueOf(this.quartoAtual.getAndar()));
            this.telaCadastro.getjTextFieldMetragem().setText(String.valueOf(this.quartoAtual.getMetragem()));
            this.telaCadastro.getjTextFieldCapacidade().setText(String.valueOf(this.quartoAtual.getCapacidadeHospedes()));
            this.telaCadastro.getjTextFieldObs().setText(this.quartoAtual.getObs());
            this.telaCadastro.getjTextFieldValorDiaria().setText(String.valueOf(this.quartoAtual.getValorDiaria()));
            
            Object comboBox = this.telaCadastro.getjComboBoxTipoQuarto();
            if (comboBox instanceof JComboBox) {
                ((JComboBox) comboBox).setSelectedItem(this.quartoAtual.getIdentificacao());
            }
            
            Object checkAnimais = this.telaCadastro.getjCheckBoxFlagAnimais();
            if (checkAnimais instanceof JCheckBox) {
                if (this.quartoAtual.getFlagAnimais() == '1') {
                    ((JCheckBox) checkAnimais).setSelected(true); 
                } else {
                    ((JCheckBox) checkAnimais).setSelected(false);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false); 
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true); 
            this.quartoAtual = new Quarto();
            Object idField = this.telaCadastro.getjTextFieldId();
            if (idField != null) { ((JTextField) idField).setEnabled(false); }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); 
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
            this.quartoAtual = new Quarto();
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            String identificacaoSelecionada = (String) this.telaCadastro.getjComboBoxTipoQuarto().getSelectedItem();
            
            if (this.telaCadastro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório.");
                return;
            }
            if (identificacaoSelecionada == null || identificacaoSelecionada.equals(IDENTIFICACOES[0])) {
                JOptionPane.showMessageDialog(null, "Selecione a Identificação (Tipo) do Quarto.");
                return;
            }
            
            float valorDiaria;
            int capacidadeHospedes;
            float metragem;
            int andar;
            
            try { valorDiaria = Float.parseFloat(this.telaCadastro.getjTextFieldValorDiaria().getText().replace(',', '.')); } 
            catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "O Valor Diária é inválido."); return; }
            
            try { capacidadeHospedes = Integer.parseInt(this.telaCadastro.getjTextFieldCapacidade().getText()); }
            catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "A Capacidade é inválida."); return; }
            
            try { metragem = Float.parseFloat(this.telaCadastro.getjTextFieldMetragem().getText().replace(',', '.')); }
            catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "A Metragem é inválida."); return; }
            
            try { andar = Integer.parseInt(this.telaCadastro.getjTextFieldAndar().getText()); }
            catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "O Andar é inválido."); return; }

            this.quartoAtual.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
            this.quartoAtual.setCapacidadeHospedes(capacidadeHospedes);
            this.quartoAtual.setMetragem(metragem);
            this.quartoAtual.setIdentificacao(identificacaoSelecionada);
            this.quartoAtual.setAndar(andar);
            
            Object checkAnimais = this.telaCadastro.getjCheckBoxFlagAnimais();
            if (checkAnimais instanceof JCheckBox) {
                 this.quartoAtual.setFlagAnimais(((JCheckBox) checkAnimais).isSelected() ? '1' : '0');
            } else {
                 this.quartoAtual.setFlagAnimais('0');
            }
            
            this.quartoAtual.setObs(this.telaCadastro.getjTextFieldObs().getText());
            this.quartoAtual.setStatus('L'); 
            this.quartoAtual.setValorDiaria(valorDiaria); 
            
            try {
                boolean isNovoRegistro = this.quartoAtual.getId() == 0;
                servicoQuarto.salvar(this.quartoAtual);
                
                JOptionPane.showMessageDialog(null, isNovoRegistro ? "Quarto cadastrado com sucesso!" : "Quarto atualizado com sucesso!");
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                this.quartoAtual = new Quarto();
                
            } catch (RuntimeException e) {
                 JOptionPane.showMessageDialog(null, 
                         "Erro ao salvar no banco: " + e.getMessage(), 
                         "ERRO DE PERSISTÊNCIA", 
                         JOptionPane.ERROR_MESSAGE);
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            
            TelaBuscaQuarto telaBusca = new TelaBuscaQuarto(null, true);
            ControllerBuscaQuarto controllerBusca = new ControllerBuscaQuarto(telaBusca); 
            telaBusca.setVisible(true);
            
            if (ControllerBuscaQuarto.codigoSelecionado != 0) {
                this.quartoAtual = servicoQuarto.buscarPorId(ControllerBuscaQuarto.codigoSelecionado);
                
                if (this.quartoAtual != null) {
                    Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    
                    carregarDadosNaTela(); 
                    
                    ControllerBuscaQuarto.codigoSelecionado = 0;
                }
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}