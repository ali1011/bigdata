import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;
/**
main class of spark modul
**/
public class Main {
   public static void main(String[]args){
       //Create a SparkContext to initialize
       SparkConf config = new SparkConf().setMaster("local").setAppName("Word Count");

       // Create a Java version of the Spark Context
       JavaSparkContext sc = new JavaSparkContext(config);

       // Load the text into a Spark RDD, which is a distributed representation of each line of text....
       JavaRDD<String> textFile = sc.textFile("hdfs:///tmp/shakespeare.txt");
       JavaPairRDD<String, Integer> counts = textFile
               .flatMap(s -> Arrays.asList(s.split("[ ,]")).iterator())
               .mapToPair(word -> new Tuple2<>(word, 1))
               .reduceByKey((a, b) -> a + b);
       counts.foreach(p -> System.out.println(p));
       counts.saveAsTextFile("hdfs:///tmp/shakespeareWordCount");
       System.out.println();

   }
}
