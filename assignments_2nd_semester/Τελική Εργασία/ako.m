function b=ako(n)
b(1)=1/13;
for i=1:(n-1);
    b(i+1)=((i+1)/(i^3+2)-b(i))/i;
end
end