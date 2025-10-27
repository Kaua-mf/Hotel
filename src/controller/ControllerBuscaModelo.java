package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Import ArrayList
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Modelo; // ***** CORREÇÃO: Importar Modelo *****
import service.ServicoModelo; // ***** CORREÇÃO: Importar ServicoModelo *****
import view.TelaBuscaModelo; // ***** CORREÇÃO: Usar TelaBuscaModelo *****

public class ControllerBuscaModelo implements ActionListener {

    // ***** CORREÇÃO: Usar TelaBuscaModelo *****
    TelaBuscaModelo telaBuscaModelo; 
    // ***** CORREÇÃO: Instanciar ServicoModelo *****
    ServicoModelo servicoModelo = new ServicoModelo(); 

    // ***** CORREÇÃO: Construtor recebe TelaBuscaModelo *****
    public ControllerBuscaModelo(TelaBuscaModelo telaBuscaModelo) {
        this.telaBuscaModelo = telaBuscaModelo;

        // Adiciona listeners aos botões da TelaBuscaModelo
        this.telaBuscaModelo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaModelo.getjButtonFiltar().addActionListener(this); // Assumindo 'Filtar'
        this.telaBuscaModelo.getjButtonSair().addActionListener(this);

        // ***** CORREÇÃO: Carrega dados de Modelo *****
        carregarDadosIniciais(); 
    }

    // Método para carregar dados iniciais de Modelo
    private void carregarDadosIniciais() {
        try {
            // ***** CORREÇÃO: Chama buscarTodos() de ServicoModelo *****
            List<Modelo> listaModelos = servicoModelo.buscarTodos(); 
            preencheTabela(listaModelos); // Preenche a tabela com Modelos
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this.telaBuscaModelo, // Usa a tela correta
                "Erro ao carregar dados iniciais: " + e.getMessage(), 
                "Erro de Carga", 
                JOptionPane.ERROR_MESSAGE);
             e.printStackTrace(); // Imprime erro no console
        }
    }

    // Método para preencher a JTable com Modelos
    // ***** CORREÇÃO: Recebe List<Modelo> *****
    private void preencheTabela(List<Modelo> listaModelos) { 
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
        tabela.setRowCount(0); // Limpa linhas existentes

        if (listaModelos != null) { 
            // ***** CORREÇÃO: Itera sobre lista de Modelos *****
            for (Modelo modelo : listaModelos) { 
                // Define as colunas a serem exibidas para Modelo
                // Exemplo: ID, Nome do Modelo, Nome da Marca
                tabela.addRow(new Object[]{
                    modelo.getId(),
                    modelo.getNome(), // Ou getDescricao() se você voltou a usar esse
                    // Acessa a Marca dentro do Modelo
                    (modelo.getMarca() != null ? modelo.getMarca().getDescricao() : ""), // Mostra descrição da Marca
                    modelo.getStatus() 
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        // ***** CORREÇÃO: Usa a variável telaBuscaModelo *****
        if (evento.getSource() == this.telaBuscaModelo.getjButtonCarregar()) { 
            int selectedRow = this.telaBuscaModelo.getjTableDados().getSelectedRow(); 

            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(this.telaBuscaModelo, "Selecione uma linha para carregar.");
            } else {
                int codigo = (int) this.telaBuscaModelo.getjTableDados().getValueAt(selectedRow, 0);
                
                // Assume que TelaBuscaModelo tem o método setCodigoSelecionado
                this.telaBuscaModelo.dispose(); 
            }

        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonFiltar()) { 
            String filtro = this.telaBuscaModelo.getjTFFiltro().getText();
            int tipoBuscaIndex = this.telaBuscaModelo.getjCBFiltro().getSelectedIndex(); 
             // Assume que o ComboBox de filtro na TelaBuscaModelo tem: 0=ID, 1=Nome Modelo, 2=Nome Marca

            try {
                // ***** CORREÇÃO: Lista de Modelos *****
                List<Modelo> listaModelos; 

                if (filtro.trim().isEmpty()) {
                    // ***** CORREÇÃO: Busca todos os Modelos *****
                    listaModelos = servicoModelo.buscarTodos(); 
                } else {
                    // ***** CORREÇÃO: Chama buscarPorFiltro de ServicoModelo *****
                    listaModelos = servicoModelo.buscarPorFiltro(filtro, tipoBuscaIndex); 
                }
                // ***** CORREÇÃO: Preenche tabela com Modelos *****
                preencheTabela(listaModelos); 

            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBuscaModelo, 
                    "Erro ao filtrar dados: " + e.getMessage(), 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace(); 
            }

        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonSair()) {
            this.telaBuscaModelo.dispose(); 
        }
    }
}