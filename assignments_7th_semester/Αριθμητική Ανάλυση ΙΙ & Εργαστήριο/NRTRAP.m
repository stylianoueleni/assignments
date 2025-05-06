function ynew = NRTRAP(tprev ,tnew ,yprev ,f,fy,KNR)

h = tnew -tprev;
x0 = yprev;

for k=1:KNR
xnew = x0 - (x0-yprev -h/2*(f(tprev ,yprev)+f(tnew ,x0)))/(1-h/2*fy(tnew ,x0));
x0 = xnew;
endfor

ynew = xnew;
end;
