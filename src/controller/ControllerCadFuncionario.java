package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.funcionarioAtual = new Funcionario(); // Cria um novo objeto funcionário

        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {

            if (this.telaCadastro.getjTextFieldNome().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatório.");
                return; 
            }
            
            try {
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
            TelaBuscaFuncionario telaBusca = new TelaBuscaFuncionario(null, true);
            ControllerBuscaFuncionario controllerBusca = new ControllerBuscaFuncionario(telaBusca);
            telaBusca.setVisible(true);

        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}