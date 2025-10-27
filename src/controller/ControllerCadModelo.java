package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Marca;
import model.Modelo;
import service.ServicoMarca;
import service.ServicoModelo;
import utilities.Utilities;
import view.TelaBuscaModelo;
import view.TelaCadastroModelo; 

public class ControllerCadModelo implements ActionListener {

    TelaCadastroModelo tela;
    ServicoModelo servicoModelo = new ServicoModelo();
    ServicoMarca servicoMarca = new ServicoMarca(); 
    Modelo modeloAtual;

    public ControllerCadModelo(TelaCadastroModelo tela) {
        this.tela = tela;
        this.modeloAtual = new Modelo();

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);
        
        carregarMarcasNoComboBox();

        Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
    }

    private void carregarMarcasNoComboBox() {
        try {
            List<Marca> listaMarcas = servicoMarca.buscarTodos();
            
            DefaultComboBoxModel<Marca> comboBoxModel = new DefaultComboBoxModel<>(
                listaMarcas.toArray(new Marca[0])
            );
            
            this.tela.getjComboBoxMarca().setModel(comboBoxModel); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar marcas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjButtonNovo()) {
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
            this.modeloAtual = new Modelo();
            this.tela.getjTextFieldId().setEnabled(false);
            this.tela.getjTextFieldModelo().requestFocus(); // Foco no campo Modelo

        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
            this.modeloAtual = new Modelo(); // Limpa objeto atual

        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            
            String nomeModelo = this.tela.getjTextFieldModelo().getText(); 
            Marca marcaSelecionada = (Marca) this.tela.getjComboBoxMarca().getSelectedItem();
            
            if (nomeModelo == null || nomeModelo.trim().isEmpty()) { 
                JOptionPane.showMessageDialog(null, "O campo 'Modelo' é obrigatório.");
                this.tela.getjTextFieldModelo().requestFocus();
                return;
            }
            if (marcaSelecionada == null || marcaSelecionada.getId() == 0) { 
                JOptionPane.showMessageDialog(null, "Selecione uma 'Marca' válida.");
                return;
            }
            
            this.modeloAtual.setNome(nomeModelo); 
            this.modeloAtual.setMarca(marcaSelecionada); 
            this.modeloAtual.setStatus('A');
            
            System.out.println("DEBUG: Tentando salvar Modelo com nome: [" + this.modeloAtual.getNome() + "]"); 
            
            try {
                if (this.modeloAtual.getId() > 0) {
                     servicoModelo.atualizar(this.modeloAtual);
                     JOptionPane.showMessageDialog(null, "Modelo atualizado com sucesso!");
                } else {
                     servicoModelo.salvar(this.modeloAtual);
                     JOptionPane.showMessageDialog(null, "Modelo salvo com sucesso!");
                }
                
                Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
                this.modeloAtual = new Modelo(); 
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar/atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); 
            }

        } else if (e.getSource() == this.tela.getjButtonBuscar()) {
            
            TelaBuscaModelo telaBusca = new TelaBuscaModelo(null, true);
            
            ControllerBuscaModelo controllerBusca = new ControllerBuscaModelo(telaBusca); 
            
            telaBusca.setVisible(true);
            
        } else if (e.getSource() == this.tela.getjButtonSair()) {
            this.tela.dispose();
        }
    }
}