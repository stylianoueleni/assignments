clear
clc
format long
AL=diag(-1*ones(1,99),-1);
AR=diag(-1*ones(1,99),1);
D=diag(12*ones(1,100));
A=D+AL+AR;
b=10*ones(100,1);
b(1)=11;b(100)=11;
for j=1:5
    omega=1+1/(2^j);
    x0=0.25*ones(100,1);
    x0(1)=0.5;
    x0(100)=0.5;
    for i=1:5
        x1=(omega*AL+D)\((D-omega*(D+AR))*x0+omega*b);
        x0=x1;
    end
    omega
    r=b-A*x1;
    norm(r,2)
end
