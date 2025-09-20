# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem completa do JDK para compilar o código
FROM eclipse-temurin:21-jdk-jammy AS builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia primeiro os arquivos de dependência para aproveitar o cache do Docker
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa as dependências do projeto
RUN ./mvnw dependency:go-offline

# Copia o restante do código fonte
COPY src ./src

# Compila e empacota a aplicação, pulando os testes
RUN ./mvnw package -DskipTests


# Estágio 2: Imagem Final de Execução
# Usamos uma imagem JRE, que é menor que a JDK, para rodar a aplicação
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar gerado no estágio anterior para a imagem final
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta que a aplicação usa (definida no application.properties)
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado
ENTRYPOINT ["java", "-jar", "app.jar"]