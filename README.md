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
  
###   
  
  



