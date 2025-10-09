package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Marca;
import model.Modelo;
import model.Veiculo;
import service.ServicoMarca;
import service.ServicoModelo;
import service.ServicoVeiculo;
import view.TelaBuscaVeiculo;
import view.TelaCadastroVeiculo;
// Assumindo que a classe Utilities está no pacote utilities
import utilities.Utilities; 

public class ControllerCadVeiculo implements ActionListener {

    TelaCadastroVeiculo telaCadastro;
    Veiculo veiculoAtual; // Objeto para armazenar o veículo em edição ou novo
    
    ServicoVeiculo servicoVeiculo = new ServicoVeiculo();
    ServicoMarca servicoMarca = new ServicoMarca();
    ServicoModelo servicoModelo = new ServicoModelo();

    /**
     * Construtor de dois argumentos (Tela e Objeto Veiculo).
     * @param telaCadastro A interface gráfica de cadastro.
     * @param veiculoObjeto O objeto Veiculo a ser carregado ou um novo objeto.
     */
    public ControllerCadVeiculo(view.TelaCadastroVeiculo telaCadastro, Veiculo veiculo) {
        // CORRIGIDO: Agora aceita o segundo parâmetro 'veiculoObjeto'
        this.telaCadastro = telaCadastro;
        this.veiculoAtual = veiculo; // Inicializa o objeto
        
        // Adiciona Listeners aos botões
        this.telaCadastro.getjButtonNovo().addActionListener(this);
        this.telaCadastro.getjButtonCancelar().addActionListener(this);
        this.telaCadastro.getjButtonGravar().addActionListener(this);
        this.telaCadastro.getjButtonBuscar().addActionListener(this);
        this.telaCadastro.getjButtonSair().addActionListener(this);
        
        // Carrega os dados das chaves estrangeiras
        carregarMarcas();
        carregarModelos();

        // Configuração inicial da tela
        // Se o objeto tiver dados, ele deve ser carregado aqui.
        Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); // Deixa 'Novo' e 'Buscar' ativos
        Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); // Deixa campos desativados
    }

    private void carregarMarcas() {
        try {
            // Assume que ServicoMarca.buscarTodos() chama o DAO e retorna List<Marca>
            DefaultComboBoxModel<Marca> model = new DefaultComboBoxModel<>(servicoMarca.buscarTodos().toArray(new Marca[0]));
            this.telaCadastro.getjComboBoxMarca().setModel(model);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Marcas: " + e.getMessage(), "Erro de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarModelos() {
        try {
            // Assume que ServicoModelo.buscarTodos() chama o DAO e retorna List<Modelo>
            DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<>(servicoModelo.buscarTodos().toArray(new Modelo[0]));
            this.telaCadastro.getjComboBoxModelo().setModel(model);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Modelos: " + e.getMessage(), "Erro de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastro.getjButtonNovo()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), false); // Desativa Novo/Buscar
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), true); // Ativa campos e limpa
            
            // Cria um novo objeto Veiculo para ser preenchido
            this.veiculoAtual = new Veiculo();
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonCancelar()) {
            Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true); // Ativa Novo/Buscar
            Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false); // Desativa campos
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonGravar()) {
            
            Marca marcaSelecionada = (Marca) this.telaCadastro.getjComboBoxMarca().getSelectedItem();
            Modelo modeloSelecionado = (Modelo) this.telaCadastro.getjComboBoxModelo().getSelectedItem();
            
            // --- VALIDAÇÕES E OBRIGATORIEDADE ---
            if (this.telaCadastro.getjTextFieldPlaca().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo 'Placa' é obrigatório.");
                return;
            }
            
            // Validação de Chaves Estrangeiras
            if (marcaSelecionada == null || marcaSelecionada.getId() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione uma Marca válida.");
                return;
            }
            if (modeloSelecionado == null || modeloSelecionado.getId() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um Modelo válido.");
                return;
            }
            
            // --- ATRIBUIÇÃO E PERSISTÊNCIA ---
            this.veiculoAtual.setPlaca(this.telaCadastro.getjTextFieldPlaca().getText());
            this.veiculoAtual.setCor(this.telaCadastro.getjTextFieldCor().getText());
            this.veiculoAtual.setMarca(marcaSelecionada);
            this.veiculoAtual.setModelo(modeloSelecionado);
            this.veiculoAtual.setStatus('A');
            
            // Trata as demais FKs como nulas (para não violar NOT NULL se for o caso)
            this.veiculoAtual.setFuncionario(null);
            this.veiculoAtual.setFornecedor(null);
            this.veiculoAtual.setHospede(null);

            try {
                // Chama o Service para persistir no DB
                servicoVeiculo.salvar(this.veiculoAtual);
                
                JOptionPane.showMessageDialog(null, "Veículo salvo com sucesso!");
                
                // Restaura a tela para o estado inicial
                Utilities.ativaDesativa(this.telaCadastro.getjPanelBotoes(), true);
                Utilities.limpaComponentes(this.telaCadastro.getjPanelDados(), false);
                
            } catch (RuntimeException e) {
                 // Captura erros do DAO/DB (como a falha de Chave Estrangeira)
                 JOptionPane.showMessageDialog(null, 
                    "Erro ao salvar no banco: " + e.getMessage(), 
                    "ERRO DE PERSISTÊNCIA", 
                    JOptionPane.ERROR_MESSAGE);
            }

        } else if (evento.getSource() == this.telaCadastro.getjButtonBuscar()) {
            // Fluxo de busca
            TelaBuscaVeiculo telaBusca = new TelaBuscaVeiculo(null, true);
            // Assumindo que ControllerBuscaVeiculo recebe apenas a tela
            ControllerBuscaVeiculo controllerBusca = new ControllerBuscaVeiculo(telaBusca); 
            telaBusca.setVisible(true);
            
        } else if (evento.getSource() == this.telaCadastro.getjButtonSair()) {
            this.telaCadastro.dispose();
        }
    }
}