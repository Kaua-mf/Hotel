package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ProdutoCopa;
import service.ServicoProdutoCopa;
import view.TelaBuscaProdutoCopa;
import view.TelaCadastroProdutoCopa;

public class ControllerCadProdutoCopa implements ActionListener {

    TelaCadastroProdutoCopa telaCadastro;
    ServicoProdutoCopa servicoProdutoCopa = new ServicoProdutoCopa();

    public ControllerCadProdutoCopa(TelaCadastroProdutoCopa telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
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

                ProdutoCopa produto = new ProdutoCopa();
                produto.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                produto.setValor(valor);
                produto.setStatus('A');
                
                servicoProdutoCopa.salvar(produto);
                JOptionPane.showMessageDialog(telaCadastro, "Produto salvo com sucesso!");
                
                utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(telaCadastro, "O 'Valor' inserido não é um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
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