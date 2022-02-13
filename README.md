# dynamodb-example

Aplicação de exemplo para demonstrar o uso do dynamodb local.

Trata-se de uma simples api que interage com o dynamoDB.

OBS: Para executar usando dynamodb local você deve passar a propriedade
```properties 
aws.endpoint=http://localhost:8000
```
Não informar esta propriedade no application.properties fará com que a aplicação tente se comunicar com os serviços reais da aws.
Caso este seja seu objetivo você deve ter a credenciais de acesso a aws configuradas no seu ambiente, 
para mais detalhes consulte a [documentação oficial da aws](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials.html).


## como executar

requisitos:
 - Java 11
 - Docker

primeiro, para subir os containers execute na raiz do projeto
```sh
docker-compose up --build
```

em seguida para subir a aplicação
```sh
./gradlew bootRun
```

## como usar

A aplicação simula uma api para cadastrar pintores e suas pinturas.

É possível acessar o swagger da api no endereço `http://localhost:8080/swagger-ui.html` 


Seguem alguns comandos curl para teste

**Criando um pintor**
```sh
curl --location --request POST 'localhost:8080/painters' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "123",
    "name": "Leonardo da Vinci",
    "birthdate": "1452-04-15",
    "paintings":[
        {
            "name": "Mona Lisa (La Gioconda)",
            "location": "Louvre, Paris"
        },
        {
            "name": "The Last Supper",
            "location": "Santa Maria delle Grazie, Milan"
        }
    ]
}'
```

**Bucando um pintor pelo id**
```sh
curl --location --request GET 'localhost:8080/painters/123'
```

**Deletando um pintor**
```sh
curl --location --request DELETE 'localhost:8080/painters/123'
```
