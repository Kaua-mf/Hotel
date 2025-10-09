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
        this.vagaAtual = new VagaEstacionamento(); // Inicializa o objeto
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        // Configuração inicial: Botões Novo/Buscar ativos, campos desativados
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            this.vagaAtual = new VagaEstacionamento(); // Prepara novo objeto
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            // --- VALIDAÇÕES ---
            if (this.telaCadastro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório.");
                return;
            }
            
            // --- MAPEAMENTO E PERSISTÊNCIA ---
            try {
                // 1. Mapeamento e Validação da Metragem
                String metragemTexto = this.telaCadastro.getjFormattedTextFieldMetragem().getText();
                float metragem = Float.parseFloat(metragemTexto.replace(",", "."));
                
                // 2. Mapeamento dos campos
                this.vagaAtual.setDescricao(this.telaCadastro.getjTextFieldDescricao().getText());
                this.vagaAtual.setObs(this.telaCadastro.getjTextFieldObs().getText());
                this.vagaAtual.setMetragemVaga(metragem);
                this.vagaAtual.setStatus('A'); 

                // 3. Chamada ao Serviço com tratamento de erro
                servicoVaga.salvar(this.vagaAtual);
                
                JOptionPane.showMessageDialog(null, "Vaga salva com sucesso!");
                
                // 4. Restaura o estado da tela
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O campo 'Metragem' deve ser um número válido. Use '.' para decimal.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException e) {
                // Captura erros do DAO/DB (ex: falha de conexão/transação)
                JOptionPane.showMessageDialog(null, "Erro de Persistência: " + e.getMessage(), "ERRO DE BANCO DE DADOS", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaVaga telaBusca = new TelaBuscaVaga(null, true);
            ControllerBuscaVaga controllerBusca = new ControllerBuscaVaga(telaBusca);
            telaBusca.setVisible(true);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}