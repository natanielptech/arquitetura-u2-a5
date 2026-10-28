# Playbook: refatoração assistida

1. Execute a suíte completa e registre o baseline.
2. Peça à IA para apontar smells com justificativa e localização; trate a lista como hipótese.
3. Escolha um candidato com benefício claro e leia o método/classe e seus chamadores.
4. Peça uma alteração pequena, explicitando invariantes e comportamento a preservar.
5. Inspecione o diff; execute testes e compare resultados com o baseline.
6. Se algo falhar ou mudar sem intenção, reverta o passo e investigue.
7. Solicite revisão humana, documente a sugestão aproveitada e a evidência.

**Checklist:** baseline verde · comportamento preservado · diff pequeno · testes depois · reversão disponível · origem registrada.
