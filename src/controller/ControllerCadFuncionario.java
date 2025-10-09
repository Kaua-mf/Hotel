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

        // Adiciona os listeners para os botões
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);

        // Configuração inicial da tela
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            // Ação para o botão NOVO
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.funcionarioAtual = new Funcionario(); // Cria um novo objeto funcionário

        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            // Ação para o botão CANCELAR
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            // Ação para o botão GRAVAR

            // 1. VALIDAÇÃO: Verifica se o campo de nome está preenchido
            // ALTERAÇÃO: Trocado 'getjTextFieldNomeFantasia()' por um nome mais adequado, como 'getjTextFieldNome()'.
            //            Você deve renomear o componente na sua classe 'TelaCadastroFuncionario'.
            if (this.telaCadastro.getjTextFieldNome().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatório.");
                return; // Para a execução se o nome estiver vazio
            }
            
            // 2. MAPEAMENTO: Pega os dados da tela e coloca no objeto 'funcionarioAtual'
            try {
                // --- DADOS PESSOAIS ---
                this.funcionarioAtual.setNome(this.telaCadastro.getjTextFieldNome().getText());
                this.funcionarioAtual.setFone(this.telaCadastro.getjFormattedTextFieldFone1().getText());
                this.funcionarioAtual.setFone2(this.telaCadastro.getjFormattedTextFieldFone2().getText());
                this.funcionarioAtual.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
                this.funcionarioAtual.setRg(this.telaCadastro.getjTextFieldRg().getText());
                
                // REMOVIDO: this.funcionarioAtual.setCpf(...) -> Você disse que não precisava.
                // Se precisar, é só adicionar a linha de volta.
                // this.funcionarioAtual.setCpf(this.telaCadastro.getjFormattedTextFieldCpf().getText());
                
                // --- ENDEREÇO ---
                this.funcionarioAtual.setCep(this.telaCadastro.getjFormattedTextFieldCep().getText());
                this.funcionarioAtual.setLogradouro(this.telaCadastro.getjTextFieldLogradouro().getText());
                this.funcionarioAtual.setBairro(this.telaCadastro.getjTextFieldBairro().getText());
                this.funcionarioAtual.setCidade(this.telaCadastro.getjTextFieldCidade().getText());
                this.funcionarioAtual.setComplemento(this.telaCadastro.getjTextFieldComplemento().getText());

                // --- DADOS DE ACESSO E CONTROLE ---
                this.funcionarioAtual.setUsuario(this.telaCadastro.getjTextFieldUsuario().getText());
                this.funcionarioAtual.setSenha(String.valueOf(this.telaCadastro.getjPasswordFieldSenha().getPassword()));
                this.funcionarioAtual.setObs(this.telaCadastro.getjTextFieldObs().getText());
                
                // DATA DE CADASTRO: É melhor o banco de dados cuidar disso com 'CURRENT_TIMESTAMP'. 
                // Se você precisar definir manualmente, descomente a linha abaixo.
                // this.funcionarioAtual.setDataCadastro(this.telaCadastro.getjTextFieldDataCadastro().getText());
                
                // STATUS: Define o status como 'A' (Ativo) por padrão ao salvar
                this.funcionarioAtual.setStatus('A'); 

                // REMOVIDOS: Os campos abaixo não existem na sua tabela 'funcionario'.
                // this.funcionarioAtual.setContato(...);
                // this.funcionarioAtual.setRazaoSocial(...);
                // this.funcionarioAtual.setCnpj(...);
                // this.funcionarioAtual.setInscricaoEstadual(...);

                // 3. PERSISTÊNCIA: Chama o serviço para salvar o objeto no banco de dados
               if (this.funcionarioAtual.getId() == 0) { // Se o ID for 0, é um novo cadastro
    servicoFuncionario.criar(this.funcionarioAtual); // Use o método criar()
    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
} else { // Se já tem ID, é uma atualização
    servicoFuncionario.atualizar(this.funcionarioAtual); // Use o método atualizar()
    JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
}

                // 4. FINALIZAÇÃO: Restaura o estado inicial da tela
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);

            } catch (Exception e) {
                // Tratamento genérico de erros
                JOptionPane.showMessageDialog(null, "Erro ao gravar dados: \n" + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Ajuda a depurar mostrando o erro no console
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            // Ação para o botão BUSCAR (seu código aqui já estava bom)
            TelaBuscaFuncionario telaBusca = new TelaBuscaFuncionario(null, true);
            ControllerBuscaFuncionario controllerBusca = new ControllerBuscaFuncionario(telaBusca);
            telaBusca.setVisible(true);

        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            // Ação para o botão SAIR
            this.telaCadastro.dispose();
        }
    }
}