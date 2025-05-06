function y= ExpEulSys(a, b, y0, N, f)
h=(b-a)/N;
t=linspace(a, b, N+1);
d=length(y0);
y=zeros(d, N+1);
y(:, 1)=y0;

for n=1:N
    y(:, n+1)=y(:, n)+h*f(t(n),y(:, n));
end
end