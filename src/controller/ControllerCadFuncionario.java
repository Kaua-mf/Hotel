package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Funcionario;
import service.ServicoFuncionario;
import view.TelaBuscaFuncionario;
import view.TelaCadastroFuncionario;
import utilities.Utilities;

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

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            // Ação para o botão NOVO
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            
            this.funcionarioAtual = new Funcionario();
            
            // Define o ComboBox para a primeira opção
            this.telaCadastro.getjComboBoxSexo().setSelectedIndex(0);

            // Define a data de cadastro automaticamente
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = agora.format(formatoBrasileiro);
            this.telaCadastro.getjTextFieldDataCadastro().setText(dataFormatada);

        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            // Ação para o botão CANCELAR
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            // Ação para o botão GRAVAR
            if (this.telaCadastro.getjTextFieldNome().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatório.");
                return; 
            }
            
            try {
                // Mapeia todos os dados da tela para o objeto
                this.funcionarioAtual.setNome(this.telaCadastro.getjTextFieldNome().getText());
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

                // --- LÓGICA DO SEXO NO LUGAR CORRETO ---
                int indiceSelecionado = this.telaCadastro.getjComboBoxSexo().getSelectedIndex();
                char sexoParaSalvar;

                switch (indiceSelecionado) {
                    case 0: sexoParaSalvar = 'M'; break;
                    case 1: sexoParaSalvar = 'F'; break;
                    default: sexoParaSalvar = 'O'; break;
                }
                this.funcionarioAtual.setSexo(String.valueOf(sexoParaSalvar));
                // Persiste o objeto no banco de dados
                if (this.funcionarioAtual.getId() == 0) { 
                    servicoFuncionario.criar(this.funcionarioAtual); 
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                } else {
                    servicoFuncionario.atualizar(this.funcionarioAtual); 
                    JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
                }

                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao gravar dados: \n" + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            // Ação para o botão BUSCAR
            TelaBuscaFuncionario telaBusca = new TelaBuscaFuncionario(null, true);
            ControllerBuscaFuncionario controllerBusca = new ControllerBuscaFuncionario(telaBusca);
            telaBusca.setVisible(true);

        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            // Ação para o botão SAIR
            this.telaCadastro.dispose();
        }
    }
}