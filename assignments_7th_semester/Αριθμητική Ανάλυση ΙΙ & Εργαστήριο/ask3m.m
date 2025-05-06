clear;clc;

a = 1;
f = @(t,y) y.^3 - (1+a)*t*exp(-(1+a)/2*t.^2)-exp(-(3+3*a)/2*t.^2)

fy = @(t,y) 3*y.^2;
t0 = 0;
tM = 2;
y0 = 1;
N = [20 40 80 160];
KNR = 3;
exact = @(t) exp(-(1+a)/2*t.^2);

for j = 1:3

[t1,y1] = BDF3(t0,tM,N(j),y0,f,fy,KNR);
y_exact1 = exact(t1);

err1 = max(abs(y1'-y_exact1));

[t2,y2] = BDF3(t0,tM,N(j+1),y0,f,fy,KNR);
y_exact2 = exact(t2);

err2 = max(abs(y2'-y_exact2));

if j==1
err(1) = err1;
subplot(2,2,1);
plot(t1,y1,'*',t1,y_exact1);
title(["N = " num2str(N(1))]);
endif
err(j+1) = err2;
subplot(2,2,j+1);
plot(t2,y2,'*',t2,y_exact2);
title(["N = " num2str(N(j+1))]);

p(j) = log(err1/err2)/log(2);
endfor;

err
p