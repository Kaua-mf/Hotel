package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Funcionario; // Alterado para o modelo Funcionario
import service.ServicoFuncionario; // Alterado para o serviço Funcionario
import view.TelaBuscaFuncionario; // Alterado para a tela de Funcionario

/**
 * Controller responsável pela lógica da tela de busca de funcionários.
 * Gerencia as ações de filtrar, carregar todos e sair da tela.
 */
public class ControllerBuscaFuncionario implements ActionListener {

    TelaBuscaFuncionario telaBusca;
    ServicoFuncionario servicoFuncionario = new ServicoFuncionario();

    /**
     * Construtor que recebe a tela de busca e inicializa os componentes e listeners.
     * @param telaBusca A instância da tela de busca de funcionário.
     */
    public ControllerBuscaFuncionario(TelaBuscaFuncionario telaBusca) {
        this.telaBusca = telaBusca;
        
        // Adiciona os listeners para os botões da tela
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        // Carrega os dados na tabela assim que a tela é aberta
        preencheTabelaComDados();
    }
    
    /**
     * Método auxiliar para buscar todos os funcionários e preencher a tabela na view.
     * Inclui tratamento de erro para falhas de conexão com o banco de dados.
     */
    private void preencheTabelaComDados() {
        try {
            List<Funcionario> listaFuncionarios = servicoFuncionario.buscarTodos();
            this.telaBusca.preencheTabela(listaFuncionarios);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, 
                "Falha ao carregar dados! Verifique a conexão com o banco de dados.", 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Método que trata as ações dos botões (filtrar, carregar, sair).
     * @param evento O evento de ação gerado pelo clique no botão.
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        // Ação para o botão FILTRAR
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            try {
                // Chama o serviço para buscar os funcionários com base no filtro
                List<Funcionario> resultados = servicoFuncionario.buscarPorFiltro(filtro, tipoBusca);
                this.telaBusca.preencheTabela(resultados);
                
            } catch (RuntimeException e) {
                 JOptionPane.showMessageDialog(null, 
                    "Falha ao realizar a busca no banco de dados. Tente novamente.", 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace();
            }
            
        // Ação para o botão SAIR
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
            
        // Ação para o botão CARREGAR (recarregar todos os dados)
        } else if (evento.getSource() == this.telaBusca.getjButtonCarregar()) {
            // Limpa o campo de filtro e recarrega a tabela com todos os dados.
            this.telaBusca.getjTFFiltro().setText("");
            preencheTabelaComDados();
        }
    }
}