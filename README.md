Sistema de Gestão Hoteleira (ProjetoHotel)
Este é um projeto de um sistema de gestão para hotéis, desenvolvido como parte de um trabalho acadêmico. A aplicação é construída em Java com a interface gráfica utilizando a biblioteca Swing.

Status do Projeto
O projeto está em desenvolvimento. A fase atual foca na construção da estrutura da aplicação, nas interfaces de usuário e na lógica de negócio com dados salvos em memória.

Tecnologias Utilizadas
Linguagem: Java

Interface Gráfica (GUI): Java Swing

IDE: Apache NetBeans (inferido pelo fluxo de trabalho e estrutura dos ficheiros)

Build Tool: Apache Ant (inferido pelos logs de compilação)

Arquitetura
O projeto segue uma arquitetura baseada no padrão MVC (Model-View-Controller), com uma camada adicional de Serviço para desacoplar a lógica de negócio do acesso a dados.

Model: Representa a estrutura dos dados (ex: Hospede.java, Quarto.java). Fica no pacote model.

View: Representa a camada de apresentação (as telas), como TelaCadastroHospede.java. Fica no pacote view.

Controller: Faz a ponte entre a View e o Model/Service, contendo a lógica de controlo dos eventos da tela (ex: cliques de botão). Fica no pacote controller.

Service: Simula a camada de acesso a dados (DAO - Data Access Object). É responsável por gerir listas de objetos em memória. Fica no pacote service.

Funcionalidades Implementadas
Atualmente, o sistema possui módulos de cadastro completos com as seguintes funcionalidades:

Criação e Salvamento: Permite inserir novos dados através de um formulário e salvá-los em uma lista em memória.

Busca e Filtragem: Apresenta uma tela de busca que exibe todos os registos e permite filtrar por diferentes critérios.

Interface Reativa: Os botões de ação são habilitados e desabilitados conforme o estado da operação (novo, gravar, cancelar), utilizando uma classe Utilities.

Módulos de Cadastro
Os seguintes cadastros, marcados no diagrama de classes, foram implementados:

Cadastros Principais:

Hóspede

Funcionário

Fornecedor

Quarto

Cadastros de Apoio:

Produto/Copa

Serviço

Vaga de Estacionamento

Veículo

Marca e Modelo (como dependências de Veículo)

Como Executar o Projeto
Para compilar e executar o projeto, siga os passos abaixo.

Pré-requisitos
Java Development Kit (JDK) 17 ou superior.

Apache NetBeans IDE.

Executando pelo NetBeans
Abra o NetBeans.

Vá em Arquivo > Abrir Projeto....

Navegue até a pasta raiz do ProjetoHotel e clique em "Abrir Projeto".

Com o projeto aberto, clique com o botão direito sobre ele na árvore de projetos e selecione "Limpar e Construir".

Após a construção, clique com o botão direito novamente e selecione "Executar". A TelaMenuPrincipal será exibida.

Executando pelo Arquivo JAR
Após "Limpar e Construir" o projeto no NetBeans, uma pasta dist será criada na raiz do projeto.

Dentro da pasta dist, você encontrará o ficheiro ProjetoHotel.jar.

Você pode executar este ficheiro dando um duplo clique sobre ele ou através do terminal com o seguinte comando:

Bash

java -jar "C:\Caminho\Para\O\Projeto\dist\ProjetoHotel.jar"
