clear
clc
AL=diag(-1*ones(1,99),-1);
AR=diag(-1*ones(1,99),1);
D=diag(12*ones(1,100));
A=D+AL+AR;
x0=zeros(100,1);
b=10*ones(100,1);
b(1)=11;b(100)=11;
r0=b-A*x0;
for i=1:12
    Ar0=A*r0;
    ak=(r0'*r0)/(r0'*Ar0);
    x1=x0+ak*r0;
    r1=r0-ak*Ar0;
    x0=x1;
    r0=r1;
end
x1
normar=norm(r1,2);
normab=norm(b,2);
deiktis=cond(A,2);
sfalma=(deiktis*normar)/normab