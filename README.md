### GENERAL

Our robust backend project, powered by Spring Boot, forms the backbone of our real-time chat app. Utilizing the efficiency of WebSockets, it enables seamless and instantaneous communication between users. Crafted with precision and efficiency in mind, the backend ensures secure message transmission while maintaining the integrity of user data. With a strong foundation in Spring Boot's technology, our backend project guarantees the reliability and scalability required to support the app's real-time messaging features effectively.

### DESCRIPTION

```json
{
  "Framework"                  : "Spring Boot"
  "Also Used"                  : "Spring Security, JPA"
  "Token Type"                 : "JWT (JSON Web Token)"
  "Database"                   : "MySQL"
  "TCP"                        : "WebSocket"
  "Frontend"                   : "Native Android App"
  "Frontend Project"           : "https://github.com/saadahmedscse/Realtime-Chat-App-Android"
}
```

### ENDPOINTS

##### CREATE ACCOUNT
```json
  {
    "Create Account"           : "http://localhost:8080/api/create-account",
    "API"  : {
                "Content-Type" : "multipart/form-data"
                "Accept"       : "application/json"
                "Method"       : "POST"
                "Body"         : {
                                    "name"               : "String"
                                    "email"              : "String"
                                    "gender"             : "String"
                                    "password"           : "String"
                                    "confirmPassword"    : "String"
                                    "photo"              : "File"
                                 }
             }
  }
```

##### LOGIN
```json

  {
    "Login"                    : "http://localhost:8080/api/login",
    "API"  : {
                "Content-Type" : "application/json"
                "Accept"       : "application/json"
                "Method"       : "POST"
                "Body"         : {
                                    "email"              : "String"
                                    "password"           : "String"
                                 }
             }
  }
```

##### GET FRIENDS
```json

  {
    "Get Friends"              : "http://localhost:8080/api/user",
    "API"  : {
                "Header"       : "Authorization (Bearer Token)"
                "Accept"       : "application/json"
                "Method"       : "GET"
             }
  }
```

##### GET PROFILE

```json
  {
    "Get Profile"              : "http://localhost:8080/api/user/profile",
    "API"  : {
                "Header"       : "Authorization (Bearer Token)"
                "Accept"       : "application/json"
                "Method"       : "GET"
             }
  }
```

##### GET FRIEND'S PROFILE

```json
  {
    "Get Friend's Profile"     : "http://localhost:8080/api/user/profile/{user_id}",
    "API"  : {
                "Header"       : "Authorization (Bearer Token)"
                "Accept"       : "application/json"
                "Method"       : "GET"
             }
  }
```

##### CREATE CONVERSATION

```json
  {
    "Create Conversation"      : "http://localhost:8080/api/conversation/create",
    "API"  : {
                "Header"       : "Authorization (Bearer Token)"
                "Content-Type" : "application/json"
                "Accept"       : "application/json"
                "Method"       : "POST"
                "Body"         : {
                                    "p1Email"           : "String"
                                    "p2Email"           : "String"
                                 }
             }
  }
```

##### GET MY CONVERSATIONS

```json
  {
    "Get My Conversation"      : "http://localhost:8080/api/conversation",
    "API"  : {
                "Header"       : "Authorization (Bearer Token)"
                "Accept"       : "application/json"
                "Method"       : "GET"
             }
  }
```

##### GET SINGLE CONVERSATION BY EMAILS

```json
  {
    "Get Single Conversation"  : "http://localhost:8080/api/conversation/single",
    "API"  : {
                "Header"       : "Authorization (Bearer Token)"
                "Content-Type" : "application/json"
                "Accept"       : "application/json"
                "Method"       : "POST"
                "Body"         : {
                                    "p1Email"           : "String"
                                    "p2Email"           : "String"
                                 }
             }
  }
```

### DEPENDENCIES

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
            <version>3.1.3</version>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>

        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.9</version>
        </dependency>

    </dependencies>
```
