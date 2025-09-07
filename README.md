# runtrack_java_spring

runtrack java bootrack

### Qu'est-ce que Spring Initializr et comment peut-il faciliter la création d'un nouveau projet Spring ?

Spring Initializr est un outil en ligne (utilisable via des IDE comme IntelliJ ou des suites de logiciel comme Spring Tools Suite) qui permet de générer rapidement la structure de base d'un nouveau projet Spring.
Cela permet d'éviter de configurer manuellement le projet (via pom.xml par exemple si on utilise Maven).

### Pourquoi le fichier pom.xml est-il crucial dans un projet Maven ?

Le fichier pom.xml est absolument central dans un projet Maven, car il définit tout
ce que Maven doit savoir pour construire, gérer et déployer ton application.

### Qu'est-ce qu'un contrôleur dans le contexte de Spring MVC ?

Le controller dans le contexte de Spring MVC se charge de la communication entre le modèle et la view.
Il fait le lien entre notre code et le framework qui va pouvoir renvoyer la page HTML.

### Comment Spring permet-il l'injection de propriétés depuis des fichiers de configuration ?

Spring utilise une classe springframework.beans.factory.annotation.Value qui va se  
charger d'aller récupérer les valeurs des propriétés afin de se trouvant dans les  
fichiers de configuration. Cette classe nous permet ensuite d'utiliser le décorateur  
@Value et d'injecter la valeur voulu dans la variable de notre choix.

### : Pourquoi serait-il utile d'avoir différents profils dans une application Spring ?

Les profils Spring permettent d’adapter la configuration de l’application selon l’environnement  
(développement, test, production). Par exemple, on peut utiliser une base de données en mémoire  
en mode dev et une base réelle en mode prod. Cela évite de modifier le code et rend l’application  
plus flexible et sécurisée.

### **Questions : Question : Comment le modèle MVC aide-t-il à structurer une application web ?**

Le modèle MVC (Model View Controller) permet de séparer la logique métier et la gestion des données (model) de l'interface graphique (vue).
Le controller se charge de faire communiquer ce que l'utilisateur voit avec la logique de  
l'application, selon les actions de l'utilisateur, le controller va permettre d'aller  
demander au model les informations

### \*Question : Quelle est la différence entre Thymeleaf et les templates HTML classiques ?\*\*

Thymeleaf permet de créer des balises dynamiques, c'est à dire de remplacer leur contenu en  
fonction du code. Alors qu'une page HTML standard ne peut qu'afficher ce qui a été écrit en l'état.

---

## ### Job 3

**Question : Comment pouvez-vous passer des données d'un contrôleur à unevue dans Spring ?**  
Pour passer des données du controller vers la vue, il suffit d'utiliser les attributs th: dans la vue,  
déterminer le nom de la variable sous ce format ${nomDeVariable}.  
Une fois dans le controller, on utilise la méthode model.addAttribute("nomDeLaVariable",  
laValeurDeLaVariable); où laValeurDeLaVariable est la valeur trouvée dans le controller via le Model  
(en général).

### ### Job 5

**Question : Comment Spring permet-il la validation des données du formulaire ?**

Spring valide les données grâce à @Valid @ModelAttribute, il va utiliser un objet  
(classe que l'on crée) pour vérifier que les données respectent certaines conditions  
(comme @NotBlank pour les string et @NotNull pour les nombres par exemple), ces  
annotations permettent de vérifier les valeurs avant de les appliquer à l'objet, si les  
conditions ne sont pas respectée, alors .hasError sera déclenché par le binding des  
données du formulaire et de l'objet, ce qui permet de facilement gérer les erreurs  
d'entrées utilisateurs sans réécrire la logique toujours utilisées pour les inputs.  
Erreur ou non, il est simple avec .hasError de renvoyer la vue nécessaire.

## ** Qu'est-ce que JPA et comment facilite-t-il l'accès aux bases de données ?**

Spring Data JPA fournit une implémentation de la couche d'accès aux données pour une  
application Spring. C'est une brique très pratique car elle permet de ne pas réinventer  
la roue de l'accès aux données à chaque nouvelle application et donc de se concentrer sur  
la partie métier.

# ### Job 2

**Question : Pourquoi les bases de données en mémoire, comme H2, sont-elles utiles pendant le développement ?**

Cela permet d'avoir d'émuler la phase de production de l'application sans avoir besoin de  
modifier la véritable base de données. L'avoir en mémoire permet une plus grande rapidité  
d'exécution pour les tests

## Quelle est l'importance des annotations, telles que @Entity, dans le contexte de JPA ?\*\*

Les annotations comme @Entity permet de faire le lien entre le code Java et JPA (Java Persistence API)  
qui va communiquer avec la base de données.  
Cela va permettre de facilement mapper entre l'objet et la table et vice versa.  
Les autres annotations permet de paramètrer les différents éléments qui doivent pouvoir être en lien  
avec la base de données.  
@Id permet d'identifier la clé primaire d'une table dans un objet, @GeneratedValue permet de spécifier  
que la valeur doit être générée automatiquement et pas manuellement.  
Cela permet de faire du SQL sans faire de requête directement et de se concentrer sur la logique métier  
de l'application

### **Comment Spring Data facilite-t-il la création de requêtes de base de données?**

Spring Data JPA réduit le code standard, promeut les meilleures pratiques et améliore  
la productivité des développeurs en offrant un moyen pratique d'interagir avec les  
bases de données relationnelles tout en conservant la flexibilité nécessaire pour  
personnaliser les requêtes et les comportements lorsque cela est nécessaire.

## Job 05 **Comment pouvez-vous créer et lire des entités avec Spring Data JPA ?**

Pour créer et lire des entités avec Spring Data JPA, on définit une interface qui étend  
JpaRepository. Cela nous donne déjà accès aux méthodes CRUD comme save() pour insérer ou
mettre à jour, et findAll() / findById() pour lire. On peut aussi déclarer des méthodes  
personnalisées, par exemple User findByEmail(String email), que Spring implémente automatiquement.
Exemple rapide :
java  
public interface UserRepository extends JpaRepository<User, Long> {  
User findByEmail(String email);  
}  
Comment la méthode save de Spring Data JPA peut-elle être utilisée à la fois pour la création et la mise à  
jour ?

## Job 06 **Comment la méthode save de Spring Data JPA peut-elle être utilisée à la fois pour la création et la mise à jour ?**

La méthode save() de Spring Data JPA fait à la fois création (INSERT) et mise à jour (UPDATE) grâce au  
comportement de JPA/Hibernate qui se base sur la clé primaire (@Id) de l’entité :

**Si l’entité n’a pas encore d’ID (ou que l’ID n’existe pas en base):**

    save() effectue un INSERT → création d’un nouvel enregistrement.

**Si l’entité a déjà un ID correspondant à une ligne existante en base:**

<<<<<<< HEAD
save() effectue un UPDATE → la ligne est modifiée avec les nouvelles valeurs.
=======
**Comment stocker en toute sécurité les mots de passe des utilisateurs avec Spring Security?**

Avec Spring Security, on stocke les mots de passe hachés et salés grâce à un PasswordEncoder comme BCryptPasswordEncoder, ou Argon2PasswordEncoder (plus récent, résistant aux attaques GPU/ASIC).  
Il ne faut pas stocker les mots-de-passes en clair; et mieux vaut eviter les cryptages MD5, SHA-1, ou SHA-256 seuls.  
On encode au moment de l’inscription et on utilise matches() pour vérifier lors de la connexion, via:  
passwordEncoder.matches(rawPassword, hashedPassword).

> > > > > > > 7c9e61c43d65a1d92d9c325ebf967d7a72242a19

---

## Jour 3

## **Pourquoi est-il conseillé de séparer la logique métier des contrôleurs ?**

Séparer la logique métier des contrôleurs permet d’avoir un code plus clair, testable, réutilisable et conforme aux bonnes pratiques d’architecture.  
Chaque couche a sa responsabilité : le contrôleur gère les requêtes, le service applique les règles métier.

---

# JOUR 5

cheat-sheet THymeleat/ Spring Boot

1. Intégration rapide

   - placer ses vues dans `src/main/resources/templates/ `
   - Avec `spring-boot-starter-thymeleaf `, `return "view";` rend `templates/view.html`.

2. Syntaxes-clés
   . Expressions :  
   . ${...} : variables du Models (model.addAtribute("person", p) ->${person.name})
   . \*{...} : variables liées à l'objet du formulaire courant (th:objet)
   . #{...}: messages i18n (messages.properties)
   .@{...}: URLs ( @{/login}, @/user/{id}(id=${u.id}))

3. Attributs Thymeleaf fréquents  
   . `th:text="${msg}` : texte échappé  
   . `th:utext="${html}` : texte non échappé (à éviter si possible)  
   . `th: if="${cond}"`, `th:unless="${cond}"` : conditions  
   . `th:each="item : ${list}"` : boucles  
   . `th:href="@{/path}", th:src="@{/img/logo.png}"` : liens/ressources  
   . `th:classappend="${active} ? 'is-active'"` : classes dynamiques  
   . `th:replace="fragments/nav :: navbar" / th:insert` : inclusion de fragments

4. Formulaires (binding Spring MVC)

```html
<form th:action="@{/register}" th:object="${form}" method="post">
  <input th:field="*{username}" />
  <input th:field="*{password}" type="password" />
  <button type="submit">OK</button>
</form>
```

. `th:object="${form}"` → l’objet ajouté dans le modèle par le contrôleur  
`. th:field="*{username}"` génère `name="username"`, gère la valeur et les erreurs

**Affichage des erreurs de validation**

```html
<div th:if="${#fields.hasErrors('username')}" th:errors="*{username}">
  Erreur username
</div>
```

5. Framents & mise en page  
   . Déclare un fragment :

```html
<!-- templates/fragments/layout.html -->
<header th:fragment="siteHeader">
  <h1>Mon App</h1>
</header>
```

. Reutilise-le

```html
<div th:replace="fragments/layout :: siteHeader"></div>
```

6. Exemples :

### Liste

```html
<ul>
  <li th:each="p : ${persons}" th:text="${p.name}">Nom</li>
</ul>
```

### Condition

```html
<p th:if="${persons.size() == 0}">Aucune personne</p>
```

### URL avec paramètres

```html
<a th:href="@{/view/{id}(id=${p.id})}">Voir</a>
```

7. Controleur coté serveur :

```java
@GetMapping("/register")
public String show(Model model){
  model.addAttribute("form", new RegistrationForm());
  return "register";
}

@PostMapping("/register")
public String submit(@Valid @ModelAttribute("form") RegistrationForm form,
                     BindingResult errors){
  if (errors.hasErrors()) return "register";
  // ... save
  return "redirect:/login?registered";
}

```

---

**Comment liez-vous une liste d'objets à une vue Thymeleaf ?**

On lie une liste d’objets en l’ajoutant au modèle dans le contrôleur (`model.addAttribute("persons", liste);`), puis on l’affiche dans la vue avec Thymeleaf en utilisant `th:each` (`<tr th:each="p : ${persons}">`).

**Comment liez-vous un objet à un formulaire Thymeleaf ?**

On lie un objet à un formulaire Thymeleaf avec `th:object` pour lier l'objet et `th:field="{propriété}"` pour chaque champ du formulaire.

**Comment Thymeleaf gère-t-il les messages d'erreur de validation ?**  
Thymeleaf affiche les messages d'erreur de validation grâce à l'objet `BindingResult` lié au formulaire, avec les attributs ``th:errors` et `#fields.hasErrors(...)` pour montrer les messages définis par les annotations (`@NotNull`, `@Siz`, etc,)

**Comment pouvez-vous accéder à l'utilisateur actuellement connecté dans une vue Thymeleaf ?**

1. Ajouter dans la balise login du `html`, `xmlns:sec="https://www.thymeleaf.org/extras/spring-security"` puis
2. utiliser : `<span sec:authentication="name"></span>` ou `<span sec:authentication="principal.username"></span>`
3. et rajouter la balise `<div th:replace="~{fragments/header :: appHeader}"></div>` dans toutes les pages où vous voulez avoir la personnalisation.
