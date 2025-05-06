clear
clc

k=[4,6,20,200];
x1=1;
x2=2;
X0=[x1;x2];
for j=1:4
    a=k(j)
    xtel=NR(X0,k(j));
end

x1=2;
x2=4;
X0=[x1;x2];
for j=1:4
    a=k(j)
    xtel=NR(X0,k(j));
end

X0=rand(2,1);
for j=1:4
    a=k(j)
    xtel=NR(X0,k(j));
end