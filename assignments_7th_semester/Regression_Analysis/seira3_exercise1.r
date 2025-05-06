# Εισαγωγή δεδομένων
data<-read.csv(file.choose(),header=TRUE,sep='')
data
attach(data)
l <- c(1, 0, 1, 0, 1, 0, 1, 0)

# Προσαρμογή του μοντέλου παλινδρόμησης Poisson
poisson_model <- glm(accidents ~ season + l, family = poisson(link = "log"), data = data)

# Αποτελέσματα του μοντέλου
summary(poisson_model)


fit_null<-glm(accidents~1, family=poisson)
anova(fit_null, poisson_model, test="Chisq")
r.squared<-(fit_null$deviance-poisson_model$deviance)/fit_null$deviance
r.squared


r.pears<-residuals(poisson_model,type='pearson')
r.dev<-residuals(poisson_model)
sum(r.pears^2)
r.pears.null<-residuals(fit_null,type='pearson')
sum(r.pears.null^2)