clear
clc
A=[4 2/3 -4/3 4/3;2/3 4 0 0;-4/3 0 6 2;4/3 0 2 6];
idiotimes=zeros(4,1);
for i=1:15
    i
    [Q, R]=qr(A);
    A=R*Q
    idiotimes=diag(A)
end
