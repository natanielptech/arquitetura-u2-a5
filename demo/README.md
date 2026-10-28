# Demo Java/Spring

Mini-sistema educacional em Java 21 e Spring Boot 3.4. A implementação não integra pagamento, banco de dados ou infraestrutura externa.

## Executar

```sh
mvn test
mvn spring-boot:run
```

## Endpoints

- `GET /api/orders/{id}`: consulta um pedido.
- `POST /api/orders/{id}/status`: altera o status. Corpo: `{"status":"PAID"}`.

O repositório em memória inicia com o pedido `order-1`, em `CREATED`. Status aceitos: `CREATED`, `PAID`, `SHIPPED`, `CANCELLED`. Uma transição para o mesmo status não publica evento.

```sh
curl -X POST http://localhost:8080/api/orders/order-1/status \
  -H 'Content-Type: application/json' \
  -d '{"status":"PAID"}'
```

## O que observar

- `legacy/LegacyOrderService` contém intencionalmente método longo, responsabilidades misturadas, condicionais por tipo e muitos parâmetros. Seus testes registram resultados esperados para servir de baseline em exercícios de refatoração.
- `refactored/RefactoredOrderService` mostra uma sugestão de extração: parâmetros reunidos em `OrderRequest`, cálculo isolado em `OrderPricingService` e texto em `OrderSummaryFormatter`. Os testes comparam os resultados com a versão legada em cenários equivalentes; use-os como exemplo de verificação antes/depois, não como prova de cobertura total.
- `order/OrderService` publica `OrderStatusChangedEvent` via `ApplicationEventPublisher`; `NotificationListener` e `AuditListener` recebem o evento com `@EventListener`. É um exemplo didático de Observer, não de mensageria durável.
- A implementação atual e seus testes são a versão funcional do demo. Refatorações propostas devem ser feitas em passos pequenos, mantendo os testes verdes antes e depois.

## Transparência

Este repositório demonstra padrões, não recomenda aceitar automaticamente código gerado. Registre apoio de IA em mudanças relevantes usando `../templates/registro-de-apoio-de-ia.md`; aprovação e merge exigem revisão humana.
