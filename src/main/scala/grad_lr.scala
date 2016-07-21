import breeze.plot._
import scala.math._
import java.awt.{Color, Paint}

class LinearRegression(x: List[Double], y: List[Double]){

	// define the instance variables
	val x_input = x map (_.toDouble) // ensure its double
	val y_input = y map (_.toDouble)

	def compute_line(b:Double, m:Double, x_i: Double): Double = {
		m*x_i + b
	}

	def compute_error(b: Double, m: Double): Double = {
		
		/* //annoymous function to cal y = mx + b
		val k = { (x_i: Double) => 
			m*x_i + b
		}

		var total_err = 0.0
		while (i < y.length) {
			var s = ( y_input(i) - (m*x_input(i) + b) )
			total_err += scala.math.pow(s,2)
			i+=1
		}
		*/

		val yhats = x_input map( j => compute_line(b,m,j))
		val diff = y_input zip yhats map ( z => scala.math.pow(z._1 - z._2, 2))
		val total_err = diff.sum
		
		total_err/y_input.length.toDouble
	}

	def grad_step(b_curr: Double, m_curr: Double, alpha: Double): (Double, Double) = {
		//http://stackoverflow.com/questions/2189784/in-scala-is-there-a-way-to-take-convert-two-lists-into-a-map
		var b_grad = 0.0
		var m_grad = 0.0
		val N = x_input.length

		for (i <- 0 to N-1){
			b_grad += (-2.0/N) * (y_input(i) - compute_line(b_curr, m_curr, x_input(i)))
			m_grad += (-2.0/N) * (x_input(i))*(y_input(i) - compute_line(b_curr, m_curr, x_input(i)))
			//println(b_grad, m_grad)
		}
		val new_b = b_curr - (alpha * b_grad)
		val new_m = m_curr - (alpha * m_grad)

		(new_b, new_m)
	}

	def run_grad_descent(start_b: Double, start_m: Double, alpha:Double, max_its: Int): (Double, Double) ={
		var phi = (start_b, start_m)

		for (i <- 0 to max_its){
			phi = grad_step(phi._1, phi._2, alpha)
			val err = compute_error(phi._1, phi._2)
			println(s"----------------iteration $i: $err")
		}
		phi
	}
}

object Grad_Linear_Regression{
	def main(args: Array[String]) {

		val pth = "/home/vagrant/workspace/grad_lr/src/main/scala/data.csv"
		val (x, y) = load_data(pth)

		val alpha = 0.0001 // learning rate 
		val initial_b = 0.01 // initial y-intercept
		val initial_m = 0.03 // initial slope
		val its = 1000

		val lr = new LinearRegression(x,y)
		val (fin_b, fin_m) = lr.run_grad_descent(initial_b, initial_m, alpha, its)
		//val fin_b = 7.99102098
		//val fin_m = 1.32242102
		println(s"b: $fin_b, m: $fin_m")
		val fin_err = lr.compute_error(fin_b, fin_m)
		println(s"The error is $fin_err")

		// plot 
		val fig = Figure()
		val p1 = fig.subplot(1,1,0)
		p1 += scatter(x,y, {_ => 1}, {(_:Int) => Color.BLUE})
		
		val p2 = fig.subplot(1,2,1)
		val y_preds = x map( j => lr.compute_line(fin_b, fin_m,j))
		p2 += scatter(x,y, {_ => 1}, {(_:Int) => Color.BLUE})
		p2 += plot(x, y_preds, colorcode="red")
		fig.refresh()
		
	}

	def load_data(path:String) : (List[Double], List[Double]) = {
		val src = io.Source.fromFile(path)
		val x_i= scala.collection.mutable.ListBuffer.empty[String]
		val y_i= scala.collection.mutable.ListBuffer.empty[String]
		for(line <- src.getLines){
			val cols = line.split(",").map(_.trim)
			x_i += cols(0)
			y_i += cols(1)
			//println(s"${cols(0)}|${cols(1)}")
		}
		val x_points = x_i.toList map (_.toDouble)
		val y_points = y_i.toList map (_.toDouble)

		(x_points, y_points)
	}

	def generate_values(): (List[Double], List[Double]) = {
		val rand = new scala.util.Random
		val x = Seq.fill(25)(rand.nextInt(100)).toList
		val y = Seq.fill(25)(rand.nextInt(100)).toList
		(x map(_.toDouble),y map(_.toDouble))
	
	} 
}