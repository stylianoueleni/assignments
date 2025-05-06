clear;clc;
a=0.0; b=10.0; f=@(t,y)-50*y;Df=@(t,y)-50;
yexact=@(t)exp(-50*t); y0=1.0; maxits=2; N=100;
yExpl=ExplicitEuler(a,b,N,y0,f);
yImpl=ImplicitEuler(a,b,N,y0,f,Df,maxits);
t=linspace(a,b,N+1);
figure(1)
plot(t,yexact(t),'r',t, yExpl,'k--')
ylim([-0.1,1.1])
xlim([-0.1,10.1])
legend('exact','explicit Euler')

figure(2)
plot(t,yexact(t),'r',t,yImpl,'k--')
ylim([-0.1,1.1])
xlim([-0.1,10.1])
legend('exact','implicit Euler')