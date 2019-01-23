library(plyr)
library(ggplot2)
theme_update(plot.title = element_text(hjust = 0.5)) #Center title for ggplot

#Import file
data = read.csv("Rio_Olympic_Games.csv", sep=";")

#Create a dataframe
df = data.frame(data); df

############################# Comparison between men and women by country #############################
## The idea here is to compare the gender parity for each country.
#######################################################################################################
##First we compare the number of male & female athletes by country.
nbMenWomen = count(df,c("nationality", "sex")) #Count the number of men & women by country
ggplot(nbMenWomen, aes(x=nationality, y=freq, fill=sex)) + geom_col(position = "dodge") + ylab("Number of athlete") + ggtitle("Number of athlete by sex and by country") + theme(axis.text.x = element_text(angle = 90, hjust = 1))

##Then, to have a better vision of the gender parity, we decide to plot the percentage of male & female athlete by country.
totalAthleteByCountry = count(nbMenWomen, "nationality") #Compute the number of athlete (male&female) by country
nbMenWomen$percentage = (nbMenWomen$freq/totalAthleteByCountry$freq[match(nbMenWomen$nationality, totalAthleteByCountry$nationality)]) #Add a column to nbMenWomen which contain the percentage of athlete by sex and by country
ggplot(nbMenWomen, aes(x=nationality, y=percentage, fill=sex)) + geom_col(position = "dodge") + ylab("Percentage of athlete") + scale_y_continuous(labels=scales::percent) + ggtitle("Percentage of athlete by sex by country")


########################### Percentage of medal won by athlete by country #############################
## Here, we are going to compare the percentage of medal won by athlete by country which
## means the number of medal won by country divided by the total number of athlete by country.
#######################################################################################################
totalMedalByCountry = ddply(df, "nationality", summarize, totalMedal=sum(gold, silver, bronze)) #use ddply to create a new data frame containing the total number of medals won by country
totalMedalByCountry$efficiency = totalMedalByCountry$totalMedal/totalAthleteByCountry$freq[match(totalAthleteByCountry$nationality, totalAthleteByCountry$nationality)]; totalMedalByCountry #divide the number of medal won by country by the total number of athlete by country
ggplot(totalMedalByCountry, aes(x=nationality, y=efficiency, fill=nationality)) + geom_col(show.legend=FALSE) + ylab("Percentage of medal won by athlete") + scale_y_continuous(labels=scales::percent) + ggtitle("Percentage of medal won by athlete by country")


###################### Percentage of medal won by athlete by sex and by country #######################
## In this part we include the sex of the athlete to the previous part analysis. So, this will show 
## if the female athletes have an impact on the result of their country. To do this, we divide the
## the number of medal by country and by sex by the total number of athlete by country and by sex.
#######################################################################################################
totalMedalBySexByCountry = ddply(df, c("nationality", "sex"), summarize, totalMedal=sum(gold, silver, bronze)) #use ddply to create a dataframe containing the total number of medal won by country by distinguishing the gender for each country.
totalMedalBySexByCountry$efficacity = totalMedalBySexByCountry$totalMedal/nbMenWomen$freq[match(totalMedalBySexByCountry$nationality, nbMenWomen$nationality)] #divide the number of medal by country and by sex by the total number of athlete by country and by sex.
ggplot(totalMedalBySexByCountry, aes(x=nationality, y=efficacity, fill=sex)) + geom_col() + ylab("Percentage of medal won by athlete") + scale_y_continuous(labels=scales::percent) + ggtitle("Percentage of medal won by athlete by sex and by country")

