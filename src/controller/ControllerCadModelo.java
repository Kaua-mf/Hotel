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
import view.TelaCadastroModelo; // Importe sua tela de cadastro de Modelo

public class ControllerCadModelo implements ActionListener {

    TelaCadastroModelo tela;
    ServicoModelo servicoModelo = new ServicoModelo();
    ServicoMarca servicoMarca = new ServicoMarca(); // Para carregar o ComboBox
    Modelo modeloAtual;

    public ControllerCadModelo(TelaCadastroModelo tela) {
        this.tela = tela;
        this.modeloAtual = new Modelo();

        // Adiciona os listeners aos botões
        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);
        
        // Carrega as marcas no ComboBox
        carregarMarcasNoComboBox();

        // Configuração inicial da tela
        Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
    }

    private void carregarMarcasNoComboBox() {
        try {
            List<Marca> listaMarcas = servicoMarca.buscarTodos();
            
            // Cria um modelo de ComboBox com a lista de marcas
            DefaultComboBoxModel<Marca> comboBoxModel = new DefaultComboBoxModel<>(
                listaMarcas.toArray(new Marca[0])
            );
            
            // Ajuste o nome 'getjComboBoxMarca' se for diferente na sua tela
            this.tela.getjComboBoxMarca().setModel(comboBoxModel); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar marcas: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjButtonNovo()) {
            // Ação NOVO
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
            this.modeloAtual = new Modelo();
            this.tela.getjTextFieldId().setEnabled(false);

        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            // Ação CANCELAR
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), false);

        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            // Ação GRAVAR
            
            // Pega os dados da tela (ajuste os nomes dos 'get')
            String nomeModelo = this.tela.getjTextFieldModelo().getText(); // Da imagem image_81e957.png
            Marca marcaSelecionada = (Marca) this.tela.getjComboBoxMarca().getSelectedItem();
            
            // Validações
            if (nomeModelo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Modelo' é obrigatório.");
                return;
            }
            if (marcaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma 'Marca' válida.");
                return;
            }
            
            // Preenche o objeto
            this.modeloAtual.setDescricao(nomeModelo);
            this.modeloAtual.setMarca(marcaSelecionada); // Associa a marca
            this.modeloAtual.setStatus('A');
            
            try {
                servicoModelo.salvar(this.modeloAtual);
                JOptionPane.showMessageDialog(null, "Modelo salvo com sucesso!");
                
                Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == this.tela.getjButtonBuscar()) {
            // Ação BUSCAR (implementação futura)
            JOptionPane.showMessageDialog(null, "Tela de Busca ainda não implementada.");
            
        } else if (e.getSource() == this.tela.getjButtonSair()) {
            // Ação SAIR
            this.tela.dispose();
        }
    }
}