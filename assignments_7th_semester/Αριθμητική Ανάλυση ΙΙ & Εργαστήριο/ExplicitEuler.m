function y=ExplicitEuler(a,b,N,y0,f)
    h=(b-a)/N; t=linspace(a,b,N+1);
    y=zeros(1,N+1); y(1)=y0;
    for n=1:N
    y(n+1)=y(n)+h*f(t(n),y(n));
    end
end
