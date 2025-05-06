clear; clc;
a=0;
b=2;
y0=1;
%f=@(t,y) log(y^2+1)-9*t*exp(-(9/2)*t^2)-log(exp(-9*t^2)+1);
f=@(t,y) log(y^2+2)-4*t*exp(-(4/2)*t^2)-log(exp(-4*t^2)+2);
%yexact=@(t)exp(-9*t.^2 /2);
yexact=@(t)exp(-4*t.^2 /2);

%Tableau Butcher
A=[0 0 0 0; 1/3 0 0 0; -1/3 1 0 0; 1 -1 1 0];
tau=[0; 1/3; 2/3; 1];
bhta=[1/8; 3/8; 3/8; 1/8];
h=0.01;
N=(b-a)/h
[Y, t]=RKE(a,b,N,y0,f,A,tau,bhta)
yex=yexact(t);
err=max(abs(Y(:)-yex(:)));
plot(t,Y,'*',t,yex);
title(["N=" num2str(N)]);
%Ns=[10, 20, 40, 80];
Ns=[10, 20, 40];
errs=zeros(length(Ns),1);
rates=zeros(length(Ns)-1,1);
figure;
for k=1:length(Ns)
    N=Ns(k);
    [Y, t]=RKE(a,b,N,y0,f,A,tau,bhta);
    yex=yexact(t);
    errs(k)=max(abs(Y(:)-yex(:)));
    subplot(2,2,k);
    plot(t,Y,'*',t,yex);
    title(["N=" num2str(N)]);
end
for i=1:length(Ns)-1
    rates(i)=log(errs(i)/errs(i+1))/log(Ns(i+1)/Ns(i));
end
errs
rates

