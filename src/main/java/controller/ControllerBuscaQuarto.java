package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Quarto;
import service.ServicoQuarto;
import view.TelaBuscaQuarto;

public class ControllerBuscaQuarto implements ActionListener {

    TelaBuscaQuarto telaBusca;
    ServicoQuarto servicoQuarto = new ServicoQuarto();

    public ControllerBuscaQuarto(TelaBuscaQuarto telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        List<Quarto> listaQuartos = servicoQuarto.buscarTodos();
        this.telaBusca.preencheTabela(listaQuartos);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            List<Quarto> resultados = servicoQuarto.buscarPorFiltro(filtro, tipoBusca);
            this.telaBusca.preencheTabela(resultados);
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}