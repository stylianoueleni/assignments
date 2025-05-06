clear; clc;
a=0;
b=2;
%f=@(t,y) log(y^2+1)-9*t*exp(-(9/2)*t^2)-log(exp(-9*t^2)+1);
f=@(t,y) log(y^2+2)-4*t*exp(-(4/2)*t^2)-log(exp(-4*t^2)+2);
%yexact=@(t)exp(-9*t.^2 /2);
yexact=@(t)exp(-4*t.^2 /2);

%Df=@(t,y)(2*y)/(y^2+1);
Df=@(t,y)(2*y)/(y^2+2);

figure;
y0=1;maxits=3;
h=0.01;
N=(b-a)/h;
[yBDF2,t]=BDF2ex3(a,b,y0,N,f,Df,maxits)
yex=yexact(t);
plot(t,yBDF2,'*',t,yex);
title(["N=" num2str(N)]);


%Ns=[20, 40, 80, 160];
Ns=[10, 20, 40];
figure;
errsBDF2=zeros(length(Ns),1); ratesBDF2=zeros(length(Ns)-1,1);
for i=1:length(Ns)
    N=Ns(i);
    [yBDF2,t]=BDF2ex3(a,b,y0,N,f,Df,maxits);
    yex=yexact(t);
    errsBDF2(i)=max(abs(yex(:)-yBDF2(:)));
    subplot(2,2,i);
    plot(t,yBDF2,'*',t,yex);
    title(["N=" num2str(N)]);
end
for i=1:length(Ns)-1
    ratesBDF2(i)=log(errsBDF2(i)/errsBDF2(i+1))/log(Ns(i+1)/Ns(i));
end
errsBDF2
ratesBDF2