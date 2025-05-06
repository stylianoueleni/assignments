clear
clc

k=[4,6,20,200];
x1=1;
x2=2;
X0=[x1;x2];
xprev=X0;
for j=1:4
    for i=1:k(j)
        xtel=JF(X0)\(JF(X0)*xprev-F(xprev));
        xprev=xtel;
    end
    xtel
end