package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Servico;
import service.ServicoServico;
import view.TelaBuscaServico;
import view.TelaCadastroServico; 

public class ControllerCadServico implements ActionListener {

    TelaCadastroServico telaCadastro;
    ServicoServico servicoServico = new ServicoServico();
    Servico servicoAtual = new Servico(); 

    public ControllerCadServico(TelaCadastroServico telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); 
    }
    
    private void carregarDadosNaTela() {
        if (this.servicoAtual != null) {
            this.telaCadastro.getjTextFieldId().setText(String.valueOf(this.servicoAtual.getId()));
            this.telaCadastro.getjTextFieldDescricao().setText(this.servicoAtual.getDescricao());
            this.telaCadastro.getjTextFieldObs().setText(this.servicoAtual.getObs()); // Novo campo
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.servicoAtual = new Servico();
            this.telaCadastro.getjTextFieldId().setEnabled(false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            this.servicoAtual = new Servico();

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.telaCadastro, "Descrição é obrigatória.");
                return;
            }

            try {
                this.servicoAtual.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                this.servicoAtual.setObs(this.telaCadastro.getjTextFieldObs().getText()); // Novo campo
                
                this.servicoAtual.setStatus('A'); 

                boolean isNovoRegistro = this.servicoAtual.getId() == 0;
                servicoServico.salvar(this.servicoAtual);
                
                JOptionPane.showMessageDialog(telaCadastro, isNovoRegistro ? "Serviço cadastrado com sucesso!" : "Serviço atualizado com sucesso!");
                
                utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                this.servicoAtual = new Servico();

            } catch (RuntimeException ex) {
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
                    utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    
                    carregarDadosNaTela();
                    
                    ControllerBuscaServico.codigoSelecionado = 0; 
                }
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}