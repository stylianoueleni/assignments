clear
clc
AL=diag(-1*ones(1,99),-1);
AR=diag(-1*ones(1,99),1);
D=diag(12*ones(1,100));
A=D+AL+AR;
x0=zeros(100,1);
b=10*ones(100,1);
b(1)=11;b(100)=11;
for i=1:8
    x1=(AL+D)\(-AR*x0+b);
    x0=x1;
end
r=b-A*x1;
normar=norm(r,2);
normab=norm(b,2);
deiktis=cond(A,2);
sfalma=(deiktis*normar)/normab