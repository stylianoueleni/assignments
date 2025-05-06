data<-c(3,2,1,1,1,2,5,1,3,2,4)
p<-seq(0.01,0.99,0.001)
L<-function(data,p){
m=length(p)
f<-rep(NA,m)
for(i in 1:m){
f[i]=prod(dgeom(data,p[i],log=F))}
return(f)}
f<-L(data-1,p)
plot(p,f,type='l',xlab='probability',ylab='Likelihood')
p[order(f)[length(f)]]

simulate_dice_rolls <- function(N) {
  if (N <= 0 || !is.numeric(N) || N != as.integer(N)) {
    stop("Το N πρέπει να είναι θετικός ακέραιος")
  }
  
  count_successes <- 0
  
  for (i in 1:N) {
    rolls <- sample(1:6, 4, replace = TRUE)
    if (length(unique(rolls)) == 4) {
      count_successes <- count_successes + 1
    }
  }
  
  return(count_successes / N)
}
theoretical_probability <- (6/6) * (5/6) * (4/6) * (3/6)
simulation_result <- simulate_dice_rolls(10000)
print(paste("Προσομοιωμένη πιθανότητα:", simulation_result))
print(paste("Θεωρητική πιθανότητα:", theoretical_probability))

