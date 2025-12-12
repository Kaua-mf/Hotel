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
        preencheTabelaComDados();
    }

    private void preencheTabelaComDados() {
        try {
            List<Fornecedor> listaFornecedores = servicoFornecedor.buscarTodos();
            this.telaBusca.preencheTabela(listaFornecedores);
        } catch (RuntimeException e) {
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
                List<Fornecedor> resultados = servicoFornecedor.buscarPorFiltro(filtro, tipoBusca);
                this.telaBusca.preencheTabela(resultados);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null,
                        "Falha ao realizar a busca no banco de dados. Tente novamente.",
                        "Erro de Busca",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        } else if (evento.getSource() == this.telaBusca.getjButtonCarregar()) {
            int linhaSelecionada = this.telaBusca.getjTableDados().getSelectedRow();
            if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(null, "Selecione um registro na tabela para carregar.");
                return; 
            }
            int idFornecedor = (int) this.telaBusca.getjTableDados().getValueAt(linhaSelecionada,
                    0);
            ControllerCadFornecedor.codigo = idFornecedor;
            this.telaBusca.dispose();
        }
    }
}
