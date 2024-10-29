# GED App - Gerenciador de Documentos

### Projeto Integrador - APAE Criciúma

Este projeto tem como objetivo desenvolver um aplicativo de gestão de documentos para a APAE de Criciúma, fornecendo uma solução eficiente e acessível para o gerenciamento de arquivos.

---

## Guia Inicial do Projeto

### Pré-requisitos

- **Java 21**  
- **Docker** e **WSL** instalados na máquina (preferível)
- **IDE** de sua escolha (ex.: IntelliJ, VS Code)
- **PostgreSQL**

---

## Clonar o repositório

Clone o repositório na pasta que preferir do seu computador com o seguinte comando no seu terminal:
```bash
  git clone https://github.com/JuannCarloss/ged-app.git
```
---

## Após clonar

- Abra o projeto na sua IDE
- Abra o terminal interno da IDE e execute o seguinte comando nele:
```bash
  mvn clean install  
```
---

## Swagger
O Swagger é um pacote de dependências do Java que cria sozinho um front-end básico que documenta os endpoints e controllers que existem na aplicação

As configurações necessárias para que ele funcione já estão feitas, sendo necessário apenas algumas anotações nos controllers que você pode encontrar os  exemplos explicados abaixo em ``/controllers/HealthChecker.java``

Para ver esse front-end **Inicie a Aplicação** e <a href="http://localhost:8080/swagger-ui/index.html">Clique aqui</a>

---

### Anotações do Swagger

# ``@Tag``

A anotação ``@Tag`` é responsável por agrupar nossos endpoints em algum contexto, por exemplo: 

### Sem ``@Tag``
![image](https://github.com/user-attachments/assets/ef68af61-ad76-4b51-a4e4-28265b0cd3e9)

### Com ``@Tag`` com o nome "Saúde"
![image](https://github.com/user-attachments/assets/f8d7893c-3dcc-4b70-9a78-b00b92ef6fa8)

Dessa forma, caso eu crie mais um endcpoint no ``/controllers/HealthChecker.java`` colocando ele nessa ``@Tag`` com nome **"Saúde"**, no Swagger eles vão ficar agrupadinhos e fáceis de se encontrar, por exemplo:

![image](https://github.com/user-attachments/assets/1d76e3e1-11ae-4a84-9ce7-2775e76db6f6)

---

# ``@Operation``

A anotação ``@Operation`` é responsável por esclarecer o que cada endpoint faz, por exemplo:

### Com ``@Operation`` (endpoint ``/health/cheacker1``)
![image](https://github.com/user-attachments/assets/4292af4f-3a66-4928-8ef6-26f4bc5135dd)

Vou deixar por enquanto apenas essas duas anotações para o pessoal pegar o jeito por enquanto, ao alocar tarefas, vou explicando outras anotações que vamos usar.

---

# Commit Standards (Padrões de Commit)

Como nosso foco nesse projeto vai ser a documentação para deixar esse lindo legado para as próximas turmas de ADS modificarem o mesmo, o mais correto a se fazer é seguirmos alguns padrões pelo menos de documentação para que eles possam se encontrar com mais facilidade no futuro, por isso pensei em seguirmos alguns padrões de commits para esse projeto

### O que é ?

Padrões de commit é uma prática onde vamos identificar o tipo do commit anotando o início dele com alguns nomes **PADRÕES**, os nomes que mais vamos usar acredito que possa vir ser:

---

**FEAT** - usado quando você cria alguma nova funcionalidade, por exemplo:

Criei um novo método de encriptar a senha na classe service, o commit seria:

**Feat: método que criptografa a senha do usuário**

---

**REFACT** - usado quando você apenas refatora algum bloco de código, sem alterar a funcionalidade do código em sí, por exemplo:

Mudei o código de salvar usuário e apenas mudei a mensagem da exception, deixando ela mais clara, o commit seria:

**Refact: mensagem da exception melhorada para maior clareza**

---

**BUILD** - usado quando você muda alguma configuração que afete o start/runtime da aplicação, por exemplo:

Adicionei a dependência do Spring Security na Aplicação, o commit seria:

**Build: dependência do Spring Security adicionado**

---

**FIX** - usado quando você ajusta algum bug no código, por exemplo:

Fiz uma mudança em um código que deveria retornar paginado de 5 em 5 e retornava de 10 em 10, o commit seria:

**Fix: ajustado para paginação 5 em 5**

---

Caso tenha se interessado no assunto e queira ver mais anotações e padrões que o mercado de trabalho segue, fica o link para leitura: <a href="https://medium.com/linkapi-solutions/conventional-commits-pattern-3778d1a1e657">Conventional Commits</a>

Caso tenham alguma dúvida, fiquem a vontade para entrar em contato comigo que eu ajudo com todo prazer, quaisquer mudanças que verem e quiserem adicionar a esse documento estão livres para faze-lá, agradeço a compreensão de todos.
