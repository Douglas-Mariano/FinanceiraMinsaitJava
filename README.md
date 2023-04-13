# Projeto de empréstimos em Java com Spring

Este é um projeto simples de empréstimos em Java com Spring, que permite ao usuário cadastrar um cliente e solicitar empréstimos.

Projeto Desenvolvido junto com o programa Jovem Profissional e 40+ | Trainee de Sistemas Java e Angular, da empresa Minsait.

Instrutor Gleyser Bomfim Guimarães.



### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

* JDK 1.8 ou superior
* Maven 3.2 ou superior
* Spring Boot 2.0 ou superior
* O aplicativo estará disponível em `http://localhost:8080`.
  
  

### Endpoints disponíveis

##### Cliente

POST - http://localhost:8080/api/v1/financeira/clientes

GET - http://localhost:8080/api/v1/financeira/clientes

GET - http://localhost:8080/api/v1/financeira/clientes/:cpf

PUT - http://localhost:8080/api/v1/financeira/clientes/:cpf

DELETE - http://localhost:8080/api/v1/financeira/clientes/:cpf

##### Emprestimo

POST - http://localhost:8080/api/v1/financeira/clientes/:cpf/emprestimos

GET - http://localhost:8080/api/v1/financeira/clientes/:cpf/emprestimos

GET - http://localhost:8080/api/v1/financeira/clientes/:cpf/emprestimos/:id

DELETE - http://localhost:8080/api/v1/financeira/clientes/:cpf/emprestimos/:id



### Testes

Para poder testar a aplicação aqui esta uma lista de CPF gerados no site https://www.geradorcpf.com/

- 37836806898

- 14718533228

- 45411315522

- 08561342870

- 32131345623
  
  




































