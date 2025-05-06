function y=TrapeziouFP(a,b,y0,N,f,maxits)
    h=(b-a)/N; t=linspace(a,b,N+1);
    y=zeros(1,N+1);y(1)=y0;
    for n=1:N
        yold=y(n); ynew=y(n);
        for k=1:maxits
            ynew=yold+0.5*h*(f(t(n),yold)+f(t(n+1),ynew));
        end
        y(n+1)=ynew;
    end
end
