clear
clc

%a.erotima
b=ako(100)

%b.erotima
b10000000=ako2(10000000)

%c.erotima
f=@(x)((x+1)./(ako(x).*(x.^3+2).*x))-(1./x);
x=1:50;
yf=f(x);
plot(x,yf)

%d.erotima
sum100=sum(ako(100))
sum1000=sum(ako(1000))
sum10000=sum(ako(10000))