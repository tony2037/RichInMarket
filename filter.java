import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

class MarketCapGrowthMapper extends Mapper<Object, Text, Text, FloatWritable> {
    private Text company = new Text();
    private FloatWritable marketCap = new FloatWritable();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().split(",");
        String companyName = tokens[0];
        int year = Integer.parseInt(tokens[1]);
        float cap = Float.parseFloat(tokens[2]);
        company.set(companyName);
        marketCap.set(cap);
        context.write(company, marketCap);
    }
}

class MarketCapGrowthReducer extends Reducer<Text, FloatWritable, Text, Text> {
    public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        float prevCap = -1;
        int count = 0;
        for (FloatWritable value : values) {
            float cap = value.get();
            if (prevCap != -1 && cap > prevCap && ((cap - prevCap) / prevCap) >= 0.05) {
                count++;
            } else {
                count = 0;
            }
            prevCap = cap;
        }
        if (count >= 2) {
            context.write(key, new Text("Market cap growth rate > 5% for past 3 years"));
        }
    }
}
