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
                JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório.");
                return;
            }
            
            Servico servico = new Servico();
            
            servico.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
            
            servico.setObs(this.telaCadastro.getjTextFieldObs().getText());
            
            servico.setStatus('A');
            
            servicoServico.salvar(servico);
            JOptionPane.showMessageDialog(null, "Serviço salvo com sucesso!");
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaServico telaBusca = new TelaBuscaServico(null, true);
            ControllerBuscaServico controllerBusca = new ControllerBuscaServico(telaBusca);
            telaBusca.setVisible(true);
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}