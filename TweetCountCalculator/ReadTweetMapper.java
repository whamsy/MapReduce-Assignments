import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ReadTweetMapper
        extends Mapper<LongWritable, Text, Text, LongWritable> {
        
        @Override
   
        public void map(LongWritable key, Text value, Context context) 
                        throws IOException, InterruptedException{
                
                
                String line = value.toString();
                String [] wordlist ={"Chicago", "Dec", "Java", "hackathon"};

                for (String word : wordlist) {

                      
                        int j = 0;
                        StringTokenizer token = new StringTokenizer(line, ",:#- ");
                        ArrayList<String> list = new ArrayList<String>();

                        while (token.hasMoreTokens()) {
                                list.add(token.nextToken());
                        }

                        for (int i = 0; i < list.size(); i++) {

                                if (list.get(i).equalsIgnoreCase(word)) {
                                        j++;
                                        break;
                                }
                        }

                        context.write(new Text(word), new IntWritable(j));       
                }
        }
}