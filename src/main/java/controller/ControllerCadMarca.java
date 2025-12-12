package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Marca;
import service.ServicoMarca;
import utilities.Utilities;
import view.TelaBuscaMarca;      
import view.TelaCadastroMarca; 

public class ControllerCadMarca implements ActionListener {

    TelaCadastroMarca tela;
    ServicoMarca servicoMarca = new ServicoMarca();
    Marca marcaAtual;

    public ControllerCadMarca(TelaCadastroMarca tela) {
        this.tela = tela;
        this.marcaAtual = new Marca(); 

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);
        
        Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
    }
    
    private void carregarDadosNaTela() {
        if (this.marcaAtual != null) {
            this.tela.getjTextFieldId().setText(String.valueOf(this.marcaAtual.getId()));
            this.tela.getjTextFieldMarca().setText(this.marcaAtual.getDescricao());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjButtonNovo()) {
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
            this.marcaAtual = new Marca();
            this.tela.getjTextFieldId().setEnabled(false); 
            this.tela.getjTextFieldMarca().requestFocus(); 

        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
            this.marcaAtual = new Marca(); 

        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            
            String nomeMarca = this.tela.getjTextFieldMarca().getText();            
            
            if (nomeMarca.trim().isEmpty()) {
               JOptionPane.showMessageDialog(null, "O campo 'Marca' é obrigatório.");
               this.tela.getjTextFieldMarca().requestFocus(); 
               return;
            }
            
            this.marcaAtual.setDescricao(nomeMarca); 
            this.marcaAtual.setStatus('A'); 
            
            try {
                boolean isNovoRegistro = this.marcaAtual.getId() == 0;
                
                servicoMarca.salvar(this.marcaAtual); 
                
                String mensagem = isNovoRegistro ? "Marca cadastrada com sucesso!" : "Marca atualizada com sucesso!";
                JOptionPane.showMessageDialog(null, mensagem);
                
                Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
                this.marcaAtual = new Marca(); 
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar/atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); 
            }

        } else if (e.getSource() == this.tela.getjButtonBuscar()) {
            
            TelaBuscaMarca telaBusca = new TelaBuscaMarca(null, true); 
            ControllerBuscaMarca controllerBusca = new ControllerBuscaMarca(telaBusca); 
            telaBusca.setVisible(true); 
            
            if (ControllerBuscaMarca.codigoSelecionado != 0) {
                
                this.marcaAtual = servicoMarca.buscarPorId(ControllerBuscaMarca.codigoSelecionado);
                
                if (this.marcaAtual != null) {
                    Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
                    
                    carregarDadosNaTela(); 
                    
                    ControllerBuscaMarca.codigoSelecionado = 0; 
                } else {
                    JOptionPane.showMessageDialog(tela, "Erro ao buscar a marca com ID: " + ControllerBuscaMarca.codigoSelecionado);
                }
            }
            
        } else if (e.getSource() == this.tela.getjButtonSair()) {
            this.tela.dispose();
        }
    }
}