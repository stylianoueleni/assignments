data<-na.omit(read.table("http://www.math.ntua.gr/~fouskakis/Data_Analysis/Exercises/pharmacy.txt", header=T, na.strings="$"))
nrow(data)

summary(data$age)
hist(data$age, main='Histogram of variable Age',xlab='Age (years)',ylim=c(0,15))
boxplot(data$age,main='Boxplot of variable Age')
fivenum(data$age)

table(data$category)
prop.table(table(data$category))
barplot(table(data$category),main='Bar Chart of variable Category',xlab='Category',ylab='Frequency',ylim=c(0,35))
pie(table(data$category),main='Pie Chart of variable Category')

table(data$sex)
prop.table(table(data$sex))
barplot(table(data$sex),main='Bar Chart of variable Sex', xlab='Sex',ylab='Frequency',ylim=c(0,40))
pie(table(data$sex),main='Pie Chart of variable Sex')

summary(data$med)
hist(data$med, main='Histogram of variable Med', xlab='Med(€)')
boxplot(data$med,main='Boxplot of variable Med')
fivenum(data$med)

summary(data$population)
hist(data$population, main='Histogram of variable Population',xlab='population',ylim=c(0,25))
boxplot(data$population,main='Boxplot of variable Population')
fivenum(data$population)

summary(data$money)
hist(data$money, main='Histogram of variable Money',xlab='Money (€)', ylim=c(0,20))
boxplot(data$money,main='Boxplot of variable Money')
fivenum(data$money)


plot(data$money,type='n',main='Category-Money diagram',ylab='money (euros)')
text(data$money, label=data$category)

plot(data$age,data$money,xlab='Age (years)',ylab='money (euros)',main='Age-Money diagram')

plot(data$money,type='n',main='Sex-Money diagram',ylab='money (euros)')
text(data$money,label=data$sex)

plot(data$population,data$money,xlab='Population',ylab='money (euros)',main='Population-Money diagram')

plot(data$med,data$money,xlab='Med (euros)',ylab='money (euros)',main='Med-Money diagram')


f_age<-rep('0',44)
f_age[data$age>=18&data$age<30]<- '[18-30)'
f_age[data$age>=30&data$age<50]<- '[30-50)'
f_age[data$age>=50]<- '>=50'
table(f_age)
prop.table(table(f_age))

f_pop<-rep('0',44) 
q1<-quantile(data$population,0.25,na.rm=T) 
q2<-quantile(data$population,0.5,na.rm=T) 
q3<-quantile(data$population,0.75,na.rm=T) 
f_pop[data$population>=0&data$population<q1]<-'[0,q1) ' 
f_pop[data$population>=q1&data$population<q2]<-'[q1,q2)' 
f_pop[data$population>=q2&data$population<q3]<-'[q2,q3)' 
f_pop[data$population>q3]<- '>=q3 '
f_pop[f_pop=='0']<-NA
table(f_pop)
prop.table(table(f_pop))

table(f_pop,f_age)
prop.table(table(f_pop,f_age))

barplot(table(f_pop,f_age),xlab='Age',legend=levels(factor(f_pop)),main='Stacked barplot f_pop - f_age')