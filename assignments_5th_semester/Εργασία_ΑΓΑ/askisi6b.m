clear
clc
format long
AL=diag(-1*ones(1,99),-1);
AR=diag(-1*ones(1,99),1);
D=diag(12*ones(1,100));
A=D+AL+AR;
x0=0.25*ones(100,1);
x0(1)=0.5;
x0(100)=0.5;
for i=1:100
    y1=((A-4*diag(ones(1,100)))^(-1))*x0;
    x1=y1/norm(y1,2);
    Ay=((A-4*diag(ones(1,100)))^(-1))*y1;
    Ri=y1'*Ay*10^3
    x0=x1;
end
