function [t,Y] = trapezSys(a,b,h,Y0,F,JF,KNR)
    N = (b-a)/h;
    t = linspace(a,b,N+1);
    Y = zeros(length(Y0),N+1);
    Y(:,1) = Y0;
    for i=1:N
    Y(:,i+1) = trapSysNR(t(i+1),Y(:,i),h,F,JF,KNR);
    end
end
    
function sol = trapSysNR(tnew ,Yold ,h,F,JF,KNR)
    x0 = Yold;
    told = tnew -h;
    G = @(x) x-x0-h/2*(F(told ,x0)+F(tnew ,x));
    
    for k=1:KNR
    JG = eye(length(Yold))-h/2*JF;
    sol = JG\(JG*Yold -G(Yold));
    Yold = sol;
    end
end
