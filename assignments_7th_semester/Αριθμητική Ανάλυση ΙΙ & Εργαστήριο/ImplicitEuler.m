function y=ImplicitEuler(a,b,N,y0,f,Df,maxits)
    h=(b-a)/N; t=linspace(a,b,N+1);
    y=zeros(1,N+1); y(1)=y0;
    for n=1:N
    yold=y(n);ynew=y(n);
        for k=1:maxits
            g=ynew-yold-h*f(t(n+1),ynew);
            Dg=1.0-h*Df(t(n+1),ynew);
            ynew=ynew-g/Dg;
        end
    y(n+1)=ynew;
    end
end
