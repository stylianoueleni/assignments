find_perfect_numbers <- function(numbers) {
  if (!all(numbers > 0) || !all(numbers==floor(numbers))) {
    stop("Το διάνυσμα πρέπει να περιέχει μόνο θετικούς ακέραιους αριθμούς.")
  }

  perfect_numbers <- c()
  for (num in numbers) {
    divisors <- 1:(num-1)
    sum_divisors <- sum(divisors[which(num %% divisors == 0)])
    if (sum_divisors == num && num!=1) {
      perfect_numbers <- c(perfect_numbers, num)
    }
  }
  if (length(perfect_numbers) == 0) {
    print("Δεν βρέθηκαν τέλειοι αριθμοί στο διάνυσμα.")
  } else {
    return(perfect_numbers)
  }
}

# Παράδειγμα χρήσης
numbers <- c(6, 12, 28, 496, 1)
find_perfect_numbers(numbers)

numbers <- c(6, 12, -28, 496, 1)
find_perfect_numbers(numbers)

numbers <- c(6, 12.5, 28, 496, 1)
find_perfect_numbers(numbers)



simulate_exponential <- function(n) {
  if (n!=floor(n) || n <= 0) {
    stop("Ο αριθμός n πρέπει να είναι θετικός ακέραιος.")
  }
  u <- runif(n)
  simulated_values <- -log(1 - u) * 2
  return(simulated_values)
}


n <- 10
simulated_values1 <- simulate_exponential(n)
print(simulated_values1)

n <- 100
simulated_values2 <- simulate_exponential(n)
print(simulated_values2)

n <- 1000
simulated_values3 <- simulate_exponential(n)
print(simulated_values3)

n <- 10000
simulated_values4 <- simulate_exponential(n)
print(simulated_values4)

par(mfrow=c(2,2))
hist(simulated_values1, main="Histogram for n=10")
lines(dexp(1:10,1/2),col="blue",lwd=2)
legend("topright",legend="ΣΠΠ", col="blue", lwd=2)
hist(simulated_values2, main="Histogram for n=100")
lines(dexp(1:100,1/2),col="blue",lwd=2)
legend("topright",legend="ΣΠΠ", col="blue", lwd=2)
hist(simulated_values3, main="Histogram for n=1000")
lines(dexp(1:1000,1/2),col="blue",lwd=2)
legend("topright",legend="ΣΠΠ", col="blue", lwd=2)
hist(simulated_values4, main="Histogram for n=10000")
lines(dexp(1:10000,1/2),col="blue",lwd=2)
legend("topright",legend="ΣΠΠ", col="blue", lwd=2)


