package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Marca;
import service.ServicoMarca;
import utilities.Utilities;
import view.TelaCadastroMarca; // Importe sua tela de cadastro de Marca

public class ControllerCadMarca implements ActionListener {

    TelaCadastroMarca tela;
    ServicoMarca servicoMarca = new ServicoMarca();
    Marca marcaAtual;

    public ControllerCadMarca(TelaCadastroMarca tela) {
        this.tela = tela;
        this.marcaAtual = new Marca(); // Inicia com um objeto vazio

        // Adiciona os listeners aos botões
        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);
        
        // Configuração inicial da tela
        Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjButtonNovo()) {
            // Ação NOVO
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
            this.marcaAtual = new Marca(); // Cria um novo objeto
            this.tela.getjTextFieldId().setEnabled(false); // ID não deve ser editável

        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            // Ação CANCELAR
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), false);

        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            // Ação GRAVAR
            
            // Pega o nome do campo de texto (verifique o nome na sua tela)
            String nomeMarca = this.tela.getjTextFieldMarca().getText(); // Ajuste 'getjTextFieldMarca'
            
            if (nomeMarca.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Marca' é obrigatório.");
                return;
            }
            
            // Preenche o objeto marcaAtual
            this.marcaAtual.setDescricao(nomeMarca);
            this.marcaAtual.setStatus('A'); // Define como Ativo
            
            try {
                servicoMarca.salvar(this.marcaAtual);
                JOptionPane.showMessageDialog(null, "Marca salva com sucesso!");
                
                // Volta ao estado inicial
                Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == this.tela.getjButtonBuscar()) {
            // Ação BUSCAR (implementação futura)
            // Aqui você chamaria a TelaBuscaMarca e seu respectivo controller
            JOptionPane.showMessageDialog(null, "Tela de Busca ainda não implementada.");
            
        } else if (e.getSource() == this.tela.getjButtonSair()) {
            // Ação SAIR
            this.tela.dispose();
        }
    }
}