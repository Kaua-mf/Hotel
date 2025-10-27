package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Marca;
import service.ServicoMarca;
import utilities.Utilities;
import view.TelaBuscaMarca;      // ***** Importe a TelaBuscaMarca *****
import view.TelaCadastroMarca; 

public class ControllerCadMarca implements ActionListener {

    TelaCadastroMarca tela;
    ServicoMarca servicoMarca = new ServicoMarca();
    Marca marcaAtual;

    public ControllerCadMarca(TelaCadastroMarca tela) {
        this.tela = tela;
        this.marcaAtual = new Marca(); // Inicia com um objeto vazio

        // Adiciona os listeners aos botões
        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);
        
        // Configuração inicial da tela
        Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjButtonNovo()) {
            // Ação NOVO
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), true);
            this.marcaAtual = new Marca(); // Cria um novo objeto
            this.tela.getjTextFieldId().setEnabled(false); // ID não deve ser editável
            this.tela.getjTextFieldMarca().requestFocus(); // Foco no campo de texto

        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            // Ação CANCELAR
            Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
            // Limpa o objeto atual também, para evitar salvar dados antigos se clicar Novo -> Cancelar -> Gravar
            this.marcaAtual = new Marca(); 

        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            // Ação GRAVAR
            
            String nomeMarca = this.tela.getjTextFieldMarca().getText();            
            
            if (nomeMarca.trim().isEmpty()) {
               JOptionPane.showMessageDialog(null, "O campo 'Marca' é obrigatório.");
               this.tela.getjTextFieldMarca().requestFocus(); 
               return;
            }
            
            this.marcaAtual.setDescricao(nomeMarca); 
            this.marcaAtual.setStatus('A'); 
            
            try {
                if (this.marcaAtual.getId() > 0) {
                     servicoMarca.atualizar(this.marcaAtual); 
                     JOptionPane.showMessageDialog(null, "Marca atualizada com sucesso!");
                } else {
                     servicoMarca.salvar(this.marcaAtual); 
                     JOptionPane.showMessageDialog(null, "Marca salva com sucesso!");
                }
                
                Utilities.ativaDesativa(this.tela.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.tela.getjPanelDados(), false);
                this.marcaAtual = new Marca(); // Limpa o objeto após salvar/atualizar
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar/atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); 
            }

        } else if (e.getSource() == this.tela.getjButtonBuscar()) {
            // ***** AÇÃO BUSCAR SIMPLIFICADA *****
            
            // 1. Cria a Tela de Busca
            TelaBuscaMarca telaBusca = new TelaBuscaMarca(null, true); 
            
            // 2. Cria o Controller da Tela de Busca
            ControllerBuscaMarca controllerBusca = new ControllerBuscaMarca(telaBusca); 
            
            // 3. Exibe a Tela de Busca (o código pausa aqui até ela fechar)
            telaBusca.setVisible(true); 
            
            // 4. NÃO FAZ NADA com o resultado selecionado por enquanto.
            //    (O código que usava getCodigoSelecionado() foi removido)
            
        } else if (e.getSource() == this.tela.getjButtonSair()) {
            // Ação SAIR
            this.tela.dispose();
        }
    }
}