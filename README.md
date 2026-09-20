
# Cadastro de Produtos Android - SQLite

## Sobre o projeto

Aplicativo Android desenvolvido em Java para realizar o cadastro de produtos utilizando banco de dados SQLite.

O projeto foi desenvolvido como atividade acadêmica do curso de Análise e Desenvolvimento de Sistemas (ADS), com o objetivo de praticar o desenvolvimento de aplicações Android, a persistência de dados e a validação de informações.

## Funcionalidades

- Cadastro de produtos.
- Informações de nome e preço.
- Validação dos dados informados.
- Armazenamento dos produtos utilizando SQLite.
- Listagem dos produtos cadastrados.
- Persistência dos dados após fechar e reabrir o aplicativo.

## Tecnologias utilizadas

- Java
- Android Studio
- SQLite
- SQLiteOpenHelper
- XML para desenvolvimento da interface

## Validações

O aplicativo realiza validações para evitar o cadastro de dados inválidos:

- O nome do produto deve conter pelo menos 3 caracteres.
- O preço deve ser um valor numérico maior que zero.
- Campos obrigatórios não podem ficar vazios.

## Estrutura do projeto

- `app`: módulo principal da aplicação Android.
- `MainActivity.java`: responsável pela interação com a interface.
- `Produto.java`: classe que representa o produto.
- `ProdutoDbHelper.java`: responsável pelo gerenciamento do banco de dados SQLite.

## Objetivo acadêmico

Desenvolver conhecimentos em programação Java para Android, manipulação de banco de dados SQLite, validação de dados e armazenamento persistente de informações.

## Autor

Gustavo do Nascimento Rodrigues
