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
import view.TelaBuscaModelo; // ***** IMPORTE A TELA DE BUSCA *****
import view.TelaCadastroModelo; 

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
            e.printStackTrace(); // Imprime erro no console
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
            this.tela.getjTextFieldModelo().requestFocus(); // Foco no campo Modelo

        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            // Ação CANCELAR
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
            this.modeloAtual = new Modelo(); // Limpa objeto atual

        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            // Ação GRAVAR
            
            String nomeModelo = this.tela.getjTextFieldModelo().getText(); 
            Marca marcaSelecionada = (Marca) this.tela.getjComboBoxMarca().getSelectedItem();
            
            // Validações
            if (nomeModelo == null || nomeModelo.trim().isEmpty()) { 
                JOptionPane.showMessageDialog(null, "O campo 'Modelo' é obrigatório.");
                this.tela.getjTextFieldModelo().requestFocus();
                return;
            }
            if (marcaSelecionada == null || marcaSelecionada.getId() == 0) { 
                JOptionPane.showMessageDialog(null, "Selecione uma 'Marca' válida.");
                return;
            }
            
            // Preenche o objeto (Usando setNome)
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
            // ***** AÇÃO BUSCAR SIMPLIFICADA (SEM CARREGAR DADOS) *****
            
            // 1. Cria a Tela de Busca de Modelo
            TelaBuscaModelo telaBusca = new TelaBuscaModelo(null, true);
            
            // 2. Cria o Controller da Tela de Busca (Assume que você o criou)
            ControllerBuscaModelo controllerBusca = new ControllerBuscaModelo(telaBusca); 
            
            // 3. Exibe a Tela de Busca
            telaBusca.setVisible(true);
            
            // 4. NÃO FAZ NADA com o resultado selecionado.
            //    (O código que usava getCodigoSelecionado() foi removido)
            
        } else if (e.getSource() == this.tela.getjButtonSair()) {
            // Ação SAIR
            this.tela.dispose();
        }
    }
}