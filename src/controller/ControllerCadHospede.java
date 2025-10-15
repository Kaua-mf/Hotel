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
        this.hospedeAtual = new Hospede(); 
        
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
            
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = agora.format(formatoBrasileiro);
            
            this.telaCadastro.getjTextFieldDataCadastro().setText(dataFormatada);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldNomeFantasia().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatório.");
                return;
            }
            
            try {
                this.hospedeAtual.setNome(this.telaCadastro.getjTextFieldNomeFantasia().getText());
                this.hospedeAtual.setFone(this.telaCadastro.getjFormattedTextFieldFone1().getText());
                this.hospedeAtual.setFone2(this.telaCadastro.getjFormattedTextFieldFone2().getText());
                this.hospedeAtual.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
                this.hospedeAtual.setCep(this.telaCadastro.getjFormattedTextFieldCep().getText());
                this.hospedeAtual.setLogradouro(this.telaCadastro.getjTextFieldLogradouro().getText());
                this.hospedeAtual.setBairro(this.telaCadastro.getjTextFieldBairro().getText());
                this.hospedeAtual.setCidade(this.telaCadastro.getjTextFieldCidade().getText());
                this.hospedeAtual.setComplemento(this.telaCadastro.getjTextFieldComplemento().getText());
                this.hospedeAtual.setDataCadastro(this.telaCadastro.getjTextFieldDataCadastro().getText()); 
                this.hospedeAtual.setCpf(this.telaCadastro.getjFormattedTextFieldCpf().getText());
                this.hospedeAtual.setRg(this.telaCadastro.getjTextFieldRg().getText());
                this.hospedeAtual.setObs(this.telaCadastro.getjTextFieldObs().getText()); 
                this.hospedeAtual.setContato(this.telaCadastro.getjTextFieldContato().getText()); 
                this.hospedeAtual.setRazaoSocial(this.telaCadastro.getjTextFieldRazaoSocial().getText()); 
                this.hospedeAtual.setCnpj(this.telaCadastro.getjFormattedTextFieldCnpj().getText());
                this.hospedeAtual.setInscricaoEstadual(this.telaCadastro.getjTextFieldInscricaoEstadual().getText());
                
                this.hospedeAtual.setStatus('A'); 

                servicoHospede.salvar(this.hospedeAtual);
                
                JOptionPane.showMessageDialog(null, "Hóspede salvo com sucesso!");
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar Hóspede: " + e.getMessage(), "ERRO DE BANCO DE DADOS", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro de formato de dados: " + e.getMessage(), "ERRO DE FORMATO", JOptionPane.ERROR_MESSAGE);
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