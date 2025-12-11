package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.VagaEstacionamento;
import service.ServicoVagaEstacionamento;
import view.TelaBuscaVaga;

public class ControllerBuscaVaga implements ActionListener {

    TelaBuscaVaga telaBusca;
    ServicoVagaEstacionamento servicoVaga = new ServicoVagaEstacionamento();
    
    public static int codigoSelecionado;

    public ControllerBuscaVaga(TelaBuscaVaga telaBusca) {
        this.telaBusca = telaBusca;
        codigoSelecionado = 0; 

        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        carregarDadosIniciais();
    }
    
    private void carregarDadosIniciais() {
        try {
             List<VagaEstacionamento> listaVagas = servicoVaga.buscarTodos();
             this.telaBusca.preencheTabela(listaVagas); 
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this.telaBusca, 
                "Erro ao carregar dados iniciais: " + e.getMessage(), 
                "Erro de Carga", 
                JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) { 
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            try {
                List<VagaEstacionamento> resultados = servicoVaga.buscarPorFiltro(filtro, tipoBusca);
                this.telaBusca.preencheTabela(resultados);
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBusca, "Erro ao filtrar dados.", "Erro de Busca", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (evento.getSource() == this.telaBusca.getjButtonCarregar()) {
            int selectedRow = this.telaBusca.getjTableDados().getSelectedRow(); 

            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(this.telaBusca, "Selecione uma linha para carregar.");
            } else {
                int codigo = (int) this.telaBusca.getjTableDados().getValueAt(selectedRow, 0);
                ControllerBuscaVaga.codigoSelecionado = codigo;
                this.telaBusca.dispose(); 
            }
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            ControllerBuscaVaga.codigoSelecionado = 0;
            this.telaBusca.dispose();
        }
    }
}