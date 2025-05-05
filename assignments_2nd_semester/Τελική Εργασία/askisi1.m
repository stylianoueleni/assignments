clear
clc

%a.erwtima
AA=[13*ones(5,1),-[-1:2:7]',10*ones(5,1)];
d=[-3,0,2];
A=full(spdiags(AA,d,5,5))
orizousa=det(A)
antistrofos=inv(A)
norma2=norm(A,2)
deiktiskatastasis2=cond(A,2)

%b.erwtima
syms l
charaktiristikopolionimo=charpoly(A,l)
[idiodianismata,idiotimes]=eig(A)

%c.erotima
b=[-2 -2 -2 -2 -2]';
x=(A^2+A)\b

%d.erotima
tic
C=[(-2)*ones(10^6,1)];
B=sparse(1:10^6-2,3:10^6,10*ones(1,10^6-2),10^6,10^6)+sparse(1:10^6,1:10^6,[1:-2:3-2*10^6],10^6,10^6)+sparse(4:10^6,1:10^6-3,13*ones(1,10^6-3),10^6,10^6);
y=B\C;
y(1:2)
y(10^6-1:10^6)
toc