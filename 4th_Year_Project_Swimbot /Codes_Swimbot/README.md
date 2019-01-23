# ProjetTutSwimbot

Vous trouverez dans ce dossier les codes que nous avons développé durant le projet tuteuré portant sur la mesure de la fréquence cardiaque sur un bonnet de natation
et transmission sans fil aquatique.

- LectureFichierTXT :
	Ce dossier correspond au projet complet développé sous l’IDE Android Studio (en langage Java Android donc) concernant la connexion série entre l’Arduino et la Newton.
	Ce projet gère la prise en charge des données de fréquence cardiaque reçue par la Newton en provenance de l’Arduino. Il réalise ainsi le calcul de la fréquence cardiaque moyenne, basé sur toutes les fréquences cardiaques mesurées par le PulseSensor stockée dans le fichier texte « HeartRateData.txt ».
	L’application « CavaSwimmer» gère ensuite l’affichage de cette moyenne au moyen de trois boutons :
		* FC MOYENNE : ouvre une nouvelle fenêtre qui affiche la moyenne de la fréquence cardiaque mesurée.
		* NOUVEAU TEST : renvoie sur la première fenêtre, et redémarre une nouvelle acquisition des données du capteur de fréquence cardiaque.
		* EXIT : quitte l’application et renvoie sur l’écran principal du	système Android.

- CavaSwimmer_Bluetooth :
	Ce dossier correspond au projet complet développé sous l’IDE Android Studio (en langage Java Android) concernant la connexion Bluetooth entre l’Arduino et la Newton (ou un Smartphone).
	Cette application permet l’établissement de la connexion entre la module BLE de l’Arduino Uno (module HC-05 dans notre projet) et l’appareil sur lequel est exécutée l’application. 
	Une fois la connexion établie, l’utilisateur peut afficher sa fréquence cardiaque en temps réel ainsi que sa moyenne sur l’application « CavaSwimmer»

- PulseSensor_FC_Arduino :
	Ce dossier contient les codes Arduino assurant le filtrage du signal analogique reçue par l’Arduino, sa conversion en numérique dans une variable nommée « BPM » ainsi que les lignes de code permettant l’exécution du script « scriptFiltre.sh ».

- Code Graphique MIT App Inventor :
	Cette image représente le code graphique permettant une connexion en Bluetooth et l’implémentation d’une application Android pour Smartphone.

