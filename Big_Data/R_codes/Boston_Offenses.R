##library(ggplot2)
##library(plyr)
theme_update(plot.title = element_text(hjust = 0.5)) #Center title for ggplot

#Import file
data = read.csv("Boston_Offenses.csv", sep=";")

#Create a dataframe
df = data.frame(data)

#################################### Plot the number of offenses ######################################
## Plot the number of occurence of each offenses to have an idea of the most important type of
## offenses that happened in Boston.
#######################################################################################################
nbOffenses = count(df, "OFFENSE_CODE_GROUP") #count the occurence for each offense.
ggplot(nbOffenses, aes(x=OFFENSE_CODE_GROUP, y=freq, fill=OFFENSE_CODE_GROUP)) + geom_col(show.legend=FALSE) + xlab("Offenses") + ylab("Number of occurence") + ggtitle("Number of offenses") + theme(axis.text.x = element_text(angle = 90, hjust = 1))

######################## Plot the number of offenses by month and by year #############################
## Here we want to see if there is a tendency for the offenses during certain month of the year.
#######################################################################################################
nbOffensesByMonthYear = count(df, c("MONTH", "YEAR")) #count the number of offense that during a month of a year.
ggplot(nbOffensesByMonthYear, aes(x=MONTH, fill=YEAR)) +  geom_col(aes(y=freq), position = "dodge", show.legend=FALSE) + scale_x_continuous(breaks = c(1:12)) + facet_grid(YEAR~.) + xlab("Month") + ylab("Number of occurence") + ggtitle("Number of offenses by month and by year")


################################# Number of offenses by district ######################################
## We want to see if there is some district where the number of offenses is bigger.
## We study the number of offenses by district and by year to see if the districts
## with the bigge number of offenses are always the same in the time.
#######################################################################################################
nbOffensesByDistrictYear = count(df, c("DISTRICT", "YEAR")) #count the total number of offenses by district by year.
totalOffenseByYear = count(df,c("YEAR")) #compute the total of offenses by year
nbOffensesByDistrictYear$Percentage = nbOffensesByDistrictYear$freq/totalOffenseByYear$freq[match(nbOffensesByDistrictYear$YEAR, totalOffenseByYear$YEAR)] #Add a column to 
ggplot(nbOffensesByDistrictYear, aes(x=DISTRICT, fill=DISTRICT)) +  geom_col(aes(y=Percentage), position = "dodge", show.legend=FALSE) + facet_grid(YEAR~.) + xlab("District") + ylab("Number of occurence") + ggtitle("Number of offenses by district and by year") #NA = Unknown district


################################# Dangerous offenses by district ######################################
## Finally we want to see if the district is really dangerous.
## First we want to plot for each district the repartition of the 10 most frequent offenses.
## Then we select the two offenses 'Drug Violation' and 'Vandalism' to show a correlation between
## the number of offenses by district and these two particular offenses.
#######################################################################################################
nbOffensesByDistrict = count(df, c("DISTRICT","OFFENSE_CODE_GROUP")) #count the total number of offenses by district.
TopOffenses= ddply(nbOffensesByDistrict,c("DISTRICT"),transform,rang=rank(-freq)) # give the rank of the number offenses by district
Top5 = TopOffenses[TopOffenses$rang<=10,] # keep the 10 most frequent offenses
Top5=na.omit(Top5) # remove all the N/A lines
ggplot(Top5,aes(x=DISTRICT,y=freq,fill=OFFENSE_CODE_GROUP))+facet_wrap(Top5$OFFENSE_CODE_GROUP)+geom_col(position = "dodge", show.legend=TRUE) + xlab("District") + ylab("Number of occurence") + ggtitle("Offenses by district") #NA = Unknown district

dfModified = df[df$OFFENSE_CODE_GROUP=="Drug Violation" | df$OFFENSE_CODE_GROUP=="Vandalism",] # keep only this two offenses
nbOffensesByDistrict = count(dfModified, c("DISTRICT","OFFENSE_CODE_GROUP")) #count the total number of offenses by district.
# plot the number of drug and vandalism offenses by district
ggplot(nbOffensesByDistrict, aes(x=DISTRICT, y=freq, fill=DISTRICT))+facet_grid(OFFENSE_CODE_GROUP~.)+  geom_col(position = "dodge", show.legend=FALSE) + xlab("District") + ylab("Number of occurence") + ggtitle("Number of offenses by district") #NA = Unknown district

