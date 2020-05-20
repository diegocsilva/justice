# Justice

O sistema Justice foi desenvolvido para auxiliar na gestão de casos jurídicos.

# Arquitetura
- Microservice
- REST
- DDD

# Tecnologias Utilizadas
- **JAVA**

- **Spring Boot** - Um conceituado ecosistema focado para a criação de microservices, já provém várias ferramentas que auxiliam no desenvolvimento, possui integração com Google DataStore, o que facilitou na escolha.

- **Google DataStore** - O Cloud Datastore é um banco de dados NoSQL altamente escalonável, ideal para aplicativos Web e de dispositivos móveis, tem um grande foco em Bigdata.

# Executando a aplicação
### Pré-requisitos
- [Java (JDK8)](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) - Deve estar instalado e configurado para compilar e rodar a aplicação
- [Google Datastore](https://cloud.google.com/datastore) - Para subir a aplicação é necessário ter uma conta ativa na Google e acesso a criação de um novo projeto do Datastore no Google Cloud Plataform, após a criação do projeto você deverá deverá configurar localmente a váriavel de ambiemte para que a aplicação consiga se conectar a base, o [link](https://cloud.spring.io/spring-cloud-gcp/1.1.x/single/spring-cloud-gcp.html#_credentials) a seguir irá ajudar. 
- [Maven](https://maven.apache.org/install.html)
- [Node](https://nodejs.org/en/download/) Instale a versão estável mais atual do mesmo
- [Yarn](https://classic.yarnpkg.com/pt-BR/docs/install/#debian-stable) Instale a versão estável mais atual do mesmo

### Subindo a stack

#### Backend
Após baixar o projeto em uma pasta no computador e de ter instalado os pré-requisitos, abra um terminal e vá até a pasta raiz do projeto, em seguida execute:
		`cd justice-backend`
E logo após:
    `mvn clean install`
O comando acima gerará o artefato para que suba a aplicação localmente, após a conclusão do processo acima, execute o comando abaixo para iniciar a aplicação do backend:
    `java -jar target/justice-0.0.1-SNAPSHOT.jar`
Se tudo ocorrer bem você já poderá acessar a documentação do backend feita com o swagger no endereço:

http://localhost:8080/swagger-ui.html

#### Frontend
Após baixar o projeto em uma pasta no computador e de ter instalado os pré-requisitos, abra um terminal e vá até a pasta raiz do projeto, em seguida execute:
		`cd justice-frontend`
E logo após:
    `yarn`
E então faça o build do projeto:
    `yarn build --profile`
Agora precisamos configurar um servidor para rodar a aplicação e usaremos o [serve](https://www.npmjs.com/package/serve) para isso, executando os seguintes passos: 
    `npm i serve`
E após a instalação:
    `serve build`
Pronto no terminal aparecerá um link do endereço local onde está rodando a aplicação do front end, é só copiar e acesso-lo no browser de sua preferência    
