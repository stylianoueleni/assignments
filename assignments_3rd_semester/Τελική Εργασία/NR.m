function [xtel]=NR(X0,k)
for i=1:k
    xtel=JF(X0)\(JF(X0)*X0-F(X0));
    X0=xtel;
end
xtel
end