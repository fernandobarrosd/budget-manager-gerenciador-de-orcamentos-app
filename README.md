# Budget Manager - Gerenciador de orçamentos

Um aplicativo de gerenciamento de orçamentos

## Requisitos funcionais

- Cadastro de orçamentos
- Listar os orçamentos
- Pesquisar orçamentos
- Filtar os orçamentos pela data, categoria, quantidade de itens do orçamento
- Deletar orçamentos
- Alterar orçamentos
- Marcar os itens do orçamento como concluídos
- Poder efetuar o backup dos orçamentos e posteriormente recuperar todos os orçamentos


## Requisitos não funcionais
- Os orçamentos devem ser salvos localmente no dispositivo
- O padrão de projeto deve ser o MVVM
- Não é necessário se autenticar no aplicativo pra poder utilizar as funcionalidades
- Para poder cadastrar um orçamento, o orçamento deve possuir no minimo um item
- As categorias que um orçamento pode receber são: concluido, não concluido e expirado
- Não é obrigatório data de término quando for cadastrar um orçamento
- Para poder marcar um orçamento como concluído todos os itens do orçamento devem estar concluídos
- Caso todos items do orçamento estiverem concluídos e o orçamento tiver uma data de término, deve marcar o orçamento como concluído automaticamente
- Caso eu defina um orçamento com uma data de término e a passou da data, deve automaticamente marcar o orçamento como expirado
- Não deve ser possível alterar orçamentos expirados e concluídos
- Quando for efetuar o backup dos orçamentos salvar em um arquivo .json
