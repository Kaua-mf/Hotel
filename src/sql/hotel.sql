-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 26/10/2025 às 21:45
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `hotel`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `alocacao_vaga`
--

CREATE TABLE `alocacao_vaga` (
  `id` int(11) NOT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `veiculo_id` int(11) DEFAULT NULL,
  `vaga_estacionamento_id` int(11) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `caixa`
--

CREATE TABLE `caixa` (
  `id` int(11) NOT NULL,
  `valor_de_abertura` float DEFAULT NULL,
  `valor_de_fechamento` float DEFAULT NULL,
  `data_hora_abertura` datetime DEFAULT NULL,
  `data_hora_fechamento` datetime DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `check`
--

CREATE TABLE `check` (
  `id` int(11) NOT NULL,
  `data_hora_cadastro` datetime DEFAULT NULL,
  `data_hora_entrada` datetime DEFAULT NULL,
  `data_hora_saida` datetime DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `check_quarto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `check_hospede`
--

CREATE TABLE `check_hospede` (
  `id` int(11) NOT NULL,
  `tipo_hospede` varchar(45) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL,
  `hospede_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `check_quarto`
--

CREATE TABLE `check_quarto` (
  `id` int(11) NOT NULL,
  `data_hora_inicio` datetime DEFAULT NULL,
  `data_hora_fim` datetime DEFAULT NULL,
  `obs` varchar(45) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `quarto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `copa_quarto`
--

CREATE TABLE `copa_quarto` (
  `id` int(11) NOT NULL,
  `quantidade` float DEFAULT NULL,
  `data_hora_pedido` datetime DEFAULT NULL,
  `obs` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `check_quarto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `fone` varchar(16) DEFAULT NULL,
  `fone2` varchar(16) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(14) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `razao_social` varchar(100) DEFAULT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `inscricao_estadual` varchar(15) DEFAULT NULL,
  `contato` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `fornecedor`
--

INSERT INTO `fornecedor` (`id`, `nome`, `fone`, `fone2`, `email`, `cep`, `logradouro`, `bairro`, `cidade`, `complemento`, `data_cadastro`, `cpf`, `rg`, `obs`, `status`, `usuario`, `senha`, `razao_social`, `cnpj`, `inscricao_estadual`, `contato`) VALUES
(1, 'Nova Era Componentes', '(48) 98877-6655', '(48) 3344-5566', 'vendas@novaera.com', '88010-000', 'Avenida Hercílio Luz', 'Centro', 'Florianópolis', 'Andar 3, Sala 10', '2025-10-15 10:20:00', '111.222.333-44', '1.234.567', 'Fornecedor de peças de informática', 'A', 'novaera_user', 'senha_nova_1', 'Nova Era Comércio de Componentes Eletrônicos LTDA', '22.333.444/0001-55', '258.123.456.789', 'Sr. Ricardo'),
(2, 'LogiExpress Entregas', '(41) 99192-9394', NULL, 'comercial@logiexpress.net', '80010-010', 'Rua das Flores', 'Centro', 'Curitiba', 'Galpão B', '2025-10-15 11:45:00', '555.666.777-88', '7.654.321', 'Serviços de logística e entrega rápida', 'A', 'logiexpress', 'senha_log_2', 'LogiExpress Transportes Rápidos S/A', '55.666.777/0001-88', '159.753.456.123', 'Sra. Mariana'),
(22, 'SasASasaasd', '(023) 12312-3123', '(023) 12312-3123', '', NULL, NULL, NULL, NULL, NULL, '2025-10-15 18:23:57', NULL, NULL, NULL, 'A', NULL, NULL, '', '  .   /    -  ', '', ''),
(23, 'awdasdasdadad', '(021) 23123-1231', '(021) 23123-1231', '', NULL, NULL, NULL, NULL, NULL, '2025-10-15 18:29:24', NULL, NULL, NULL, 'A', NULL, NULL, 'awdadawdawd', '  .   /    -  ', '', ''),
(24, 'a', '(0  )      -    ', '(0  )      -    ', '', NULL, NULL, NULL, NULL, NULL, '2025-10-15 18:29:47', NULL, NULL, NULL, 'A', NULL, NULL, '', '  .   /    -  ', '', ''),
(25, 'ACME LTDA', '(11) 98765-4321', '(11) 2345-6789', 'contato@acme.com', '01001-000', 'Praça da Sé', 'Sé', 'São Paulo', 'Lado Ímpar', '2025-10-15 18:30:00', '123.456.789-00', '12.345.678-9', 'Fornecedor de materiais de escritório', 'A', 'acme_user', 'senha_forte_123', 'ACME Vendas e Serviços LTDA', '12.345.678/0001-90', '111.222.333.444', 'Sra. Joana'),
(26, 'asdasdasd', '(0  )      -    ', '(0  )      -    ', '', NULL, NULL, NULL, NULL, NULL, '2025-10-15 18:49:09', NULL, NULL, NULL, 'A', NULL, NULL, '', '  .   /    -  ', '', '');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `fone` varchar(16) DEFAULT NULL,
  `fone2` varchar(16) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(14) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`id`, `nome`, `fone`, `fone2`, `email`, `cep`, `logradouro`, `bairro`, `cidade`, `complemento`, `data_cadastro`, `cpf`, `rg`, `sexo`, `obs`, `status`, `usuario`, `senha`) VALUES
(1, 'Funcionário Padrão', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'A', NULL, NULL),
(11, 'asdasda', '(0  )      -    ', '', '', '  .   -   ', '', '', '', '', NULL, NULL, '', NULL, '', 'A', 'wadawd22', 'sada22'),
(12, 'kaka', '(021) 23312-3123', '(012) 31231-2312', '', '  .   -   ', '', '', '', '', NULL, NULL, '', NULL, '', 'A', '', ''),
(13, 'asdasdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', '\0', '', 'A', '', ''),
(14, 'asdasdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', '\0', '', 'A', '', ''),
(15, 'fsdfsdfsdsdfsdf', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(16, 'asfasdasasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(17, 'sdasdasdasdas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(18, 'asdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', 'aewqadw', '2323232'),
(19, 'asdasdas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(20, 'asdasda', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(21, 'asdasdas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(22, 'asdasdas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(23, 'asdasdas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(24, 'asdasdas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(25, 'asdasdas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(26, 'asdasda', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(27, 'asdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(28, 'sdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(29, 'asdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'M', '', 'A', '', ''),
(30, 'sadasdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'M', '', 'A', '', ''),
(31, 'sadasdasdasd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(32, 'aaaaaaaaaaaaa', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'M', '', 'A', '', ''),
(33, 'sssssssssssssssss', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'F', '', 'A', '', ''),
(34, 'ffffffffffffffff', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'M', '', 'A', '', ''),
(35, 'ggggggggggggggggg', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(36, 'gggggggggggggggggggg', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(37, 'dhdfhdfadfhgdfhagfhadhfdahadfhdfafhdafhd', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', '', ''),
(38, 'SEGSDSDGSDGGDSGDSDGSDGS', '(021) 31231-2312', '(012) 31231-2312', '', '  .   -   ', '', '', '', '', NULL, NULL, '', 'O', '', 'A', 'GSDGSDGSDGSD', '342423423');

-- --------------------------------------------------------

--
-- Estrutura para tabela `hospede`
--

CREATE TABLE `hospede` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `fone` varchar(16) DEFAULT NULL,
  `fone2` varchar(16) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `data_cadastro` datetime NOT NULL DEFAULT current_timestamp(),
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(14) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `razao_social` varchar(100) DEFAULT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `inscricao_estadual` varchar(15) DEFAULT NULL,
  `contato` varchar(100) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `hospede`
--

INSERT INTO `hospede` (`id`, `nome`, `fone`, `fone2`, `email`, `cep`, `logradouro`, `bairro`, `cidade`, `complemento`, `data_cadastro`, `cpf`, `rg`, `obs`, `status`, `usuario`, `senha`, `razao_social`, `cnpj`, `inscricao_estadual`, `contato`, `sexo`) VALUES
(1, 'Hóspede Padrão', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00 00:00:00', NULL, NULL, NULL, 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'kaua', '(0  )      -    ', '', 'dasdas', '  .   -   ', 'asdasd', 'sadas', '', 'dasdasda', '2009-10-25 00:00:00', '   .   .   -  ', '', 'sdasdas', 'A', NULL, NULL, 'asdasdas', '  .   /    -  ', '', 'asdad', NULL),
(3, 'asdasd', '(0  )      -    ', '', '', '  .   -   ', '', '', '', '', '2002-02-02 00:00:00', '   .   .   -  ', '', 'sdasdas', 'A', NULL, NULL, '', '  .   /    -  ', '', '', NULL),
(4, 'aaaaaaaaa', '(0  )      -    ', '12312312312', 'dsadasd', '31.312-312', 'sdasda', 'asdasda', 'dsasd', 'sdasd', '2002-02-02 00:00:00', '   .   .   -  ', '312312312312', 'asdasda', 'A', NULL, NULL, 'aaaaaa', '  .   /    -  ', 'aaaaa', 'sdasdasda', NULL),
(5, 'aaaaaaaaaaa', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', '2025-10-15 21:56:34', '   .   .   -  ', '', '', 'A', NULL, NULL, '', '  .   /    -  ', '', '', NULL),
(6, 'aaaaaaaaas', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', '2025-10-15 22:00:38', '   .   .   -  ', '', '', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 'Outro'),
(7, 'sssssssssss', '(0  )      -    ', '(0  )      -    ', '', '  .   -   ', '', '', '', '', '2025-10-15 22:00:58', '   .   .   -  ', '', '', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 'Feminino');

-- --------------------------------------------------------

--
-- Estrutura para tabela `marca`
--

CREATE TABLE `marca` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `marca`
--

INSERT INTO `marca` (`id`, `descricao`, `status`) VALUES
(1, 'Marca de Teste OK', 'A'),
(2, 'yjt', 'A');

-- --------------------------------------------------------

--
-- Estrutura para tabela `modelo`
--

CREATE TABLE `modelo` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `marca_id` int(11) DEFAULT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `modelo`
--

INSERT INTO `modelo` (`id`, `descricao`, `status`, `marca_id`, `nome`) VALUES
(1, NULL, NULL, NULL, 'Sedan Básico'),
(2, NULL, NULL, NULL, 'SUV');

-- --------------------------------------------------------

--
-- Estrutura para tabela `movimento_caixa`
--

CREATE TABLE `movimento_caixa` (
  `id` int(11) NOT NULL,
  `data_hora_movimento` datetime DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `caixa_id` int(11) DEFAULT NULL,
  `receber_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `oderm_servico`
--

CREATE TABLE `oderm_servico` (
  `id` int(11) NOT NULL,
  `data_hora_cadastro` datetime DEFAULT NULL,
  `data_hora_prevista_inicio` datetime DEFAULT NULL,
  `data_hora_prevista_termino` datetime DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL,
  `servico_id` int(11) DEFAULT NULL,
  `quarto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto_copa`
--

CREATE TABLE `produto_copa` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `copa_quarto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto_copa`
--

INSERT INTO `produto_copa` (`id`, `descricao`, `valor`, `obs`, `status`, `copa_quarto_id`) VALUES
(1, 'asadas', 123, 'asadas', 'A', NULL),
(2, 'asdasd', 222, 'asdasd', 'A', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `quarto`
--

CREATE TABLE `quarto` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `capacidade_hospedes` int(11) DEFAULT NULL,
  `metragem` float DEFAULT NULL,
  `identificacao` varchar(45) DEFAULT NULL,
  `andar` int(11) DEFAULT NULL,
  `flag_animais` tinyint(4) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `valor_diaria` float NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `quarto`
--

INSERT INTO `quarto` (`id`, `descricao`, `capacidade_hospedes`, `metragem`, `identificacao`, `andar`, `flag_animais`, `obs`, `status`, `valor_diaria`) VALUES
(1, 'aa', 0, 0, NULL, 0, 0, 'aa', '\0', 123),
(2, 'kaua', 0, 0, NULL, 0, 0, 'ssss', '\0', 1222),
(3, '', 0, 0, NULL, 22, 1, 'sfdfs', 'A', 22),
(4, '', 0, 0, NULL, 22, 1, 'asdasd', 'A', 222),
(5, 'aaaaa', 0, 0, NULL, 22, 0, 'aaa', 'A', 222),
(6, 'asdasdasd', 0, 0, NULL, 0, 0, 'asdasdasdasdasdasdad', '\0', 123);

-- --------------------------------------------------------

--
-- Estrutura para tabela `receber`
--

CREATE TABLE `receber` (
  `id` int(11) NOT NULL,
  `data_hora_cadastro` datetime DEFAULT NULL,
  `valor_original` float DEFAULT NULL,
  `desconto` float DEFAULT NULL,
  `acrescimo` float DEFAULT NULL,
  `valor_pago` float DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `reserva`
--

CREATE TABLE `reserva` (
  `id` int(11) NOT NULL,
  `data_hora_reserva` datetime DEFAULT NULL,
  `data_prevista_entrada` date DEFAULT NULL,
  `data_prevista_saida` date DEFAULT NULL,
  `obs` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `servico`
--

CREATE TABLE `servico` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `servico`
--

INSERT INTO `servico` (`id`, `descricao`, `obs`, `STATUS`) VALUES
(1, NULL, 'asdasdasa', 'A'),
(2, NULL, 'sadas', 'A'),
(3, NULL, 'asdasdasdas', 'A'),
(4, 'asdasdasd', 'asdasdasd', 'A');

-- --------------------------------------------------------

--
-- Estrutura para tabela `vaga_estacionamento`
--

CREATE TABLE `vaga_estacionamento` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `metragem_vaga` float DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `vaga_estacionamento`
--

INSERT INTO `vaga_estacionamento` (`id`, `descricao`, `obs`, `metragem_vaga`, `status`) VALUES
(1, 'asas', 'asasa', 12, 'A'),
(2, 'awdadasda', 'sdasdada', 222, 'A');

-- --------------------------------------------------------

--
-- Estrutura para tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `id` int(11) NOT NULL,
  `placa` varchar(7) NOT NULL,
  `cor` varchar(45) DEFAULT NULL,
  `modelo_id` int(11) DEFAULT NULL,
  `funcionario_id` int(11) DEFAULT NULL,
  `fornecedor_id` int(11) DEFAULT NULL,
  `hospede_id` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `veiculo`
--

INSERT INTO `veiculo` (`id`, `placa`, `cor`, `modelo_id`, `funcionario_id`, `fornecedor_id`, `hospede_id`, `status`) VALUES
(15, 'as212', 'asas', 2, NULL, NULL, NULL, 'A'),
(28, 'aas212', 'asas', 2, NULL, NULL, NULL, 'A');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `alocacao_vaga`
--
ALTER TABLE `alocacao_vaga`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_alocacao_vaga_veiculo1_idx` (`veiculo_id`),
  ADD KEY `fk_alocacao_vaga_vaga_estacionamento1_idx` (`vaga_estacionamento_id`),
  ADD KEY `fk_alocacao_vaga_check1_idx` (`check_id`);

--
-- Índices de tabela `caixa`
--
ALTER TABLE `caixa`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `check`
--
ALTER TABLE `check`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_check_check_quarto1_idx` (`check_quarto_id`);

--
-- Índices de tabela `check_hospede`
--
ALTER TABLE `check_hospede`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_check_hospede_check1_idx` (`check_id`),
  ADD KEY `fk_check_hospede_hospede1_idx` (`hospede_id`);

--
-- Índices de tabela `check_quarto`
--
ALTER TABLE `check_quarto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_check_quarto_quarto1_idx` (`quarto_id`);

--
-- Índices de tabela `copa_quarto`
--
ALTER TABLE `copa_quarto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_copa_quarto_check_quarto1_idx` (`check_quarto_id`);

--
-- Índices de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `hospede`
--
ALTER TABLE `hospede`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `marca`
--
ALTER TABLE `marca`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `modelo`
--
ALTER TABLE `modelo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_modelo_marca1_idx` (`marca_id`);

--
-- Índices de tabela `movimento_caixa`
--
ALTER TABLE `movimento_caixa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_movimento_caixa_caixa_idx` (`caixa_id`),
  ADD KEY `fk_movimento_caixa_receber1_idx` (`receber_id`);

--
-- Índices de tabela `oderm_servico`
--
ALTER TABLE `oderm_servico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_oderm_servico_check1_idx` (`check_id`),
  ADD KEY `fk_oderm_servico_servico1_idx` (`servico_id`),
  ADD KEY `fk_oderm_servico_quarto1_idx` (`quarto_id`);

--
-- Índices de tabela `produto_copa`
--
ALTER TABLE `produto_copa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_produto_copa_copa_quarto1_idx` (`copa_quarto_id`);

--
-- Índices de tabela `quarto`
--
ALTER TABLE `quarto`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `receber`
--
ALTER TABLE `receber`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_receber_check1_idx` (`check_id`);

--
-- Índices de tabela `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_reserva_check1_idx` (`check_id`);

--
-- Índices de tabela `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `vaga_estacionamento`
--
ALTER TABLE `vaga_estacionamento`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_veiculo_modelo1_idx` (`modelo_id`),
  ADD KEY `fk_veiculo_funcionario1_idx` (`funcionario_id`),
  ADD KEY `fk_veiculo_fornecedor1_idx` (`fornecedor_id`),
  ADD KEY `fk_veiculo_hospede1_idx` (`hospede_id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `alocacao_vaga`
--
ALTER TABLE `alocacao_vaga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `caixa`
--
ALTER TABLE `caixa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `check`
--
ALTER TABLE `check`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `check_hospede`
--
ALTER TABLE `check_hospede`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `check_quarto`
--
ALTER TABLE `check_quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `copa_quarto`
--
ALTER TABLE `copa_quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de tabela `hospede`
--
ALTER TABLE `hospede`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `marca`
--
ALTER TABLE `marca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `modelo`
--
ALTER TABLE `modelo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `movimento_caixa`
--
ALTER TABLE `movimento_caixa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `oderm_servico`
--
ALTER TABLE `oderm_servico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produto_copa`
--
ALTER TABLE `produto_copa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `quarto`
--
ALTER TABLE `quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `receber`
--
ALTER TABLE `receber`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `servico`
--
ALTER TABLE `servico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `vaga_estacionamento`
--
ALTER TABLE `vaga_estacionamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `alocacao_vaga`
--
ALTER TABLE `alocacao_vaga`
  ADD CONSTRAINT `fk_alocacao_vaga_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_alocacao_vaga_vaga_estacionamento1` FOREIGN KEY (`vaga_estacionamento_id`) REFERENCES `vaga_estacionamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_alocacao_vaga_veiculo1` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `check`
--
ALTER TABLE `check`
  ADD CONSTRAINT `fk_check_check_quarto1` FOREIGN KEY (`check_quarto_id`) REFERENCES `check_quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `check_hospede`
--
ALTER TABLE `check_hospede`
  ADD CONSTRAINT `fk_check_hospede_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_check_hospede_hospede1` FOREIGN KEY (`hospede_id`) REFERENCES `hospede` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `check_quarto`
--
ALTER TABLE `check_quarto`
  ADD CONSTRAINT `fk_check_quarto_quarto1` FOREIGN KEY (`quarto_id`) REFERENCES `quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `copa_quarto`
--
ALTER TABLE `copa_quarto`
  ADD CONSTRAINT `fk_copa_quarto_check_quarto1` FOREIGN KEY (`check_quarto_id`) REFERENCES `check_quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `modelo`
--
ALTER TABLE `modelo`
  ADD CONSTRAINT `fk_modelo_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `movimento_caixa`
--
ALTER TABLE `movimento_caixa`
  ADD CONSTRAINT `fk_movimento_caixa_caixa` FOREIGN KEY (`caixa_id`) REFERENCES `caixa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_movimento_caixa_receber1` FOREIGN KEY (`receber_id`) REFERENCES `receber` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `oderm_servico`
--
ALTER TABLE `oderm_servico`
  ADD CONSTRAINT `fk_oderm_servico_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_oderm_servico_quarto1` FOREIGN KEY (`quarto_id`) REFERENCES `quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_oderm_servico_servico1` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `produto_copa`
--
ALTER TABLE `produto_copa`
  ADD CONSTRAINT `fk_produto_copa_copa_quarto1` FOREIGN KEY (`copa_quarto_id`) REFERENCES `copa_quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `receber`
--
ALTER TABLE `receber`
  ADD CONSTRAINT `fk_receber_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk_reserva_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `veiculo`
--
ALTER TABLE `veiculo`
  ADD CONSTRAINT `fk_veiculo_fornecedor1` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_veiculo_funcionario1` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_veiculo_hospede1` FOREIGN KEY (`hospede_id`) REFERENCES `hospede` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_veiculo_modelo1` FOREIGN KEY (`modelo_id`) REFERENCES `modelo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
