package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Funcionario;
import service.ServicoFuncionario;
import utilities.Utilities;
import view.TelaBuscaFuncionario;
import view.TelaCadastroFuncionario;

public class ControllerCadFuncionario implements ActionListener {

    TelaCadastroFuncionario telaCadastro;
    ServicoFuncionario servicoFuncionario = new ServicoFuncionario();
    Funcionario funcionarioAtual;

    public ControllerCadFuncionario(TelaCadastroFuncionario telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        this.funcionarioAtual = new Funcionario(); 
        
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
        DateTimeFormatter formatoBanco = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.telaCadastro.getjTextFieldNome().setText(this.funcionarioAtual.getNome());
        this.telaCadastro.getjFormattedTextFieldCpf().setText(this.funcionarioAtual.getCpf());
        this.telaCadastro.getjFormattedTextFieldFone1().setText(this.funcionarioAtual.getFone());
        this.telaCadastro.getjFormattedTextFieldFone2().setText(this.funcionarioAtual.getFone2());
        this.telaCadastro.getjTextFieldEmail().setText(this.funcionarioAtual.getEmail());
        this.telaCadastro.getjTextFieldRg().setText(this.funcionarioAtual.getRg());
        this.telaCadastro.getjFormattedTextFieldCep().setText(this.funcionarioAtual.getCep());
        this.telaCadastro.getjTextFieldLogradouro().setText(this.funcionarioAtual.getLogradouro());
        this.telaCadastro.getjTextFieldBairro().setText(this.funcionarioAtual.getBairro());
        this.telaCadastro.getjTextFieldCidade().setText(this.funcionarioAtual.getCidade());
        this.telaCadastro.getjTextFieldComplemento().setText(this.funcionarioAtual.getComplemento());
        this.telaCadastro.getjTextFieldUsuario().setText(this.funcionarioAtual.getUsuario());
        this.telaCadastro.getjPasswordFieldSenha().setText(this.funcionarioAtual.getSenha()); 
        this.telaCadastro.getjTextFieldObs().setText(this.funcionarioAtual.getObs());

        try {
             if (this.funcionarioAtual.getDataCadastro() != null) {
                LocalDateTime dataDb = LocalDateTime.parse(this.funcionarioAtual.getDataCadastro(), formatoBanco);
                this.telaCadastro.getjTextFieldDataCadastro().setText(dataDb.format(formatoBrasileiro));
             }
        } catch (Exception e) {
             this.telaCadastro.getjTextFieldDataCadastro().setText("");
        }
        
        this.telaCadastro.getjTextFieldDataCadastro().setEnabled(false);

        String sexoDB = this.funcionarioAtual.getSexo();
        if ("M".equalsIgnoreCase(sexoDB)) {
             this.telaCadastro.getjComboBoxSexo().setSelectedIndex(0);
        } else if ("F".equalsIgnoreCase(sexoDB)) {
             this.telaCadastro.getjComboBoxSexo().setSelectedIndex(1);
        } else {
             this.telaCadastro.getjComboBoxSexo().setSelectedIndex(2); 
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            
            this.funcionarioAtual = new Funcionario(); 
            this.telaCadastro.getjComboBoxSexo().setSelectedIndex(0);

            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            this.telaCadastro.getjTextFieldDataCadastro().setText(agora.format(formatoBrasileiro));
            this.telaCadastro.getjTextFieldDataCadastro().setEnabled(false);
            
            this.telaCadastro.getjTextFieldNome().requestFocus();
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            ControllerBuscaFuncionario.codigoSelecionado = 0; 

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldNome().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatório.");
                this.telaCadastro.getjTextFieldNome().requestFocus();
                return; 
            }

            String cpfToValidate = this.telaCadastro.getjFormattedTextFieldCpf().getText().replaceAll("\\D","");
            
            boolean cpfvalido = service.ValidarDoc.validarCPF(cpfToValidate);

            if (!cpfToValidate.isEmpty() && !cpfvalido) {
                JOptionPane.showMessageDialog(telaCadastro, "CPF Inválido.");
                this.telaCadastro.getjFormattedTextFieldCpf().requestFocus();
                return;
            }
            
            try {
                this.funcionarioAtual.setNome(this.telaCadastro.getjTextFieldNome().getText());
                this.funcionarioAtual.setCpf(this.telaCadastro.getjFormattedTextFieldCpf().getText());
                this.funcionarioAtual.setFone(this.telaCadastro.getjFormattedTextFieldFone1().getText());
                this.funcionarioAtual.setFone2(this.telaCadastro.getjFormattedTextFieldFone2().getText());
                this.funcionarioAtual.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
                this.funcionarioAtual.setRg(this.telaCadastro.getjTextFieldRg().getText());
                this.funcionarioAtual.setCep(this.telaCadastro.getjFormattedTextFieldCep().getText());
                this.funcionarioAtual.setLogradouro(this.telaCadastro.getjTextFieldLogradouro().getText());
                this.funcionarioAtual.setBairro(this.telaCadastro.getjTextFieldBairro().getText());
                this.funcionarioAtual.setCidade(this.telaCadastro.getjTextFieldCidade().getText());
                this.funcionarioAtual.setComplemento(this.telaCadastro.getjTextFieldComplemento().getText());
                this.funcionarioAtual.setUsuario(this.telaCadastro.getjTextFieldUsuario().getText());
                this.funcionarioAtual.setSenha(String.valueOf(this.telaCadastro.getjPasswordFieldSenha().getPassword()));
                this.funcionarioAtual.setObs(this.telaCadastro.getjTextFieldObs().getText());
                this.funcionarioAtual.setStatus('A'); 

                int indiceSelecionado = this.telaCadastro.getjComboBoxSexo().getSelectedIndex();
                String sexoParaSalvar; 
                switch (indiceSelecionado) {
                    case 0: sexoParaSalvar = "M"; break;
                    case 1: sexoParaSalvar = "F"; break;
                    default: sexoParaSalvar = "O"; break;
                }
                this.funcionarioAtual.setSexo(sexoParaSalvar);
                
                if (this.funcionarioAtual.getId() == 0) {
                    LocalDateTime agora = LocalDateTime.now();
                    DateTimeFormatter formatoBanco = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    this.funcionarioAtual.setDataCadastro(agora.format(formatoBanco));
                }

                servicoFuncionario.salvar(this.funcionarioAtual);
                
                String mensagem = (this.funcionarioAtual.getId() == 0) ? "Funcionário cadastrado!" : "Funcionário atualizado!";
                JOptionPane.showMessageDialog(null, mensagem);

                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                ControllerBuscaFuncionario.codigoSelecionado = 0;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao gravar dados: \n" + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            
            TelaBuscaFuncionario telaBusca = new TelaBuscaFuncionario(null, true);
            ControllerBuscaFuncionario controllerBusca = new ControllerBuscaFuncionario(telaBusca);
            telaBusca.setVisible(true); 

            if (ControllerBuscaFuncionario.codigoSelecionado != 0) {
                this.funcionarioAtual = servicoFuncionario.buscarPorId(ControllerBuscaFuncionario.codigoSelecionado);
                
                if (this.funcionarioAtual != null) {
                    Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    carregarDadosNaTela(); 
                } 
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}