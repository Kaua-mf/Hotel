package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Hospede;
import service.ServicoHospede;
import view.TelaBuscaHospede;

public class ControllerBuscaHospede implements ActionListener {

    public static int codigoSelecionado = 0; 

    TelaBuscaHospede telaBusca;
    ServicoHospede servicoHospede = new ServicoHospede();

    public ControllerBuscaHospede(TelaBuscaHospede telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        codigoSelecionado = 0;
        
        preencheTabelaComDados();
    }
    
    private void preencheTabelaComDados() {
        List<Hospede> listaHospede = servicoHospede.buscarTodos();
        this.telaBusca.preencheTabela(listaHospede);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex(); 
            
            List<Hospede> resultados = servicoHospede.buscarPorFiltro(filtro, tipoBusca);
            this.telaBusca.preencheTabela(resultados);
            
        } else if (evento.getSource() == this.telaBusca.getjButtonCarregar()) {
            int linhaSelecionada = this.telaBusca.getjTableDados().getSelectedRow();
            
            if (linhaSelecionada != -1) {
                codigoSelecionado = (int) this.telaBusca.getjTableDados().getValueAt(linhaSelecionada, 0);
                this.telaBusca.dispose(); 
            }
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}