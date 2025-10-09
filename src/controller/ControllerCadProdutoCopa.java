package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ProdutoCopa;
import service.ServicoProdutoCopa;
import view.TelaBuscaProdutoCopa;
import view.TelaCadastroProdutoCopa;
import utilities.Utilities;

public class ControllerCadProdutoCopa implements ActionListener {

    TelaCadastroProdutoCopa telaCadastro;
    ServicoProdutoCopa servicoProdutoCopa = new ServicoProdutoCopa();
    ProdutoCopa produtoAtual = new ProdutoCopa(); 

    public ControllerCadProdutoCopa(TelaCadastroProdutoCopa telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.produtoAtual = new ProdutoCopa(); // Novo objeto
        
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
        
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.telaCadastro, "O campo 'Descrição' é obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                String valorTexto = this.telaCadastro.getjFormattedTextFieldValor().getText();
                
                if (valorTexto.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this.telaCadastro, "O campo 'Valor' é obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                float valor = Float.parseFloat(valorTexto.replace(",", "."));

                this.produtoAtual.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                this.produtoAtual.setValor(valor);
                
                this.produtoAtual.setObs(this.telaCadastro.getjTextFieldObs().getText()); 
                
                this.produtoAtual.setStatus('A');
                
                servicoProdutoCopa.salvar(this.produtoAtual);
                
                JOptionPane.showMessageDialog(telaCadastro, "Produto salvo com sucesso!");
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(telaCadastro, "O 'Valor' inserido não é um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(telaCadastro, "Erro ao salvar: " + ex.getMessage(), "ERRO DE PERSISTÊNCIA", JOptionPane.ERROR_MESSAGE);
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaProdutoCopa telaBusca = new TelaBuscaProdutoCopa(null, true);
            ControllerBuscaProdutoCopa controllerBusca = new ControllerBuscaProdutoCopa(telaBusca);
            telaBusca.setVisible(true);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}