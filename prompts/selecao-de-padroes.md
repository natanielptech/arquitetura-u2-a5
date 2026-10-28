# Prompt: explorar padrões

> Avalie o problema: “notificar vários componentes quando o status do pedido muda, sem acoplar o serviço aos consumidores”. Compare a solução mais simples com Observer via eventos Spring (`ApplicationEventPublisher`/`@EventListener`). Liste prós, contras, acoplamento, sincronismo, falhas e riscos de over-engineering. Não trate o padrão como requisito: indique que evidência comprovaria a necessidade.

## Verificação humana obrigatória

- O problema existe hoje, com consumidores reais?
- A simplicidade atual é insuficiente?
- A solução considera entrega síncrona e comportamento em falhas?
- A complexidade adicional está justificada por evidência?
- A decisão e os trade-offs foram confirmados por uma pessoa?
