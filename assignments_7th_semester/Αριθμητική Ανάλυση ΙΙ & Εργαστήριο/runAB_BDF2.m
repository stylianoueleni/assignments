clear;clc;
a=1.0;b=2.0;
f=@(t,y)(t^2+y^2)/(2*t*y);
Df=@(t,y)(y^2-t^2)/(2*t*y^2);
yexact=@(t)sqrt(t.*(t+3));
y0=2;maxits=5;
Ns=[25, 50, 100, 200, 400, 800, 1600, 3200];
errsAB=zeros(length(Ns),1); ratesAB=zeros(length(Ns)-1,1);
errsBDF2=zeros(length(Ns),1); ratesBDF2=zeros(length(Ns)-1,1);
for i=1:length(Ns)
    yAB=AdamsBashforth(a,b,y0,Ns(i),f);
    yBDF2=BDF2(a,b,y0,Ns(i),f,Df,maxits);
    t=linspace(a,b,Ns(i)+1);
    errsAB(i)=max(abs(yexact(t)-yAB));
    errsBDF2(i)=max(abs(yexact(t)-yBDF2));
end
for i=1:length(Ns)-1
    den=log(Ns(i+1)/Ns(i));
    ratesAB(i)=log(errsAB(i)/errsAB(i+1))/den;
    ratesBDF2(i)=log(errsBDF2(i)/errsBDF2(i+1))/den;
end
errsAB
ratesAB
errsBDF2
ratesBDF2