function y= RKsys(a, b, y0, N, A, bhta, tau, f)
    h=(b-a)/N;
    d=length(y0);
    y=zeros(d, N+1);
    y(:, 1)=y0;
    
    q=length(bhta);
    kn=zeros(d,q);
    
    t = linspace(a, b, N+1);
    
    for n = 1:N
        for i = 1:q        
            tni = t(n) + tau(i) * h;
            s = zeros(d, 1);
            for j = 1:i-1
                s = s + A(i, j) * kn(:, j);
            end 
            kn(:, i) = f(tni, y(:, n) + h * s);
        end
        y(:, n+1) = y(:,n)+h*kn*bhta;
    end
end