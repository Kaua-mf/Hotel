package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Hospede;
import service.ServicoHospede;
import view.TelaBuscaHospede;
import view.TelaCadastroHospede;

public class ControllerCadHospede implements ActionListener {

    TelaCadastroHospede telaCadastro;
    ServicoHospede servicoHospede = new ServicoHospede();

    public ControllerCadHospede(TelaCadastroHospede telaCadastro) {
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
            Hospede hospede = new Hospede();
            
            hospede.setNome(this.telaCadastro.getjTextFieldNomeFantasia().getText());
            hospede.setFone1(this.telaCadastro.getjFormattedTextFieldFone1().getText());
            hospede.setFone2(this.telaCadastro.getjFormattedTextFieldFone2().getText());
            hospede.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
            hospede.setCep(this.telaCadastro.getjFormattedTextFieldCep().getText());
            hospede.setLogradouro(this.telaCadastro.getjTextFieldLogradouro().getText());
            hospede.setBairro(this.telaCadastro.getjTextFieldBairro().getText());
            hospede.setCidade(this.telaCadastro.getjTextFieldCidade().getText());
            hospede.setComplemento(this.telaCadastro.getjTextFieldComplemento().getText());
            hospede.setCpf(this.telaCadastro.getjFormattedTextFieldCpf().getText());
            hospede.setRg(this.telaCadastro.getjTextFieldRg().getText());
            hospede.setRazaoSocial(this.telaCadastro.getjTextFieldRazaoSocial().getText());
            hospede.setCnpj(this.telaCadastro.getjFormattedTextFieldCnpj().getText());
            hospede.setInscricaoEstadual(this.telaCadastro.getjTextFieldInscricaoEstadual().getText());
            hospede.setStatus('A');
            
            servicoHospede.salvar(hospede);
            JOptionPane.showMessageDialog(telaCadastro, "Hóspede salvo com sucesso!");
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaHospede telaBusca = new TelaBuscaHospede(null, true);
            ControllerBuscaHospede controllerBusca = new ControllerBuscaHospede(telaBusca);
            telaBusca.setVisible(true);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}