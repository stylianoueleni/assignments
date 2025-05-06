clear;clc;
a = 0;
b = 1;
h = [0.1 0.001];
k = [-2 -1 0 1 2];
A = 0;
B = 2;
i = 1;
j = 1;

exact = @(x) x.*(x.^3-2);

for m=1:length(h)
    Ns(m)=(b-a)/h(m);
end

for j=1:length(k)
    r = @(x) 10^(k(j));
    f = @(x) -12*x.^2+x.*(x.^3-2)*r(x);
    
    disp(["Deiktis katastasis pinaka M gia h=0.1, k=" num2str(k(j))]);
    [x1,y1] = FDM(a,b,h(1),A,B,r,f);
    y_exact1 = exact(x1);
    err1(j) = max(abs(y_exact1 -y1'));
    subplot(2,length(k),j);
    plot(x1,y1,'*',x1,y_exact1);
    title(["h = " num2str(h(1)) " k = " num2str(k(j))]);
    
    disp(["Deiktis katastasis pinaka M gia h=0.001, k=" num2str(k(j))]);
    [x2,y2] = FDM(a,b,h(2),A,B,r,f);
    y_exact2 = exact(x2);
    err2(j) = max(abs(y_exact2 -y2'));
    subplot(2,length(k),j+length(k));
    plot(x2,y2,'*',x2,y_exact2);
    title(["h = " num2str(h(2)) " k = " num2str(k(j))]);
    
    
    p(j) = log(err1(j)/err2(j))/log(h(1)/h(2));
end


err1
err2
p