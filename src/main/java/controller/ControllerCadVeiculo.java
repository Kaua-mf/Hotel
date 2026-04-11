package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Modelo;
import model.Veiculo;
import service.ServicoModelo;
import service.ServicoVeiculo;
import utilities.Utilities; 
import view.TelaBuscaVeiculo;
import view.TelaCadastroVeiculo; 

public class ControllerCadVeiculo implements ActionListener {

    TelaCadastroVeiculo telaCadastro;
    Veiculo veiculoAtual; 
    
    ServicoVeiculo servicoVeiculo = new ServicoVeiculo();
    ServicoModelo servicoModelo = new ServicoModelo();

   public ControllerCadVeiculo(TelaCadastroVeiculo telaCadastro, Veiculo veiculo) { 
        this.telaCadastro = telaCadastro;
        this.veiculoAtual = (veiculo != null) ? veiculo : new Veiculo();
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        carregarModelos(); 

        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); 
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
        
        if (this.telaCadastro.getjTextFieldId() != null) {
             this.telaCadastro.getjTextFieldId().setEnabled(false); 
        }
    }

    private void carregarModelos() {
        try {
            List<Modelo> modelos = servicoModelo.buscarTodos();
            
            Modelo placeholder = new Modelo(); 
            placeholder.setId(0); 
            placeholder.setNome("Selecione um Modelo..."); 
            modelos.add(0, placeholder); 
            
            DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<>(modelos.toArray(new Modelo[0]));
            this.telaCadastro.getjComboBoxModelo().setModel(model); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Modelos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDadosNaTela() {
        if (this.veiculoAtual != null) {
            this.telaCadastro.getjTextFieldId().setText(String.valueOf(this.veiculoAtual.getId()));
            this.telaCadastro.getjFormattedTextFieldPlaca().setText(this.veiculoAtual.getPlaca());
            this.telaCadastro.getjTextFieldCor().setText(this.veiculoAtual.getCor());
            
            DefaultComboBoxModel<Modelo> model = (DefaultComboBoxModel<Modelo>) this.telaCadastro.getjComboBoxModelo().getModel();
            
            for (int i = 0; i < model.getSize(); i++) {
                Modelo itemCombo = model.getElementAt(i);
                if (this.veiculoAtual.getModelo() != null && itemCombo.getId() == this.veiculoAtual.getModelo().getId()) {
                    this.telaCadastro.getjComboBoxModelo().setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false); 
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true); 
            
            this.veiculoAtual = new Veiculo();
            if (this.telaCadastro.getjTextFieldId() != null) {
                 this.telaCadastro.getjTextFieldId().setEnabled(false);
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); 
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
            this.veiculoAtual = new Veiculo();
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            Modelo modeloSelecionado = (Modelo) this.telaCadastro.getjComboBoxModelo().getSelectedItem();
            
            if (this.telaCadastro.getjFormattedTextFieldPlaca().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Placa' é obrigatório.");
                return;
            }
            
            if (modeloSelecionado == null || modeloSelecionado.getId() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um Modelo válido.");
                return;
            }
            
            this.veiculoAtual.setPlaca(this.telaCadastro.getjFormattedTextFieldPlaca().getText());
            this.veiculoAtual.setCor(this.telaCadastro.getjTextFieldCor().getText());
            this.veiculoAtual.setModelo(modeloSelecionado);
            this.veiculoAtual.setStatus('A');
            
            // Definindo proprietários como null conforme lógica original
            this.veiculoAtual.setFuncionario(null); 
            this.veiculoAtual.setFornecedor(null);
            this.veiculoAtual.setHospede(null); 

            try {
                boolean isNovoRegistro = this.veiculoAtual.getId() == 0;
                
                servicoVeiculo.salvar(this.veiculoAtual);
                
                String mensagem = isNovoRegistro ? "Veículo cadastrado com sucesso!" : "Veículo atualizado com sucesso!";
                JOptionPane.showMessageDialog(null, mensagem);
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                this.veiculoAtual = new Veiculo();
                
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace();
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            
            TelaBuscaVeiculo telaBusca = new TelaBuscaVeiculo(null, true);
            ControllerBuscaVeiculo controllerBusca = new ControllerBuscaVeiculo(telaBusca); 
            telaBusca.setVisible(true);
            
            if (ControllerBuscaVeiculo.codigoSelecionado != 0) {
                this.veiculoAtual = servicoVeiculo.buscarPorId(ControllerBuscaVeiculo.codigoSelecionado);
                
                if (this.veiculoAtual != null) {
                    Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    
                    carregarDadosNaTela(); 
                    
                    ControllerBuscaVeiculo.codigoSelecionado = 0;
                }
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}