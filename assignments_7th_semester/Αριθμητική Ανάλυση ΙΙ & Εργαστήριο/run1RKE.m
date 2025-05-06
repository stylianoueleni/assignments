a=0;
b=1;
y0=1;
f=@(t,y)y;
yexact=@(t)exp(t);

%Tableau Butcher
A=[0 0 0; (1/2) 0 0; 0 (3/4) 0];
tau=[0; (1/2); (3/4)];
bhta=[(2/9); (1/3); (4/9)];
N1=100;
[Y1, t1]=RKE(a,b,N1,y0,f,A,tau,bhta);
yex1=yexact(t1);

plot(t1,Y1,'*',t1,yex1);
err1=max(abs(Y1(:)-yex1(:)))

N2=200;
[Y2, t2]=RKE(a,b,N2,y0,f,A,tau,bhta);
yex2=yexact(t2);
err2=max(abs(Y2(:)-yex2(:)))

p=log(err1/err2)/log(N2/N1)