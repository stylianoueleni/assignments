# Εισαγωγή δεδομένων
data<-read.csv(file.choose(),header=TRUE,sep='')
data
attach(data)

model <- glm(cbind(y, n - y) ~ E + x, family = binomial)

summary(model)

fit_null<-glm(cbind(y, n - y) ~1, family=binomial)
anova(fit_null, model, test="Chisq")

1-pchisq(model$deviance, model$df.residual)

pstar<-y/n
plot(log(pstar/(1-pstar))~x, ylab="logit(p*)",pch=c(1,16)[E])

plot(log(pstar/(1-pstar))~log(x), ylab="logit(p*)",pch=c(1,16)[E])

model2 <- glm(cbind(y, n - y) ~ E + log(x), family = binomial)
summary(model2)
1-pchisq(model2$deviance, model2$df.residual)

cooks<-cooks.distance(model)
cooks
plot(cooks)
abline(h=4/length(cooks),col="red",lty=2)




# Έλεγχος καταλληλότητας του μοντέλου (χρησιμοποιούμε residuals και διάγνωση)
library(car)
influenceIndexPlot(model) # Εντοπισμός σημείων επιρροής

# Χρήση λογαρίθμου της ποσότητας των εντομοκτόνων
model_log <- glm(cbind(y, n - y) ~ E + log(x), family = binomial(link = "logit"))

# Σύνοψη αποτελεσμάτων για το νέο μοντέλο
summary(model_log)

# Σύγκριση μοντέλων
anova(model, model_log, test = "Chisq")
