# Desafio CTC TECH - 50 mil frases mais repetidas em um arquivo.


## Sobre o projeto

Projeto desenvolvido para avaliação de conhecimento da empresa CTC TECH.   
Consiste em buscar as 50 mil frases mais repetidas de um arquivo muito grande que não caiba na memória do computador (por exemplo, mais de 100GB)

## Pré-requisitos para executar o projeto:

* Java: 11 ou superior.
* Maven: 3.8.1
* JUnit 5.

## Para executar a aplicação:

No diretório do projeto execute o seguinte comando no terminal:  
`mvn clean install`   
`
Abra o projeto em uma IDE.      
execute a classe "FrasesMaisFrequentesMain" do pacote com.ricardofarias.ctc.main   `


## Nome arquivo de entrada.

Coloque na raiz do projeto o arquivo de entrada. O nome do arquivo deve ser:  `basedados-frases.txt`      
Caso queira informar um novo nome, informe o nome no paramêntro do método "LerArquivo.lerArquivoBaseDeDados da classe main.   

No final do processamento será gerado um novo arquivo com o nome `resultado-frases.txt` na raiz do projeto.   

Rode o projeto em uma IDE ou Mova o arquivo frasesMaisRepetidas-1.0-SNAPSHOT.jar para raiz do projeto (local do arquivo basedados-frases.txt) e o execute.   

**O arquivo gerado contém a frase e sua quantidade dividida por | (pipe).**   

**O tempo de processamento varia conforme o tamanho do arquivo de entrada.**
## Autor:

Ricardo Farias.

https://www.linkedin.com/in/ricardo14231/

## Licença:

MIT
