clear
clc
AL=[0 0 0;10^-5 0 0; 0 10^-5 0];
AR=[0 2 0; 0 0 1; 0 0 0];
D=diag(5*ones(1,3));
A=D+AL+AR;
x0=[-0.5 0.2 -0.4]';
b=[7 6.00001 5.00001]';
for i=1:10
    x1=(D+AL)\(-AR*x0+b);
    dx(i)=norm(x1-x0,1);
    x0=x1;
end
format long
dx'