function [y,t]=BDF2ex3(a,b,y0,N,f,Df,maxits)
    h=(b-a)/N; t=linspace(a,b,N+1);
    y=zeros(1,N+1); y(1)=y0;
    t(1)=a;
    t(2)=t(1)+h;
    x0=y(1);
    for i=1:maxits
        xnew = x0 - (x0-y(1) -h/2*(f(t(1) ,y(1))+f(t(2) ,x0)))/(1-h/2*Df(t(2) ,x0));
        x0=xnew;
    end
    y(2)=xnew;
    
    %for j=1:2
    %    t(j+1)=t(j)+h;
    %     x0=y(j);
    %    for i=1:maxits
    %        xnew = x0 - (x0-y(j) -h/2*(f(t(j) ,y(j))+f(t(j+1) ,x0)))/(1-h/2*Df(t(j+1) ,x0));
    %        x0=xnew;
    %    end
    %    y(j+1)=xnew;
    %end

    for n=1:N-1
     Y0=y(n);Y1=y(n+1);Y2=y(n+2);
     for k=1:maxits
         g=Y2-Y0-h/3*(f(t(n+2),Y2)+4*f(t(n+1),Y1)+f(t(n),Y0));
         Dg=1-h/3*Df(t(n+2),Y2);
         Y2=Y2-g/Dg;
     end
     y(n+2)=Y2;
    end
end