# BCB - Big Chat Brasil (Teste técnico)

## Como executar o projeto

- Executar o arquivo [docker-compose.yml](./docker/docker-compose.yml) via IDE ou via comando:
```shell
cd ./docker
docker-compose up -d
```
- Executar o Liquibase via IDE ou via comando:
  ```shell
  mvn clean compile liquibase:update
  ```

- Executar os 3 projetos: 
  - [TesteTecnicoApplication](./src/main/java/br/com/irrah/testetecnico/TesteTecnicoApplication.java)
    - API deste projeto executa na porta 9000.
  - [BackofficeApplication](./bcb-backoffice/src/main/java/br/com/irrah/testetecnico/backoffice/BackofficeApplication.java)
    - API deste projeto executa na porta 9001.
  - [BcbEnvioMensagemApplication](./bcb-envio-mensagem/src/main/java/br/com/irrah/testetecnico/BcbEnvioMensagemApplication.java)
- Collection do projeto disponível no arquivo [BCB.postman_collection.json](./BCB.postman_collection.json)

Premissas:

- Os 2 módulos que estão inclusos no mesmo repositório estão em um mesmo repositório para ser necessário enviar somente um link para a correção do teste.
A ideia seria que cada projeto tenha o seu repositório.
- O backoffice será um projeto expandido no futuro, portanto foi feito como um projeto separado da API principal.
- O envio de mensagens foi feito como microsserviço para ser mais facil o escalonamento horizontal, pois o fluxo de mensagens poderá aumentar consideravelmente com a evolução do sistema.
- A tarifa de mensagem a princípio está definida em código, porém é necessário que ela esteja exposta de outra forma para ser possível a alteração sem subir uma nova versão do sistema.
- Os planos foram implementados com as seguintes regras em mente:
  - Pré-Pago
    - Enquanto o cliente possuir saldo, será possível enviar mensagens.
    - Caso o cliente tente enviar uma mensagem sem o saldo necessário, não será possível.
    - Caso ocorra algum erro no envio da mensagem, o saldo do cliente será reembolsado automaticamente.
    - Penso que há cenários onde o saldo do cliente possa atingir valores negativos, nesse cenário bastaria o cliente adicionar crédito para compensar o valor negativo.
  - Pós-pago
    - O limite de crédito utilizado pelo cliente é calculado utilizando a quantidade de mensagens enviadas com sucesso no mês atual.
    - Enquanto o cliente não ultrapassar o limite de crédito é possível enviar mensagens.
- O tratamento de erros será feito futuramente.
- Auditoria do banco de dados será implementada na versão final.