package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Fornecedor;
import service.ServicoFornecedor;
import view.TelaBuscaFornecedor;

public class ControllerBuscaFornecedor implements ActionListener {

    TelaBuscaFornecedor telaBusca;
    ServicoFornecedor servicoFornecedor = new ServicoFornecedor();

    public ControllerBuscaFornecedor(TelaBuscaFornecedor telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        // Chamada inicial (deve incluir tratamento de erro)
        preencheTabelaComDados();
    }
    
    private void preencheTabelaComDados() {
        try {
            List<Fornecedor> listaFornecedores = servicoFornecedor.buscarTodos();
            this.telaBusca.preencheTabela(listaFornecedores);
        } catch (RuntimeException e) {
            // Se falhar ao carregar no início
            JOptionPane.showMessageDialog(null, 
                "Falha ao carregar dados iniciais! Verifique a conexão com o banco de dados.", 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            try {
                // A lógica de busca no banco é encapsulada pelo ServicoFornecedor
                List<Fornecedor> resultados = servicoFornecedor.buscarPorFiltro(filtro, tipoBusca);
                this.telaBusca.preencheTabela(resultados);
            } catch (RuntimeException e) {
                // Se a consulta de filtro falhar (ex: erro SQL ou conexão)
                 JOptionPane.showMessageDialog(null, 
                    "Falha ao realizar a busca no banco de dados. Tente novamente.", 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace();
            }
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}