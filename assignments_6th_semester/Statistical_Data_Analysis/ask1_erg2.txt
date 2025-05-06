data<-na.omit(read.table("http://www.math.ntua.gr/~fouskakis/Data_Analysis/Exercises/pharmacy.txt", header=T, na.strings="$"))
nrow(data)
data<-subset(data,age>=18)
nrow(data)
attach(data)
shapiro.test(money)
x<-data$money
par(mfrow=c(1,2))
hist(x)
qqnorm(x)
qqline(x)
t.test(x,alternative="less",mu=45,conf.level=0.95)

{wilcox.test(x,mu=45,alternative="less")}

Cosmetics_or_not<-rep(0,69)
Cosmetics_or_not[category=="cosmetics"]<-1
Cosmetics_or_not<-factor(Cosmetics_or_not)
money_cosmetics<-money[Cosmetics_or_not==1]
money_not<-money[Cosmetics_or_not==0]
summary(money_cosmetics)
summary(money_not)
var(money_cosmetics)
var(money_not)
par(mfrow=c(1,2))
hist(money_cosmetics)
hist(money_not)
boxplot(money_cosmetics,money_not,names=c('Cosmetics','Other Categories'),main='Boxplot of variable Money')
fivenum(money_cosmetics)
fivenum(money_not)

shapiro.test(money_cosmetics)
shapiro.test(money_not)
var.test(money_cosmetics,money_not,conf.level=0.9)
t.test(money_cosmetics,money_not,alternative="greater",conf.level=0.9,var.equal=T)

money_Man<-money[sex=='Man']
money_Woman<-money[sex=='Woman']
summary(money_Man)
summary(money_Woman)
var(money_Man)
var(money_Woman)
par(mfrow=c(1,2))
hist(money_Man)
hist(money_Woman)
boxplot(money_Man,money_Woman,names=c('Man','Woman'),main='Boxplot of variable Money')
fivenum(money_Man)
fivenum(money_Woman)
shapiro.test(money_Man)
shapiro.test(money_Woman)
var.test(money_Man,money_Woman,conf.level=0.99)
t.test(money_Man,money_Woman,conf.level=0.99,var.equal=T)

data$med_f<-ifelse(data$med<70,0,1)
barplot(table(data$med_f,data$sex),beside=TRUE,col=c("blue","red"),legend=TRUE)
med_f_man<-data$med_f[sex== 'Man']
med_f_woman<-data$med_f[sex=='Woman']
x<-c(23,24)
n<-c(36,33)
prop.test(x,n,alternative='greater')

chisq.test(data$category,data$sex)
