import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ReadTweet {
        public static void main(String[] args) throws Exception {

                Job job = new Job();
                job.setJarByClass(ReadTweet.class);
                job.setJobName("Read Tweet");

                FileInputFormat.addInputPath(job, new Path(args[0]));
                FileOutputFormat.setOutputPath(job, new Path(args[1]));

                job.setMapperClass(ReadTweetMapper.class);
                job.setReducerClass(ReadTweetReducer.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(LongWritable.class);

                System.exit(job.waitForCompletion(true) ? 0 : 1);

        }
}
