clear;clc;
a=0.0; b=1.0; y0=1.0;
yexact=@(t)exp(t).*(log(t+1)+1);
sinex=@(t)sin(exp(t).*(log(t+1)+1));
f=@(t,y)sin(y)+exp(t)*(log(t+1)+(t+2)/(t+1))-sinex(t);
Df=@(t,y)cos(y);maxits=2;
Ns=[100, 200, 400, 800, 1600, 3200, 6400, 12800];
errs=zeros(length(Ns),1); rates=zeros(length(Ns)-1,1);
for i=1:length(Ns)
    %y=TrapeziouNR(a,b,y0,Ns(i),f,Df,maxits);
    y=TrapeziouFP(a,b,y0,Ns(i),f,maxits);
    t=linspace(a,b,Ns(i)+1);
    errs(i)=max(abs(yexact(t)-y));
end
for i=1:length(Ns)-1
    rates(i)=log(errs(i)/errs(i+1)/log(Ns(i+1)/Ns(i)));
end
errs
rates

