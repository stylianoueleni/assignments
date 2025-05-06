data<-na.omit(read.table("http://www.math.ntua.gr/~fouskakis/Data_Analysis/Exercises/pharmacy.txt", header=T, na.strings="$"))
nrow(data)

cor.test(data$age,data$med)
cor.test(data$age,data$population)
cor.test(data$age,data$money)
cor.test(data$med,data$population)
cor.test(data$med,data$money)
cor.test(data$population,data$money)


attach(data)
results<-lm(money~med)
results
summary(results)
confint(results)
plot(med,money,xlab='Med(€)',ylab='Money(€)',main='Graph of the points(med,money)')
abline(results,col='red')
legend('topleft',col='red',lty=1,legend='Least Squares Regression Line')

x<-data$med
y<-data$money
plot(x,y,xlab='Med(€)',ylab='Money(€)')
qqnorm(results$res)
qqline(results$res)
par(mfrow=c(1,2))
plot(results$res,results$fitted,xlab='Residuals(€)',ylab='Fitted Values(€)')
plot(results$res,x,xlab='Residuals(€)',ylab='Med(€)')
plot(1:71,results$res)

results2<-lm(money~age+category+sex+med+population)
results2
confint(results2)
summary(results2)

Page<-residuals(results2,'partial')[,1]
Pmed<-residuals(results2,'partial')[,4]
Ppopulation<-residuals(results2,'partial')[,5]
par(mfrow=c(1,3))
plot(Page,age,xlab='Partial Residuals of age(€)',ylab='Age(years)',main="(1)")
plot(Pmed,med,xlab='Partial Residuals of med(€)',ylab='Med(€)',main="(2)")
plot(Ppopulation,population,xlab='Partial Residuals of population(€)',ylab='Population',,main="(3)")
qqnorm(results2$res)
qqline(results2$res)
plot(results2$res,results2$fitted,xlab='Resiuals(€)',ylab='Fitted Values(€)')
plot(1:71,results2$res)

predict(results2,int='c',list(category='cosmetics', age=20,sex='Man', med=60, population=1500))

agecentered<-age-mean(age)
medcentered<-med-mean(med)
populationcentered<-population-mean(population)

logMoney=log(money)
logAge=log(age)
logMed=log(med)
logPopulation=log(population)
results3<-lm(logMoney~logAge+category+sex+logMed+logPopulation)

Plage<-residuals(results3,'partial')[,1]
Plmed<-residuals(results3,'partial')[,4]
Plpopulation<-residuals(results3,'partial')[,5]
par(mfrow=c(1,3))
plot(Plage,logAge,xlab='Partial Residuals of logAge(€)',ylab='logAge(years)',main="(1)")
plot(Plmed,logMed,xlab='Partial Residuals of logMed(€)',ylab='logMed(€)',main="(2)")
plot(Plpopulation,logPopulation,xlab='Partial Residuals of logPopulation(€)',ylab='logPopulation',,main="(3)")
qqnorm(results3$res)
qqline(results3$res)
plot(results3$res,results3$fitted,xlab='Resiuals(€)',ylab='Fitted Values(€)')
plot(1:71,results3$res)

predict(results3,int='c',list(category='cosmetics', logAge=log(20),sex='Man', logMed=log(60), logPopulation=log(1500)))

AIC(results2)
AIC(results3)+2*sum(log(money))

