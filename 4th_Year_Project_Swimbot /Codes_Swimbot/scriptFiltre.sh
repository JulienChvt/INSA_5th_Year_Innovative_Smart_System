# Script "scriptFiltre.sh"
#
# Fonction : Recupere les donnees du Pulse-Sensor envoyees par l'Arduino,
#            verifie que ces donnes correspondent a une frequence cardiaque
#            et sauvegarde cette valeur dans le fichier texte "HeartRateData.txt"
#            sur la carte Newton a l'adresse /data
#
# Ce script est lance par la carte Arduino au moment de l'initialisation des communications
# (setup())

!/bin/bash

echo "------- Begin --------"

#On supprime l'ancien fichier contenant les valeurs de frequence cardiaque
#de l'ancienne sequence de mesure

rm /sdcard/HeartRateData.txt

while true;
do	
	#Recuperation des donnees envoyees par la carte Arduino
	
	read -r data

	#On verifie si les données recues correspondent a une frequence cardiaque
	#ce qui est repere par le charactere 'B' devant la valeur de la donnees
	#Si tel est la cas, on enleve le charactere 'B' devant la valeur de la
	#frequence cardiaque
	#et on la sauvegarde dans le fichier HeartRateData.txt

	if [ ${data:0:1} == "B" ] && [ $data != "lue" ]
	then
		echo ${data:1:3} >> /sdcard/HeartRateData.txt
	fi


done
echo "--------- End ----------"

exit 0;
