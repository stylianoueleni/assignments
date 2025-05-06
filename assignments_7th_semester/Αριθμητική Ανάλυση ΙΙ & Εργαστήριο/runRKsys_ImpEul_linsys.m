clear;clc;
a=0.0;
b=1.0;
y0=[1; 4];

yexact=@(t)[exp(-7*t);4*exp(-7*t)];
B=[-8003, 1999; 23988, -6004];

A = [0, 0, 0;
     1/2, 0, 0;
     0, 0.75, 0];

f=@(t,y)B*y;

tau=[0; 1/2; 0.75];
bhta=[2/9; 1/3; 4/9];

N=100;

t = linspace(a, b, N+1);

yE= ExpEulSys(a, b, y0, N, f)
yI= ImpEul_linsys(a, b, y0, B, N)
yRK= RKsys(a, b, y0, N, A, bhta, tau, f)


yt = yexact(t);
y1 = yt(1, :);
yE1 = yE(1, :);
yI1 = yI(1, :);
yRK1 = yRK(1, :);

figure(1)
plot(t, y1, 'r', t, yE1, 'k--');
legend('exact', 'explicit Euler');

figure(2)
plot(t, y1, 'r', t, yI1, 'k--');
legend('exact', 'implicit Euler');


figure(3)
plot(t, y1, 'r', t, yRK1, 'k--');
legend('exact', 'RK Ralston');
