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
    
    private void carregarDadosNaTela() {
        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        this.telaCadastro.getjTextFieldNome().setText(this.hospedeAtual.getNome());
        this.telaCadastro.getjFormattedTextFieldFone1().setText(this.hospedeAtual.getFone());
        this.telaCadastro.getjFormattedTextFieldFone2().setText(this.hospedeAtual.getFone2());
        this.telaCadastro.getjTextFieldEmail().setText(this.hospedeAtual.getEmail());
        this.telaCadastro.getjFormattedTextFieldCep().setText(this.hospedeAtual.getCep());
        this.telaCadastro.getjTextFieldLogradouro().setText(this.hospedeAtual.getLogradouro());
        this.telaCadastro.getjTextFieldBairro().setText(this.hospedeAtual.getBairro());
        this.telaCadastro.getjTextFieldCidade().setText(this.hospedeAtual.getCidade());
        this.telaCadastro.getjTextFieldComplemento().setText(this.hospedeAtual.getComplemento());
        this.telaCadastro.getjFormattedTextFieldCpf().setText(this.hospedeAtual.getCpf());
        this.telaCadastro.getjFormattedTextFieldCnpj().setText(this.hospedeAtual.getCnpj());
        this.telaCadastro.getjTextFieldRg().setText(this.hospedeAtual.getRg());
        this.telaCadastro.getjTextFieldObs().setText(this.hospedeAtual.getObs());

        try {
             LocalDateTime dataDb = LocalDateTime.parse(this.hospedeAtual.getDataCadastro(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
             this.telaCadastro.getjTextFieldDataCadastro().setText(dataDb.format(formatoBrasileiro));
        } catch (Exception e) {
             this.telaCadastro.getjTextFieldDataCadastro().setText("Erro na data");
        }
        
        String sexoDB = this.hospedeAtual.getSexo();
        for (int i = 0; i < this.telaCadastro.getjComboBoxSexo().getItemCount(); i++) {
            if (this.telaCadastro.getjComboBoxSexo().getItemAt(i).equals(sexoDB)) {
                this.telaCadastro.getjComboBoxSexo().setSelectedIndex(i);
                break;
            }
        }
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
            this.telaCadastro.getjTextFieldNome().requestFocus();
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            ControllerBuscaHospede.codigoSelecionado = 0; 
            
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

                if (this.hospedeAtual.getId() == 0) {
                    LocalDateTime agora = LocalDateTime.now();
                    DateTimeFormatter formatoBanco = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    this.hospedeAtual.setDataCadastro(agora.format(formatoBanco));
                }
                
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

            if (ControllerBuscaHospede.codigoSelecionado != 0) {
                
                this.hospedeAtual = servicoHospede.buscarPorId(ControllerBuscaHospede.codigoSelecionado);
                
                if (this.hospedeAtual != null) {
                    Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    
                    carregarDadosNaTela(); 
                    
                    ControllerBuscaHospede.codigoSelecionado = 0; 
                } else {
                    JOptionPane.showMessageDialog(telaCadastro, "Erro ao buscar o hóspede com ID: " + ControllerBuscaHospede.codigoSelecionado);
                }
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}