function y=AdamsBashforth(a,b,y0,N,f)
    h=(b-a)/N; t=linspace(a,b,N+1);
    y=zeros(1,N+1); y(1)=y0;
    bhta=[2/9;1/3;4/9]; tau=[0;0.5;0.75];
    A=[0,0,0;0.5,0,0;0,0.75,0];
    [y(1:4),t14]=RKE(a,t(4),3,y0,f,A,tau,bhta);
    for n=1:N-3
        Y0=y(n);Y1=y(n+1);Y2=y(n+2);Y3=y(n+3);
        f0=f(t(n),Y0);f1=f(t(n+1),Y1); f2=f(t(n+2),Y2);f3=f(t(n+3),Y3);
        y(n+4)=Y3+(h/24)*(55*f3-59*f2+37*f1-9*f0);
    end
end