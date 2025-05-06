clear
clc

k=[4,6,20,200];
n=[4,6,20,200];
for i=1:4
    x=ones(n(i),1).*(10/9);
    x(1,1)=x(1,1)/2;
    X0=x;
    for j=1:4
        if n(i)>6
           xtel=norm(NRn(X0,k(j),n(i)));
        else xtel=NRn(X0,k(j),n(i));
        end
        xtel
    end
end

for i=1:4
    X0=randn(n(i),1)
    for j=1:4
        if n(i)>6
           xtel=norm(NRn(X0,k(j),n(i)));
        else xtel=NRn(X0,k(j),n(i));
        end
        xtel
    end
end

%for i=1:2
%    a=n(i)
%    x=ones(n(i),1).*(10/9);
%    x(1,1)=x(1,1)/2;
%    X0=x;
%    for j=1:4
%        b=k(j)
%        xtel=NRn(X0,k(j),n(i));
%        xtel
%    end
%end

%for i=3:4
%    a=n(i)
%    x=ones(n(i),1).*(10/9);
%    x(1,1)=x(1,1)/2;
%    X0=x;
%    for j=1:4
%         b=k(j)
%        xtel=norm(NRn(X0,k(j),n(i)));
%        xtel
%    end
%end