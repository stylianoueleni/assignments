function [xtel]=NRn(X0,k,n)
for i=1:k
    xtel=JFn(X0,n)\(JFn(X0,n)*X0-Fn(X0,n));
    X0=xtel;
end
end