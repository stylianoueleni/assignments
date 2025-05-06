function [xtel]=mNRn(X0,k,n)
for i=1:k
    xtel=mJFn(X0,n)\(mJFn(X0,n)*X0-mFn(X0,n));
    X0=xtel;
end
end