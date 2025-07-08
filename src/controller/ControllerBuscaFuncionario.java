package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
import service.ServicoFuncionario;
import view.TelaBuscaFuncionario; 

public class ControllerBuscaFuncionario implements ActionListener {

    TelaBuscaFuncionario telaBusca;
    ServicoFuncionario servicoFuncionario = new ServicoFuncionario();

    public ControllerBuscaFuncionario(TelaBuscaFuncionario telaBusca) {
        this.telaBusca = telaBusca;

        this.telaBusca.getjButtonCarregar().addActionListener(this);
        this.telaBusca.getjButtonFiltar().addActionListener(this);
        this.telaBusca.getjButtonSair().addActionListener(this);
        
        preencheTabela(servicoFuncionario.buscarTodos());
    }
    
    private void preencheTabela(List<Funcionario> listaFuncionarios) {
        DefaultTableModel modelo = (DefaultTableModel) this.telaBusca.getjTableDados().getModel();
        modelo.setRowCount(0);

        for (Funcionario funcionario : listaFuncionarios) {
            modelo.addRow(new Object[]{
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getUsuario(), 
                funcionario.getStatus()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBusca.getjButtonFiltar()) {
            String filtro = this.telaBusca.getjTFFiltro().getText();
            int tipoBusca = this.telaBusca.getjCBFiltro().getSelectedIndex();
            
            List<Funcionario> resultados = servicoFuncionario.buscarPorFiltro(filtro, tipoBusca);
            preencheTabela(resultados);
            
        } else if (evento.getSource() == this.telaBusca.getjButtonSair()) {
            this.telaBusca.dispose();
        }
    }
}