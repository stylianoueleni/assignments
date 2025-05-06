function [J] = mJFn(X,n)
a=10/9*ones(n,1);
sum=0;
for i=1:n
    sum=sum+(X(i,1)-a(i,1))^4;
end
A=sum-cos(sum);
d=1+sin(sum);
for i=1:n
    f(i,1)=-(A+n)^(-2)*4*abs(X(i)-a(i))^3*d;
end
for i=1:n
    D=X(i)-a(i);
    for j=1:n
        DD=X(j)-a(j);
        if i==j
            J(i,j)=32*(A+n)^(-3)*D^6*d^2-4*(A+n)^(-2)*D^2*(3*d+4*D^4*cos(sum));
        else
            J(i,j)=16*(A+n)^(-3)*D^3*(DD)^3*(2*d)^2-cos(sum)*(A+n);
        end
    end
end
end