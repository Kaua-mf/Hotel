package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Fornecedor;
import service.ServicoFornecedor;
import view.TelaBuscaFornecedor;
import view.TelaCadastroFornecedor;
import utilities.Utilities;

public class ControllerCadFornecedor implements ActionListener {
    
    // Esta variável estática é usada para receber o ID selecionado na tela de busca
    public static int codigo = 0;
    
    TelaCadastroFornecedor telaCadastro;
    ServicoFornecedor servicoFornecedor = new ServicoFornecedor();
    Fornecedor fornecedorAtual; 

    public ControllerCadFornecedor(TelaCadastroFornecedor telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        // Estado inicial da tela
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        // --- BOTÃO NOVO ---
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
            
            this.fornecedorAtual = new Fornecedor();
            
            // Define data de cadastro apenas se for NOVO
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            this.telaCadastro.getjTextFieldDataCadastro().setText(agora.format(formatoBrasileiro));
            this.telaCadastro.getjTextFieldNomeFantasia().requestFocus();

        // --- BOTÃO CANCELAR ---
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
            // Reseta o código estático para evitar lixo de memória
            codigo = 0; 

        // --- BOTÃO GRAVAR ---
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            // Validações básicas
            if (this.telaCadastro.getjTextFieldNomeFantasia().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "Nome Fantasia é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
                this.telaCadastro.getjTextFieldNomeFantasia().requestFocus();
                return;
            }

            String cnpjToValidate = this.telaCadastro.getjFormattedTextFieldCnpj().getText().replaceAll("\\D", "");
            
            // Nota: Assumi que a classe ValidarDoc está no pacote service ou utilities corretamente
            // Verifica se o campo não está vazio antes de validar
            if (cnpjToValidate.isEmpty()) {
                 JOptionPane.showMessageDialog(telaCadastro, "CNPJ é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
                 return;
            }
            
            boolean cnpjValido = service.ValidarDoc.validarCNPJ(cnpjToValidate); // Certifique-se que o import está correto

            if (!cnpjValido) {
                JOptionPane.showMessageDialog(telaCadastro, "CNPJ Inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                this.telaCadastro.getjFormattedTextFieldCnpj().requestFocus();
                return;
            }
            
            // Preenchendo o objeto com os dados da tela
            this.fornecedorAtual.setNome(this.telaCadastro.getjTextFieldNomeFantasia().getText());
            this.fornecedorAtual.setRazaoSocial(this.telaCadastro.getjTextFieldRazaoSocial().getText());
            this.fornecedorAtual.setCnpj(this.telaCadastro.getjFormattedTextFieldCnpj().getText());
            this.fornecedorAtual.setInscricaoEstadual(this.telaCadastro.getjTextFieldInscricaoEstadual().getText());
            this.fornecedorAtual.setContato(this.telaCadastro.getjTextFieldContato().getText());
            this.fornecedorAtual.setFone1(this.telaCadastro.getjFormattedTextFieldFone1().getText());
            this.fornecedorAtual.setEmail(this.telaCadastro.getjTextFieldEmail().getText());
            this.fornecedorAtual.setStatus('A');
            
            // LÓGICA DE DATA:
            // Se for inclusão (ID == 0 ou nulo), define a data de cadastro.
            // Se for alteração, mantemos a data que já estava no banco (não mexemos nela aqui).
            if (this.fornecedorAtual.getId() == 0) { 
                DateTimeFormatter dtfDb = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                this.fornecedorAtual.setDataCadastro(dtfDb.format(LocalDateTime.now()));
            }
            
            try {
                servicoFornecedor.salvar(this.fornecedorAtual);
                
                JOptionPane.showMessageDialog(telaCadastro, "Dados gravados com sucesso!");
                
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                codigo = 0; // Reseta variável de controle

            } catch (Exception e) {
                JOptionPane.showMessageDialog(telaCadastro, "Erro ao gravar:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        // --- BOTÃO BUSCAR ---
        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            TelaBuscaFornecedor telaBusca = new TelaBuscaFornecedor(null, true);
            ControllerBuscaFornecedor controllerBusca = new ControllerBuscaFornecedor(telaBusca);
            
            // 1. Abre a tela (o código para aqui até a tela fechar por causa do 'modal=true')
            telaBusca.setVisible(true);
            
            // 2. Quando a tela fecha, verificamos se a variável estática 'codigo' foi preenchida
            if (codigo != 0) {
                // Buscamos o objeto completo no banco
                this.fornecedorAtual = servicoFornecedor.buscarPorId(codigo);
                
                // Habilitamos a tela para edição
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true);
                
                // 3. Método auxiliar para preencher a tela (CRIADO ABAIXO)
                carregarDadosNaTela();
            }

        // --- BOTÃO SAIR ---
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }

    /**
     * Pega os dados do Objeto fornecedorAtual e joga nos campos da tela.
     */
    private void carregarDadosNaTela() {
        if (this.fornecedorAtual != null) {
            this.telaCadastro.getjTextFieldNomeFantasia().setText(this.fornecedorAtual.getNome());
            this.telaCadastro.getjTextFieldRazaoSocial().setText(this.fornecedorAtual.getRazaoSocial());
            this.telaCadastro.getjFormattedTextFieldCnpj().setText(this.fornecedorAtual.getCnpj());
            this.telaCadastro.getjTextFieldInscricaoEstadual().setText(this.fornecedorAtual.getInscricaoEstadual());
            this.telaCadastro.getjTextFieldContato().setText(this.fornecedorAtual.getContato());
            this.telaCadastro.getjFormattedTextFieldFone1().setText(this.fornecedorAtual.getFone1());
            this.telaCadastro.getjTextFieldEmail().setText(this.fornecedorAtual.getEmail());
            
            // Formatar a data para exibir na tela (assumindo que no banco está yyyy-MM-dd HH:mm:ss)
            try {
                String dataDoBanco = this.fornecedorAtual.getDataCadastro();
                // Aqui você pode precisar converter dependendo de como vem do banco.
                // Exemplo simples:
                this.telaCadastro.getjTextFieldDataCadastro().setText(dataDoBanco); 
            } catch (Exception e) {
                this.telaCadastro.getjTextFieldDataCadastro().setText("");
            }
            
            // Importante: No seu DAO/Service, o ID deve estar preenchido no objeto fornecedorAtual
            // para que ao clicar em GRAVAR, ele saiba que é um UPDATE e não INSERT.
        }
    }
}