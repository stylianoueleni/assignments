function [t,y] = BDF3(a,b,N,y0,f,fy,KNR)

t = linspace(a,b,N+1);
h = (b-a)/N;
y = zeros(N+1,1);

y(1) = y0;
t(1) = a;
%use trapezium method (using NR) to find y1,y2,y3
for i=1:2
t(i+1) = t(i)+h;
y(i+1) = NRTRAP(t(i),t(i+1),y(i),f,fy,KNR);
end

for i=1:N-2
y(i+3) = NRBDF3(t(i+3),h,y(i),y(i+1),y(i+2),f,fy,KNR);
end

end