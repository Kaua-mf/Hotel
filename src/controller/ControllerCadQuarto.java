package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Quarto;
import model.TipoQuarto;
import service.ServicoQuarto;
import view.TelaBuscaQuarto;
import view.TelaCadastroQuarto;

public class ControllerCadQuarto implements ActionListener {

    TelaCadastroQuarto telaCadastro;
    ServicoQuarto servicoQuarto = new ServicoQuarto();

    public ControllerCadQuarto(TelaCadastroQuarto telaCadastro) {
        this.telaCadastro = telaCadastro;
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        DefaultComboBoxModel<TipoQuarto> comboBoxModel = new DefaultComboBoxModel<>();
        for(TipoQuarto tipo : servicoQuarto.buscarTiposQuarto()) {
            comboBoxModel.addElement(tipo);
        }
        this.telaCadastro.getjComboBoxTipoQuarto().setModel(comboBoxModel);

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
            Quarto quarto = new Quarto();
            quarto.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
            quarto.setValorDiaria(Float.parseFloat(this.telaCadastro.getjFormattedTextFieldValor().getText()));
            quarto.setObs(this.telaCadastro.getjTextFieldObs().getText());
              this.telaCadastro.getjCheckBoxFlagAnimais().setEnabled(false); 
        this.telaCadastro.getjCheckBoxFlagAnimais().setState(false);
            servicoQuarto.salvar(quarto);
            JOptionPane.showMessageDialog(telaCadastro, "Quarto salvo com sucesso!");
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaQuarto telaBusca = new TelaBuscaQuarto(null, true);
            ControllerBuscaQuarto controllerBusca = new ControllerBuscaQuarto(telaBusca);
            telaBusca.setVisible(true);
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}