package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Funcionario;
import service.ServicoFuncionario;
import view.TelaBuscaFuncionario;
import view.TelaCadastroFuncionario;

public class ControllerCadFuncionario implements ActionListener {

    TelaCadastroFuncionario telaCadastro;
    ServicoFuncionario servicoFuncionario = new ServicoFuncionario();

    public ControllerCadFuncionario(TelaCadastroFuncionario telaCadastro) {
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
            if (this.telaCadastro.getjTextFieldNomeFantasia().getText().trim().isEmpty() || 
                this.telaCadastro.getjTextFieldUsuario().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Os campos 'Nome' e 'Usuário' são obrigatórios.");
                return;
            }
            
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(this.telaCadastro.getjTextFieldNomeFantasia().getText());
            funcionario.setUsuario(this.telaCadastro.getjTextFieldUsuario().getText());
            funcionario.setSenha(new String(this.telaCadastro.getjPasswordFieldSenha().getPassword()));
            funcionario.setCpf(this.telaCadastro.getjFormattedTextFieldCpf().getText());
            funcionario.setStatus('A');
            
            servicoFuncionario.salvar(funcionario);
            JOptionPane.showMessageDialog(telaCadastro, "Funcionário salvo com sucesso!");
            utilities.Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaFuncionario telaBusca = new TelaBuscaFuncionario(null, true);
            ControllerBuscaFuncionario controllerBusca = new ControllerBuscaFuncionario(telaBusca);
            telaBusca.setVisible(true);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}