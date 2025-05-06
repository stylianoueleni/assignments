function [f] = mFn(X,n)
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
end