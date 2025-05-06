function sol = NRBDF3(t2,h,y0,y1,y2,f,fy,KNR)
x0 = y2;
for k=1:KNR
xnew = x0 - (11*x0 -18*y2+9*y1 -2*y0 -6*h*f(t2,x0))/(11-6*h*fy(t2,x0));
x0 = xnew;
endfor

sol = xnew;
end;
