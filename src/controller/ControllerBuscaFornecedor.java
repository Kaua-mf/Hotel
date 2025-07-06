package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
        List<Fornecedor> listaFornecedores = servicoFornecedor.buscarTodos();
        this.telaBusca.preencheTabela(listaFornecedores);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            List<Fornecedor> resultados = servicoFornecedor.buscarPorFiltro(filtro, tipoBusca);
            this.telaBusca.preencheTabela(resultados);
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}