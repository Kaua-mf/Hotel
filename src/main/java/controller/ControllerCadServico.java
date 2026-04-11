package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Servico;
import service.ServicoServico;
import utilities.Utilities;
import view.TelaBuscaServico;
import view.TelaCadastroServico; 

public class ControllerCadServico implements ActionListener {

    TelaCadastroServico telaCadastro;
    ServicoServico servicoServico = new ServicoServico();
    Servico servicoAtual; 

    public ControllerCadServico(TelaCadastroServico telaCadastro) {
        this.telaCadastro = telaCadastro;
        this.servicoAtual = new Servico();
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);

        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
    }
    
    private void carregarDadosNaTela() {
        if (this.servicoAtual != null) {
            this.telaCadastro.getjTextFieldId().setText(String.valueOf(this.servicoAtual.getId()));
            this.telaCadastro.getjTextFieldDescricao().setText(this.servicoAtual.getDescricao());
            this.telaCadastro.getjTextFieldObs().setText(this.servicoAtual.getObs());
            
            this.telaCadastro.getjTextFieldId().setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.servicoAtual = new Servico();
            this.telaCadastro.getjTextFieldId().setEnabled(false);
            this.telaCadastro.getjTextFieldDescricao().requestFocus();

        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            this.servicoAtual = new Servico();

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.telaCadastro, "Descrição é obrigatória.", "Atenção", JOptionPane.WARNING_MESSAGE);
                this.telaCadastro.getjTextFieldDescricao().requestFocus();
                return;
            }

            try {
                this.servicoAtual.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                this.servicoAtual.setObs(this.telaCadastro.getjTextFieldObs().getText());
                this.servicoAtual.setStatus('A'); 

                boolean isNovoRegistro = this.servicoAtual.getId() == 0;
                
                servicoServico.salvar(this.servicoAtual);
                
                String mensagem = isNovoRegistro ? "Serviço cadastrado com sucesso!" : "Serviço atualizado com sucesso!";
                JOptionPane.showMessageDialog(telaCadastro, mensagem);
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                this.servicoAtual = new Servico();

            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(telaCadastro, "Erro ao salvar: " + ex.getMessage(), "ERRO DE PERSISTÊNCIA", JOptionPane.ERROR_MESSAGE);
                 ex.printStackTrace();
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaServico telaBusca = new TelaBuscaServico(null, true);
            ControllerBuscaServico controllerBusca = new ControllerBuscaServico(telaBusca);
            telaBusca.setVisible(true);
            
            if (ControllerBuscaServico.codigoSelecionado != 0) {
                this.servicoAtual = servicoServico.buscarPorId(ControllerBuscaServico.codigoSelecionado);
                
                if (this.servicoAtual != null) {
                    Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    
                    carregarDadosNaTela();
                    
                    ControllerBuscaServico.codigoSelecionado = 0; 
                }
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}