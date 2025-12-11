package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Quarto;
import service.ServicoQuarto;
import view.TelaBuscaQuarto;

public class ControllerBuscaQuarto implements ActionListener {

    TelaBuscaQuarto telaBusca;
    ServicoQuarto servicoQuarto = new ServicoQuarto();
    
    public static int codigoSelecionado;

    public ControllerBuscaQuarto(TelaBuscaQuarto telaBusca) {
        this.telaBusca = telaBusca;
        codigoSelecionado = 0; 

        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        carregarDadosIniciais();
    }
    
    private void carregarDadosIniciais() {
        try {
             List<Quarto> listaQuartos = servicoQuarto.buscarTodos();
             this.telaBusca.preencheTabela(listaQuartos); 
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
            
            String nomeColuna; 
            
            switch (tipoBusca) {
                case 0:
                    nomeColuna = "id";
                    break;
                case 1:
                    nomeColuna = "descricao"; 
                    break;
                case 2:
                    nomeColuna = "identificacao"; 
                    break;
                default:
                    nomeColuna = null;
                    break;
            }
            // FIM DA SUBSTITUIÇÃO

            try {
                List<Quarto> resultados = servicoQuarto.buscarPorFiltro(nomeColuna, filtro);
                this.telaBusca.preencheTabela(resultados);
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBusca, "Erro ao filtrar dados.", "Erro de Busca", JOptionPane.ERROR_MESSAGE);
            }

            try {
                List<Quarto> resultados = servicoQuarto.buscarPorFiltro(nomeColuna, filtro);
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
                ControllerBuscaQuarto.codigoSelecionado = codigo;
                this.telaBusca.dispose(); 
            }
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            ControllerBuscaQuarto.codigoSelecionado = 0;
            this.telaBusca.dispose();
        }
    }
}