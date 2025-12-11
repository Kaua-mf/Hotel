package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Veiculo;
import service.ServicoVeiculo;
import view.TelaBuscaVeiculo;

public class ControllerBuscaVeiculo implements ActionListener {

    TelaBuscaVeiculo telaBusca;
    ServicoVeiculo servicoVeiculo = new ServicoVeiculo();
    
    public static int codigoSelecionado;

    public ControllerBuscaVeiculo(TelaBuscaVeiculo telaBusca) {
        this.telaBusca = telaBusca;
        codigoSelecionado = 0; 

        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        carregarDadosIniciais();
    }
    
    private void carregarDadosIniciais() {
        try {
             List<Veiculo> listaVeiculos = servicoVeiculo.buscarTodos();
             // Assumindo que sua TelaBuscaVeiculo tem o método preencheTabela
             this.telaBusca.preencheTabela(listaVeiculos); 
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
            // Assumindo que 0=ID, 1=Placa, 2=Cor
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            try {
                List<Veiculo> resultados = servicoVeiculo.buscarPorFiltro(filtro, tipoBusca);
                this.telaBusca.preencheTabela(resultados);
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBusca, "Erro ao filtrar dados.", "Erro de Busca", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (evento.getSource() == this.telaBusca.getjButtonCarregar()) {
            int selectedRow = this.telaBusca.getjTableDados().getSelectedRow(); 

            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(this.telaBusca, "Selecione uma linha para carregar.");
            } else {
                // Assume que o ID está na coluna 0 da tabela
                int codigo = (int) this.telaBusca.getjTableDados().getValueAt(selectedRow, 0);
                ControllerBuscaVeiculo.codigoSelecionado = codigo;
                this.telaBusca.dispose(); 
            }
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            ControllerBuscaVeiculo.codigoSelecionado = 0;
            this.telaBusca.dispose();
        }
    }
}