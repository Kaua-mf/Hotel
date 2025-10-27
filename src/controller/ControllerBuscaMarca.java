package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Marca;
import service.ServicoMarca; // Import the service
import view.TelaBuscaMarca; // ***** CORRECTION: Use the correct View class *****

public class ControllerBuscaMarca implements ActionListener {

    // ***** CORRECTION: Use the correct View class *****
    TelaBuscaMarca telaBuscaMarca; 
    // ***** CORRECTION: Instantiate the service *****
    ServicoMarca servicoMarca = new ServicoMarca(); 

    // ***** CORRECTION: Constructor takes the correct View class *****
    public ControllerBuscaMarca(TelaBuscaMarca telaBuscaMarca) {
        this.telaBuscaMarca = telaBuscaMarca;

        // Add listeners to buttons
        this.telaBuscaMarca.getjButtonCarregar().addActionListener(this);
        this.telaBuscaMarca.getjButtonFiltar().addActionListener(this); // Assuming 'Filtar' is the correct name
        this.telaBuscaMarca.getjButtonSair().addActionListener(this);

        // ***** CORRECTION: Load initial data when the screen opens *****
        carregarDadosIniciais(); 
    }

    // Helper method to load initial data
    private void carregarDadosIniciais() {
        try {
            // ***** CORRECTION: Call buscarTodos() from the service instance *****
            List<Marca> listaMarcas = servicoMarca.buscarTodos(); 
            preencheTabela(listaMarcas); // Use the helper to fill the table
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this.telaBuscaMarca, 
                "Erro ao carregar dados iniciais: " + e.getMessage(), 
                "Erro de Carga", 
                JOptionPane.ERROR_MESSAGE);
             e.printStackTrace(); // Print detailed error to console
        }
    }

    // Helper method to populate the JTable
    private void preencheTabela(List<Marca> listaMarcas) {
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaMarca.getjTableDados().getModel();
        tabela.setRowCount(0); // Clear existing rows

        if (listaMarcas != null) { // Check if the list is not null
            for (Marca marca : listaMarcas) {
                tabela.addRow(new Object[]{
                    marca.getId(),
                    marca.getDescricao(), // Assumes Marca has getDescricao()
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
                
                // Assumes TelaBuscaMarca has this method to store the selected ID
                
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
                    // ***** CORRECTION: Pass the filter text and the index *****
                    // The service (ServicoMarca) will use tipoBuscaIndex to choose the column
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
            this.telaBuscaMarca.dispose(); 
        }
    }
}