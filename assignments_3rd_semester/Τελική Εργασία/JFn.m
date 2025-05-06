function [J] = JFn(X,n)
a=10/9*ones(n,1);
sum=0;
for i=1:n
    sum=sum+(X(i,1)-a(i,1))^4;
end
for i=1:n
    f(i,1)=-(sum-cos(sum)+n)^(-2)*4*abs(X(i)-a(i))^3*(1+sin(sum));
end
for i=1:n
    for j=1:n
        if i==j
            J(i,j)=32*(sum-cos(sum)+n)^(-3)*(X(i)-a(i))^6*(1+sin(sum))^2-4*(sum-cos(sum)+n)^(-2)*(X(i)-a(i))^2*(3*(1+sin(sum))+4*(X(i)-a(i))^4*cos(sum));
        else
            J(i,j)=16*(sum-cos(sum)+n)^(-3)*(X(i)-a(i))^3*(X(j)-a(j))^3*(2*(1+sin(sum))^2-cos(sum)*(sum-cos(sum)+n));
        end
    end
end
end