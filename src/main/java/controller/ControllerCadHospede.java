package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Hospede;
import service.ServicoHospede;
import view.TelaBuscaHospede;
import view.TelaCadastroHospede;
import utilities.Utilities;

public class ControllerCadHospede implements ActionListener {

    TelaCadastroHospede telaCadastro;
    ServicoHospede servicoHospede = new ServicoHospede();
    Hospede hospedeAtual;

    public ControllerCadHospede(TelaCadastroHospede telaCadastro) {
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
            
            this.hospedeAtual = new Hospede();
            
            this.telaCadastro.getjComboBoxSexo().setSelectedIndex(0);

            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            this.telaCadastro.getjTextFieldDataCadastro().setText(agora.format(formatoBrasileiro));
            this.telaCadastro.getjTextFieldDataCadastro().setEnabled(false); // Impede edição

        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            
            String cnpjToValidate = this.telaCadastro.getjFormattedTextFieldCnpj().getText().replaceAll("\\D", "");
            String cpfToValidate = this.telaCadastro.getjFormattedTextFieldCpf().getText().replaceAll("\\D","");
            
            boolean cpfvalido = service.ValidarDoc.validarCPF(cpfToValidate);
            boolean cnpjvalido = service.ValidarDoc.validarCNPJ(cnpjToValidate);

            if (cnpjToValidate.isEmpty() && cpfToValidate.isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro,"Preencha pelo menos um dos campos (CPF ou CNPJ).");
                return;
            }
            if (!cnpjToValidate.isEmpty() && !cpfToValidate.isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "Preencha somente um dos campos (CPF ou CNPJ).");
                return;
            }
            if (!cpfToValidate.isEmpty() && !cpfvalido) {
                JOptionPane.showMessageDialog(telaCadastro, "CPF Inválido.");
                return;
            }
            if (!cnpjToValidate.isEmpty() && !cnpjvalido) {
                JOptionPane.showMessageDialog(telaCadastro, "CNPJ Inválido.");
                return;
            }

            if (this.telaCadastro.getjTextFieldNome().getText().trim().isEmpty()) { 
                JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatório.");
                return; 
            }
            
            try {
                this.hospedeAtual.setNome(this.telaCadastro.getjTextFieldNome().getText());
                this.hospedeAtual.setFone(this.telaCadastro.getjFormattedTextFieldFone1().getText());
                this.hospedeAtual.setFone2(this.telaCadastro.getjFormattedTextFieldFone2().getText());
                this.hospedeAtual.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
                this.hospedeAtual.setCep(this.telaCadastro.getjFormattedTextFieldCep().getText());
                this.hospedeAtual.setLogradouro(this.telaCadastro.getjTextFieldLogradouro().getText());
                this.hospedeAtual.setBairro(this.telaCadastro.getjTextFieldBairro().getText());
                this.hospedeAtual.setCidade(this.telaCadastro.getjTextFieldCidade().getText());
                this.hospedeAtual.setComplemento(this.telaCadastro.getjTextFieldComplemento().getText());
                
                this.hospedeAtual.setCpf(this.telaCadastro.getjFormattedTextFieldCpf().getText());
                this.hospedeAtual.setCnpj(this.telaCadastro.getjFormattedTextFieldCnpj().getText());
                
                this.hospedeAtual.setRg(this.telaCadastro.getjTextFieldRg().getText());
                this.hospedeAtual.setObs(this.telaCadastro.getjTextFieldObs().getText());
                this.hospedeAtual.setStatus('A');

                String sexoSelecionado = (String) this.telaCadastro.getjComboBoxSexo().getSelectedItem();
                this.hospedeAtual.setSexo(sexoSelecionado);

                LocalDateTime agora = LocalDateTime.now();
                DateTimeFormatter formatoBanco = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                this.hospedeAtual.setDataCadastro(agora.format(formatoBanco));

                servicoHospede.salvar(this.hospedeAtual);
                
                JOptionPane.showMessageDialog(null, "Hóspede salvo com sucesso!");
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar Hóspede: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaHospede telaBusca = new TelaBuscaHospede(null, true);
            ControllerBuscaHospede controllerBusca = new ControllerBuscaHospede(telaBusca);
            telaBusca.setVisible(true);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}