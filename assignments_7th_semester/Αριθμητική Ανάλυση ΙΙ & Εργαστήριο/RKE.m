function [sol,t]=RKE(a,b,N,y0,f,A,tau,bhta)
    t=linspace(a,b,N+1);
    h=(b-a)/N;
    sol=zeros(N+1,1);
    sol(1)=y0;
    q=length(tau);
    for n=1:N
        tn=zeros(q,1);
        kn=zeros(q,1);
        for i=1:q
            tn(i)=t(n)+tau(i)*h;
            s1=0;
            for j=1:q 
                s1=s1+h*A(i,j)*kn(j);
            end
            kn(i)=f(tn(i),sol(n)+s1);
        end
        s2=0;
        for i=1:q 
            s2=s2+h*bhta(i)*kn(i);
        end
        sol(n+1)=sol(n)+s2;
    end
end
