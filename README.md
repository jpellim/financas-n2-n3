# financas-n2-n3

Foi utilizado o banco de dados relacional embarcado H2

Foi escolhida a opção B - consulta assíncrona de alto volume 


CRUD de ativos

/ativo

create (POST)

{ "nome": "nome_ativo", "tipo": "RV", "dataEmissao": "02-02-2021", "dataVencimento": "22-02-2021", "precoMercado": "23.00" }

find All (GET)

update (PUT)

/ativo 

{ "nome": "nome_ativo", "tipo": "RV", "dataEmissao": "02-02-2021", "dataVencimento": "22-02-2021", "precoMercado": "23.00" }

delete (DELETE)

/ativo/{id} (id do ativo)

Movimentações de Ativos

/movimentacao

Compra

/comprar

{ "ativo": { "id": 101 }, "tipo": "COMPRA", "dataMovimento": "03-02-2021", "quantidade": 20, "valorMovimentacao": 230.00 }

Venda

/vender

{ "ativo": { "id": 101 }, "tipo": "VENDA", "dataMovimento": "03-02-2021", "quantidade": 10, "valorMovimentacao": 130.00 }

Consultar saldo

/conta-corrente?dataConsulta=03/02/2021    (GET)

COnsultar Posição de ativos

/posicao-ativos?dataConsulta=03/02/2021    (GET)
