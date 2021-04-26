# Serviço web 
 Serviço web com Jersey para conversão de moedas. Possui um docker container para o servidor web com tomcat contendo o serviço web, e um cliente para testar o serviço. 
 
 **Estrutura:** 
 *projetos*: pasta que contém o projeto inteiro, ou seja, um projeto para o serviço web e um outro projeto para o serviço web cliente. Também subi as bibliotecas que são utilizadas no projeto cliente. 
 *webapps*: contém o .war do servico web para poder rodar no docker. 
 *docker-compose.yml*: documento docker compose para rodar o servidor tomcat do servico web. 
 
 **Rodando a aplicação:**  
 Pela linha de comando entrar na pasta raiz e executar *docker-compose up -d*.
 O servico web estará executando. Tem duas maneiras de testar o serviço web. Por uma plataforma tipo *Postman* ou pelo *servico web cliente* que está na pasta *projetos*->*servico_cliente_jessica*.
 
 **URIs**  
 **URI** | **Método** | **Efeito**
 --------|-------|--------
 /dolar|GET| Busca a cotação na data de hoje
 /dolar/{data} |GET | Busca a cotação na data especificada na URI
 /dolar/{data}|DELETE|Remove a cotação da data especificada na URI
 /dolar{data}/{valor}|POST|Adiciona a cotação para a data especificada na URI
 /dolar/{data}/{valor|PUT|Altera a cotação para a data especificada na URI 
 
 *obs* os formatos de data tratados são: *22/04/2021*,*22042021* ou *22.04.2021*. 
 Exemplos:
 /dolar/22/04/2021
 /dolar/22042021
 /dolar/22.04.2021
 
 **Atributos estaticos**
 Os objetos da cotação são com atributos estáticos, não utilizando banco de dados.
 

