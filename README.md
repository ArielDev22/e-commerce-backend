# 🛒 Ziara - E-commerce Full Stack

**Ziara** é uma aplicação de e-commerce full stack altamente funcional, com front-end desenvolvido em **React.js** (CSS puro) e back-end estruturado em **Java com Spring Boot**, utilizando uma arquitetura **monolítica integrada**. Este projeto foi desenvolvido com fins acadêmicos, mas com um padrão de qualidade voltado à produção.

## 🚀 Funcionalidades Principais

- ✅ Autenticação de usuários (JWT)
- 🔍 Busca de produtos por **nome** e **categoria**
- 🛒 Gerenciamento de **carrinho** e **favoritos**
- 💳 Simulação de **pagamento por cartão**
- 📧 Envio de e-mails (confirmação de cadastro e contato)
- ⚠️ Tratamento centralizado de exceções

## 🛠️ Tecnologias Utilizadas

### Backend
- **Linguagem**: Java
- **Frameworks**: Spring Boot, Spring Security, SpringMail, Spring Data JPA
- **Bibliotecas**: JWT, Lombok
- **Banco de Dados**: 
  - Em desenvolvimento: H2
  - Em produção/testes finais: MySQL
- **Ferramentas de apoio**: Postman

## 📁 Estrutura do Backend

### Domínios:
- `address`, `auth`, `card`, `cart`, `contact`, `email`, `favorite`, `handler`, `order`, `payment`, `product`, `token`, `user`

### Infraestrutura:
- `config`: configurações do banco de dados
- `security`: autenticação e autorização com JWT
- `utils`: utilitários diversos

## 🔐 Segurança

- Geração e validação de tokens com **JWT**
- Rotas protegidas por perfil de acesso
- Senhas criptografadas
- Controle de sessão baseado em tokens

## 🔄 Rotas de Exemplo

```http
POST /auth/login
GET  /products/of?category=sport
PUT  /my-account/address
````

## 🔧 Como Executar Localmente

> **Pré-requisitos:**
- Java 17+
- Maven
- MySQL em execução

1. **Clone o repositório**:
```bash
git clone https://github.com/ArielDev22/e-commerce-backend.git
````

2. **Configure o banco de dados** no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/e_commerce_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

3. **Execute a aplicação**:

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 🔗 Link para Apresentação no LinkedIn

Confira a apresentação pública do projeto no LinkedIn:

📎 [Apresentação Ziara - LinkedIn (Victtoria Correia)](https://www.linkedin.com/posts/victtoria-correia-3268v_reactjs-springboot-fullstack-activity-7340827643543457793-iAuF)

---

## 👨‍💻 Desenvolvedores

| Nome                  | Função                       | Links                                                              |
| --------------------- | ---------------------------- | ------------------------------------------------------------------- |
| **Ariel Rodrigues**   | Backend (Java / Spring Boot) | [GitHub](https://github.com/ArielDev22)
| **Victtoria Correia** | Frontend (React.js / CSS)    | [Frontend](https://github.com/Victtoriacorreia01/E-commerce-Faculdade)    |
