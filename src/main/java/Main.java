import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;
/**
main class of spark module 
test on hdfs
**/
public class Main {
   public static void main(String[]args){
       //Create a SparkContext to initialize
       //Create a SparkContext to initialize
       SparkConf config = new SparkConf().setMaster("local").setAppName("Word Count");

       // Create a Java version of the Spark Context but old
       JavaSparkContext sc = new JavaSparkContext(config);

       // Load the text into a Spark RDD, which is a distributed representation of each line of text : RDD collection ef elements
       //JavaRDD<String> textFile = sc.textFile("hdfs:///tmp/shakespeare.txt");
       JavaRDD<String> textFile = sc.textFile("src/main/resources/shakespeare.txt");
       JavaPairRDD<String, Integer> counts = textFile
               .flatMap(line -> Arrays.asList(line.split("[ ,]")).iterator())
               .mapToPair(word -> new Tuple2<>(word, 1))
               .reduceByKey((a, b) -> a + b);
       counts.foreach(p -> System.out.println(p));
       counts.saveAsTextFile("tmp/shakespeareWordCount");
       System.out.println();

   }
}
