package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Servico;
import service.ServicoServico;
import view.TelaBuscaServico;

public class ControllerBuscaServico implements ActionListener {

    TelaBuscaServico telaBusca;
    ServicoServico servicoServico = new ServicoServico();

    public ControllerBuscaServico(TelaBuscaServico telaBusca) {
        this.telaBusca = telaBusca;
        
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        List<Servico> listaServicos = servicoServico.buscarTodos();
        this.telaBusca.preencheTabela(listaServicos);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) { 
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            List<Servico> resultados = servicoServico.buscarPorFiltro(filtro, tipoBusca);
            this.telaBusca.preencheTabela(resultados);
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}