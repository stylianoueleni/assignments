clear; clc;
a=0.0;
b=2.0;
y0=[1;1];


B=[999, 2024; -1002, -2025];

A=[0 0 0 0; 1/3 0 0 0; -1/3 1 0 0; 1 -1 1 0];
tau=[0; 1/3; 2/3; 1];
bhta=[1/8; 3/8; 3/8; 1/8];

f=@(t,y)B*y;

KNR=3;
h=[0.1, 0.01, 0.001];

for j=1:3

    %(A)
    N=(b-a)/h(j);
    t = linspace(a, b, N+1)
    yRK= RKsys(a, b, y0, N, A, bhta, tau, f)

    figure(1);
    subplot(2,2,j)
    plot( t, yRK);
    title(["h = " num2str(h(j))]);
    legend('y1','y2');

    %(Γ)
    [t,y] = trapezSys(a,b,h(j),y0,f,B,KNR);
    figure(2)
    subplot(2,2,j)
    plot(t,y);
    title(["h = " num2str(h(j))]);
    legend('y1','y2');
end

%(B)
h=0.0001;
[t,y]=trapezSys(a,b,h,y0,f,B,KNR);
figure(3)
plot(t,y);
legend('y1','y2');
