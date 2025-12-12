package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Marca;
import service.ServicoMarca; 
import view.TelaBuscaMarca; 

public class ControllerBuscaMarca implements ActionListener {

    TelaBuscaMarca telaBuscaMarca; 
    ServicoMarca servicoMarca = new ServicoMarca(); 
    
    public static int codigoSelecionado = 0; 

    public ControllerBuscaMarca(TelaBuscaMarca telaBuscaMarca) {
        this.telaBuscaMarca = telaBuscaMarca;
        
        codigoSelecionado = 0;

        this.telaBuscaMarca.getjButtonCarregar().addActionListener(this);
        this.telaBuscaMarca.getjButtonFiltar().addActionListener(this); 
        this.telaBuscaMarca.getjButtonSair().addActionListener(this);

        carregarDadosIniciais(); 
    }

    private void carregarDadosIniciais() {
        try {
            List<Marca> listaMarcas = servicoMarca.buscarTodos(); 
            preencheTabela(listaMarcas); 
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this.telaBuscaMarca, 
                "Erro ao carregar dados iniciais: " + e.getMessage(), 
                "Erro de Carga", 
                JOptionPane.ERROR_MESSAGE);
             e.printStackTrace(); 
        }
    }

    private void preencheTabela(List<Marca> listaMarcas) {
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaMarca.getjTableDados().getModel();
        tabela.setRowCount(0); 

        if (listaMarcas != null) { 
            for (Marca marca : listaMarcas) {
                tabela.addRow(new Object[]{
                    marca.getId(),
                    marca.getDescricao(), 
                    marca.getStatus()
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == this.telaBuscaMarca.getjButtonCarregar()) {
            
            int selectedRow = this.telaBuscaMarca.getjTableDados().getSelectedRow(); 

            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(this.telaBuscaMarca, "Selecione uma linha para carregar.");
            } else {
                int codigo = (int) this.telaBuscaMarca.getjTableDados().getValueAt(selectedRow, 0);
                ControllerBuscaMarca.codigoSelecionado = codigo; 
                this.telaBuscaMarca.dispose(); 
            }

        } else if (evento.getSource() == this.telaBuscaMarca.getjButtonFiltar()) { 
            
            String filtro = this.telaBuscaMarca.getjTFFiltro().getText();
            int tipoBuscaIndex = this.telaBuscaMarca.getjCBFiltro().getSelectedIndex(); 

            try {
                List<Marca> listaMarcas;

                if (filtro.trim().isEmpty()) {
                    listaMarcas = servicoMarca.buscarTodos();
                } else {
                    listaMarcas = servicoMarca.buscarPorFiltro(filtro, tipoBuscaIndex); 
                }
                preencheTabela(listaMarcas); 

            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBuscaMarca, 
                    "Erro ao filtrar dados: " + e.getMessage(), 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace(); 
            }

        } else if (evento.getSource() == this.telaBuscaMarca.getjButtonSair()) {
            ControllerBuscaMarca.codigoSelecionado = 0; 
            this.telaBuscaMarca.dispose(); 
        }
    }
}