package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.VagaEstacionamento;
import service.ServicoVagaEstacionamento;
import view.TelaBuscaVaga;
import view.TelaCadastroVaga;

public class ControllerCadVaga implements ActionListener {

    TelaCadastroVaga telaCadastro;
    ServicoVagaEstacionamento servicoVaga = new ServicoVagaEstacionamento();

    public ControllerCadVaga(TelaCadastroVaga telaCadastro) {
        this.telaCadastro = telaCadastro;
        // Adicione os listeners
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
            try {
                VagaEstacionamento vaga = new VagaEstacionamento();
                vaga.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                vaga.setObs(this.telaCadastro.getjTextFieldObs().getText());
                
                String metragemTexto = this.telaCadastro.getjFormattedTextFieldMetragem().getText();
                float metragem = Float.parseFloat(metragemTexto.replace(",", "."));
                vaga.setMetragemvaga(metragem);
                
                vaga.setStatus('A');
                servicoVaga.salvar(vaga);
                JOptionPane.showMessageDialog(null, "Vaga salva com sucesso!");
                utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O campo 'Metragem' deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaVaga telaBusca = new TelaBuscaVaga(null, true);
            ControllerBuscaVaga controllerBusca = new ControllerBuscaVaga(telaBusca);
            telaBusca.setVisible(true);
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}