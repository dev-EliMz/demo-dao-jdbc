## Java JDBC: Implementação com demo-dao-jdbc

## Sobre o projeto

Este projeto consiste em uma aplicação feita a partir de um pequeno projeto desenvolvido no curso Java COMPLETO Programação Orientada a Objetos + Projetos, ministrado pelo Professor Nelio Alves da DevSuperior na Udemy.

O projeto extende a arquitetura desenvolvida ao longo do projeto demo-dao-jdbc original, utilizando arquitetura em camadas para controle de dados.

O foco do projeto é treinar boas práticas de:

- Separação de responsabilidades 
- Arquitetura em camadas
- Tratamento de excessões
- Organização de código orientado a domínio

## Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

application (UI / Menu)
        v
services (Regras de negócio)
        V
dao (Acesso a dados - JDBC)
        V
db (Conexão com o banco de dados)

* Camadas 

- Application

Responsável pela interação com o usuário via console, esta camada contém os menus, trata excessões e exibe mensagens.

- Services

Contendo as regras de negócio, os serviços validam os dados de entrada e controlam o fluxo da aplicação

- Model: Pacotes implementados no curso de java
- model.dao

Contém as interfaces Dao e define operações de persistência.

- model.dao.impl

Possui as implementações JDBC e DAOs, executa SQL.

- model.entities

Entidades de domínio: Seller e Department.

- Db: Implementado no curso de java

Gerencia conexão com o banco, trata excessões de infraestrutura.

## Conceitos aplicados

- DAO (Data Access Object)
- Factory Pattern (DaoFactory)
- Tratamento de excessões estruturado
- Validação na camada de serviço
- Separação entre domínio, infraestrurua e apresentação

## Tecnologias utilizadas

- Java
- JDBC
- MariaDB
- Eclipse IDE

## Como se preparar para a execução da aplicação

1. Clone o repositório:

> git clone https://github.com/dev-EliMz/demo-dao-jdbc.git

2. Vá para o diretório do projeto

> cd demo-dao-jdbc

## Banco de dados

O projeto utiliza MariaDB, diferentemente do projeto original. Antesde executar a aplicação, é necessário criar o banco de dados e popular as tabelas.

Certifique-se de que o projeto contém os arquivos: schema.sql e seed.sql.

* Passo a passo para configurar o banco

1. Criar o banco de dados

Acesse o MariaDB via terminal:

> mysql -u seu_usuario -p -h 

Crie o banco:

> CREATE DATABASE nome_do_banco;
> USE nome_do_banco;

2. Executar o schema e o seed (estrutura das tabelas e dados iniciais)

Ainda dentro do MariaDB:

> SOURCE caminho/para/demo-dao-jdbc/init.sql;

Exemplos:

> SOURCE $HOME/myprojects/demo-dao-jdbc/init.sql;

Ou se já estiver no diretório do projeto: 
> SOURCE init.sql;

Isso irá executar os arquivos schema e seed.

Verifique se tudo funcionou com:

> SELECT * FROM department;
> SELECT * FROM seller;

5. Configurar conexão

Após configurar e testar o banco, edite o arquivo "dbconfig.propertiesExample" colocando os nome do banco, usuário e senha de acesso ao banco de dados, depois renomeie o arquivo para "dbconfig.properties".

Exemplo:

dburl=jdbc:mariadb://localhost:3306/nome_do_banco 
user=seu_usuario 
password=sua_senha

- Nota: MariaDB geralmente utiliza a porta :3306.

## Como executar

Na pasta do projeto (/demo-dao-jdbc/) execute no terminal:

> java application/Main



