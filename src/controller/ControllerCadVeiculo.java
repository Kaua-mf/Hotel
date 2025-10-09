package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Marca;
import model.Modelo;
import model.Veiculo;
import service.ServicoMarca;
import service.ServicoModelo;
import service.ServicoVeiculo;
import view.TelaBuscaVeiculo;
import view.TelaCadastroVeiculo;
import utilities.Utilities; 

public class ControllerCadVeiculo implements ActionListener {

    TelaCadastroVeiculo telaCadastro;
    Veiculo veiculoAtual; 
    
    ServicoVeiculo servicoVeiculo = new ServicoVeiculo();
    ServicoMarca servicoMarca = new ServicoMarca();
    ServicoModelo servicoModelo = new ServicoModelo();

    public ControllerCadVeiculo(view.TelaCadastroVeiculo telaCadastro, Veiculo veiculo) {
        this.telaCadastro = telaCadastro;
        this.veiculoAtual = veiculo; 
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        carregarMarcas();
        carregarModelos();

        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); 
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
    }

    private void carregarMarcas() {
        try {
            DefaultComboBoxModel<Marca> model = new DefaultComboBoxModel<>(servicoMarca.buscarTodos().toArray(new Marca[0]));
            this.telaCadastro.getjComboBoxMarca().setModel(model);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Marcas: " + e.getMessage(), "Erro de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarModelos() {
        try {
            DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<>(servicoModelo.buscarTodos().toArray(new Modelo[0]));
            this.telaCadastro.getjComboBoxModelo().setModel(model);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Modelos: " + e.getMessage(), "Erro de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false); 
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true); 
            
            this.veiculoAtual = new Veiculo();
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); 
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            Marca marcaSelecionada = (Marca) this.telaCadastro.getjComboBoxMarca().getSelectedItem();
            Modelo modeloSelecionado = (Modelo) this.telaCadastro.getjComboBoxModelo().getSelectedItem();
            
            if (this.telaCadastro.getjTextFieldPlaca().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Placa' é obrigatório.");
                return;
            }
            
            if (marcaSelecionada == null || marcaSelecionada.getId() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione uma Marca válida.");
                return;
            }
            if (modeloSelecionado == null || modeloSelecionado.getId() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um Modelo válido.");
                return;
            }
            
            this.veiculoAtual.setPlaca(this.telaCadastro.getjTextFieldPlaca().getText());
            this.veiculoAtual.setCor(this.telaCadastro.getjTextFieldCor().getText());
            this.veiculoAtual.setMarca(marcaSelecionada);
            this.veiculoAtual.setModelo(modeloSelecionado);
            this.veiculoAtual.setStatus('A');
            this.veiculoAtual.setFuncionario(null);
            this.veiculoAtual.setFornecedor(null);
            this.veiculoAtual.setHospede(null);

            try {
                servicoVeiculo.salvar(this.veiculoAtual);
                
                JOptionPane.showMessageDialog(null, "Veículo salvo com sucesso!");
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                
            } catch (RuntimeException e) {
                 JOptionPane.showMessageDialog(null, 
                    "Erro ao salvar no banco: " + e.getMessage(), 
                    "ERRO DE PERSISTÊNCIA", 
                    JOptionPane.ERROR_MESSAGE);
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaVeiculo telaBusca = new TelaBuscaVeiculo(null, true);
            ControllerBuscaVeiculo controllerBusca = new ControllerBuscaVeiculo(telaBusca); 
            telaBusca.setVisible(true);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}