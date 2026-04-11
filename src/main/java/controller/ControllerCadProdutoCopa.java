package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ProdutoCopa;
import service.ServicoProdutoCopa;
import utilities.Utilities;
import view.TelaBuscaProdutoCopa;
import view.TelaCadastroProdutoCopa;

public class ControllerCadProdutoCopa implements ActionListener {

    TelaCadastroProdutoCopa telaCadastro;
    ServicoProdutoCopa servicoProdutoCopa = new ServicoProdutoCopa();
    ProdutoCopa produtoAtual; 

    public ControllerCadProdutoCopa(TelaCadastroProdutoCopa telaCadastro) {
        this.telaCadastro = telaCadastro;
        this.produtoAtual = new ProdutoCopa();
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
    }
    
    private void carregarDadosNaTela() {
        if (this.produtoAtual != null) {
            this.telaCadastro.getjTextFieldIds().setText(String.valueOf(this.produtoAtual.getId()));
            this.telaCadastro.getjTextFieldDescricao().setText(this.produtoAtual.getDescricao());
            this.telaCadastro.getjFormattedTextFieldValor().setText(String.format("%.2f", this.produtoAtual.getValor()).replace(".", ","));
            this.telaCadastro.getjTextFieldObs().setText(this.produtoAtual.getObs());
            
            this.telaCadastro.getjTextFieldIds().setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.produtoAtual = new ProdutoCopa(); 
            this.telaCadastro.getjTextFieldIds().setEnabled(false);
            this.telaCadastro.getjTextFieldDescricao().requestFocus();
        
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            this.produtoAtual = new ProdutoCopa();
        
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.telaCadastro, "O campo 'Descrição' é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
                this.telaCadastro.getjTextFieldDescricao().requestFocus();
                return;
            }
            
            try {
                String valorTexto = this.telaCadastro.getjFormattedTextFieldValor().getText();
                if (valorTexto.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this.telaCadastro, "O campo 'Valor' é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                float valor = Float.parseFloat(valorTexto.replace(",", "."));

                this.produtoAtual.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                this.produtoAtual.setValor(valor);
                this.produtoAtual.setObs(this.telaCadastro.getjTextFieldObs().getText()); 
                this.produtoAtual.setStatus('A');
                
                boolean isNovoRegistro = this.produtoAtual.getId() == 0;
                
                servicoProdutoCopa.salvar(this.produtoAtual);
                
                String mensagem = isNovoRegistro ? "Produto cadastrado com sucesso!" : "Produto atualizado com sucesso!";
                JOptionPane.showMessageDialog(telaCadastro, mensagem);
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                this.produtoAtual = new ProdutoCopa();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(telaCadastro, "O 'Valor' inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(telaCadastro, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaProdutoCopa telaBusca = new TelaBuscaProdutoCopa(null, true);
            ControllerBuscaProdutoCopa controllerBusca = new ControllerBuscaProdutoCopa(telaBusca);
            telaBusca.setVisible(true);
            
            if (ControllerBuscaProdutoCopa.codigoSelecionado != 0) {
                
                this.produtoAtual = servicoProdutoCopa.buscarPorId(ControllerBuscaProdutoCopa.codigoSelecionado);
                
                if (this.produtoAtual != null) {
                    Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    
                    carregarDadosNaTela(); 
                    
                    ControllerBuscaProdutoCopa.codigoSelecionado = 0; 
                } else {
                    JOptionPane.showMessageDialog(telaCadastro, "Erro ao buscar o produto com ID: " + ControllerBuscaProdutoCopa.codigoSelecionado);
                }
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}