package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.VagaEstacionamento;
import service.ServicoVagaEstacionamento;
import view.TelaBuscaVaga;
import view.TelaCadastroVaga;
import utilities.Utilities;

public class ControllerCadVaga implements ActionListener {

    TelaCadastroVaga telaCadastro;
    ServicoVagaEstacionamento servicoVaga = new ServicoVagaEstacionamento();
    private VagaEstacionamento vagaAtual; 

    public ControllerCadVaga(TelaCadastroVaga telaCadastro) {
        this.telaCadastro = telaCadastro;
        this.vagaAtual = new VagaEstacionamento();
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
        
        if (this.telaCadastro.getjTextFieldId() != null) {
            this.telaCadastro.getjTextFieldId().setEnabled(false); 
        }
    }
    
    private void carregarDadosNaTela() {
        if (this.vagaAtual != null) {
            this.telaCadastro.getjTextFieldId().setText(String.valueOf(this.vagaAtual.getId()));
            this.telaCadastro.getjTextFieldDescricao().setText(this.vagaAtual.getDescricao());
            this.telaCadastro.getjTextFieldObs().setText(this.vagaAtual.getObs());
            
            String metragemFormatada = String.format("%.2f", this.vagaAtual.getMetragemVaga()).replace(".", ",");
            this.telaCadastro.getjFormattedTextFieldMetragem().setText(metragemFormatada);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.vagaAtual = new VagaEstacionamento(); 
            if (this.telaCadastro.getjTextFieldId() != null) {
                 this.telaCadastro.getjTextFieldId().setEnabled(false);
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            this.vagaAtual = new VagaEstacionamento();
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            if (this.telaCadastro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório.");
                return;
            }
            
            try {
                String metragemTexto = this.telaCadastro.getjFormattedTextFieldMetragem().getText();
                float metragem = Float.parseFloat(metragemTexto.replace(",", "."));
                
                this.vagaAtual.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                this.vagaAtual.setObs(this.telaCadastro.getjTextFieldObs().getText());
                this.vagaAtual.setMetragemVaga(metragem);
                this.vagaAtual.setStatus('A'); 

                boolean isNovoRegistro = this.vagaAtual.getId() == 0;
                
                servicoVaga.salvar(this.vagaAtual);
                
                String mensagem = isNovoRegistro ? "Vaga cadastrada com sucesso!" : "Vaga atualizada com sucesso!";
                JOptionPane.showMessageDialog(null, mensagem);
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                this.vagaAtual = new VagaEstacionamento();
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O campo 'Metragem' deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            
            TelaBuscaVaga telaBusca = new TelaBuscaVaga(null, true);
            ControllerBuscaVaga controllerBusca = new ControllerBuscaVaga(telaBusca);
            telaBusca.setVisible(true);
            
            if (ControllerBuscaVaga.codigoSelecionado != 0) {
                this.vagaAtual = servicoVaga.buscarPorId(ControllerBuscaVaga.codigoSelecionado);
                
                if (this.vagaAtual != null) {
                    Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                    Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                    
                    carregarDadosNaTela();
                    
                    ControllerBuscaVaga.codigoSelecionado = 0;
                }
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}