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
  
###   **Questions : Question : Comment le modèle MVC aide-t-il à structurer une application web ?**  
Le modèle MVC (Model View Controller) permet de séparer la logique métier et la gestion des données (model) de l'interface graphique (vue).
Le controller se charge de faire communiquer ce que l'utilisateur voit avec la logique de  
l'application, selon les actions de l'utilisateur, le controller va permettre d'aller   
demander au model les informations   
  
### *Question : Quelle est la différence entre Thymeleaf et les templates HTML classiques ?**  
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
  
## Quelle est l'importance des annotations, telles que @Entity, dans le contexte de JPA ?**  
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
JpaRepository.  Cela nous donne déjà accès aux méthodes CRUD comme save() pour insérer ou 
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
  
    save() effectue un UPDATE → la ligne est modifiée avec les nouvelles valeurs.  
  
---  
   

  


  



