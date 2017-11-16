# jaxrs-playground
Webservice Restful para estudo de JAX-RS, JAXB e etc

## Recomenações

Criar um container docker para o MongoDB:

```sh
$ docker pull mvertes/alpine-mongo
$ docker run -d --name mongo -p 27017:27017 mvertes/alpine-mongo
$ docker exec -ti mongo mongo
```

Dentro do shell do MongoDB:

```sh
> use jaxrsplayground
> exit
```

## Configuração
Caso seja necessário alterar alguma configuração de conexão com o MongoDB, edite o arquivo [src/main/resources/mongodb.properties](./src/main/resources/mongodb.properties).

## Serviços disponibilizados
| Método | Serviço | Função | Entrada | Content-Type |
|---|---|---|---|---|
| GET | /services/v1/usuario | Lista usuários cadastrados |   | application/json, application/xml |
| GET | /services/v1/usuario/{id} | Recupera o usuário | {id} - Identificador do usuário | application/json, application/xml |
| POST | /services/v1/usuario | Inclui um usuário | [Usuario]() | application/json, application/xml |

## Tipos
### Usuario
```json
{
"usuario": "teste1",
"email": "teste1@teste.com",
"senha": "wololow",
"dataCadastro": "2017-11-16T17:06:10-02:00",
"funcoes": ["administrador","usuario"]
}
```
```xml
<usuario>
  <id>5a0ddbb22767f31afc54cbbe</id>
  <usuario>teste</usuario>
  <email>teste@teste.com</email>
  <senha>wololow</senha>
  <dataCadastro>2017-11-16T14:02:00.000-02:00</dataCadastro>
  <funcoes>
    <funcao>administrador</funcao>
    <funcao>usuario</funcao>
  </funcoes>
</usuario>

```
Observação: dataCadastro deve ser no formato ISO 8601