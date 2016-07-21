# Linear Regression in Scala
This is a simple example of Linear Regression in Scala with gradient descent to optimise the parameters. The file grad_lr.scala provides the whole class. There is a file of a simple data where we preform the prediction as well. 

# Running the code
The code is packaged with SBT to include breeze. Ensure that Scala and SBT are installed. You may need to change build.sbt to reflect your version of scala. (line 5). As plotting is enable, Mac users may need to enable X11. My development is done on a Vagrant Ubuntu box.  

To run:
'''
sbt compile
sbt run
'''

# Let's talk Math
Linear regression can be solved in two ways 1) approximiation 2) analytical solution. We will demonstrate both techniques. 

