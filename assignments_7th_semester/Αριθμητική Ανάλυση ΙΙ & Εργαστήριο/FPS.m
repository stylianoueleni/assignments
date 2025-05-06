function x = FPS(x0, g, Nfp)
    n = length(x0);
    x = x0;

    for i = 1:Nfp
        xold=x;
        for j = 1:n
            gx = g(xold); 
            x(j) = gx(j);
        end
    end
end


