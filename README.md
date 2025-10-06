# Tecnologias Utilizadas no projeto:
<p align="center">
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/java.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/spring.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/spring_boot.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/maven.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/swagger.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/hibernate.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/junit.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/mocikto.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/lombok.png" width="60"/>
</p>
<p align="center">
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/git.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/postgresql.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/docker.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/nginx.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/html.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/sass.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/angular.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/typescript.png" width="60"/>
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/npm.png" width="60"/>
</p>
<br>
<div align="center">

| **Backend**        | **DevOps / Banco de Dados** | **Frontend**   |
|---------------------|-----------------------------|----------------|
| Java               | Git                         | HTML           |
| Spring Framework   | PostgreSQL                  | Sass           |
| Spring Boot        | Docker                      | Angular        |
| Maven              | Nginx                       | TypeScript     |
| Swagger            |                             | NPM            |
| Hibernate          |                             |                |
| JUnit              |                             |                |
| Mockito            |                             |                |
| Lombok             |                             |                |

</div>





<br>

## Descrição do projeto
O projeto MinhaCasa é uma plataforma robusta e intuitiva voltada para facilitar a experiência de compra, venda e aluguel de imóveis. Desenvolvido com as mais recentes tecnologias Java, Spring Boot, Spring Security, PostgreSQL, JPA, Hibernate, Docker, Angular e TypeScript, o MinhaCasa oferece uma solução completa para usuários interessados no mercado imobiliário.

Recursos Principais:

#### Cadastro e Gestão de Imóveis: 
Os usuários podem cadastrar facilmente seus imóveis para venda ou locação, gerenciando todas as informações relevantes de forma eficiente.
#### Super Filtro Avançado: 
Utilizando tecnologias de filtragem avançada, o MinhaCasa permite que os usuários encontrem o imóvel ideal de acordo com suas preferências específicas.
#### Segurança e Privacidade: 
Implementação de Spring Security garante a segurança dos dados dos usuários e protege as transações realizadas na plataforma.
#### Testes Unitários: 
Utilização de Junit5 e Mockito para garantir a qualidade do código e a robustez das funcionalidades implementadas.<br><br>
Com uma interface moderna e responsiva, o MinhaCasa proporciona uma experiência fluida tanto para usuários que desejam comercializar quanto para aqueles que estão em busca de seu próximo lar. Este projeto visa não apenas simplificar, mas também aprimorar a forma como negócios imobiliários são conduzidos, oferecendo uma solução completa e confiável para todas as necessidades relacionadas ao mercado de imóveis.

# Rodando o projeto com Docker
0. Tenha o git juntamente com o git bash instalado na sua máquina.
1. Tenha o docker instalado na sua máquina;
2. Garanta que o docker foi iniciado e está rodando;
3. Clone o repositório em uma pasta da sua preferência;
4. Acesse a pasta raiz do projeto;
5. Abra o git bash dentro da pasta;
6. Permita a execução do arquivo `start.sh` com o comando `chmod +x ./start.sh`;
7. Execute o comando `./start.sh`;
8. Acesse `http://localhost:80` para acessar a interface do projeto;
```
Com isso, todos os container serão criados e configurados, incluindo:
* Banco de dados PostgreSQL
* Frontend: Angular 17
* Backend: Java 17 + Spring
```
9. Utilize o `email:admin@gmail.com` e a `senha: 12345678a!` para fazer login como administrador. 


## Documentação para usuários
* Para ter acesso a todas as funcionalidades do sistema, crie uma conta ou utilize as credenciais fornecidas na sessão `Rodando o projeto com Docker`.
* A documentação dos endpoints da api do projeto foi feita usando o Swagger, para acessar a documentação, acesse o link abaixo no navegador:<br>
``` `http://localhost:8080/swagger-ui/index.html#/` ```

## Documentação para desenvolvimento(localmente)
1. O projeto necessita de duas pastas para armazenar imagens, elas devem ser criadas no exato caminho:
```
C:
  /app
    /images-profile <- pasta
    /files-immobile <- pasta
```
2. Para instalar as dependências, utilize o comando `mvn install -DskipTests` para pular os testes e não gerar conflitos com o acesso ao banco de dados.
3. Existem 3 arquivos `.yml` para configurações, utilize a variável de ambiente `spring.profiles.active=dev` para utilizar as configurações de desenvolvimento, tendo como credenciais de banco de dados:
```
PostgreSql

user: postgres
password: 1234 <- altere conforme necessário!
```
4. O projeto foi desenvolvido na IDE InteliJ, e utiliza o plugin `Lombok` para diminuir a verbosidade, e acelerar o desenvolvimento, portanto, sem ele, o irá gerar erros no compilador `no modo desenvolvimento`, mas funcionará perfeitamente utilizando o Docker!
