package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Marca;
import model.Modelo;
import service.ServicoMarca;
import service.ServicoModelo;
import utilities.Utilities;
import view.TelaBuscaModelo;
import view.TelaCadastroModelo;

public class ControllerCadModelo implements ActionListener {

    TelaCadastroModelo tela;
    ServicoModelo servicoModelo = new ServicoModelo();
    ServicoMarca servicoMarca = new ServicoMarca();
    Modelo modeloAtual;

    public ControllerCadModelo(TelaCadastroModelo tela) {
        this.tela = tela;
        this.modeloAtual = new Modelo();

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);
        
        carregarMarcasNoComboBox();

        Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
    }

    private void carregarDadosNaTela() {
        if (this.modeloAtual != null) {
            this.tela.getjTextFieldId().setText(String.valueOf(this.modeloAtual.getId()));
            this.tela.getjTextFieldModelo().setText(this.modeloAtual.getNome());
            
            Marca marcaDoModelo = this.modeloAtual.getMarca();
            if (marcaDoModelo != null) {
                DefaultComboBoxModel<Marca> model = (DefaultComboBoxModel<Marca>) this.tela.getjComboBoxMarca().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.getElementAt(i).getId() == marcaDoModelo.getId()) {
                        this.tela.getjComboBoxMarca().setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }


    private void carregarMarcasNoComboBox() {
        try {
            List<Marca> listaMarcas = servicoMarca.buscarTodos();
            
            DefaultComboBoxModel<Marca> comboBoxModel = new DefaultComboBoxModel<>(
                listaMarcas.toArray(new Marca[0])
            );
            
            this.tela.getjComboBoxMarca().setModel(comboBoxModel); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar marcas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjButtonNovo()) {
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
            this.modeloAtual = new Modelo();
            this.tela.getjTextFieldId().setEnabled(false);
            this.tela.getjTextFieldModelo().requestFocus(); 

        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
            this.modeloAtual = new Modelo(); 

        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            
            String nomeModelo = this.tela.getjTextFieldModelo().getText();
            Marca marcaSelecionada = (Marca) this.tela.getjComboBoxMarca().getSelectedItem();
            
            if (nomeModelo == null || nomeModelo.trim().isEmpty()) { 
                JOptionPane.showMessageDialog(null, "O campo 'Modelo' é obrigatório.");
                this.tela.getjTextFieldModelo().requestFocus();
                return;
            }
            if (marcaSelecionada == null || marcaSelecionada.getId() == 0) { 
                JOptionPane.showMessageDialog(null, "Selecione uma 'Marca' válida.");
                return;
            }
            
            this.modeloAtual.setNome(nomeModelo); 
            this.modeloAtual.setMarca(marcaSelecionada); 
            this.modeloAtual.setStatus('A');
            
            try {
                boolean isNovoRegistro = this.modeloAtual.getId() == 0;
                
                servicoModelo.salvar(this.modeloAtual); 
                
                String mensagem = isNovoRegistro ? 
                                  "Modelo salvo com sucesso!" : 
                                  "Modelo atualizado com sucesso!";
                                  
                JOptionPane.showMessageDialog(null, mensagem);
                
                Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
                this.modeloAtual = new Modelo(); 
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar/atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } else if (e.getSource() == this.tela.getjButtonBuscar()) {
            
            TelaBuscaModelo telaBusca = new TelaBuscaModelo(null, true);
            ControllerBuscaModelo controllerBusca = new ControllerBuscaModelo(telaBusca); 
            telaBusca.setVisible(true);
            
            if (ControllerBuscaModelo.codigoSelecionado != 0) {
                
                this.modeloAtual = servicoModelo.buscarPorId(ControllerBuscaModelo.codigoSelecionado);
                
                if (this.modeloAtual != null) {
                    Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
                    
                    carregarDadosNaTela(); 
                    
                    ControllerBuscaModelo.codigoSelecionado = 0; 
                } else {
                    JOptionPane.showMessageDialog(tela, "Erro ao buscar o modelo com ID: " + ControllerBuscaModelo.codigoSelecionado);
                }
            }
            
        } else if (e.getSource() == this.tela.getjButtonSair()) {
            this.tela.dispose();
        }
    }
}