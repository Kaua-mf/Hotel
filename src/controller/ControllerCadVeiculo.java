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

public class ControllerCadVeiculo implements ActionListener {

    TelaCadastroVeiculo telaCadastro;
    ServicoVeiculo servicoVeiculo = new ServicoVeiculo();
    ServicoMarca servicoMarca = new ServicoMarca();
    ServicoModelo servicoModelo = new ServicoModelo();

    public ControllerCadVeiculo(TelaCadastroVeiculo telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        // Adiciona os listeners
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        // Carrega os dados nos ComboBoxes
        carregarMarcas();
        carregarModelos();

        utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
    }

    private void carregarMarcas() {
        DefaultComboBoxModel<Marca> model = new DefaultComboBoxModel<>(servicoMarca.buscarTodos().toArray(new Marca[0]));
        this.telaCadastro.getjComboBoxMarca().setModel(model);
    }

    private void carregarModelos() {
        DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<>(servicoModelo.buscarTodos().toArray(new Modelo[0]));
        this.telaCadastro.getjComboBoxModelo().setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            if (this.telaCadastro.getjTextFieldPlaca().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Placa' é obrigatório.");
                return;
            }
            
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(this.telaCadastro.getjTextFieldPlaca().getText());
            veiculo.setCor(this.telaCadastro.getjTextFieldCor().getText());
            veiculo.setMarca((Marca) this.telaCadastro.getjComboBoxMarca().getSelectedItem());
            veiculo.setModelo((Modelo) this.telaCadastro.getjComboBoxModelo().getSelectedItem());
            veiculo.setStatus('A');
            
            servicoVeiculo.salvar(veiculo);
            JOptionPane.showMessageDialog(null, "Veículo salvo com sucesso!");
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaVeiculo telaBusca = new TelaBuscaVeiculo(null, true);
            ControllerBuscaVeiculo controllerBusca = new ControllerBuscaVeiculo(telaBusca);
            telaBusca.setVisible(true);
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}