clear;clc;
a=0;
b = 1;
h = [0.1 0.001];
k = [-2 -1 0 1 2];
A = exp(1);
B = 1;

for m=1:length(h)
    Ns(m)=(b-a)/h(m);
end

uexact = @(x) exp(1-x.^2);
errs=zeros(length(Ns),length(k));
rates=zeros(length(Ns)-1, length(k));

figure;

for m=1:length(Ns)
    for j=1:length(k)
        r = @(x) 10.^(k(j));
        f = @(x) exp((-x.^2)+1).*(-(4*x.^2-2)+r(x));
        disp(["Deiktis katastasis gia h=" h(m), "k=" num2str(k(j))]);
        U= FDM_Dirichlet(a, b, A, B, r, f, Ns(m));
        x=linspace(a, b, Ns(m) + 1);
        errs(m,j)=max(abs(uexact(x)-U));
        subplot(length(h), length(k), (m - 1) * length(k) + j)
        plot(x,U,'*',x,uexact(x));
        title(sprintf('h = %.3f, k = %d', h(m), k(j)));
    end
end

for j=1:length(k)
    for i=1:length(Ns)-1
        rates(i,j)=log(errs(i,j)/errs(i+1,j))/log(Ns(i+1)/Ns(i));
    end
end

errs
rates