package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Fornecedor;
import service.ServicoFornecedor;
import view.TelaBuscaFornecedor;
import view.TelaCadastroFornecedor;

public class ControllerCadFornecedor implements ActionListener {

    TelaCadastroFornecedor telaCadastro;
    ServicoFornecedor servicoFornecedor = new ServicoFornecedor();

    public ControllerCadFornecedor(TelaCadastroFornecedor telaCadastro) {
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
            Fornecedor fornecedor = new Fornecedor();
            
            fornecedor.setNome(this.telaCadastro.getjTextFieldNomeFantasia().getText());
            fornecedor.setRazaoSocial(this.telaCadastro.getjTextFieldRazaoSocial().getText());
            fornecedor.setCnpj(this.telaCadastro.getjFormattedTextFieldCnpj().getText());
            fornecedor.setInscricaoEstadual(this.telaCadastro.getjTextFieldInscricaoEstadual().getText());
            fornecedor.setContato(this.telaCadastro.getjTextFieldContato().getText());
            fornecedor.setFone1(this.telaCadastro.getjFormattedTextFieldFone1().getText());
            fornecedor.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
            fornecedor.setStatus('A');
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            fornecedor.setDataCadastro(dtf.format(LocalDateTime.now()));
            
            servicoFornecedor.salvar(fornecedor);
            JOptionPane.showMessageDialog(telaCadastro, "Fornecedor salvo com sucesso!");
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaFornecedor telaBusca = new TelaBuscaFornecedor(null, true);
            ControllerBuscaFornecedor controllerBusca = new ControllerBuscaFornecedor(telaBusca);
            telaBusca.setVisible(true);
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}