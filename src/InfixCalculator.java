import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Author: Edan Meyer
public class InfixCalculator {
	public static void main(String[] args) {
		String output = "";
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
		    String line;
		    while ((line = br.readLine()) != null){
		    	if(!line.equals(""))
		    		output += String.format("%.2f", Processor.infixEval(line)) + "\n";
		    	else
		    		output += "\n";
		    }
		    output = output.substring(0, output.length()-1);
		} catch (IOException e) {
			e.printStackTrace();
		}try (BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]))){
			bw.write(output);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
