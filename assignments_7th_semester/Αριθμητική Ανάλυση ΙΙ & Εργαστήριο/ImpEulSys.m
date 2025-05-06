function y= ImpEulSys(a, b, y0, N, f, Jf, maxits)
    h=(b-a)/N;
    t=linspace(a, b, N+1);
    d=length(y0);
    y=zeros(d, N+1);
    y(:, 1)=y0;
    
    for n=1:N
        yold=y(:, n);
        ynew=y(:, n);
        
        for k= 1:maxits
            G = ynew-yold-h*f(t(n+1),ynew);
            JG = eye(d)-h*Jf(t(n+1), ynew); 
            ynew=ynew-inv(JG)*G; 
        end 
    
        y(:, n+1) = ynew; 
    end 
end 