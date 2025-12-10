package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.VagaEstacionamento;
import service.ServicoVagaEstacionamento;
import view.TelaBuscaVaga;

public class ControllerBuscaVaga implements ActionListener {

    TelaBuscaVaga telaBusca;
    ServicoVagaEstacionamento servicoVaga = new ServicoVagaEstacionamento();

    public ControllerBuscaVaga(TelaBuscaVaga telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        List<VagaEstacionamento> listaVagas = servicoVaga.buscarTodos();
        this.telaBusca.preencheTabela(listaVagas);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            this.telaBusca.preencheTabela(servicoVaga.buscarPorFiltro(filtro, tipoBusca));
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}