
f0 <- read.table("f0.txt")
f1 <- read.table("f1.txt")

f0.lm <- lm(V5 ~ V1 + V2 + V3 + V1*V2 + V1*V3 + V2*V3, data = f0)
f1.lm <- lm(V5 ~ V1 + V2 + V3 + V1*V2 + V1*V3 + V2*V3, data = f1)

f0.timelm <- lm(V4 ~ V1 + V2 + V3 + V1*V2 + V1*V3 + V2*V3, data = f0)
f1.timelm <- lm(V4 ~ V1 + V2 + V3 + V1*V2 + V1*V3 + V2*V3, data = f1)

f0.resids <- resid(f0.lm)
f1.resids <- resid(f1.lm)

summary(aov(f0.lm))
summary(aov(f1.lm))

#par(mfrow=c(1,1))

#plot(f0.lm, main="f0")
#plot(f1.lm, main="f1")


f0.function <- function(x) {

	-(93.404 - 0.109 * x[1] -8.16*x[2] - 0.036*x[3] + 1.25*x[1]*x[2] + 0.0035 *x[1]*x[3]- 0.26*x[2]*x[3])}
	

f1.function <- function(x) {

	-(105.5 - 0.921 * x[1] -3.611*x[2] - 1.354*x[3] - 0.694*x[1]*x[2] + 0.097 *x[1]*x[3] + 0.625*x[2]*x[3])}


optim(c(7, 0.3, 6),f0.function, lower = c(4, 0.1, 2), upper = c(10, 0.5, 10))


optim(c(7, 0.3, 6),f1.function, lower = c(4, 0.1, 2), upper = c(10, 0.5, 10))
