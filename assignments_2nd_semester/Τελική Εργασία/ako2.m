function b=ako2(n)
b(1)=1/13;
for i=1:(n-1);
    b=((i+1)/(i^3+2)-b(1))/i;
    b(1)=b;
end
end