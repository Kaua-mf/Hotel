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
import utilities.Utilities;

public class ControllerCadFornecedor implements ActionListener {

    TelaCadastroFornecedor telaCadastro;
    ServicoFornecedor servicoFornecedor = new ServicoFornecedor();
    Fornecedor fornecedorAtual; 

    public ControllerCadFornecedor(TelaCadastroFornecedor telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            
            this.fornecedorAtual = new Fornecedor();
            
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = agora.format(formatoBrasileiro);
            
            this.telaCadastro.getjTextFieldDataCadastro().setText(dataFormatada);

        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldNomeFantasia().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "O campo 'Nome Fantasia' é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
                this.telaCadastro.getjTextFieldNomeFantasia().requestFocus();
                return;
            }

            String cnpjToValidate = this.telaCadastro.getjFormattedTextFieldCnpj().getText().replaceAll("\\D", "");
            boolean cnpjValido = service.ValidarDoc.validarCNPJ(cnpjToValidate);

            if (cnpjToValidate.isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "Campo CNPJ é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
                this.telaCadastro.getjFormattedTextFieldCnpj().requestFocus();
                return;
            }

            if (!cnpjValido) {
                JOptionPane.showMessageDialog(telaCadastro, "CNPJ Inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                this.telaCadastro.getjFormattedTextFieldCnpj().requestFocus();
                return;
            }
            
            this.fornecedorAtual.setNome(this.telaCadastro.getjTextFieldNomeFantasia().getText());
            this.fornecedorAtual.setRazaoSocial(this.telaCadastro.getjTextFieldRazaoSocial().getText());
            this.fornecedorAtual.setCnpj(this.telaCadastro.getjFormattedTextFieldCnpj().getText());
            this.fornecedorAtual.setInscricaoEstadual(this.telaCadastro.getjTextFieldInscricaoEstadual().getText());
            this.fornecedorAtual.setContato(this.telaCadastro.getjTextFieldContato().getText());
            this.fornecedorAtual.setFone1(this.telaCadastro.getjFormattedTextFieldFone1().getText());
            this.fornecedorAtual.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
            this.fornecedorAtual.setStatus('A');
            
            DateTimeFormatter dtfDb = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.fornecedorAtual.setDataCadastro(dtfDb.format(LocalDateTime.now()));
            
            try {
                servicoFornecedor.salvar(this.fornecedorAtual);
                
                JOptionPane.showMessageDialog(telaCadastro, "Dados gravados com sucesso!");
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(telaCadastro, "Erro ao gravar os dados:\n" + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaFornecedor telaBusca = new TelaBuscaFornecedor(null, true);
            ControllerBuscaFornecedor controllerBusca = new ControllerBuscaFornecedor(telaBusca);
            telaBusca.setVisible(true);

        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}