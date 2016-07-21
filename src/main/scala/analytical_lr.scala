import breeze.plot._
import breeze.linalg._
import breeze.numerics._
import scala.math._
import scala.reflect.ClassTag
import java.awt.{Color, Paint}


object Analytical_LR{
	def main(args: Array [String]){
		val pth = "/home/vagrant/workspace/grad_lr/src/main/scala/data.csv"
		val (x_i, y_i) = load_data(pth)

		val t = DenseMatrix.ones[Double](x_i.size, 1)
		val x = DenseMatrix.horzcat(t, x_i.toDenseMatrix.t)
		val y = y_i.toDenseMatrix.t

		val thetas = inv(x.t * x) * x.t * y

		val fin_b = thetas(0,0)
		val fin_m = thetas(1,0)
		println(s"b: $fin_b, m: $fin_m")

		
	}

	def load_data(path:String) : (DenseVector[Double], DenseVector[Double]) = {
		val file = io.Source.fromFile(path)
		val lines = file.getLines.toVector
		val splitLines = lines.map {_.split(",")}
		
		def toList[T:ClassTag](index:Int, converter:(String=>T)): DenseVector[T] = 
			DenseVector.tabulate(lines.size) {irow => converter(splitLines(irow)(index))}

		val x = toList(0, elem => elem.toDouble)
		val y = toList(1, elem => elem.toDouble)
	
		(x,y)


	}
}