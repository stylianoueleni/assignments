# Ορισμός παραμέτρων Weibull
lambda <- 2   # Παράμετρος κλίμακας
k <- 1.5      # Παράμετρος σχήματος
n <- 100000     # Πλήθος δειγμάτων

# Δημιουργία ψευδοτυχαίων αριθμών από την U[0,1]
U <- runif(n, min = 0, max = 1)

# Μετασχηματισμός με την αντίστροφη συνάρτηση κατανομής Weibull
X <- lambda * (-log(1 - U))^(1/k)

# Σχεδίαση ιστογράμματος των παραγόμενων τιμών
hist(X, breaks = 30, probability = TRUE, col = "lightblue",
     main = "Τυχαία δείγματα από την κατανομή Weibull",
     xlab = "Τιμές", ylab = "Πυκνότητα")

# Σχεδίαση της θεωρητικής συνάρτησης πυκνότητας Weibull
curve(dweibull(x, shape = k, scale = lambda), col = "red", add = TRUE, lwd = 2)

lambda <- 2
k <- 1.5
n <- 100000
U <- runif(n, min = 0, max = 1)
X <- lambda * (-log(1 - U))^(1/k)
hist(X, breaks = 30, probability = TRUE, col = "lightblue", main = "Τυχαία δείγματα από την κατανομή Weibull", xlab = "Τιμές", ylab = "Πυκνότητα")
curve(dweibull(x, shape = k, scale = lambda), col = "red", add = TRUE, lwd = 2)
legend("topright", legend = c("Ιστόγραμμα προσομοίωσης", "Θεωρητική πυκνότητα Weibull"), fill = c("lightblue", NA), border = "black", lty = c(NA, 1), col = c("lightblue", "red"), lwd = c(NA, 2), pch = c(22, NA), bg = "white")
