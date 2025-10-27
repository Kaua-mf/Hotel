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

    public ControllerBuscaVeiculo(TelaBuscaVeiculo telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        preencheTabelaComDados();
    }

    private void preencheTabelaComDados() {
        try {
            List<Veiculo> listaVeiculos = servicoVeiculo.buscarTodos();
            this.telaBusca.preencheTabela(listaVeiculos);
        } catch (RuntimeException e) {
             JOptionPane.showMessageDialog(null, 
                "Falha ao carregar dados. Verifique a conexão com o banco de dados.", 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            try {
                List<Veiculo> resultados = servicoVeiculo.buscarPorFiltro(filtro, tipoBusca);
                this.telaBusca.preencheTabela(resultados);
            } catch (RuntimeException e) {
                 JOptionPane.showMessageDialog(null, 
                    "Falha ao buscar dados. Erro: " + e.getMessage(), 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}