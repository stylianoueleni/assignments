clear; clc;
a=0;
b=2;
y0=1;
f=@(t,y) log(y.^2+1)-9*t.*exp(-(9/2)*t.^2)-log(exp(-9*t.^2)+1);
yexact=@(t)exp(-9*t.^2 /2);

A=[5/12 -1/12; 3/4 1/4];
bhta=[3/4 1/4];
tau=[1/3; 1];

Ns=[10, 20, 40, 80];
Nfp=[2 7];
errs=zeros(length(Ns),1);
rates=zeros(length(Ns)-1,1);

for j=1:length(Nfp)
    figure;
    for k=1:length(Ns)
        N=Ns(k);
        [Y, t]= RKI(a,b,y0, N, A, bhta, tau ,f, Nfp(j));
        yex=yexact(t);
        errs(k,j)=max(abs(Y(:)-yex(:)));
        subplot(2,2,k);
        plot(t,Y,'*',t,yex);
        title(["N=" num2str(N) ",Nfp=" num2str(Nfp(j))]);
    end
    for i=1:length(Ns)-1
        rates(i,j)=log(errs(i,j)/errs(i+1,j))/log(Ns(i+1)/Ns(i));
    end
end
errs
rates
