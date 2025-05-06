function [U]= FDM_Dirichlet(a, b, A, B, r, f, N)
    h=(b-a)/N;
    x = linspace(a, b, N+1);
    U=zeros(1, N+1);
    
    U(1)=A;
    U(N+1)=B;
    
    a1=(-1/h^2)*ones(N-1,1);
    %a1(N-1) = -2/h^2;
    a2=(2/h^2)*ones(N-1,1)+r(x(2:N))';
    a3=(-1/h^2)*ones(N-1,1);
    
    M=spdiags([a1, a2, a3], [-1, 0, 1], N-1, N-1);
    condest(M)
  
    F= f(x(2:N))'; 
    F(1) = f(x(2)) + A / h^2; 
    F(N-1) = f(x(N)) + B / h^2; 
    
    Uint=M\F;
    U(2:N)=Uint';
end