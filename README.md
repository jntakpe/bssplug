Application lisant des fichiers json et les renvoyant sous forme de endpoints REST
=====================
Le fichier *application.yml* permet de configurer l'application à l'aide des paramètres suivants :
    
* server.port : Le port sur lequel le serveur tomcat embedded doit démarrer
* server.context-path : Le contexte root du serveur (par exemple : http://localhost:8080/MON_CONTEXTE_ROOT)
* env.rootDir : Le répertoire parynt contenant les sous-répertoires et leurs fichiers json

Pour démarrer l'application :

* via les sources : Dézipper les sources, depuis l'invite de commandes : mvn spring-boot:run
* via le jar : Depuis l'invite de commandes : java -jar MON_FICHIER.jar.
 Les properties sont externalisables en tappant : java -jar MON_FICHIER.jar --spring.config.location=PATH_TO/application.yml
 
 Pour s'authentifier : http://localhost:8080/MON_CONTEXTE_ROOT/auth. Un seul mot de passe est accepté 'password' et une seule date de naissance '21/04/1973'
 
 Pour simuler un appel BSS : http://localhost:8080/MON_CONTEXTE_ROOT/MON_SOUS_DOSSIER/MON_NOM_DE_FICHIER_JSON
