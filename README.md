# **Checkerly - Backend**

Bem-vindo ao repositório do backend do **Checkerly**, um sistema desenvolvido como projeto integrador para simplificar a gestão de eventos. Este backend foi implementado utilizando **Java** com **Spring Boot**, oferecendo APIs seguras e robustas para suportar as funcionalidades do sistema.

---

## **Funcionalidades Principais**
- **Gerenciamento de usuários, eventos e participantes.**
- **Registro de presença via QR Code.**
- **Geração e envio de certificados em PDF por email.**
- **Autenticação e autorização utilizando JWT.**

---

## **Tecnologias Utilizadas**
- **Linguagem:** Java 21  
- **Framework:** Spring Boot  
- **Banco de Dados:** MongoDB  
- **Segurança:** Spring Security com autenticação JWT e hashing de senhas com BCrypt  
- **Envio de Emails:** JavaMail API  
- **Deploy:** Linux Debian  

---

## **Pré-requisitos**
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:  
- **Java 21**  
- **Maven**  
- **MongoDB**  
- **Git**  

---

## **Configuração do Ambiente**

## 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/checkerly-backend.git
cd checkerly-backend
```

## **2. Configure as variáveis de ambiente**
Defina as variáveis necessárias para a aplicação funcionar corretamente, como credenciais do banco de dados e configurações de email. Consulte a documentação do Spring Boot para configurar seu ambiente.

## **3. Compile o projeto**
```bash
mvn clean install
```

## **4. Execute o servidor**
```bash
mvn spring-boot:run
```
---

## Rotas Principais 
## ** Autenticação **
- POST /auth/login
- Endpoint para login e geração de token JWT.
- POST /auth/register
- Registro de novos usuários.

## ** Eventos **
- POST /events
- Criação de um novo evento.
- GET /events/{id}
- Retorna detalhes de um evento.

## ** Presenças **
- POST /attendance
- Registra a presença de um usuário via QR Code.

---

## ** Contribuindo **
Contribuições são bem-vindas! Por favor, abra uma issue ou envie um pull request para melhorias e correções.

---

## ** Criado com 💻 e ☕ por Equipe Checkerly. **
