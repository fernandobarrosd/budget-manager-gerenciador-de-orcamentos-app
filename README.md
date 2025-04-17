# Budget Manager - Gerenciador de orçamentos

Um aplicativo de gerenciamento de orçamentos

## Requisitos funcionais

- Cadastro de orçamentos
- Listar os orçamentos
- Pesquisar orçamentos
- Filtar os orçamentos pela data, categoria e os que foram concluídos ou não
- Deletar orçamentos
- Deletar automaticamente os orçamentos que possuem uma data de finalização
- Alterar orçamentos
- Marcar os requisitos do orçamento como concluídos
- Poder efetuar o backup dos orçamentos e posteriormente recuperar todos os orçamentos


## Requisitos não funcionais
- Os orçamentos serão salvos localmente no dispositivo
- O padrão de projeto deve ser o MVVM
- Não é necessário se autenticar na aplicativo pra poder utilizar as funcionalidades
- Para poder marcar um orçamento como concluído, todos os requisitos do orçamento devem ser concluídos
- Para poder cadastrar um orçamento, o orçamento deve possuir no minimo um requisito
- Caso eu defina um orçamento com uma data de finalização e a passou da data, deve adicionar o orçamento na categoria de<br>
orçamentos expirados
- Não deve ser possível alterar orçamentos expirados

## O que são os requisitos do orçamento
Todos os itens do orçamento
