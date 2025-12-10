package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.ProdutoCopa;
import service.ServicoProdutoCopa;
import view.TelaBuscaProdutoCopa;

public class ControllerBuscaProdutoCopa implements ActionListener {

    TelaBuscaProdutoCopa telaBusca;
    ServicoProdutoCopa servicoProdutoCopa = new ServicoProdutoCopa();

    public ControllerBuscaProdutoCopa(TelaBuscaProdutoCopa telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        List<ProdutoCopa> listaProdutos = servicoProdutoCopa.buscarTodos();
        this.telaBusca.preencheTabela(listaProdutos);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            List<ProdutoCopa> resultados = servicoProdutoCopa.buscarPorFiltro(filtro, tipoBusca);
            this.telaBusca.preencheTabela(resultados);
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}