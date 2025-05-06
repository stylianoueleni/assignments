x<-c(2,4,6,12,18,24)
y<-c(1.07,1.88,2.26,2.78,2.97,2.99)
logty<-log(3-y)
model<-lm(logty~x)
summary(model)

a<-exp(coef(model)[1])
b<-coef(model)[2]

plot(x,y,main="Scatter Plot", xlab="x", ylab="y")
curve(3-a*exp(b*x),add=TRUE,col="blue")
#lines(x, fitted(nls(y~3-a*exp(b*x),start=list(a=1, b=-0.1))),col="red")

plot(x, logty, main="Linear Transformation of Data", xlab="x", ylab="log(y')")
abline(model,col="blue")

qqnorm(residuals(model))
qqline(residuals(model),col="red")

plot(residuals(model),fitted(model), main="Residuals vs Predicted Prices", ylab="Predicted Prices",xlab="Residuals")
abline(v=0,col="red")

plot(x,logty)
abline(model)

plot(1:6,model$res)

confint(model)
              

plogyp<-predict(model, list(x=c(9)),int="p")
plogyp
3-exp(plogyp)
plogyc<-predict(model, list(x=c(9)),int="c")
plogyc
3-exp(plogyc)

model2<-lm(y~x)
qqnorm(residuals(model2))
qqline(residuals(model2),col="red")
shapiro.test(residuals(model2))
ks.test(model2$res,"pnorm",mean(model2$res),sd(model2$res))

plot(residuals(model2),fitted(model2), main="Residuals vs Predicted Prices", ylab="Predicted Prices",xlab="Residuals")
abline(v=0,col="red")

plot(x,y)
abline(model2)

plot(1:6,model2$res)


