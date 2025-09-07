## configuration Job 7 - Spring Web Flow 
1. ajouter dans pom.xml  

````xml
<dependency>
    <groupId>org.springframework.webflow</groupId>
    <artifactId>spring-webflow</artifactId>
    <version>3.0.0</version>
</dependency>

````
2. creer un fichier de configuration du flux  

dans `src/main/resources/flows/purchase-flow.xml`
  
````xml
<?xml version="1.0" encoding="UTF-8"?>
<flow xmlns="http://www.springframework.org/schema/webflow"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="
        http://www.springframework.org/schema/webflow
        https://www.springframework.org/schema/webflow/spring-webflow-2.5.xsd">

    <!-- Étape 1 : accueil -->
    <view-state id="start" view="purchase/start">
        <transition on="next" to="details"/>
    </view-state>

    <!-- Étape 2 : saisie des détails -->
    <view-state id="details" view="purchase/details">
        <transition on="next" to="confirm"/>
        <transition on="back" to="start"/>
    </view-state>

    <!-- Étape 3 : confirmation -->
    <view-state id="confirm" view="purchase/confirm">
        <transition on="finish" to="end"/>
        <transition on="back" to="details"/>
    </view-state>

    <!-- Fin -->
    <end-state id="end" view="purchase/success"/>
</flow>

````  
  
3. Créer les vues Thymeleaf  
  
Dans `src/main/resources/templates/purchase/`  

start.html
  
```html
<h1>Bienvenue dans le processus d'achat</h1>
<form th:action="flowExecutionUrl" method="post">
    <button type="submit" name="_eventId_next">Commencer</button>
</form>

```  
details.html  
  
````html
<h1>Détails de l'achat</h1>
<form th:action="flowExecutionUrl" method="post">
    <label>Produit: <input type="text" name="product"/></label><br/>
    <button type="submit" name="_eventId_back">Retour</button>
    <button type="submit" name="_eventId_next">Suivant</button>
</form>

````  
  
confirm.html  
  
````html
<h1>Confirmez votre achat</h1>
<p>Produit saisi : [[${flowScope.product}]]</p>
<form th:action="flowExecutionUrl" method="post">
    <button type="submit" name="_eventId_back">Retour</button>
    <button type="submit" name="_eventId_finish">Confirmer</button>
</form>

````  
  
success.html  
````html
<h1>Achat terminé 🎉</h1>
<p>Merci pour votre commande.</p>
<a th:href="@{/}">Retour à l'accueil</a>

````  
4. configurer Web Flow
Dans la class `WebFlowConfig` ou `WebMvcConfig` :

```java
@Configuration
@EnableWebMvc
public class WebFlowConfig {

    @Bean
    public FlowExecutor flowExecutor(FlowRegistry flowRegistry) {
        return new FlowExecutorImpl(flowRegistry);
    }

    @Bean
    public FlowRegistry flowRegistry() {
        FlowDefinitionRegistryBuilder builder = new FlowDefinitionRegistryBuilder();
        builder.setBasePath("classpath:flows");
        builder.addFlowLocationPattern("/**/*-flow.xml");
        return builder.build();
    }

    @Bean
    public FlowHandlerMapping flowHandlerMapping(FlowExecutor executor) {
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(-1);
        mapping.setFlowExecutor(executor);
        return mapping;
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter(FlowExecutor executor) {
        FlowHandlerAdapter adapter = new FlowHandlerAdapter();
        adapter.setFlowExecutor(executor);
        return adapter;
    }
}

```  

5. pour tester  
Lancer l'app et aller sur  
````bash
http://localhost:8080/purchase

````  
tu suivras les étapes : start → details → confirm → success.  
