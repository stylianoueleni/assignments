function [y,t] = RKI(a,b,y0, N, A, bhta, tau ,f, Nfp)
     t = linspace(a,b,N+1);
     h = (b-a)/N;
     y = zeros(N+1,1);
     y(1) = y0;
     q = length(tau);
     for n=1:N
         tn = t(n) + h*tau;
         kn = zeros(q,1);
         g = @(x) f(tn,y(n)+s(x,A,h));
         kn = FPS(kn,g,Nfp);
         y(n+1) = y(n) + h*bhta*kn;
     end
end
function y = s(x, A, h)
    n = length(x);
    y = zeros(n, 1);

    for i = 1:n
        y(i) = h * A(i, :) * x;
    end
end