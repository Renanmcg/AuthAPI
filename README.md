# AuthAPI
Nome da API: Authentication API

Tecnologias Utilizadas:

Spring Boot: Plataforma para construir aplicativos Java baseados em Spring.
Kotlin: Linguagem de programação que roda na Máquina Virtual Java (JVM).
Spring Security: Um módulo do Spring Framework que fornece autenticação e autorização.
Hibernate (JPA): Framework de mapeamento objeto-relacional (ORM) para persistência de dados.
JWT (JSON Web Tokens): Um método seguro e compacto para representar informações entre duas partes.
Swagger: Uma estrutura para documentação de APIs.
JUnit 5 e Mockito: Frameworks para testes de unidade e mock de objetos.
Recursos Principais:

Registro de Usuário:

Endpoint: POST /api/auth/register
Descrição: Permite aos usuários se registrarem fornecendo um nome de usuário e senha.
Autenticação: Aberto a todos (não requer autenticação).
Resposta:
Usuário registrado com sucesso (código de status HTTP 201) ou erro se o nome de usuário já existir (código de status HTTP 400).
Login:

Endpoint: POST /api/auth/login
Descrição: Permite que os usuários façam login fornecendo um nome de usuário e senha.
Autenticação: Aberto a todos (não requer autenticação).
Resposta:
Token de acesso (JWT) é retornado se as credenciais estiverem corretas (código de status HTTP 200), ou erro se as credenciais estiverem erradas (código de status HTTP 401).
Documentação da API:

Endpoint: /swagger-ui.html
Descrição: A documentação Swagger é gerada automaticamente e permite aos desenvolvedores visualizarem e testarem os endpoints da API.
Segurança:

A API usa o Spring Security para proteger os recursos, exigindo autenticação para a maioria das operações.
A autenticação é baseada em tokens JWT, que são gerados durante o processo de login.
Notas Adicionais:

A API segue princípios RESTful para design de endpoints.
Ela usa o Hibernate para persistência de dados, permitindo que os dados do usuário sejam armazenados em um banco de dados.
Os testes de unidade são incorporados na aplicação para garantir a qualidade do código.
O Swagger é usado para fornecer documentação fácil de usar para a API.
