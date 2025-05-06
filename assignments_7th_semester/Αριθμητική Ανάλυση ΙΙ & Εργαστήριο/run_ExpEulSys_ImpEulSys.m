a=0.0;
b=4.0*pi;
y0=[0; 1];
maxits=1;
A=[0, 1; -1, 0];

yexact =@(t)[sin(t); cos(t)];
f=@(t,y)A*y;
Jf=@(t,y)A;

Ns=[100, 200, 400, 800, 1600, 3200];

errsE= zeros(length(Ns), 1);
ratesE=zeros(length(Ns)- 1,1);

errsI= zeros(length(Ns), 1);
ratesI=zeros(length(Ns)- 1,1);

for i=1:length(Ns)
    yE=ExpEulSys(a,b,y0,Ns(i), f);
    yI=ImpEulSys(a,b,y0,Ns(i), f,Jf,maxits);
    t=linspace(a, b, Ns(i)+1);
    errsE(i)=max(max(abs(yexact(t)-yE)));
    errsI(i)=max(max(abs(yexact(t)-yI)));
end 

for i=1:length(Ns)-1
    ratesE(i)=log(errsE(i)/errsE(i+1))/log(Ns(i+1)/Ns(i));
    ratesI(i)=log(errsI(i)/errsI(i+1))/log(Ns(i+1)/Ns(i));
end

errsE
ratesE
errsI
ratesI

yt=yexact(t);
y1=yt(1, :);
y2=yt(2, :);
yE1=yE(1, :);
yE2=yE(2, :);
yI1=yI(1, :);
yI2=yI(2, :);

figure(1)
plot(t,y1, 'r', t, yE1, 'k--')
legend('Y1', 'explicit Euler')

figure(2)
plot(t, y1, 'r', t, yI1, 'k--')
legend('Y1', 'implicit Euler')

figure(3)
plot(yE1, yE2)
legend('trajectories explicit Euler')

figure(4)
plot(yI1, yI2)
legend('trajectories implicit Euler')