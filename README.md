# Linear Regression in Scala
This is a simple example of Linear Regression in Scala with gradient descent to optimise the parameters and an analytical version where we directly solve the matrix. The file grad_lr.scala provides the whole class. There is a file of a simple data where we preform the prediction as well. We will delve into some theory, but the core of this tutorial is to see the use of Scala for matrix operations and ML.  

# Running the code
The code is packaged with SBT to include breeze. Ensure that Scala and SBT are installed. You may need to change build.sbt to reflect your version of scala. (line 5). As plotting is enable, Mac users may need to enable X11. My development is done on a Vagrant Ubuntu box.  

To run:
```
sbt compile
sbt run
```
This will give the option to run either the analytical LR or gradient LR. 

# Math Time
Linear regression can be solved in two ways 1) approximiation 2) analytical solution. We will demonstrate both techniques. 

## Linear Regression with Gradient Descent
The file grad_lr.scala will be the code reference for this section.

We demonstrate how gradient descent can be performe on linear regression. The essential bits of this code is in the LinearRegression class. A linear line is defined as 
```
y = mx + b
```
Since our data is 1-D, we have a single variate linear regression. We will only worry about the coefficient b and slope m where m is a regressor. 
