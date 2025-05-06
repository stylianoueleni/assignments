clear;clc;
a=0.0; b=1.0; y0=1.0;
yexact=@(t)cos(2*pi.*t)
f=@(t,y)y.^2+(sin(2*pi.*t)-2*pi).*sin(2*pi*t)-1.0;
Ns=[100,200,400,800,1600,3200];
errs=zeros(length(Ns),1);
rates=zeros(length(Ns)-1,1);
for i=1:length(Ns)
    y=ExplicitEuler(a,b,Ns(i),y0,f);
    t=linspace(a,b,Ns(i)+1);
    errs(i)=max(abs(yexact(t)-y));
end
for i=1:length(Ns)-1
    rates(i)=log(errs(i)/errs(i+1))/log(Ns(i+1)/Ns(i));
end
errs
rates

figure(1)
plot(t,yexact(t),'r')
hold on
plot(t,y,'k--')
hold off