# Store Stoom

Fiz umas mudanças do que era da proposta inicial, segue abaixo a sugestões de mudanças:

- Estrutura de pastas baseadas em domínios
- Service BO para duas classes Write e Read baseado no SOLID (S - Princípio da responsabilidade única) e Arquitetura Hexagonal Port, removi a interface não vejo necessidade nesse caso
- Response com DTO (Data Transfer Object) com construtores para mepeamentos (inspirado no Effective Java)
- Test Unit com Junit 5 do service Write como exemplo
- Uso do Lombok pra facilitar nossa vida, poderia usar o Java 17 record por exemplo, mas não optei em mexer na versão original

Espero que gostem. Desde já obrigado!

### OBS na raiz tem o arquivo do Postman para testes