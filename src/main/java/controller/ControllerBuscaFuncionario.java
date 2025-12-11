package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Funcionario;
import service.ServicoFuncionario; 
import view.TelaBuscaFuncionario; 


public class ControllerBuscaFuncionario implements ActionListener {

    public static int codigoSelecionado = 0; 
    
    TelaBuscaFuncionario telaBusca;
    ServicoFuncionario servicoFuncionario = new ServicoFuncionario();

    public ControllerBuscaFuncionario(TelaBuscaFuncionario telaBusca) {
        this.telaBusca = telaBusca;
        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        codigoSelecionado = 0; 
        
        preencheTabelaComDados();
    }
    
    private void preencheTabelaComDados() {
        try {
            List<Funcionario> listaFuncionarios = servicoFuncionario.buscarTodos();
            this.telaBusca.preencheTabela(listaFuncionarios);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, 
                "Falha ao carregar dados! Verifique a conexão com o banco de dados.", 
                "Erro de Conexão", 
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
                List<Funcionario> resultados = servicoFuncionario.buscarPorFiltro(filtro, tipoBusca);
                this.telaBusca.preencheTabela(resultados);
                
            } catch (RuntimeException e) {
                 JOptionPane.showMessageDialog(null, 
                    "Falha ao realizar a busca no banco de dados. Tente novamente.", 
                    "Erro de Busca", 
                    JOptionPane.ERROR_MESSAGE);
                 e.printStackTrace();
            }
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
            
        } else if (evento.getSource() == this.telaBusca.getjButtonCarregar()) {
            int linhaSelecionada = this.telaBusca.getjTableDados().getSelectedRow();
            
            if (linhaSelecionada != -1) {
                codigoSelecionado = (int) this.telaBusca.getjTableDados().getValueAt(linhaSelecionada, 0);
                this.telaBusca.dispose(); 
            } else {
                 JOptionPane.showMessageDialog(telaBusca, "Selecione um funcionário para carregar.");
            }
        }
    }
}