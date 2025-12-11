package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ProdutoCopa;
import service.ServicoProdutoCopa;
import view.TelaBuscaProdutoCopa; 

public class ControllerBuscaProdutoCopa implements ActionListener {

    TelaBuscaProdutoCopa telaBusca; 
    ServicoProdutoCopa servico = new ServicoProdutoCopa(); 
    
    public static int codigoSelecionado; 

    public ControllerBuscaProdutoCopa(TelaBuscaProdutoCopa telaBusca) {
        this.telaBusca = telaBusca;
        
        codigoSelecionado = 0; 

        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);

        carregarDadosIniciais(); 
    }

    private void carregarDadosIniciais() {
        try {
            List<ProdutoCopa> listaProdutos = servico.buscarTodos(); 
            preencheTabela(listaProdutos); 
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this.telaBusca, 
                "Erro ao carregar dados iniciais: " + e.getMessage(), 
                "Erro de Carga", 
                JOptionPane.ERROR_MESSAGE);
             e.printStackTrace(); 
        }
    }

    private void preencheTabela(List<ProdutoCopa> listaProdutos) { 
        DefaultTableModel tabela = (DefaultTableModel) this.telaBusca.getjTableDados().getModel();
        tabela.setRowCount(0); 

        if (listaProdutos != null) { 
            for (ProdutoCopa produto : listaProdutos) { 
                tabela.addRow(new Object[]{
                    produto.getId(),
                    produto.getDescricao(), 
                    produto.getValor(),
                    produto.getStatus() 
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == this.telaBusca.getjButtonCarregar()) { 
            int selectedRow = this.telaBusca.getjTableDados().getSelectedRow(); 

            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(this.telaBusca, "Selecione uma linha para carregar.");
            } else {
                int codigo = (int) this.telaBusca.getjTableDados().getValueAt(selectedRow, 0);
                
                ControllerBuscaProdutoCopa.codigoSelecionado = codigo;
                
                this.telaBusca.dispose(); 
            }

        } else if (evento.getSource() == this.telaBusca.getjButtonFiltar()) { 
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBuscaIndex = this.telaBusca.getjCBFiltro().getSelectedIndex(); 

            try {
                List<ProdutoCopa> listaProdutos; 

                if (filtro.trim().isEmpty()) {
                    listaProdutos = servico.buscarTodos(); 
                } else {
                    listaProdutos = servico.buscarPorFiltro(filtro, tipoBuscaIndex); 
                }
                preencheTabela(listaProdutos); 

            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBusca, 
                    "Erro ao filtrar dados: " + e.getMessage(), 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace(); 
            }

        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            ControllerBuscaProdutoCopa.codigoSelecionado = 0;
            this.telaBusca.dispose(); 
        }
    }
}