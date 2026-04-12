-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 12/04/2026 às 08:31
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
  `check_id` int(11) DEFAULT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL,
  `id_vaga` int(11) DEFAULT NULL,
  `id_check` int(11) DEFAULT NULL,
  `id_veiculo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `alocacao_vaga`
--

INSERT INTO `alocacao_vaga` (`id`, `obs`, `status`, `veiculo_id`, `vaga_estacionamento_id`, `check_id`, `data_entrada`, `data_saida`, `id_reserva`, `id_vaga`, `id_check`, `id_veiculo`) VALUES
(1, 'Carro estacionado', 'A', 1, 1, 1, '12/04/2026 10:30', '15/04/2026 12:00', NULL, NULL, NULL, NULL),
(5, '2026-04-04', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 2, 13, 36),
(6, '2026-04-04', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 1, 14, 1),
(7, '2026-05-05', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 1, 15, 1),
(8, '', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 1, 16, 1),
(9, '2026-04-13', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 3, 19, 38),
(10, '2026-04-13', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 2, 20, 37),
(11, '', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 3, 21, 38),
(12, '', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 3, 22, 37),
(13, '', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 3, 22, 37),
(14, '', 'A', NULL, NULL, NULL, NULL, NULL, NULL, 3, 23, 38);

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
  `status` varchar(1) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `saldo` float DEFAULT NULL
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
  `check_quarto_id` int(11) DEFAULT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL,
  `valor_total` float DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `check`
--

INSERT INTO `check` (`id`, `data_hora_cadastro`, `data_hora_entrada`, `data_hora_saida`, `obs`, `status`, `check_quarto_id`, `data_entrada`, `data_saida`, `valor_total`, `id_reserva`) VALUES
(1, '2026-04-11 15:00:00', NULL, NULL, NULL, 'A', NULL, NULL, NULL, NULL, NULL),
(2, '2026-04-11 15:00:00', NULL, NULL, NULL, 'A', NULL, NULL, NULL, NULL, NULL);

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
  `hospede_id` int(11) DEFAULT NULL,
  `tipo_pessoa` varchar(255) DEFAULT NULL,
  `pessoa_id` int(11) DEFAULT NULL,
  `id_check` int(11) DEFAULT NULL,
  `id_hospede` int(11) DEFAULT NULL,
  `id_pessoa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `check_hospede`
--

INSERT INTO `check_hospede` (`id`, `tipo_hospede`, `obs`, `status`, `check_id`, `hospede_id`, `tipo_pessoa`, `pessoa_id`, `id_check`, `id_hospede`, `id_pessoa`) VALUES
(1, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 1, NULL, 4),
(2, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 2, NULL, 4),
(3, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 3, NULL, 4),
(4, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 4, NULL, 5),
(5, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 4, NULL, 6),
(6, NULL, 'sdfasfasffas', NULL, NULL, NULL, 'Hospede', NULL, 5, NULL, 4),
(7, NULL, '1', NULL, NULL, NULL, 'Hospede', NULL, 6, NULL, 4),
(8, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 7, NULL, 5),
(9, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 8, NULL, 5),
(10, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 9, NULL, 5),
(11, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 10, NULL, 5),
(12, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 11, NULL, 5),
(13, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 12, NULL, 5),
(14, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 13, NULL, 5),
(15, NULL, 'sdas', NULL, NULL, NULL, 'Hospede', NULL, 14, NULL, 4),
(16, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 15, NULL, 4),
(17, NULL, '', NULL, NULL, NULL, 'Hospede', NULL, 16, NULL, 6),
(18, NULL, 'muito gato', NULL, NULL, NULL, 'Hospede', NULL, 19, NULL, 7),
(19, NULL, 'muito gato', NULL, NULL, NULL, 'Hospede', NULL, 20, NULL, 7),
(20, NULL, 'muito gato', NULL, NULL, NULL, 'Hospede', NULL, 21, NULL, 7),
(21, NULL, 'muito gato', NULL, NULL, NULL, 'Hospede', NULL, 22, NULL, 7),
(22, NULL, 'muito gato', NULL, NULL, NULL, 'Hospede', NULL, 23, NULL, 7);

-- --------------------------------------------------------

--
-- Estrutura para tabela `check_in_out`
--

CREATE TABLE `check_in_out` (
  `id` int(11) NOT NULL,
  `data_hora_cadastro` datetime DEFAULT NULL,
  `data_hora_entrada` datetime DEFAULT NULL,
  `data_hora_saida` datetime DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `reserva_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `check_in_out`
--

INSERT INTO `check_in_out` (`id`, `data_hora_cadastro`, `data_hora_entrada`, `data_hora_saida`, `obs`, `status`, `reserva_id`) VALUES
(1, '2026-04-11 19:01:48', '2026-04-11 00:00:00', '2026-04-11 00:00:00', 'Reserva Teste 01', 'A', 1),
(2, '2026-04-11 19:06:11', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 01', 'A', 1),
(3, '2026-04-11 19:09:02', '2026-04-02 00:00:00', '2026-04-02 00:00:00', 'Reserva Teste 01', 'A', 1),
(4, '2026-04-11 19:12:03', '2026-05-05 00:00:00', '2026-05-05 00:00:00', 'Reserva Teste 01', 'A', 1),
(5, '2026-04-11 19:37:06', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 01', 'A', 1),
(6, '2026-04-12 00:55:18', '2026-03-03 00:00:00', '2026-03-03 00:00:00', 'Reserva Teste 01', 'A', 1),
(7, '2026-04-12 01:09:58', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 01', 'A', 1),
(8, '2026-04-12 01:38:04', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 02', 'A', 2),
(9, '2026-04-12 01:43:29', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 01', 'A', 1),
(10, '2026-04-12 01:44:46', '2026-04-04 00:00:00', '2026-04-04 00:00:00', '', 'A', 3),
(11, '2026-04-12 01:48:14', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 02', 'A', 2),
(12, '2026-04-12 01:49:30', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 01', 'A', 1),
(13, '2026-04-12 01:51:09', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 01', 'A', 1),
(14, '2026-04-12 02:06:54', '2026-04-04 00:00:00', '2026-04-04 00:00:00', 'Reserva Teste 02', 'A', 2),
(15, '2026-04-12 02:14:27', '2026-05-05 00:00:00', '2026-05-05 00:00:00', 'Reserva Teste 01', 'A', 1),
(16, '2026-04-12 02:17:28', '2026-05-05 00:00:00', '2026-05-05 00:00:00', '', 'A', 1),
(17, '2026-04-12 02:42:59', '2026-05-05 00:00:00', '2025-05-05 00:00:00', '', 'A', 1),
(18, '2026-04-12 02:45:20', '2025-05-05 00:00:00', '2025-05-05 00:00:00', '', 'A', 3),
(19, '2026-04-12 03:05:35', '2026-04-13 00:00:00', '2026-04-13 00:00:00', 'oi', 'A', 7),
(20, '2026-04-12 03:08:18', '2026-04-13 00:00:00', '2026-04-13 00:00:00', 'oi', 'A', 7),
(21, '2026-04-12 03:16:54', '2026-06-06 00:00:00', '2026-06-06 00:00:00', 'oi', 'A', 7),
(22, '2026-04-12 03:18:33', '2026-06-06 00:00:00', '2026-06-06 00:00:00', 'oi', 'A', 7),
(23, '2026-04-12 03:26:44', '2026-07-07 00:00:00', '2026-07-07 00:00:00', 'oi', 'A', 7);

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
  `quarto_id` int(11) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL,
  `dataHoraFim` varchar(255) DEFAULT NULL,
  `dataHoraInicio` varchar(255) DEFAULT NULL,
  `valor_quarto` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `check_quarto`
--

INSERT INTO `check_quarto` (`id`, `data_hora_inicio`, `data_hora_fim`, `obs`, `status`, `quarto_id`, `check_id`, `dataHoraFim`, `dataHoraInicio`, `valor_quarto`) VALUES
(2, NULL, NULL, 'Mesa de trabalho e Wi-fi 5G', NULL, 11, 3, '2026-04-02', '2026-04-02', NULL),
(3, NULL, NULL, 'Pet friendly e vista mar', NULL, 9, 3, '2026-04-02', '2026-04-02', NULL),
(4, NULL, NULL, 'asdasdasdasdasdasdad', NULL, 6, 4, '2026-04-04', '2026-04-04', NULL),
(5, NULL, NULL, 'a\\sdf\\szf', NULL, 10, 5, '2026-04-04', '2026-04-04', NULL),
(6, NULL, NULL, 'Pet friendly e vista mar', NULL, 9, 6, '2026-03-03', '2026-03-03', NULL),
(7, NULL, NULL, 'ssss', NULL, 2, 7, '2026-04-04', '2026-04-04', NULL),
(8, NULL, NULL, 'asdasdasdasdasdasdad', NULL, 6, 8, '2026-04-04', '2026-04-04', NULL),
(9, NULL, NULL, 'asdasd', NULL, 4, 10, '2026-04-04', '2026-04-04', NULL),
(10, NULL, NULL, 'Cama de casal e frigobar', NULL, 7, 11, '2026-04-04', '2026-04-04', NULL),
(11, NULL, NULL, 'Cama de casal e frigobar', NULL, 7, 12, '2026-04-04', '2026-04-04', NULL),
(12, NULL, NULL, 'ssss', NULL, 2, 13, '2026-04-04', '2026-04-04', NULL),
(13, NULL, NULL, 'ssss', NULL, 2, 14, '2026-04-04', '2026-04-04', NULL),
(14, NULL, NULL, 'Duas camas de solteiro', NULL, 8, 15, '2026-05-05', '2026-05-05', NULL),
(15, NULL, NULL, 'ssss', NULL, 2, 16, '    -  -  ', '    -  -  ', NULL),
(16, NULL, NULL, 'Cama de casal e frigobar', NULL, 7, 19, '2026-04-13', '2026-04-13', NULL),
(17, NULL, NULL, 'Pet friendly e vista mar', NULL, 9, 20, '2026-04-13', '2026-04-13', NULL),
(18, NULL, NULL, 'Mesa de trabalho e Wi-fi 5G', NULL, 11, 20, '2026-04-13', '2026-04-13', NULL),
(19, NULL, NULL, 'Hidromassagem e Varanda', NULL, 10, 21, '2026-06-06', '2026-06-06', NULL),
(20, NULL, NULL, 'Cama de casal e frigobar', NULL, 7, 21, '2026-06-06', '2026-06-06', NULL),
(21, NULL, NULL, 'Hidromassagem e Varanda', NULL, 10, 22, '2026-06-06', '2026-06-06', NULL),
(22, NULL, NULL, 'Duas camas de solteiro', NULL, 8, 22, '2026-06-06', '2026-06-06', NULL),
(23, NULL, NULL, 'Hidromassagem e Varanda', NULL, 10, 22, '2026-06-06', '2026-06-06', NULL),
(24, NULL, NULL, 'Duas camas de solteiro', NULL, 8, 22, '2026-06-06', '2026-06-06', NULL),
(25, NULL, NULL, 'Duas camas de solteiro', NULL, 8, 23, '2026-07-07', '2026-07-07', NULL),
(26, NULL, NULL, 'Mesa de trabalho e Wi-fi 5G', NULL, 11, 23, '2026-07-07', '2026-07-07', NULL),
(27, NULL, NULL, 'Pet friendly e vista mar', NULL, 9, 23, '2026-07-07', '2026-07-07', NULL),
(28, NULL, NULL, 'Duas camas de solteiro', NULL, 8, 23, '2026-07-07', '2026-07-07', NULL),
(29, NULL, NULL, 'Mesa de trabalho e Wi-fi 5G', NULL, 11, 23, '2026-07-07', '2026-07-07', NULL),
(30, NULL, NULL, 'Pet friendly e vista mar', NULL, 9, 23, '2026-07-07', '2026-07-07', NULL);

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
  `check_quarto_id` int(11) DEFAULT NULL,
  `valor_total` float DEFAULT NULL,
  `id_produto` int(11) DEFAULT NULL,
  `id_quarto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `dados_check`
--

CREATE TABLE `dados_check` (
  `id` int(11) NOT NULL,
  `hospedePrincipal` varchar(255) DEFAULT NULL,
  `observacaoGeral` varchar(255) DEFAULT NULL,
  `IDquartosUtilizados` varchar(255) DEFAULT NULL,
  `vagasUtilizadas` varchar(255) DEFAULT NULL,
  `valorTotal` double DEFAULT NULL,
  `checkId` int(11) NOT NULL,
  `dataFinalizacao` datetime(6) DEFAULT NULL,
  `quartosUtilizados` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `dados_check`
--

INSERT INTO `dados_check` (`id`, `hospedePrincipal`, `observacaoGeral`, `IDquartosUtilizados`, `vagasUtilizadas`, `valorTotal`, `checkId`, `dataFinalizacao`, `quartosUtilizados`) VALUES
(1, 'Hospede 03; ', '', '2 ', 'ABC1234 ', 555, 0, NULL, NULL),
(2, 'Kauã; ', 'cliente muito gato', '7 ', 'KAU0M26 ', 245, 19, '2026-04-12 06:06:32.000000', NULL),
(3, 'Kauã; ', '2026-04-13', NULL, 'XYZ9G88 ', 500, 20, '2026-04-12 06:08:51.000000', '9 11 '),
(4, 'Kauã; ', '', NULL, 'KAU0M26 ', 2222, 21, '2026-04-12 06:17:33.000000', '10 7 '),
(5, 'Kauã; ', '', NULL, 'XYZ9G88 XYZ9G88 ', 2222, 22, '2026-04-12 06:19:05.000000', '10 8 '),
(6, 'Kauã;', '', '8 11 9', 'KAU0M26', 222, 23, '2026-04-12 06:27:15.000000', '8 11 9');

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
  `contato` varchar(100) DEFAULT NULL,
  `fone1` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `senha` varchar(45) DEFAULT NULL,
  `fone1` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`id`, `nome`, `fone`, `fone2`, `email`, `cep`, `logradouro`, `bairro`, `cidade`, `complemento`, `data_cadastro`, `cpf`, `rg`, `sexo`, `obs`, `status`, `usuario`, `senha`, `fone1`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '123', NULL),
(2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'user1', '123', NULL),
(3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'user2', '123', NULL);

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
  `sexo` varchar(10) DEFAULT NULL,
  `fone1` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `hospede`
--

INSERT INTO `hospede` (`id`, `nome`, `fone`, `fone2`, `email`, `cep`, `logradouro`, `bairro`, `cidade`, `complemento`, `data_cadastro`, `cpf`, `rg`, `obs`, `status`, `usuario`, `senha`, `razao_social`, `cnpj`, `inscricao_estadual`, `contato`, `sexo`, `fone1`) VALUES
(4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-11 18:33:15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-11 18:33:15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-11 18:33:15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 03:04:50', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '  .   /    -     ', NULL, NULL, NULL, NULL);

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
(2, 'yjt', 'A'),
(3, 'BMW', 'A'),
(4, 'Ford', 'A'),
(5, 'dasdas', 'A'),
(6, 'asda', 'A'),
(7, 'DDD', 'A'),
(8, 'dasdasd', 'A');

-- --------------------------------------------------------

--
-- Estrutura para tabela `modelo`
--

CREATE TABLE `modelo` (
  `id` int(11) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `marca_id` int(11) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `id_marca` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `modelo`
--

INSERT INTO `modelo` (`id`, `status`, `marca_id`, `nome`, `id_marca`) VALUES
(1, 'A', NULL, 'Sedan Básico', NULL),
(2, 'A', NULL, 'SUV', NULL),
(3, 'A', 2, 'asdas', NULL),
(4, 'A', 1, 'KMDF', NULL);

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
-- Estrutura para tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_cadastro` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fone1` varchar(255) DEFAULT NULL,
  `fone2` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pessoa`
--

INSERT INTO `pessoa` (`id`, `bairro`, `cep`, `cidade`, `complemento`, `cpf`, `data_cadastro`, `email`, `fone1`, `fone2`, `logradouro`, `nome`, `obs`, `rg`, `sexo`, `status`) VALUES
(1, NULL, NULL, NULL, NULL, '111.111.111-11', '2026-04-11', 'func01@hotel.com', '48 9999-1111', NULL, NULL, 'Funcionario 01', NULL, NULL, NULL, 'A'),
(2, NULL, NULL, NULL, NULL, '222.222.222-22', '2026-04-11', 'func02@hotel.com', '48 9999-2222', NULL, NULL, 'Funcionario 02', NULL, NULL, NULL, 'A'),
(3, NULL, NULL, NULL, NULL, '333.333.333-33', '2026-04-11', 'func03@hotel.com', '48 9999-3333', NULL, NULL, 'Funcionario 03', NULL, NULL, NULL, 'A'),
(4, NULL, NULL, NULL, NULL, '444.444.444-44', '2026-04-11', 'hosp01@gmail.com', '48 8888-1111', NULL, NULL, 'Hospede 01', NULL, NULL, NULL, 'A'),
(5, NULL, NULL, NULL, NULL, '555.555.555-55', '2026-04-11', 'hosp02@gmail.com', '48 8888-2222', NULL, NULL, 'Hospede 02', NULL, NULL, NULL, 'A'),
(6, NULL, NULL, NULL, NULL, '666.666.666-66', '2026-04-11', 'hosp03@gmail.com', '48 8888-3333', NULL, NULL, 'Hospede 03', NULL, NULL, NULL, 'A'),
(7, 'sao joao ', '88.708-000', '', 'ao lado do dema', '133.675.769-80', '2026-04-12 03:04:50', 'kauamachadof01@gmail.com', '(0  )      -    ', '(0  )      -    ', 'casa', 'Kauã', 'muito gato', '', 'Masculino', 'A');

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
(6, 'asdasdasd', 0, 0, NULL, 0, 0, 'asdasdasdasdasdasdad', '\0', 123),
(7, 'Quarto Standard Casal', 2, 22.5, '101', 1, 0, 'Cama de casal e frigobar', 'A', 150),
(8, 'Quarto Standard Duplo', 2, 22.5, '102', 1, 0, 'Duas camas de solteiro', 'A', 150),
(9, 'Quarto Luxo Familia', 4, 45, '201', 2, 0, 'Pet friendly e vista mar', 'A', 350),
(10, 'Suíte Master', 2, 55, '301', 3, 0, 'Hidromassagem e Varanda', 'A', 550),
(11, 'Quarto Executivo', 1, 20, '205', 2, 0, 'Mesa de trabalho e Wi-fi 5G', 'A', 200);

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
  `check_id` int(11) DEFAULT NULL,
  `data_lancamento` varchar(255) DEFAULT NULL,
  `data_pagamento` varchar(255) DEFAULT NULL,
  `data_vencimento` varchar(255) DEFAULT NULL,
  `valor_total` float DEFAULT NULL,
  `id_hospede` int(11) DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL,
  `data_emissao` datetime(6) DEFAULT NULL,
  `id_check` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `receber`
--

INSERT INTO `receber` (`id`, `data_hora_cadastro`, `valor_original`, `desconto`, `acrescimo`, `valor_pago`, `obs`, `status`, `check_id`, `data_lancamento`, `data_pagamento`, `data_vencimento`, `valor_total`, `id_hospede`, `id_reserva`, `data_emissao`, `id_check`) VALUES
(1, '2026-04-11 00:00:00', 333, 30, 0, 222, '2026-04-18', 'A', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, NULL, 222, NULL, NULL, 222, '2026-04-04', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 05:07:23.000000', 14),
(3, NULL, 555, NULL, NULL, 555, '2026-05-05', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 05:14:57.000000', 15),
(4, NULL, 555, NULL, NULL, 555, '', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 05:17:48.000000', 16),
(5, NULL, 250, NULL, NULL, 245, 'cliente muito gato', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 06:06:32.000000', 19),
(6, NULL, 550, NULL, NULL, 500, '2026-04-13', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 06:08:51.000000', 20),
(7, NULL, 2222, NULL, NULL, 2222, '', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 06:17:33.000000', 21),
(8, NULL, 2222, NULL, NULL, 2222, '', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 06:19:05.000000', 22),
(9, NULL, 222, NULL, NULL, 222, '', 'P', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-04-12 06:27:15.000000', 23);

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
  `check_id` int(11) DEFAULT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL,
  `valor_total` float DEFAULT NULL,
  `id_funcionario` int(11) DEFAULT NULL,
  `id_hospede` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `reserva`
--

INSERT INTO `reserva` (`id`, `data_hora_reserva`, `data_prevista_entrada`, `data_prevista_saida`, `obs`, `status`, `check_id`, `data_entrada`, `data_saida`, `valor_total`, `id_funcionario`, `id_hospede`) VALUES
(1, '2026-04-11 10:00:00', '2026-05-10', '2026-05-15', 'Reserva Teste 01', 'A', NULL, NULL, NULL, 500, 1, 4),
(2, '2026-04-11 11:00:00', '2026-06-01', '2026-06-05', 'Reserva Teste 02', 'A', NULL, NULL, NULL, 800, 2, 5),
(3, '2026-04-11 12:00:00', '2026-07-20', '2026-07-25', 'Reserva Teste 03', 'A', NULL, NULL, NULL, 350, 3, 6),
(4, '2026-04-12 02:39:58', '2026-04-04', '2026-04-04', '22', 'A', NULL, NULL, NULL, 0, NULL, NULL),
(5, '2026-04-12 02:56:46', '2026-05-05', '2026-05-05', '2026-05-05', 'A', NULL, NULL, NULL, 0, NULL, NULL),
(6, '2026-04-12 03:03:03', '2026-05-05', '2026-05-05', '2026-05-05', 'A', NULL, NULL, NULL, 0, NULL, 4),
(7, '2026-04-12 03:04:55', '2026-04-13', '2026-04-13', 'oi', 'A', NULL, NULL, NULL, 0, NULL, 7);

-- --------------------------------------------------------

--
-- Estrutura para tabela `reserva_quarto`
--

CREATE TABLE `reserva_quarto` (
  `id` int(11) NOT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `valor_diaria` float DEFAULT NULL,
  `valor_total` float DEFAULT NULL,
  `id_quarto` int(11) DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL
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
-- Estrutura para tabela `tipo_quarto`
--

CREATE TABLE `tipo_quarto` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(2, 'awdadasda', 'sdasdada', 222, 'A'),
(3, 'nova', 'nova', 222, 'A');

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
  `status` varchar(1) DEFAULT NULL,
  `id_fornecedor` int(11) DEFAULT NULL,
  `id_funcionario` int(11) DEFAULT NULL,
  `id_hospede` int(11) DEFAULT NULL,
  `id_marca` int(11) DEFAULT NULL,
  `id_modelo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `veiculo`
--

INSERT INTO `veiculo` (`id`, `placa`, `cor`, `modelo_id`, `funcionario_id`, `fornecedor_id`, `hospede_id`, `status`, `id_fornecedor`, `id_funcionario`, `id_hospede`, `id_marca`, `id_modelo`) VALUES
(1, 'ABC1234', 'Preto', NULL, NULL, NULL, NULL, 'A', NULL, NULL, NULL, 1, 1),
(36, 'ABC1D23', 'Preto', NULL, NULL, NULL, NULL, 'A', NULL, NULL, NULL, 1, 1),
(37, 'XYZ9G88', 'Branco', NULL, NULL, NULL, NULL, 'A', NULL, NULL, NULL, 1, 1),
(38, 'KAU0M26', 'Prata', NULL, NULL, NULL, NULL, 'A', NULL, NULL, NULL, 1, 1);

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
  ADD KEY `fk_alocacao_vaga_check1_idx` (`check_id`),
  ADD KEY `FKh4h9rs1in6omsiiru6itq5xac` (`id_reserva`),
  ADD KEY `FK56umahv81gd81to2cnatfcejw` (`id_vaga`),
  ADD KEY `FKqftojpppevuwckeogbwbqslf` (`id_check`),
  ADD KEY `FK9ly7tlvydj41rxs618gnvfpmm` (`id_veiculo`);

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
  ADD KEY `fk_check_check_quarto1_idx` (`check_quarto_id`),
  ADD KEY `FKkmflhrx0gricvyhsotqu0e2jn` (`id_reserva`);

--
-- Índices de tabela `check_hospede`
--
ALTER TABLE `check_hospede`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_check_hospede_check1_idx` (`check_id`),
  ADD KEY `fk_check_hospede_hospede1_idx` (`hospede_id`),
  ADD KEY `FKb67vxmxxm42yk1vo8atmtjs5r` (`pessoa_id`),
  ADD KEY `FK68ap0xp6wqjabjpm0eykw34vb` (`id_check`),
  ADD KEY `FK1cgc1e4mbogxoce5y6gnsmhhe` (`id_hospede`),
  ADD KEY `FKqf1o5tn6bsik6nkllvif4xi3k` (`id_pessoa`);

--
-- Índices de tabela `check_in_out`
--
ALTER TABLE `check_in_out`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4x1g0k22g7yjnrbfo3h5hskst` (`reserva_id`);

--
-- Índices de tabela `check_quarto`
--
ALTER TABLE `check_quarto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_check_quarto_quarto1_idx` (`quarto_id`),
  ADD KEY `FKa1v15258ti99ojqyr3opiv45k` (`check_id`);

--
-- Índices de tabela `copa_quarto`
--
ALTER TABLE `copa_quarto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_copa_quarto_check_quarto1_idx` (`check_quarto_id`),
  ADD KEY `FKamgpjhhqgmhw23vffy4jxo7io` (`id_produto`),
  ADD KEY `FKev33g6eiwe0svtd824c5vu8cx` (`id_quarto`);

--
-- Índices de tabela `dados_check`
--
ALTER TABLE `dados_check`
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `fk_modelo_marca1_idx` (`marca_id`),
  ADD KEY `FKnsndlfkbon3sbxik0xvclk082` (`id_marca`);

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
-- Índices de tabela `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `fk_receber_check1_idx` (`check_id`),
  ADD KEY `FK1e420lel73p8cv5ghvogtv8q4` (`id_hospede`),
  ADD KEY `FKpgli26cru9dmd2gh0bby9klfg` (`id_reserva`),
  ADD KEY `FKbuginh1tsyy6e7c4buv5wsg22` (`id_check`);

--
-- Índices de tabela `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_reserva_check1_idx` (`check_id`),
  ADD KEY `FKatssbui59kt1kvwk4xvr8x2t8` (`id_funcionario`),
  ADD KEY `FKb9jngn2my2etkgx3biv7m4utl` (`id_hospede`);

--
-- Índices de tabela `reserva_quarto`
--
ALTER TABLE `reserva_quarto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrjqw0krecnw58448li9xepk8f` (`id_quarto`),
  ADD KEY `FK3atwccg8kdgi4u01rbmvcbets` (`id_reserva`);

--
-- Índices de tabela `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tipo_quarto`
--
ALTER TABLE `tipo_quarto`
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
  ADD KEY `fk_veiculo_hospede1_idx` (`hospede_id`),
  ADD KEY `FK1oqvenyfu8uph62t14dosetil` (`id_fornecedor`),
  ADD KEY `FK87rw23sbwn8lq4dmlvhonym6c` (`id_funcionario`),
  ADD KEY `FKglviyl6o66k286wfdxo3rc0wy` (`id_hospede`),
  ADD KEY `FKptg5evm2q9d2tn6980nxfyxcl` (`id_marca`),
  ADD KEY `FK7nb3xkqesdtf9gn9s5cmlan8j` (`id_modelo`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `alocacao_vaga`
--
ALTER TABLE `alocacao_vaga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `caixa`
--
ALTER TABLE `caixa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `check`
--
ALTER TABLE `check`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `check_hospede`
--
ALTER TABLE `check_hospede`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de tabela `check_in_out`
--
ALTER TABLE `check_in_out`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de tabela `check_quarto`
--
ALTER TABLE `check_quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de tabela `copa_quarto`
--
ALTER TABLE `copa_quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `dados_check`
--
ALTER TABLE `dados_check`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de tabela `hospede`
--
ALTER TABLE `hospede`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de tabela `marca`
--
ALTER TABLE `marca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `modelo`
--
ALTER TABLE `modelo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
-- AUTO_INCREMENT de tabela `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `produto_copa`
--
ALTER TABLE `produto_copa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `quarto`
--
ALTER TABLE `quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `receber`
--
ALTER TABLE `receber`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `reserva_quarto`
--
ALTER TABLE `reserva_quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `servico`
--
ALTER TABLE `servico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `tipo_quarto`
--
ALTER TABLE `tipo_quarto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `vaga_estacionamento`
--
ALTER TABLE `vaga_estacionamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `alocacao_vaga`
--
ALTER TABLE `alocacao_vaga`
  ADD CONSTRAINT `FK56umahv81gd81to2cnatfcejw` FOREIGN KEY (`id_vaga`) REFERENCES `vaga_estacionamento` (`id`),
  ADD CONSTRAINT `FK9ly7tlvydj41rxs618gnvfpmm` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id`),
  ADD CONSTRAINT `FKh4h9rs1in6omsiiru6itq5xac` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`),
  ADD CONSTRAINT `FKqftojpppevuwckeogbwbqslf` FOREIGN KEY (`id_check`) REFERENCES `check_in_out` (`id`),
  ADD CONSTRAINT `fk_alocacao_vaga_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_alocacao_vaga_vaga_estacionamento1` FOREIGN KEY (`vaga_estacionamento_id`) REFERENCES `vaga_estacionamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_alocacao_vaga_veiculo1` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `check`
--
ALTER TABLE `check`
  ADD CONSTRAINT `FKkmflhrx0gricvyhsotqu0e2jn` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`),
  ADD CONSTRAINT `fk_check_check_quarto1` FOREIGN KEY (`check_quarto_id`) REFERENCES `check_quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `check_hospede`
--
ALTER TABLE `check_hospede`
  ADD CONSTRAINT `FK1cgc1e4mbogxoce5y6gnsmhhe` FOREIGN KEY (`id_hospede`) REFERENCES `hospede` (`id`),
  ADD CONSTRAINT `FK68ap0xp6wqjabjpm0eykw34vb` FOREIGN KEY (`id_check`) REFERENCES `check_in_out` (`id`),
  ADD CONSTRAINT `FKb67vxmxxm42yk1vo8atmtjs5r` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`),
  ADD CONSTRAINT `FKcdex08tq0w3nwckxnii8df1a9` FOREIGN KEY (`check_id`) REFERENCES `check_in_out` (`id`),
  ADD CONSTRAINT `FKqf1o5tn6bsik6nkllvif4xi3k` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`),
  ADD CONSTRAINT `fk_check_hospede_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_check_hospede_hospede1` FOREIGN KEY (`hospede_id`) REFERENCES `hospede` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `check_in_out`
--
ALTER TABLE `check_in_out`
  ADD CONSTRAINT `FK4x1g0k22g7yjnrbfo3h5hskst` FOREIGN KEY (`reserva_id`) REFERENCES `reserva` (`id`);

--
-- Restrições para tabelas `check_quarto`
--
ALTER TABLE `check_quarto`
  ADD CONSTRAINT `FKa1v15258ti99ojqyr3opiv45k` FOREIGN KEY (`check_id`) REFERENCES `check_in_out` (`id`),
  ADD CONSTRAINT `fk_check_quarto_quarto1` FOREIGN KEY (`quarto_id`) REFERENCES `quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `copa_quarto`
--
ALTER TABLE `copa_quarto`
  ADD CONSTRAINT `FKamgpjhhqgmhw23vffy4jxo7io` FOREIGN KEY (`id_produto`) REFERENCES `produto_copa` (`id`),
  ADD CONSTRAINT `FKev33g6eiwe0svtd824c5vu8cx` FOREIGN KEY (`id_quarto`) REFERENCES `quarto` (`id`),
  ADD CONSTRAINT `fk_copa_quarto_check_quarto1` FOREIGN KEY (`check_quarto_id`) REFERENCES `check_quarto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD CONSTRAINT `FK4fumwc92qrfurlo1g6jhos1sq` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`);

--
-- Restrições para tabelas `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FKpjvvctvfjm69dvye3aea5k56f` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`);

--
-- Restrições para tabelas `hospede`
--
ALTER TABLE `hospede`
  ADD CONSTRAINT `FKfaye8h8ro5tc5haq5ofppfbqm` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`);

--
-- Restrições para tabelas `modelo`
--
ALTER TABLE `modelo`
  ADD CONSTRAINT `FKnsndlfkbon3sbxik0xvclk082` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id`),
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
  ADD CONSTRAINT `FK1e420lel73p8cv5ghvogtv8q4` FOREIGN KEY (`id_hospede`) REFERENCES `hospede` (`id`),
  ADD CONSTRAINT `FKbuginh1tsyy6e7c4buv5wsg22` FOREIGN KEY (`id_check`) REFERENCES `check_in_out` (`id`),
  ADD CONSTRAINT `FKpgli26cru9dmd2gh0bby9klfg` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`),
  ADD CONSTRAINT `fk_receber_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `FKatssbui59kt1kvwk4xvr8x2t8` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`),
  ADD CONSTRAINT `FKb9jngn2my2etkgx3biv7m4utl` FOREIGN KEY (`id_hospede`) REFERENCES `hospede` (`id`),
  ADD CONSTRAINT `FKqirr6iwxec1srlqqjtih188dw` FOREIGN KEY (`check_id`) REFERENCES `check_in_out` (`id`),
  ADD CONSTRAINT `fk_reserva_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `reserva_quarto`
--
ALTER TABLE `reserva_quarto`
  ADD CONSTRAINT `FK3atwccg8kdgi4u01rbmvcbets` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`),
  ADD CONSTRAINT `FKrjqw0krecnw58448li9xepk8f` FOREIGN KEY (`id_quarto`) REFERENCES `quarto` (`id`);

--
-- Restrições para tabelas `veiculo`
--
ALTER TABLE `veiculo`
  ADD CONSTRAINT `FK1oqvenyfu8uph62t14dosetil` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`),
  ADD CONSTRAINT `FK7nb3xkqesdtf9gn9s5cmlan8j` FOREIGN KEY (`id_modelo`) REFERENCES `modelo` (`id`),
  ADD CONSTRAINT `FK87rw23sbwn8lq4dmlvhonym6c` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`),
  ADD CONSTRAINT `FKglviyl6o66k286wfdxo3rc0wy` FOREIGN KEY (`id_hospede`) REFERENCES `hospede` (`id`),
  ADD CONSTRAINT `FKptg5evm2q9d2tn6980nxfyxcl` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id`),
  ADD CONSTRAINT `fk_veiculo_fornecedor1` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_veiculo_funcionario1` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_veiculo_hospede1` FOREIGN KEY (`hospede_id`) REFERENCES `hospede` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_veiculo_modelo1` FOREIGN KEY (`modelo_id`) REFERENCES `modelo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
