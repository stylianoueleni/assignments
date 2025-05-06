function [x,y] = FDM(a,b,h,A,B,r,f)
    N = (b-a)/h;
    x = linspace(a+h,b,N);
    y = zeros(N,1);
    
    F = zeros(N,1);
    F(2:N-1) = f(x(2:N-1));
    F(1) = f(x(1))+A/h^2;
    F(N) = f(x(N))+ 2*B/h;
    
    a1 = -1/h^2*ones(N,1);
    a1(N-1) = -2/h^2;
    a2 = 2/h^2*ones(N,1) + r(x)';
    a3 = -1/h^2*ones(N,1);
    
    M = spdiags([a1,a2,a3],[-1,0,1],N,N);
    cond(M)
    
    y = M\F;
    
    y = [A;y];
    x = [a x];

end