x<-c(1.8,3.0,4.8,5.0,6.5,7.0,9.0,9.1)
y<-c(20.0,30.5,40.0,55.1,60.3,74.9,88.4,95.2)

plot(x,y,main="Scatter Plot",xlab="Imcome(x)",ylab="Energy Consuption(y)")
model<-lm(y~x)
abline(model,col="blue")

summary(model)$r.squared

qqnorm(residuals(model))
qqline(residuals(model),col="red")
hist(model$res)
shapiro.test(residuals(model))
ks.test(model$res,"pnorm",mean(model$res),sd(model$res))

plot(density(resid(model)), main = "Density Plot of Residuals with Normal Curve")
x_vals <- seq(min(resid(model)), max(resid(model)), length = 100)
y_vals <- dnorm(x_vals, mean = mean(resid(model)), sd = sd(resid(model)))
lines(x_vals, y_vals, col = "blue")

plot(x,y)
abline(model)

plot( rstandard(model),fitted(model), main="Standarsized Residuals", ylab="Fitted Values", xlab="Standarizes Residuals")
abline(v=0,col="red")
plot(1:8,model$res)

confint(model, level=0.95)

predict(model,list(x=c(8)),int="p")
predict(model,list(x=c(8)),int="c")
