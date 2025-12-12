package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Servico;
import service.ServicoServico;
import view.TelaBuscaServico;

public class ControllerBuscaServico implements ActionListener {

    TelaBuscaServico telaBusca;
    ServicoServico servicoServico = new ServicoServico();
    
    public static int codigoSelecionado;

    public ControllerBuscaServico(TelaBuscaServico telaBusca) {
        this.telaBusca = telaBusca;
        codigoSelecionado = 0;

        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        carregarDadosIniciais();
    }
    
    private void carregarDadosIniciais() {
        try {
            List<Servico> listaServicos = servicoServico.buscarTodos();
            this.telaBusca.preencheTabela(listaServicos);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this.telaBusca, 
                "Erro ao carregar dados iniciais: " + e.getMessage(), 
                "Erro de Carga", 
                JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) { 
            
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            try {
                List<Servico> resultados;
                
                if (filtro.trim().isEmpty()) {
                    resultados = servicoServico.buscarTodos();
                } else {
                    resultados = servicoServico.buscarPorFiltro(filtro, tipoBusca);
                }
                
                this.telaBusca.preencheTabela(resultados);
                
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this.telaBusca, 
                    "Erro ao filtrar dados: " + e.getMessage(), 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace();
            }
            
        } else if (evento.getSource() == this.telaBusca.getjButtonCarregar()) {
            
            int selectedRow = this.telaBusca.getjTableDados().getSelectedRow(); 

            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(this.telaBusca, "Selecione uma linha para carregar.");
            } else {
                int codigo = (int) this.telaBusca.getjTableDados().getValueAt(selectedRow, 0);
                ControllerBuscaServico.codigoSelecionado = codigo;
                this.telaBusca.dispose(); 
            }
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
             ControllerBuscaServico.codigoSelecionado = 0;
            this.telaBusca.dispose();
        }
    }
}