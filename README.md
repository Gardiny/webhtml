# hands-on-t5-razr-ultra

Repositório para o time Razr Ultra (turma 5)

## Sistema de Avaliação de Skills

- **[Histórias de Usuário](https://github.com/webacademyufac/hands-on-t5-razr-ultra/blob/main/docs/historias%20de%20usuario)**
- **[Diagrama Entidade Relacionamento](https://github.com/webacademyufac/hands-on-t5-razr-ultra/blob/main/docs/modelagem%20banco%20de%20dados/DER.png)**
- **[Tabelas](https://github.com/webacademyufac/hands-on-t5-razr-ultra/blob/main/docs/modelagem%20banco%20de%20dados/tabelas.png)**
- **[Protótipos](https://github.com/webacademyufac/hands-on-t5-razr-ultra/tree/main/docs/prot%C3%B3tipos)**

## Equipe

<a href="https://github.com/webacademyufac/hands-on-t5-razr-ultra/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=webacademyufac/hands-on-t5-razr-ultra" />
</a>


## Descrição do Projeto
Este projeto é uma aplicação web.

<details>

<summary>
    <h3>Ferramentas</h3>
</summary>

- **Visual Studio Code**
  - <https://code.visualstudio.com/Download>
- **Extension Pack for Java (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack>
- **Spring Boot Extension Pack (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=pivotal.vscode-boot-dev-pack>
- **XML (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=redhat.vscode-xml>
- **Angular Language Service (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=Angular.ng-template>
- **Git**
  - <https://git-scm.com/downloads>
- **Postman**
  - <https://www.postman.com/downloads/>
- **JDK 17**
  - Para verificar se o JDK está corretamente instalado e configurado, digite no prompt de comandos:

    ```console
    javac -version
    ```

  - Se necessário, realizar a instalação e configuração:
    - Link para download: <https://download.oracle.com/java/17/archive/jdk-17.0.10_windows-x64_bin.msi>
    - Criar a variável de ambiente JAVA_HOME configurada para o diretório de instalação do JDK. Exemplo: “C:\Program Files\Java\jdk-17”.
    - Adicionar “%JAVA_HOME%\bin” na variável de ambiente PATH.
    - Tutorial de configuração: <https://mkyong.com/java/how-to-set-java_home-on-windows-10/>
- **Maven**
  - Para verificar se o Maven está corretamente instalado e configurado, digite no prompt de comandos:

    ```console
    mvn -version
    ```

  - Se necessário, realizar a instalação e configuração:
    - Link para download: <https://dlcdn.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.zip>
    - Adicionar o diretório de instalação do Maven na variável de ambiente PATH. Exemplo: “C:\apache-maven\bin”.
    - Tutorial de instalação: <https://mkyong.com/maven/how-to-install-maven-in-windows/>
- **MySQL**
  - Verificar se o MySQL está funcionando:
    - Para tentar conectar no MySQL, no prompt de comandos digite:

      ```console
      mysql -u root -p
      ```

    - Tentar acessar com senha em branco ou senha igual ao nome de usuário (root).
    - Tutorial para resetar a senha de root, caso necessário: <https://dev.mysql.com/doc/mysql-windows-excerpt/8.0/en/resetting-permissions-windows.html>
  - Remova o banco de dados ```sgs```, se existir:
    - No prompt de comandos digite:
  
      ```console
      mysql -u root -p
      ```
  
    - Ao conectar no MySQL, execute a seguinte instrução SQL:

      ```sql
      DROP DATABASE sgs;
      ```
  
  - Se necessário, realizar a instalação:
    - Link para download: <https://dev.mysql.com/downloads/file/?id=516927>
    - [Tutorial de instalação](https://github.com/webacademyufac/tutoriais/blob/main/mysql/mysql.md)
- **Node.js (e npm)**
  - Versão 20 (LTS).
  - Para verificar a versão do Node.js, no prompt de comandos digite:

    ```console
    node --version
    ```

  - Link para download: <https://nodejs.org/dist/v20.14.0/node-v20.14.0-x64.msi>
- **Angular CLI**
  - Versão 17.
  - Para verificar a versão do Angular CLI, no prompt de comandos digite:

    ```console
    ng version
    ```

  - Tutorial de instalação: <https://v17.angular.io/guide/setup-local>
  - Para instalar o Angular CLI, no prompt de comandos digite:

    ```console
    npm i -g @angular/cli@17.3.10
    ```

</details>

> [!IMPORTANT]
> A aplicação vai iniciar no endereço <https://localhost:9000/>, com acesso local a base de dados MySQL, por meio da porta padrão 3306, além de usuário e senha "root".

<h3>Front-end</h3>

As dependências do projeto não são compartilhadas no repositório. Para instalar as dependências, a partir do diretório `./sgs`, no prompt de comandos, digite:

```console
    npm install
```

Para iniciar a aplicação, a partir do diretório `./sgs`, execute o comando:

```console
    ng serve
```

> [!IMPORTANT]
> O frontend será executado em http://localhost:4200.


## License
Huerb é licenciado sob a [MIT License](LICENSE).
