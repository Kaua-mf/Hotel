# Sistema de Gestão Hoteleira (ProjetoHotel)

*Este projeto foi desenvolvido como parte de um trabalho acadêmico, com o objetivo de criar um sistema desktop para a gestão de cadastros em um hotel.*

## 1\. Apresentação do Projeto

Este projeto consiste em um sistema de gestão em Java, com interface gráfica construída utilizando a biblioteca **Swing**. O software simula um ambiente real de hotelaria, permitindo o gerenciamento de diversas entidades essenciais para o funcionamento do negócio, como hóspedes, funcionários, quartos e serviços.

O programa permite que um usuário realize o cadastro completo de novas entidades, com validação de dados, e também busque por registos existentes através de diferentes critérios de filtro. Atualmente, os dados são salvos e manipulados em memória.

### Principais Funcionalidades

  - **Interface Gráfica Intuitiva:** Telas de cadastro e busca padronizadas para uma experiência de usuário consistente.
  - **Módulos de Cadastro Completos:** O sistema inclui cadastros para todas as entidades essenciais do hotel.
  - **Lógica de Interface Reativa:** Botões de ação (`Novo`, `Gravar`, `Cancelar`) são habilitados e desabilitados dinamicamente para guiar o fluxo do usuário.
  - **Busca com Filtros:** Todas as telas de busca permitem filtrar os resultados por diferentes atributos, como ID e Descrição.

## 2\. Design e Arquitetura

O sistema foi projetado com foco na **separação de responsabilidades**, utilizando uma arquitetura baseada no padrão **MVC (Model-View-Controller)**, com uma camada de serviço adicional.

### Diagrama UML de Classes

O diagrama a seguir ilustra as principais classes do sistema e como elas se relacionam.

**Descrição das Camadas:**

  - **Model:** Representa a estrutura dos dados (ex: `Hospede.java`, `Quarto.java`). Fica no pacote `model`.
  - **View:** Representa a camada de apresentação (as telas), como `TelaCadastroHospede.java` e `TelaBuscaFuncionario.java`. Fica no pacote `view`.
  - **Controller:** Faz a ponte entre a View e o Service, contendo a lógica de controlo dos eventos da tela (ex: cliques de botão). Fica no pacote `controller`.
  - **Service:** Simula a camada de acesso a dados (DAO - Data Access Object). É responsável por gerir listas de objetos em memória (`ArrayList`) e contém a lógica de negócio, como buscas e filtros. Fica no pacote `service`.

### Padrões e Princípios Utilizados

1.  **Model-View-Controller (MVC)**

      - **Justificativa**: Este padrão foi a base do projeto para separar os dados (Model), da interface com o utilizador (View) e da lógica de controlo (Controller). Isso torna o sistema mais organizado, fácil de manter e de expandir.

2.  **Camada de Serviço (Service Layer)**

      - **Justificativa**: Adicionar uma camada de serviço entre o Controller e o Model abstrai a origem dos dados. Atualmente, os serviços utilizam listas em memória, mas no futuro, podem ser facilmente modificados para se comunicarem com um banco de dados, sem a necessidade de alterar os Controllers.

3.  **Herança**

      - **Onde foi usada**: A classe `Pessoa` serve como superclasse para `Hospede`, `Funcionario` e `Fornecedor`.
      - **Justificativa**: Reutiliza código e estabelece uma relação "é-um-tipo-de", onde Hóspedes, Funcionários e Fornecedores compartilham atributos e comportamentos comuns de uma Pessoa.

4.  **Classe Utilitária**

      - **Onde foi usada**: `Utilities.java`.
      - **Justificativa**: Centraliza funções repetitivas de manipulação da interface gráfica (como ativar/desativar botões e limpar campos), promovendo o reuso de código e evitando duplicação.

## 3\. Estrutura do Projeto

O projeto foi organizado em pacotes para separar as responsabilidades de cada camada.

```
src
├── controller/
│   ├── ControllerCadFuncionario.java
│   ├── ControllerCadHospede.java
│   └── ... (outros controllers)
├── model/
│   ├── Funcionario.java
│   ├── Hospede.java
│   ├── Pessoa.java
│   └── ... (outros modelos)
├── service/
│   ├── ServicoFuncionario.java
│   ├── ServicoHospede.java
│   └── ... (outros serviços)
├── utilities/
│   └── Utilities.java
└── view/
    ├── TelaCadastroFuncionario.java
    ├── TelaBuscaFuncionario.java
    └── ... (outras telas)
```

## 4\. Instruções de Execução

Siga os passos abaixo para compilar e executar o projeto.

### Pré-requisitos

  - **Java Development Kit (JDK)** - Versão 17 ou superior.
  - **Apache NetBeans IDE** (recomendado, pois o projeto utiliza o seu construtor de GUI).

### Como Compilar e Executar

1.  **Abrir no NetBeans**:
      - Vá em `Arquivo > Abrir Projeto...`.
      - Navegue até a pasta raiz do `ProjetoHotel` e clique em "Abrir Projeto".
2.  **Limpar e Construir**:
      - Com o projeto aberto, clique com o botão direito sobre ele na árvore de projetos e selecione **"Limpar e Construir"**. Isso irá compilar todo o código e gerar o ficheiro `.jar`.
3.  **Executar**:
      - Após a construção bem-sucedida, clique com o botão direito novamente e selecione **"Executar"**. A `TelaMenuPrincipal` será exibida.

### Executando pelo Ficheiro JAR

1.  Após "Limpar e Construir", uma pasta `dist` será criada na raiz do projeto.
2.  Dentro da pasta `dist`, você encontrará o ficheiro `ProjetoHotel.jar`.
3.  Você pode executar este ficheiro dando um duplo clique sobre ele ou através do terminal:
    ```bash
    java -jar "C:\Caminho\Completo\Para\O\ProjetoHotel\dist\ProjetoHotel.jar"
    ```
