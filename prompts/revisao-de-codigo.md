# Prompt: primeira triagem de PR

> Faça uma triagem deste diff Java/Spring. Liste somente problemas candidatos, com arquivo/linha, evidência, impacto e forma de verificar. Considere responsabilidades, validação, segurança e testes. Não invente requisitos nem classifique hipótese como vulnerabilidade confirmada. Aponte também limitações da análise.

## Verificação humana obrigatória

- O comentário corresponde ao código e ao contexto?
- Existe caminho de execução e impacto demonstrável?
- A correção proposta preserva comportamento e contratos?
- Os testes ou evidências confirmam o problema?
- Uma pessoa revisora autorizada aprovou o PR?
