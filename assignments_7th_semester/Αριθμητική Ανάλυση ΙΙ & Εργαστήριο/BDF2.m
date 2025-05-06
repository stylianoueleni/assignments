function y=BDF2(a,b,y0,N,f,Df,maxits)
    h=(b-a)/N; t=linspace(a,b,N+1);
    y=zeros(1,N+1); y(1)=y0;
    y(2)=y(1)+h*f(t(1),y(1));
    for n=1:N-1
     Y0=y(n);Y1=y(n+1);Y2=y(n+2);
     for k=1:maxits
         g=Y2-4*Y1/3+Y0/3-2*h*f(t(n+2),Y2)/3;
         Dg=1-2*h*Df(t(n+2),Y2)/3;
         Y2=Y2-g/Dg;
     end
     y(n+2)=Y2;
    end
end