import java.util.*;
//Code force Regular Bracket Sequence
public class App {
    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        // int n = Integer.parseInt(st.nextToken());
        Scanner scan = new Scanner(System.in);
        
        int numCases = scan.nextInt();
        
        List<String> answers = new ArrayList<>();
        for (int i =0; i<=numCases;i++){
            String s = scan.nextLine();
            if(s.length()%2==0){
                int openi = s.indexOf("(");
                int closei = s.indexOf(")");
                if(openi<closei){
                    answers.add("Yes");
                }else if (closei<openi){
                    answers.add("No");
                }
            }else{
                answers.add("NO");
            }
            
        }
        for(String s : answers){
            System.out.println(s);
        }
    }
}
