# Como rodar aplicação

> 🚨 **Atenção, ler atentamente antes de codar ou iniciar a aplicação!!!!!** 🚨 

## Requisitos
- Docker;
- Git;
- Git Bash ou Qualquer terminal baseado em Bash.
  
## Desenvolvimento
Criar no **diretório base** e no diretório **.database** do seu projeto um arquivo .env com o seguinte conteúdo:
```
ROOT_PASSWORD="{senha_do_banco_desejada}"
```

Executar pelo `git bash` ou qualquer `bash` (CMD e Powershell não inclusos) o arquivo "build.sh"
```
./build.sh
```

Executar pelo `git bash` ou qualquer `bash` (CMD e Powershell não inclusos) o arquivo "./.database/build.sh"
```
cd .database
./build.sh
```

Executar pelo `git bash` ou qualquer `bash` (CMD e Powershell não inclusos) o arquivo "start.sh"
```
./run.sh
```

Abrir no **insomnia** e testar o projeto.
