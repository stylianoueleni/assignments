clear
clc

k=[4,6,20,200];
n=[4,6,20,200];
%for i=1:4
%    x=ones(n(i),1).*(10/9);
%    x(1,1)=x(1,1)/2;
%    X0=x;
%    xprev=X0;
%    for j=1:4
%        for w=1:k(j)
%            if n(i)>6
%               xtel=norm(mJFn(X0,n(i))\(mJFn(X0,n(i))*xprev-mFn(xprev,n(i))));
%            else xtel=mJFn(X0,n(i))\(mJFn(X0,n(i))*xprev-mFn(xprev,n(i)));
%            xprev=xtel;
%            end
%            xtel
%        end
%    end   
%end

for i=1:4
    a=n(i)
    X0=randn(n(i),1)
    xprev=X0;
    for j=1:4
        b=k(j)
        for w=1:k(j)
            if n(i)>6
               xtel=norm(mJFn(X0,n(i))\(mJFn(X0,n(i))*xprev-mFn(xprev,n(i))));
            else xtel=mJFn(X0,n(i))\(mJFn(X0,n(i))*xprev-mFn(xprev,n(i)));
            xprev=xtel;
            end
        end
        xtel
    end
end