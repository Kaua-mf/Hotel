package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.ServicoVeiculo;
import view.TelaBuscaVeiculo;

public class ControllerBuscaVeiculo implements ActionListener {

    TelaBuscaVeiculo telaBusca;
    ServicoVeiculo servicoVeiculo = new ServicoVeiculo();

    public ControllerBuscaVeiculo(TelaBuscaVeiculo telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        this.telaBusca.preencheTabela(servicoVeiculo.buscarTodos());
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            this.telaBusca.preencheTabela(servicoVeiculo.buscarPorFiltro(filtro, tipoBusca));
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}