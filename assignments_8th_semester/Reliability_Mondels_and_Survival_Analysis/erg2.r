library(survival)
# Δεδομένα επιβίωσης (με δεξιά αποκοπή)
times <- c(468, 725, 838, 853, 965, 1139, 1142, 1304, 1317, 1427, 
           1554, 1658, 1764, 1776, 1990, 2010, 2224, 2244, 2279, 2286)
censored <- c(rep(0, 17), rep(1, 3))  # 1 = δεξιά αποκοπή, 0 = πλήρη δεδομένα

# Δημιουργία αντικειμένου Surv για Kaplan-Meier
surv_obj <- Surv(times, event = 1 - censored)

# Εκτίμηση Kaplan-Meier
km_fit <- survfit(surv_obj ~ 1)

# Σχεδίαση της καμπύλης Kaplan-Meier
plot(km_fit, main=expression(paste("Kaplan-Meier-estimate ", hat(S)(t))), 
conf.int=FALSE,xlab="t", ylab="Survival")

km_fit$surv 

SKM<-km_fit$surv [km_fit$n.event ==1] 
SKM 
Utime<-km_fit$time[km_fit$n.event ==1] 
Utime

 plot(log(Utime),log(-log(SKM)), main=expression(paste("Weibull Plot")), xlab="ln 
t", ylab="ln-(ln S(t))", pch=19) 
abline(lm(log(-log(SKM))~log(Utime))) 


norm_quant<-qnorm(1-SKM,0,1) 
norm_quant 
plot(log(Utime), norm_quant, pch=19) 
abline(lm(norm_quant ~ log(Utime)))


plot(log(Utime),log((1-SKM)/SKM), pch=19) 
abline(lm(log((1-SKM)/SKM) ~ log(Utime))) 


# Weibull model (default in survreg is weibull with accelerated failure time)
fit_weibull <- survreg(surv_obj ~ 1, dist = "weibull")

# Log-Normal model
fit_lognorm <- survreg(surv_obj ~ 1, dist = "lognormal")

# Log-Logistic model
fit_loglogis <- survreg(surv_obj ~ 1, dist = "loglogistic")

# Σημεία χρόνου
ti <- km_fit$time[km_fit$n.event == 1]  # μοναδικοί χρόνοι αποτυχίας
SKM <- km_fit$surv[km_fit$n.event == 1]  # εκτιμήσεις Kaplan-Meier σε αυτούς

# Weibull
mu_w <- fit_weibull$coefficients
sigma_w <- fit_weibull$scale
S_weibull <- 1 - pweibull(ti, shape = 1/sigma_w, scale = exp(mu_w))

# Log-Normal
mu_ln <- fit_lognorm$coefficients
sigma_ln <- fit_lognorm$scale
S_lognorm <- 1 - plnorm(ti, meanlog = mu_ln, sdlog = sigma_ln)

# Log-Logistic
mu_ll <- fit_loglogis$coefficients
sigma_ll <- fit_loglogis$scale
S_loglogis <- 1 / (1 + (ti / exp(mu_ll))^(1 / sigma_ll))

plot(ti, SKM, pch=19, xlab="t", ylab="S(t)", main="Kaplan-Meier vs ML Models")

# Προσθήκη των εκτιμήσεων επιβίωσης από τα μοντέλα
lines(ti, S_weibull, col="blue", lwd=2, type="l")
lines(ti, S_lognorm, col="darkgreen", lwd=2, type="l")
lines(ti, S_loglogis, col="red", lwd=2, type="l")

# Υπόμνημα
legend("topright", legend=c("Kaplan-Meier", "Weibull", "Log-Normal", "Log-Logistic"),
       col=c("black", "blue", "darkgreen", "red"), lty=c(NA,1,1,1), pch=c(19, NA, NA, NA))

library(goftest)
library(fitdistrplus)
# Επιλέγουμε τα πλήρη δεδομένα
times_full <- times[censored == 0]
# Weibull
ad.test(times_full, null = function(x) pweibull(x, shape = 1/sigma_w, scale = exp(mu_w)))

# Log-Normal
ad.test(times_full, null = function(x) plnorm(x, meanlog = mu_ln, sdlog = sigma_ln))

# Log-Logistic
ad.test(times_full, null = function(x) {
  1 / (1 + (x / exp(mu_ll))^(1 / sigma_ll))
})

AIC(fit_weibull)
AIC(fit_lognorm)
AIC(fit_loglogis)

fit_weibull <- survreg(surv_obj ~ 1, dist = "weibull")
eta_hat <- 1 / fit_weibull$scale
eta_hat

se_scale <- summary(fit_weibull)$table["Log(scale)", "Std. Error"]

# Αν θέλεις, μπορείς να πάρεις το SE του scale μέσω της κανονικής μετατροπής log
scale_log <- log(fit_weibull$scale)
CI_log <- scale_log + c(-1, 1) * qnorm(0.975) * se_scale
CI_scale <- exp(CI_log)  # Δ.Ε. για scale
CI_eta <- 1 / rev(CI_scale)  # αντιστροφή και ανάποδα (γιατί το rev αλλάζει τη σειρά)
CI_eta


# Προσαρμογή εκθετικής (Weibull με scale = 1)
fit_exp <- survreg(surv_obj ~ 1, dist = "exponential")

# LRT = -2 log(Λ)
lrt_stat <- 2 * (logLik(fit_weibull) - logLik(fit_exp))
lrt_stat

# p-value
pchisq(lrt_stat, df = 1, lower.tail = FALSE)












library(survival)
data<- read.table(file.choose(), header=TRUE)
attach(data)
data
outp<-survfit(Surv(t,c)~Group, type="kaplan-meier",data=data) 
summary(outp) 
plot(outp, lty = 1:2, main=expression(paste("Kaplan-Meier-estimate ", hat(S)(t))), 
 xlab="t", ylab="Survival", lwd=2) 
legend("topright", c("Group 0", "Group 1"), lty = 1:2) 

 
out1<-survdiff(Surv(t, c) ~ Group) 
out1
out2<-survdiff(Surv(t, c) ~ Group, rho=1) 
out2

group0<-Surv(t[Group=="0"],c[Group=="0"]) 
outp0<-survfit(group0~1, type="kaplan-meier",data=data) 
summary(outp0) 

Utime0<-outp0$time[outp0$n.event==1] 
SKM0<-outp0$surv[outp0$n.event==1]  

group1<-Surv(t[Group=="1"],c[Group=="1"]) 
outp1<-survfit(group1~1, type="kaplan-meier",data=data) 
summary(outp1) 

Utime1<-outp1$time[outp1$n.event==1] 
SKM1<-outp1$surv[outp1$n.event==1]  

plot(log(Utime0),log(-log(SKM0)) , main=expression(paste("Weibull Plot-Group0")), 
xlab="ln t", ylab="ln-(ln S(t))", pch=19) 
abline(lm(log(-log(SKM0))~log(Utime0))) 

plot(log(Utime1),log(-log(SKM1)) , main=expression(paste("Weibull Plot-Group1")), 
xlab="ln t", ylab="ln-(ln S(t))", pch=19) 
abline(lm(log(-log(SKM1))~log(Utime1)))

loglogistic
plot(log(Utime0),log((1-SKM0)/SKM0), main=expression(paste("Log-Logistic Plot-Group0")), xlab="ln t", ylab=expression(ln (F(t)/S(t))), pch=19) 
abline(lm(log((1-SKM0)/SKM0)~log(Utime0)))

plot(log(Utime1),log((1-SKM1)/SKM1), main=expression(paste("Log-Logistic Plot-Group1")), xlab="ln t", ylab=expression(ln (F(t)/S(t))), pch=19) 
abline(lm(log((1-SKM1)/SKM1)~log(Utime1)))

AIC(
AIC(

# Φόρτωση βιβλιοθήκης
library(flexsurv)

fit_weibull_g0 <- flexsurvreg(Surv(t, c) ~ 1, data = data[Group == "0", ], dist = "weibull")
fit_weibull_g1 <- flexsurvreg(Surv(t, c) ~ 1, data = data[Group == "1", ], dist = "weibull")


# Εκτίμηση log-logistic για την ομάδα 0
fit_loglogis_g0 <- flexsurvreg(Surv(t, c) ~ 1, data = data[Group == "0", ], dist = "llogis")

# Εκτίμηση log-logistic για την ομάδα 1
fit_loglogis_g1 <- flexsurvreg(Surv(t, c) ~ 1, data = data[Group == "1", ], dist = "llogis")

AIC(fit_weibull_g0)
AIC(fit_weibull_g1)
AIC(fit_loglogis_g0)
AIC(fit_loglogis_g1)

# Προβολή αποτελεσμάτων
summary(fit_loglogis_g0)
summary(fit_loglogis_g1)

fit_loglogis_g0
fit_loglogis_g1

median0<-fit_loglogis_g0$res["scale","est"]


