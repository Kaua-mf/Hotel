package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Modelo;
import service.ServicoModelo;
import view.TelaBuscaModelo; 

public class ControllerBuscaModelo implements ActionListener {

    TelaBuscaModelo telaBuscaModelo; 
    ServicoModelo servicoModelo = new ServicoModelo(); 
    
    public static int codigoSelecionado = 0; 

    public ControllerBuscaModelo(TelaBuscaModelo telaBuscaModelo) {
        this.telaBuscaModelo = telaBuscaModelo;
        
        codigoSelecionado = 0; 

        this.telaBuscaModelo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaModelo.getjButtonFiltar().addActionListener(this);
        this.telaBuscaModelo.getjButtonSair().addActionListener(this);

        carregarDadosIniciais(); 
    }

    private void carregarDadosIniciais() {
        try {
            List<Modelo> listaModelos = servicoModelo.buscarTodos(); 
            preencheTabela(listaModelos); 
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this.telaBuscaModelo, 
                "Erro ao carregar dados iniciais: " + e.getMessage(), 
                "Erro de Carga", 
                JOptionPane.ERROR_MESSAGE);
             e.printStackTrace(); 
        }
    }

    private void preencheTabela(List<Modelo> listaModelos) { 
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
        tabela.setRowCount(0); 

        if (listaModelos != null) { 
            for (Modelo modelo : listaModelos) { 
                tabela.addRow(new Object[]{
                    modelo.getId(),
                    modelo.getNome(), 
                    (modelo.getMarca() != null ? modelo.getMarca().getDescricao() : "Sem Marca"), 
                    modelo.getStatus() 
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == this.telaBuscaModelo.getjButtonCarregar()) { 
            
            int selectedRow = this.telaBuscaModelo.getjTableDados().getSelectedRow(); 

            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(this.telaBuscaModelo, "Selecione uma linha para carregar.");
            } else {
                int codigo = (int) this.telaBuscaModelo.getjTableDados().getValueAt(selectedRow, 0);
                ControllerBuscaModelo.codigoSelecionado = codigo;
                this.telaBuscaModelo.dispose(); 
            }

        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonFiltar()) { 
            
            String filtro = this.telaBuscaModelo.getjTFFiltro().getText();
            int tipoBuscaIndex = this.telaBuscaModelo.getjCBFiltro().getSelectedIndex(); 

            try {
                List<Modelo> listaModelos; 

                if (filtro.trim().isEmpty()) {
                    listaModelos = servicoModelo.buscarTodos(); 
                } else {
                    listaModelos = servicoModelo.buscarPorFiltro(filtro, tipoBuscaIndex); 
                }
                preencheTabela(listaModelos); 

            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBuscaModelo, 
                    "Erro ao filtrar dados: " + e.getMessage(), 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace(); 
            }

        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonSair()) {
            ControllerBuscaModelo.codigoSelecionado = 0;
            this.telaBuscaModelo.dispose(); 
        }
    }
}