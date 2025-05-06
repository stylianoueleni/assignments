clear;clc;
a=0.0;
b=1.0;
A=1.0;
B=exp(-1);

r=@(x)4*x.^2;
f=@(x)2*exp(-x.^2);
uexact=@(x)exp(-x.^2);

Ns=[25, 50, 100, 200, 400, 800, 1600, 3200];
errs=zeros(length(Ns),1);
rates=zeros(length(Ns)-1, 1);

for i=1:length(Ns)
    U= FDM_Dirichlet(a, b, A, B, r, f, Ns(i));
    x=linspace(a, b, Ns(i) + 1);
    errs(i)=max(abs(uexact(x)-U));
end


for i=1:length(Ns)-1
    rates(i)=log(errs(i)/errs(i+1))/log(Ns(i+1)/Ns(i));
end

errs
rates

figure(1)
plot(x, uexact(x), 'r', x, U, 'k--');
legend('exact', 'FDM')