# Linear Regression in Scala
This is a simple example of Linear Regression in Scala with gradient descent to optimise the parameters and an analytical version where we directly solve the matrix. The file grad_lr.scala provides the whole class. There is a file of a simple data where we preform the prediction as well. We will delve into some theory, but the core of this tutorial is to see the use of Scala for matrix operations and ML.  

# Running the code
The code is packaged with SBT to include breeze for plotting and matrix operations. Ensure that Scala and SBT are installed. You may need to change build.sbt to reflect your version of scala. (line 5). As plotting is enable, Mac users may need to enable X11. My development is done on a Vagrant Ubuntu box.  

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
Since our data is 1-D, we have a single variate linear regression. We will only worry about the coefficient b and slope m where m is the regressor. At this point, we will refer to b and m as theta. We optimise the parameter theta by running gradient descent as seen in the function run_grad_descent. What exactly are we optimising against? We optimise theta in order to obtain the lowest error. In this case, we use the simple mean squared error for our loss function: 
![alt text][error]

 The function calls another function grad_step. This is essential as we took the partial derivatives of the loss function with respect to theta. Recall that theta includes b and m. 
![alt text][partials]

We then allow GD to run for 1000 iterations whilst calculating the loss 
each iteration. You will observe the error decreasing, and at some point stabilising.

## Analytical Linear Regression using the Normal Equation
The file analytical_lr.scala is the code reference. Please note that this code requires breeze in order to run.

 Another way to perform linear regression is to solve directly analytically the matrices. This method is easier to code, however, not preferred as the running time is O(n^3) where n is the number of features. Since, we have 1 feature this is managable. Usually, n < 1000 is tractable.

The normal equation is 
```
(X.T X)B = X.T y
B = (X.T X)^-1 X.T Y
```

Upon rearranging, we solve for in order to find the parameters
![alt text][normal]

We use breeze to apply matrix operations to solve the equation. Examining the matrix, one may ask why we added a column of ones. First, it is for numerical stability and also to get the interceptor coefficient. Without the column of ones, we would only solve for the regression coefficient (m in this case). 

On line 25, we show a shortcut of how that whole equation can be encoded into the normal equation. 

```
Ax = b
x = A\b
```
Most programming langauges (i.e. R, Python, Julia, and MatLab) will support the backslash operation. This gives the exact idential solution. 

# Conclusion
We demonstrated how linear regression can be done in Scala using gradient descent and matrices. You may ask why the solution of GD and matrices differ. GD is an approximation, not exact like the analytical solution. This could be the fault of our initial starting points for m and b.  

[error]: https://spin.atomicobject.com/wp-content/uploads/linear_regression_error1.png
[partials]: https://spin.atomicobject.com/wp-content/uploads/linear_regression_gradient1.png
[normal]: http://eli.thegreenplace.net/images/math/20baabd9d33dcd26003bc44c7d81ba39e1ad4caa.png
